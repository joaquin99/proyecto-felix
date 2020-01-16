package principal;

public class Jugador{

	private Integer puntos;
	private String nombre;
	
	public Integer getPuntos() {
		return puntos;
	}
	
	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//@override
	public String toString(){
		return "Jugador: " + this.nombre + "     " + this.puntos;
	}
	
}
