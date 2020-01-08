package juegoFelix;
import javax.swing.JOptionPane;

import entidades.EstadosFelix;
import entidades.Felix;
import graficos.DibujarNivel;
import juegoFelix.Jugador;
import juegoFelix.Nivel;

public class Jugar {
	
	public static final double AUMENTO_VENTANAS = 0.1;
	public static final double AUMENTO_TIEMPO = 0.2;
	public static final double AUMENTO_ENTIDADES = 0.2;
	public static final int NUMERO_NIVELES = 10;
	private boolean enJuego = true;
	
	private Jugador jugador;
	private ControladorPrincipal controladorApp;
	private ControladorJugar controladorJuego;
	
	public Jugar(ControladorPrincipal controladorApp,int nroNivel){
		this.controladorApp = controladorApp;
		controladorJuego = new ControladorJugar(nroNivel,this);
		jugador = new Jugador();
		Felix.getInstance().reiniciar();
		Nivel nivel = new Nivel(nroNivel,(AUMENTO_VENTANAS * (nroNivel+1)), AUMENTO_TIEMPO * (nroNivel+1),this.controladorJuego);
		nivel.iniciar();
		
	}
	
	public Jugador getJugador(){
		return this.jugador;
	}
	
	/*
	public static boolean ganoNivel(){
		return (Felix.getInstance().getEstado().equals(EstadosFelix.INMUNE) || Felix.getInstance().getEstado().equals(EstadosFelix.NORMAL));
	}
	*/
	
	/*
	public static boolean noLeQuedanVidas(){
		return (Felix.getInstance().getVidas() == 0);
	}
	*/
	
	public boolean noTerminaJuego(int nivel){
		return ((nivel < NUMERO_NIVELES) &&(Felix.getInstance().getVidas() > 0));
	}
	
	public void victoria(){
		//mostrar mensaje victoria
		System.out.println("Felicitaciones!Has ganado!");
		System.out.println("Su puntuacion fue de "+Felix.getInstance().getPuntos());
		jugador = new Jugador();
		String nombreJugador = JOptionPane.showInputDialog("Ingrese su nombre:");
		jugador.setNombre(nombreJugador);
		jugador.setPuntos(Felix.getInstance().getPuntos());
		controladorApp.actualizarRanking(jugador);
		this.enJuego = false;
		//Para intentar eliminar la ventana del juego
		DibujarNivel.getInstance().ocultar();
		controladorApp.volverAlMenu();
	}
	
	public void derrota(){
		//mostrar mensaje derrota
		System.out.println("Has perdido");
		JOptionPane.showMessageDialog(null, "Ha perdido!");
		this.enJuego = false;
		//Para intentar eliminar la ventana del juego
		DibujarNivel.getInstance().ocultar();
		controladorApp.volverAlMenu();
	}
	
	public boolean getEnJuego(){
		return this.enJuego;
	}
	
	
}


/*
public void iniciarJuego(int nivelActual){
	Nivel nivel = new Nivel((AUMENTO_VENTANAS * (nivelActual+1)), AUMENTO_TIEMPO * (nivelActual+1));
	Felix.getInstance().reiniciar();
	boolean nuevoNivel = false; 
	
	while(noTerminaJuego(nivelActual)){
		
		if(nuevoNivel){
			Felix.getInstance().setEstado(EstadosFelix.NORMAL);
			Felix.getInstance().posInicial();
			nivel = new Nivel((AUMENTO_VENTANAS * (nivelActual+1)), AUMENTO_TIEMPO * (nivelActual+1));
			nuevoNivel = false;
			System.out.println("NIVEL " + nivelActual);
		}
		nivel.mainNivel(AUMENTO_ENTIDADES*(nivelActual+1));
		
		if(ganoNivel()){
			System.out.println("Usted ha pasado de nivel! Los nicelanders festejan!");
			System.out.println("**Los nicelanders levantan a Raplh y lo tiran del edificio**");
			nivelActual++;
			nuevoNivel = true;
		}
		else{
			System.out.println("Usted ha perdido una vida");
			if (Felix.getInstance().getEstado().equals(EstadosFelix.GOLPEADOPAJARO))							//En caso de que felix haya sido golpeado por un pajaro, lo unico que se reiniciara sera la seccion
				nivel.reiniciar();
			
			else{
				//reiniciarPosicion();
				//Debo dejar de modificar directamente los objetos, enviar mensajes para que los objetos se modifiquen de manera independiente
				Felix.getInstance().posInicial();																		//En caso contrario, significa que lo golpeo un ladrillo, entonces se setea la posicion del personaje y se reinicia el nivel
				nuevoNivel = true;
			}
			
		}
	}
	if(noLeQuedanVidas())
		derrota();
	else{
		victoria();
	}
	jugador.setPuntos(Felix.getInstance().getPuntos());
	Ranking.getInstance().setRanking(jugador);
}
*/