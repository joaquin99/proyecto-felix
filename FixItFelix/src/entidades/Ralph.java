package entidades;

import juegoFelix.Seccion;

public class Ralph extends Entidad {
	 
	private int cantLadrillos;
	private boolean tieneLadrillos;
	public static final int CANTIDAD_INICIAL_LADRILLOS = 40;
	private static final int POS_INICIAL_X = 0;
	private static final int POS_INICIAL_Y = 3;
	private static final int ANCHO_RALPH = 100;
	private static final int ALTO_RALPH = 100;
	
	private static Ralph INSTANCE;
	
	
	private Ralph(){
		this.cantLadrillos = CANTIDAD_INICIAL_LADRILLOS;
		this.tieneLadrillos = true;
		this.setPos(POS_INICIAL_X,POS_INICIAL_Y);
		this.setDireccion(Direccion.DERECHA);
		this.setDimension(ANCHO_RALPH, ALTO_RALPH);
	}
	
	public static Ralph getInstance(){
		if(INSTANCE == null){
			INSTANCE = new Ralph();
		}
		return INSTANCE;
	}
	
	public void reiniciar(){
		this.cantLadrillos = CANTIDAD_INICIAL_LADRILLOS;
		this.tieneLadrillos = true;
		this.setPos(POS_INICIAL_X,POS_INICIAL_Y);
		this.setDireccion(Direccion.DERECHA);
	}
	
	public int getCantLadrillos() {
		return cantLadrillos;
	}

	public void setCantLadrillos(int cantLadrillos) {
		this.cantLadrillos = cantLadrillos;
	}
	
	public boolean getTieneLadrillos(){
		return tieneLadrillos;
	}

	public void setTieneLadrillos(boolean tieneLadrillos){
		this.tieneLadrillos = tieneLadrillos;
	}
	
	public Ladrillo[] golpearEdificio(int posXFelix, int aumentoVel) {
		Ladrillo [] arrayLadrillo = null;
		if(Posicion.comparePos(this.getPos().getPosX(), posXFelix)) {
			
			if(cantLadrillos > 0){
				int aux = 3 + (cantLadrillos % 3);
				arrayLadrillo = new Ladrillo[aux];
				for(int i = 0; i < aux; i ++){
					arrayLadrillo[i] = new Ladrillo(aumentoVel,posXFelix,this.getPos().getPosY());
				}
				cantLadrillos = cantLadrillos - aux;
			}
			else{
				setTieneLadrillos(false);
			}
		}
		return arrayLadrillo;
	}

	@Override
	public void mover() {
		int desplazar = 1; 
		if(getDireccion().equals(Direccion.IZQUIERDA)){
			desplazar = -1;
		}
		setPos(getPos().getPosX()+desplazar, getPos().getPosY());
		
		if(getPos().getPosX() >= Seccion.getAncho())
			setDireccion(Direccion.IZQUIERDA);
		else
			if(getPos().getPosX() <= 0)
				setDireccion(Direccion.DERECHA);
		
	}
	
	@Override
	public Posicion posicionGrafica(int nroSeccion) {
		Posicion posGrafica;
		switch (nroSeccion) {
		
		case 0: posGrafica = new Posicion(45 + this.getPos().getPosX() * 52, 260 - 78 * this.getPos().getPosY()); break;
		//case 1: posGrafica = new Posicion(137 + this.getPos().getPosX() * 50, 260 - 78 * this.getPos().getPosY()); break;
		//default: posGrafica = new Posicion(137 + this.getPos().getPosX() * 50, 245 - 78 * this.getPos().getPosY());
		case 1: posGrafica = new Posicion(45 + this.getPos().getPosX() * 50, 260 - 78 * this.getPos().getPosY()); break;
		default: posGrafica = new Posicion(45 + this.getPos().getPosX() * 50, 245 - 78 * this.getPos().getPosY());
		
		}
		
		return posGrafica;
	}
}

