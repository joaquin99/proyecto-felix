package juegoFelix;
import java.util.ArrayList;

import entidades.Enemigo;
import entidades.EstadosFelix;
import entidades.Felix;
import entidades.Ladrillo;
import entidades.Pajaro;
import entidades.Posicion;
import entidades.Ralph;
import graficos.DibujarEdificio;
import graficos.DibujarNivel;
import juegoFelix.Edificio;
import juegoFelix.MainJuego;
import juegoFelix.Seccion;

public class Nivel {

	TimerNivel timer;
	
	private int seccionActual;
	
	private ArrayList<Enemigo> enemigos;
	private Edificio edificioNivel;
	
	int frecuenciaPajaros = 30;
	int frecuenciaGolpesRalph = 5;
	int nroNivel;
	private int tiempoRestante;
	
	ControladorJugar controladorJuego;

	public Nivel(int nroNivel,double porcentajeVentana,ControladorJugar controladorJuego ){
		//Ralph.getInstance();
		this.controladorJuego = controladorJuego;
		System.out.println("Comenzó el nivel"+nroNivel);
		enemigos = new ArrayList<Enemigo>();
		edificioNivel = new Edificio(porcentajeVentana);
		seccionActual = 0;
		this.nroNivel = nroNivel;
		tiempoRestante = 100 - 5*nroNivel;
	}
	

  /**Verdadero si la posicion de un enemigo coincide con la de Felix
   */
	public boolean hayColision(Posicion posFelix, Posicion posEnem) {
		
		//Se pasan como enteros porque el movimiento de los enemigos puede ser decimal, lo que significa que seguira en una ventana
		return (Posicion.comparePos(posFelix.getPosX(), posEnem.getPosX()) && Posicion.comparePos(posFelix.getPosY(), posEnem.getPosY()));
	}
	
	
	/**Verdadero si Felix se quedo sin vidas o se acabo el tiempo
	  **/
	public boolean condicionesDerrota() {	
		return (Felix.getInstance().getEstado().equals(EstadosFelix.GOLPEADOLADRILLO)) || (Felix.getInstance().getEstado().equals(EstadosFelix.GOLPEADOPAJARO) || (Felix.getInstance().getVidas() <= 0));
	}
	
  
  /**Verdadero si el enemigo esta en los limites de la seccion
   * */
	public boolean estaEnSeccion(Enemigo e){
    
  	Posicion posE = e.getPos();
    return ((posE.getPosY() >= 0) &&
            (posE.getPosY() < Seccion.getAlto()) &&
            (posE.getPosX() >= 0) &&
            (posE.getPosX() < Seccion.getAncho()));
  }

	public void reiniciar(){
		System.out.println("Se reinicia en la seccion "+seccionActual);
		for(int i = seccionActual; i < Edificio.getSecciones(); i++)
			edificioNivel.getSeccion(i).iniciarSeccion(i);
		//Agregada para ver qué ocurre
		enemigos = new ArrayList<Enemigo>();
		timer = new TimerNivel(this,edificioNivel);
	}
  
	
	//Pone en ejecucion el timer
	public void iniciar() {
		Felix.getInstance().posInicial();
		Ralph.getInstance().reiniciar();
		//Prueba para que genere las secciones (de lo contrario llama al constructor vacio)
		edificioNivel = new Edificio(0.5);
		//El iniciar secciones ya se hizo en el constructor
		//edificioNivel.iniciarSecciones();
		timer = new TimerNivel(this,edificioNivel);
		
	}
	
	//Disminuye en 1 segundo el tiempo para terminar el nivel
	public void disminuirTiempo() {
		if(tiempoRestante != 0) {
			tiempoRestante--;
			System.out.println("Tiempo restante: "+tiempoRestante+" segundos");
		}
	}
	
	//Retorna true si se termino el tiempo
	public boolean tiempoTerminado() {
		if(tiempoRestante == 0)
			return true;
		else
			return false;
				
	}
	
	public int getTiempoRestante() {
		return tiempoRestante;
	}
	
	public void subirSeccion() {
		if(this.seccionActual < 2)
			this.seccionActual++;
	}
	
	//Evalua si pudo ganar
	public boolean ganoNivel() {
		
		return ((edificioNivel.getSeccion(seccionActual).condicionVictoriaSeccion()) && (seccionActual == 2));
	}
	
	//Se ejecuta
	public void finDelNivel() {
		controladorJuego.TerminarNivel(this);
	}
}
