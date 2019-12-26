package edificio;
import java.util.Random;

public class VentanaHoja extends VentanaVidrio{

	private int cantObstaculos;
	private Obstaculo[] obstaculos;
	private Random rnd = new Random();
	
	public VentanaHoja(){	
	}
	
	public VentanaHoja(boolean estaArreglado, boolean hayObstaculo){
		super(estaArreglado, hayObstaculo);
		this.cantObstaculos = 1 + rnd.nextInt(3);	//Al menos debe haber un obstaculo
		obstaculos = new Obstaculo[cantObstaculos];
		generarObstaculos();
	}
	
	@Override
	public boolean tieneObstaculos(){
		return true;
	}
	
	@Override
	public Obstaculo[] getObstaculos() {
		return this.obstaculos;
	}
	
	private void generarObstaculos(){
		switch(cantObstaculos){
			case 3: obstaculos[2] = new Maceta();

			case 2: obstaculos[1] = new Moldura();	//En caso de haber 2 obstáculos, priorizará generar moldura antes que la maceta

			case 1: obstaculos[0] = new Hoja();		//Garantiza que habrá un obstaculo hoja
		}
	}
}
