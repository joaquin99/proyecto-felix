package juegoFelix;

import java.util.Timer;
import java.util.TimerTask;

import entidades.EstadosFelix;
import entidades.Felix;

public class ControladorJugar{

	public final int TOTAL_NIVELES = 10;
	private int nroNivel;
	private Jugar jugar;
	
	public ControladorJugar(int nroNivel, Jugar jugar) {
		this.nroNivel = nroNivel;
		this.jugar = jugar;
	}
	
	
	public void TerminarNivel(Nivel nivelActual) {
		if(ganoNivel(nivelActual) && nroNivel != (TOTAL_NIVELES)) {
			System.out.println("Felicidades, superaste el nivel "+nroNivel);
			this.nroNivel++;
			Nivel nivel = new Nivel(nroNivel,(Jugar.AUMENTO_VENTANAS * (nroNivel+1)),this);
			nivel.iniciar();
			//Si no consigue pasar
		}
		else {
			if(noLeQuedanVidas()|| nivelActual.tiempoTerminado()) {
				System.out.println("Ha perdido");
				jugar.derrota();
				
			}
			//Gano el juego
			else if(ganoNivel(nivelActual) && nroNivel == (TOTAL_NIVELES)) {
				System.out.println("Ha ganado el juego");
				jugar.victoria();
			}
			else if(!ganoNivel(nivelActual)){
				
				System.out.println("Has perdido una vida");
				
				//Reinicia el nivel
				if(Felix.getInstance().getEstado() == EstadosFelix.GOLPEADOLADRILLO) {
					nivelActual.reiniciar();
				}
				//Reinicia la seccion
				else if(Felix.getInstance().getEstado() == EstadosFelix.GOLPEADOPAJARO) {
					nivelActual.reiniciar();
				}
			}
		}
	}
	
	public static boolean ganoNivel(Nivel nivel){
		return ((Felix.getInstance().getEstado().equals(EstadosFelix.INMUNE) || Felix.getInstance().getEstado().equals(EstadosFelix.NORMAL)) && (!nivel.tiempoTerminado()));
	}
	
	public static boolean noLeQuedanVidas(){
		return (Felix.getInstance().getVidas() <= 0);
	}
	
}
/*
 	
	public ControladorJugar(NuevoJugar jugar,NuevoNivel nivel) {
		
		TimerTask terminaNivel = new TimerTask() {
			
			private int nroNivel = 0;
			
			@Override
			public void run() {
				
				//
				if(jugar.noTerminaJuego(nroNivel) && jugar.ganoNivel()) {
					if(jugar.ganoNivel() && nroNivel != (TOTAL_NIVELES-1)) {
						System.out.println("Felicidades, superaste el nivel "+nroNivel);
						this.nroNivel++;
						NuevoNivel nivel = new NuevoNivel(nroNivel,(NuevoJugar.AUMENTO_VENTANAS * (nroNivel+1)), NuevoJugar.AUMENTO_TIEMPO * (nroNivel+1));
						nivel.iniciar();
					}
					//Perdio
					else if (jugar.noLeQuedanVidas()){
						System.out.println("Ha perdido!");
						jugar.derrota();
					}
					//Gano el juego
					else if(jugar.ganoNivel() && nroNivel == (TOTAL_NIVELES-1)) {
						
						System.out.println("Ha ganado el juego");
						jugar.victoria();
					}
					//Fue golpeado
					else if (!jugar.ganoNivel()){
						
						System.out.println("Has perdido una vida");
						
						//Reinicia el nivel
						if(Felix.getInstance().getEstado() == EstadosFelix.GOLPEADOLADRILLO) {
							//jugar.iniciarJuego(nivelActual);
						}
						//Reinicia la seccion
						else if(Felix.getInstance().getEstado() == EstadosFelix.GOLPEADOPAJARO) {
							
						}
						
					}
					
				}
			}
			
		};
		
		
		this.schedule(terminaNivel,0,100);
 */