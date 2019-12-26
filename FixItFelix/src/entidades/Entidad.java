package entidades;

public abstract class Entidad {

	private Posicion pos;
	private Direccion direccion;
	private Dimension dimension;


	public Entidad(){
		pos = new Posicion(0, 0);
		dimension = new Dimension(0,0);
	}
	
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Posicion getPos() {
		return this.pos;
	}
	
	public void setPos(int posX,int posY) {
		this.pos.setPosX(posX);
		this.pos.setPosY(posY);
	}
	
	public Dimension getDimension(){
		return dimension;
	}
	
	public void setDimension(int ancho, int alto) {
		this.dimension.setAncho(ancho);
		this.dimension.setAlto(alto);
	}
	
	public abstract void mover();
	
	public abstract Posicion posicionGrafica(int nroSeccion); 
	
}
