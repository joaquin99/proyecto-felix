package entidades;

public class Pajaro extends Enemigo{
	private static final int ANCHO_PAJARO = 35;
	private static final int ALTO_PAJARO = 21;
	
	public Pajaro(double aumentoVelocidad, int posX, int posY) {
		this.velocidad = velocidad + velocidad * aumentoVelocidad;
		if(super.getPos().getPosX() == 0) 
			super.setDireccion(Direccion.DERECHA);
		else 
			super.setDireccion(Direccion.IZQUIERDA);
		
		this.setPos(posX,posY);
		this.setDimension(ANCHO_PAJARO, ALTO_PAJARO);
		
	}

	public EstadosFelix daniar() {
		return EstadosFelix.GOLPEADOPAJARO;
	}

	@Override
	public void mover() {
		if(super.getDireccion().equals(Direccion.IZQUIERDA)){
			super.getPos().setPosX(this.getPos().getPosX()  - 1);
			if (getPos().getPosX() == 0) {
				super.setDireccion(Direccion.DERECHA);
			}
		}
		else {
			setPos(super.getPos().getPosX()  + 1,super.getPos().getPosY());
			if ((getPos().getPosX() + ANCHO_PAJARO) == 400) {
				super.setDireccion(Direccion.IZQUIERDA);
			}
		}
	}
	
	@Override
	public Posicion posicionGrafica(int nroSeccion) {
		Posicion posGrafica;
		switch (nroSeccion) {
		
		case 0: posGrafica = new Posicion(45 + this.getPos().getPosX() * 52, 260 - 78 * this.getPos().getPosY()); break;
		case 1: posGrafica = new Posicion(137 + this.getPos().getPosX() * 50, 260 - 78 * this.getPos().getPosY()); break;
		default: posGrafica = new Posicion(137 + this.getPos().getPosX() * 50, 245 - 78 * this.getPos().getPosY());
		
		}
		
		return posGrafica;
	}
}
