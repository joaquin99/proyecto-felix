package juegoFelix;

import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

import java.util.Iterator;

import edificio.Ventana;
import entidades.Direccion;
import entidades.Enemigo;
import entidades.EstadosFelix;
import entidades.Felix;
import entidades.Ladrillo;
import entidades.Pajaro;
import entidades.Ralph;
import graficos.DibujarEdificio;
import graficos.DibujarNivel;

import java.math.*;


public class TimerNivel extends Timer{

	private final int SEGUNDO = 1000;
	
	int tiempoTotal = 10;
	int seccionActual = 0;
	private Nivel nivelActual;
	//cantidad de secciones
	private final int NUM_SECCIONES = 3;
	
	
	ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
	
	public TimerNivel(Nivel nivelActual,Edificio edificioNivel) {

		
		//Da un error en ejecucion al hacer getSeccion. Parece que no se crean
		//this.controlFelix = controlFelix;
		this.nivelActual = nivelActual;
		DibujarEdificio.getInstance().actualizar(edificioNivel.getSeccion(seccionActual).getMatrizVentanas(),seccionActual, enemigos);
		DibujarNivel.getInstance();
		DibujarEdificio.getInstance().setTiempoNivel(nivelActual.getTiempoRestante());
		DibujarEdificio.getInstance().setNroNivelActual(nivelActual.nroNivel);
		
		System.out.println("Comenzó el nivel");
		
		//Disminuye el tiempo para terminar el nivel cada un segundo
		TimerTask tiempoJuego = new TimerTask() {
		
			public void run() {
				nivelActual.disminuirTiempo();
				DibujarEdificio.getInstance().actualizarTiempo();
			}
			
		};
		
		
		//Controla si se ha superado la sección actual
		TimerTask condicionesFinSeccion = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//Si se gana la seccion	
				if(edificioNivel.getSeccion(seccionActual).condicionVictoriaSeccion() && seccionActual < (NUM_SECCIONES-1)){
					seccionActual++;
					System.out.println("Usted ha subido a la seccion " + seccionActual);
					while(enemigos.size() != 0)
						enemigos.remove(0);
					Felix.getInstance().posInicial();
					DibujarEdificio.getInstance().actualizar(edificioNivel.getSeccion(seccionActual).getMatrizVentanas(),seccionActual, enemigos);
					DibujarEdificio.getInstance().repaint();
					nivelActual.subirSeccion();
					//El ultimo panel da 400 puntos mas que el resto
					Felix.getInstance().setPuntos(Felix.getInstance().getPuntos()+400);
				}
			}
			
			
		};
		
		
		//Controla si las condiciones de fin de nivel se cumplen
		TimerTask condicionesFinNivel = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(nivelActual.condicionesDerrota()|| nivelActual.tiempoTerminado()){

					terminarTimerNivelDerrota();
				}
				else if((edificioNivel.getSeccion(seccionActual).condicionVictoriaSeccion()) && (seccionActual == 2)) {

					terminarTimerNivelVictoria();
				}
			}
		
		};
			
		
		//Controla el comportamiento de los enemigos (movimiento, colision y daño de Felix)
		TimerTask comportamientoEnemigos = new TimerTask(){

			@Override
			public void run() {
				ArrayList<Enemigo> paraRemover = new ArrayList<Enemigo>();
				// TODO Auto-generated method stub
				for(Enemigo e:enemigos) {
					
					e.mover();  
					if(!nivelActual.estaEnSeccion(e))
						paraRemover.add(e);
				}
				
				if(Math.random()*2 > 1 && (Ralph.getInstance().getPos().getPosX() > Felix.getInstance().getPos().getPosX())) {
					Ralph.getInstance().setDireccion(Direccion.IZQUIERDA);
				}
				else if(Ralph.getInstance().getPos().getPosX() < Felix.getInstance().getPos().getPosX() && Ralph.getInstance().getPos().getPosX() < 4) {
					Ralph.getInstance().setDireccion(Direccion.DERECHA);
	
				}
				Ralph.getInstance().mover();
				
				System.out.println("Posicion Ralph: ("+Ralph.getInstance().getPos().getPosX()+","+Ralph.getInstance().getPos().getPosY()+")");
				enemigos.removeAll(paraRemover);
				DibujarNivel.getInstance().repaint();
			}	
			
		};
		
		
		//Genera una tarta cada cierto tiempo
		TimerTask generarTarta = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Ventana[][] ventanasSeccion = edificioNivel.getSeccion(seccionActual).getMatrizVentanas();
				boolean tartaGenerada = false;
				for(int i = 0;i < ventanasSeccion.length && !tartaGenerada;i++) {
					for(int j = 0;j < ventanasSeccion[i].length && !tartaGenerada;j++)
						if(Math.random()*10 > 7) {
							System.out.println("Se ha generado una tarta!");
							ventanasSeccion[i][j].setTarta(true);
							tartaGenerada = true;
							//Agregar tarta en grafica
						}
				}
			}	
			
		};
		
		//Controla el tiempo en el cual Felix puede ser inmune, volviendo a estado normal
		TimerTask controlarInmunidad = new TimerTask() {
			
			private final int TIEMPO_MAXIMO_INMUNIDAD = 5;
			private boolean contar = false;
			private int contador = 0;
			
			public void run() {
				if(Felix.getInstance().getEstado() == EstadosFelix.INMUNE)
					contar = true;
				if(contar) {
					contador++;
					if(contador == TIEMPO_MAXIMO_INMUNIDAD) {
						Felix.getInstance().setEstado(EstadosFelix.NORMAL);
						contar = false;
						contador = 0;
					}
				}
				
			}
		};
		
		
		//Se puede usar el algoritmo del viejo nivel
		//Genera pajaros y ladrillos cada cierto tiempo
		TimerTask generarEnemigos = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(Math.random()*2 > 1) {
					//Se genera un pajaro
					//enemigos.add(new Pajaro(1,0,0));
					enemigos.add(new Pajaro(1,0,(int) (Math.random()*3)));
					System.out.println("Se ha generado un enemigo en ("+enemigos.get(enemigos.size()-1).getPos().getPosX()+","+enemigos.get(enemigos.size()-1).getPos().getPosY()+")");
					DibujarEdificio.getInstance().actualizar(edificioNivel.getSeccion(seccionActual).getMatrizVentanas(),seccionActual, enemigos);
				}
			}
			
		};
		
		TimerTask lanzamientoLadrillos = new TimerTask(){
			public void run() {
				System.out.println("Se generaron ladrillos");
				Enemigo[] ladrillos = Ralph.getInstance().golpearEdificio(Felix.getInstance().getPos().getPosX(), 1);
				System.out.println(Ralph.getInstance().getCantLadrillos());
				if(ladrillos != null)
					for(Enemigo e:ladrillos)
						enemigos.add(e);
			}
		};
		
		//Hace que el contacto con los enemigos provoque daño, en vez de controlarlo solo cuando avanza
		TimerTask danioEnemigos = new TimerTask(){
			
			//Evita que se realicen nuevos controles antes de reiniciar
			private boolean golpeado = false;
			
			public void run() {
				if(!golpeado)
					for(int i = 0;i < enemigos.size() && !golpeado;i++)
						if(nivelActual.hayColision(Felix.getInstance().getPos(), enemigos.get(i).getPos())) {
							System.out.println("Felix ha sido golpeado! por "+enemigos.get(i));
							Felix.getInstance().daniarse(enemigos.get(i));
							if(enemigos.get(i).daniar().equals(EstadosFelix.GOLPEADOLADRILLO)) {
								seccionActual = 0;
								nivelActual.volverAPrimeraSeccion();
							}
							golpeado = true;
						}
			}
			
		};
		
		Felix.getInstance().setEstado(EstadosFelix.NORMAL);
		System.out.println("Estado de Felix: "+Felix.getInstance().getEstado());
		this.schedule(condicionesFinSeccion,0,10);
		this.schedule(condicionesFinNivel,0,100);
		this.schedule(generarEnemigos,SEGUNDO*5,SEGUNDO*10);
		this.schedule(generarTarta,SEGUNDO*20,SEGUNDO*10);
		this.schedule(controlarInmunidad, 0,SEGUNDO);
		this.schedule(comportamientoEnemigos,0,SEGUNDO);
		this.schedule(danioEnemigos,0,SEGUNDO/10);
		this.schedule(tiempoJuego,0,SEGUNDO);
		this.schedule(lanzamientoLadrillos,SEGUNDO*5,PorcentajeAumento.calcularDisminucion(SEGUNDO*10, nivelActual.nroNivel-1));
		
	}
	
	//Desactiva los timerTasks y va al final del nivel. Si este codigo se ejecuta un timerTask puede fallar
	private void terminarTimerNivelDerrota() {
		enemigos.removeAll(enemigos);
		this.cancel();
		this.purge();
		System.out.println("Perdiste una vida ");
		nivelActual.finDelNivel();
	}
	
	//Igual que terminarNivelDerrota pero en caso de victoria
	private void terminarTimerNivelVictoria() {
		enemigos.removeAll(enemigos);
		this.cancel();
		this.purge();
		System.out.println("Ganaste el nivel!");
		nivelActual.finDelNivel();
	}
	
}	