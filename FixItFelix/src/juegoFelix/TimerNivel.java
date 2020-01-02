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
import entidades.Pajaro;
import entidades.Ralph;
import graficos.DibujarEdificio;
import graficos.DibujarNivel;

import java.math.*;



public class TimerNivel extends Timer{

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
		System.out.println("Comenzó el nivel");
		
		
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
					//nivelActual.terminar();
				}
			}
			
			
		};
		
		
		//Controla si las condiciones de fin de nivel se cumplen
		TimerTask condicionesFinNivel = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(nivelActual.condicionesDerrota()){
					//nivelActual.terminar();
					cancel();
					purge();
					System.out.println("Perdiste");
					nivelActual.finDelNivel();
					
				}
				else if((edificioNivel.getSeccion(seccionActual).condicionVictoriaSeccion()) && (seccionActual == 2)) {
				
					//nivelActual.terminar();
					cancel();
					purge();
					System.out.println("Ganaste el nivel!");
					nivelActual.finDelNivel();
					
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
					if(nivelActual.hayColision(Felix.getInstance().getPos(), e.getPos()))
						Felix.getInstance().daniarse(e);
					
					if(!nivelActual.estaEnSeccion(e))
						paraRemover.add(e);
				}
				if(Math.random()*2 > 1 && (Ralph.getInstance().getPos().getPosX() > Felix.getInstance().getPos().getPosX()))
					Ralph.getInstance().setDireccion(Direccion.IZQUIERDA);
				else if(Ralph.getInstance().getPos().getPosX() < Felix.getInstance().getPos().getPosX())
					Ralph.getInstance().setDireccion(Direccion.DERECHA);
				
				Ralph.getInstance().mover();
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
						if(Math.random()*15 == 7) {
							ventanasSeccion[i][j].setTarta(true);
							tartaGenerada = true;
							//Agregar tarta en grafica
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
					enemigos.add(new Pajaro(1,0,0));
				}
				
				
			}
			
		};
		
		
		/*
		TimerTask tiempoJuego = new TimerTask(){
			
			
			int seccionActual = 0;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(tiempoTotal == 0 && seccionActual != 2) {
					System.out.println("Usted ha subido a la seccion " + seccionActual);
					while(enemigos.size() != 0)
						enemigos.remove(0);
					Felix.getInstance().posInicial();
					DibujarEdificio.getInstance().cambiarSeccion(edificioNivel.getSeccion(seccionActual).getMatrizVentanas(),seccionActual);
					DibujarEdificio.getInstance().repaint();
					//Provoca un NullPointerException
					//nivelActual.terminar();
					seccionActual++;
					tiempoTotal = 10;
				}
				else if (tiempoTotal != 0){
					tiempoTotal--;
					System.out.println("Tiempo restante: "+tiempoTotal);
				}
			}
			
		};
		*/
		
		this.schedule(condicionesFinSeccion,0,10);
		this.schedule(condicionesFinNivel,0,100);
		this.schedule(generarEnemigos,0,1000*20);
		this.schedule(generarTarta,0,1000*300);
		this.schedule(comportamientoEnemigos,0,100);
		//this.schedule(tiempoJuego,0,1000);
		
	}
}	