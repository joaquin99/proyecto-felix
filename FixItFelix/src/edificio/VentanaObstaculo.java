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
		cantObstaculos = 1 + rnd.nextInt(2);						//Al menos debe haber un obst�culo
		obstaculos = new Obstaculo[cantObstaculos];
		generarObstaculos();
	}
	
	
	
	private void generarObstaculos(){
		obstaculos[0] = new Moldura();								//Prioriza el obst�culo moldura
		if (cantObstaculos == 2)
			obstaculos[1] = new Maceta();							//En caso de haber m�s de un obst�culo, se generar� la maceta
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
