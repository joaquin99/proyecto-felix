package entidades;

public class Ladrillo extends Enemigo {
	
	private static final int ANCHO_LADRILLO = 21;
	private static final int ALTO_LADRILLO = 13;	
	
	
	public Ladrillo(double aumentoVel, int posX, int posY) {
		velocidad = velocidad + velocidad * aumentoVel;
		this.setDireccion(Direccion.ABAJO); 
		this.setPos(posX,posY);
		
		this.setDimension(ANCHO_LADRILLO, ALTO_LADRILLO);
	}
	
	public EstadosFelix daniar() {
		return EstadosFelix.GOLPEADOLADRILLO;
	}

	@Override
	public void mover() {
			setPos(super.getPos().getPosX(),super.getPos().getPosY() -1); 
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
