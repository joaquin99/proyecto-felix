package edificio;

public class Panel {
	private final int maxMartillazos = 2;
	private int martillazos;
	private int puntos;
	private EstadosPanel estado;
	
	public Panel() {
	}
	
	public Panel(EstadosPanel estado){
		setEstado(estado);
		this.martillazos = estado.ordinal();		//2 SANO; 1 SEMIRROTO; 0 ROTO
	}
	
	public int getMartillazos(){
		return this.martillazos;
	}
	
	public void arreglarPanel() {
		System.out.println("Cantidad de martillazos que tiene este panel antes del seteo: " + martillazos);
		//this.martillazos = martillazos + 1;
		++martillazos;
		if(martillazos == maxMartillazos)
			setEstado(EstadosPanel.SANO);
		else
			setEstado(EstadosPanel.SEMIRROTO);
		System.out.println("Cantidad de martillazos que tiene este panel despues del seteo: " + martillazos);
	}
	
	public EstadosPanel getEstado(){
		return this.estado;
	}
	
	public void setEstado(EstadosPanel estado){
		this.estado = estado;
		if(estado != EstadosPanel.SANO)
			this.puntos = 100;
		else
			this.puntos = 0;
	}
	
	public int getPuntos(){
		return this.puntos;
	}
	
	public void setPuntos(int puntos){
		this.puntos = puntos;
	}
}
