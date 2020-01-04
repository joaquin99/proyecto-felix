package entidades;
import edificio.*;
import juegoFelix.*;

public class Felix extends Entidad{
	
	private static final int ANCHO_FELIX = 25;
	private static final int ALTO_FELIX = 55;	
	
	private EstadosFelix estado;
	private int vidas;
	private int puntos;
	
	private static Felix INSTANCE;
	
	
	private Felix(){
		this.vidas = 3;
		setPos(2, 0);
		this.estado = EstadosFelix.NORMAL;
		this.puntos = 0;
		this.setDimension(ANCHO_FELIX, ALTO_FELIX);
		
	}
	
	public static Felix getInstance(){
		if(INSTANCE == null){
			INSTANCE = new Felix();
		}
		return INSTANCE;
	}
	
	public EstadosFelix getEstado() {
		return this.estado;
	}
	
	public void setEstado(EstadosFelix nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	public int getVidas(){
		return vidas;
	}
	
	public void daniarse(Enemigo ene) {
		if( !estado.equals(EstadosFelix.INMUNE) ){
			vidas = vidas -1;
			setEstado(ene.daniar());
		}
	}

	public int getPuntos(){
		return puntos;
	}
	
	public void setPuntos(int puntos){
		this.puntos = puntos;
	}

	
	public void arreglarVentana(Ventana v) {
		if(!v.getEstaArreglado()) {
			System.out.println("Se arreglara la ventana");
			v.arreglar();
			puntos = puntos + v.getPuntos();
		}
		else
			System.out.println("No se puede arreglar la ventana");
	}
	
	public void recolectarTarta(Ventana v) {
		if(v.hayTarta()){
			System.out.println("Se ha recolectado una tarta");
			setEstado(EstadosFelix.INMUNE);
			v.setTarta(false);
		}
	}
	
	public void posInicial(){
		getPos().setPosY(0);
	}
	
	public void reiniciar(){
		this.vidas = 3;
		setPos(2, 0);
		this.estado = EstadosFelix.NORMAL;
		this.puntos = 0;
	}
	
	public void mover(Ventana[][] ventanas){
		//No permite dejarla sin inicializar
		Ventana ventanaSiguiente = ventanas[getPos().getPosX()][getPos().getPosY()];
		System.out.println("Felix intenta moverse");
		if(noSeCae()){
			boolean frenar = false;
			Ventana ventanaActual = ventanas[getPos().getPosX()][getPos().getPosY()];
			if(getDireccion().equals(Direccion.DERECHA)){
				ventanaSiguiente = ventanas[getPos().getPosX() + 1][getPos().getPosY()];
				if(ventanaSiguiente.tieneObstaculos()){
					Obstaculo [] obstaculosVS = ventanaSiguiente.getObstaculos();
					for(int i = 0; ((i < obstaculosVS.length)&&(!frenar)); i++){
						if(obstaculosVS[i].getDirObstaculo().equals(Direccion.IZQUIERDA))
							frenar = true;
					}
				}
				if(ventanaActual.tieneObstaculos()){
					Obstaculo [] obstaculosVA = ventanaActual.getObstaculos();
					for(int i = 0; ((i < obstaculosVA.length)&&(!frenar)); i++){
						if(obstaculosVA[i].getDirObstaculo().equals(Direccion.DERECHA))
							frenar = true;
					}
				}
				
			}
			else
				if(getDireccion().equals(Direccion.ARRIBA)){
					ventanaSiguiente = ventanas[getPos().getPosX()][getPos().getPosY() + 1];
					if(ventanaSiguiente.tieneObstaculos()){
						Obstaculo [] obstaculosVS = ventanaSiguiente.getObstaculos();
						for(int i = 0; ((i < obstaculosVS.length)&&(!frenar)); i++){
							if(obstaculosVS[i].getDirObstaculo().equals(Direccion.ABAJO))
								frenar = true;
						}
					}
					if(ventanaActual.tieneObstaculos()){
						Obstaculo [] obstaculosVA = ventanaActual.getObstaculos();
						for(int i = 0; ((i < obstaculosVA.length)&&(!frenar)); i++){
							if(obstaculosVA[i].getDirObstaculo().equals(Direccion.ARRIBA))
								frenar = true;
						}
					}
				}
				else
					if(getDireccion().equals(Direccion.ABAJO)){
						ventanaSiguiente = ventanas[getPos().getPosX()][ getPos().getPosY() - 1];
						if(ventanaSiguiente.tieneObstaculos()){
							Obstaculo [] obstaculosVS = ventanaSiguiente.getObstaculos();
							for(int i = 0; ((i < obstaculosVS.length)&&(!frenar)); i++){
								if(obstaculosVS[i].getDirObstaculo().equals(Direccion.ARRIBA))
									frenar = true;
							}
						}
						if(ventanaActual.tieneObstaculos()){
							Obstaculo [] obstaculosVA = ventanaActual.getObstaculos();
							for(int i = 0; ((i < obstaculosVA.length)&&(!frenar)); i++){
								if(obstaculosVA[i].getDirObstaculo().equals(Direccion.ABAJO))
									frenar = true;
							}
						}
					}
			if(!frenar) {
				//Se prueba para intentar recolectar tarta
				if(getDireccion() == Direccion.IZQUIERDA)
					ventanaSiguiente = ventanas[getPos().getPosX()-1][getPos().getPosY()];
				recolectarTarta(ventanaSiguiente);
				mover();
			}
		}
	}

	public void mover() {
	
		if(getDireccion().equals(Direccion.ARRIBA))
			getPos().setPosY(getPos().getPosY()+1);

		else if(getDireccion().equals(Direccion.ABAJO))
			getPos().setPosY(getPos().getPosY()-1);
		
		else if(getDireccion().equals(Direccion.IZQUIERDA))
			getPos().setPosX(getPos().getPosX()-1);
		
		else if(getDireccion().equals(Direccion.DERECHA))
			getPos().setPosX(getPos().getPosX()+1);
		
	}
	
	public boolean noSeCae(){
		  boolean condicion = false;
		  if(getDireccion().equals(Direccion.DERECHA)){
			  if((getPos().getPosX() + 1) < Seccion.getAncho())
					condicion = true;
		  }
		  else
			  if(getDireccion().equals(Direccion.IZQUIERDA)){
				  if((getPos().getPosX() - 1) >= 0)
						condicion = true;
			  }
		 else
		  	 if(getDireccion().equals(Direccion.ABAJO)){
		  		 if((getPos().getPosY() - 1) >= 0)
		  			 condicion = true;
		  	 }
		  else
		  	if(getDireccion().equals(Direccion.ARRIBA)){
			  	 if((getPos().getPosY() + 1) < Seccion.getAlto())
			  			condicion = true;
		  	 }
		  
		  if(condicion)
			  System.out.println("No se cae");
		  else
		  	System.out.println("Se cae");
		  
		  return condicion;
	  }
	
	@Override
	public Posicion posicionGrafica(int nroSeccion) {
		Posicion posGrafica;
		switch (nroSeccion) {
		
		case 0: posGrafica = new Posicion(45 + this.getPos().getPosX() * 52, 260 - 78 * this.getPos().getPosY()); break;
		//case 1: posGrafica = new Posicion(137 + this.getPos().getPosX() * 50, 260 - 78 * this.getPos().getPosY()); break;
		//default: posGrafica = new Posicion(137 + this.getPos().getPosX() * 50, 245 - 78 * this.getPos().getPosY());
		case 1: posGrafica = new Posicion(45 + this.getPos().getPosX() * 50, 230 - 78 * this.getPos().getPosY()); break;
		default: posGrafica = new Posicion(45 + this.getPos().getPosX() * 50, 260 - 78 * this.getPos().getPosY());
		
		}
		
		return posGrafica;
	}

}