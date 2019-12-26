package entidades;

public class Posicion {

	private int posX;
	
	private int posY;

	public Posicion(){
		
	}
	
	public Posicion(int posX, int posY){
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public static boolean comparePos(int pos1, int pos2){
		return (pos1 == pos2);
	}
	
}
