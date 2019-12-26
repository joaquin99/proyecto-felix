package juegoFelix;

public class Edificio {
	
	private static final int ANCHO = Seccion.getAncho();
	private static final int SECCIONES = 3;
	private static final int ALTURA = Seccion.getAlto()*SECCIONES;
	
	
	
	private int cantVentanasConObstaculos;
	private int cantVentanasRotas;
	private Seccion []secciones;

	public Edificio() {}
	
	//Constructor de nivel
	public Edificio(Double porcentaje, double porcentajeTiempo) {

		this.cantVentanasConObstaculos = (int)(ANCHO*ALTURA*porcentaje);
		this.cantVentanasRotas = (int)(ANCHO*ALTURA*porcentaje);
		secciones = new Seccion[SECCIONES];
		iniciarSecciones();
	}
	
	public void iniciarSecciones(){
		int auxVentanasRotas = cantVentanasRotas;
		int auxVentanasConObstaculos = cantVentanasConObstaculos;
		int auxR = 0;
		int auxO = 0;
		
		for (int i = 0; i < SECCIONES; i++ ){
			auxR = auxVentanasRotas / (SECCIONES - i);
			auxVentanasRotas = auxVentanasRotas - auxR;
			
			auxO = auxVentanasConObstaculos / (SECCIONES - i);
			auxVentanasConObstaculos = auxVentanasConObstaculos - auxO;
			
			secciones[i] = new Seccion(auxR, auxO, i);
		}
	}
	
	public Seccion getSeccion(int indice){
		return secciones[indice];
	}
	
	public boolean condicionVictoria(int indice){
		return (secciones[indice].condicionVictoriaSeccion() && (indice == 2));
	}
	
	public static int getSecciones(){
		return SECCIONES;
	}
}
