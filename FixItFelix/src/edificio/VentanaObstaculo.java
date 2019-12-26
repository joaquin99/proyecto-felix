package edificio;
import java.util.Random;

public class VentanaObstaculo extends VentanaVidrio{
	
	private int cantObstaculos;
	private Obstaculo[] obstaculos;
	private Random rnd = new Random();
	
	public VentanaObstaculo(){
	}
	
	public VentanaObstaculo(boolean estaArreglado, boolean hayObstaculo){
		super(estaArreglado,hayObstaculo);
		cantObstaculos = 1 + rnd.nextInt(2);						//Al menos debe haber un obstáculo
		obstaculos = new Obstaculo[cantObstaculos];
		generarObstaculos();
	}
	
	
	
	private void generarObstaculos(){
		obstaculos[0] = new Moldura();								//Prioriza el obstáculo moldura
		if (cantObstaculos == 2)
			obstaculos[1] = new Maceta();							//En caso de haber más de un obstáculo, se generará la maceta
	}
	
	@Override
	public boolean tieneObstaculos(){
		return true;
	}
	
	@Override
	public Obstaculo[] getObstaculos(){
		return this.obstaculos;
	}
}
