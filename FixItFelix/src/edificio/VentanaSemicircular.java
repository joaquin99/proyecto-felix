package edificio;

public class VentanaSemicircular extends Ventana{
	private static int CANTIDAD_PANELES = 4;
	public VentanaSemicircular(){	
	}
	
	public VentanaSemicircular(boolean estaArreglado, boolean hayObstaculo){
		super(estaArreglado, hayObstaculo, CANTIDAD_PANELES);
	}
	
}
