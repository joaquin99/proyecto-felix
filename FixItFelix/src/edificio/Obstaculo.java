package edificio;
import entidades.Direccion;

public abstract class Obstaculo {
	
	private Direccion dirObstaculo;
	
	public Obstaculo(){	
	}
	
	public Obstaculo(Direccion dirObstaculo){
		this.dirObstaculo = dirObstaculo;
	}
	
	public Direccion getDirObstaculo(){
		return dirObstaculo;
	}
}
