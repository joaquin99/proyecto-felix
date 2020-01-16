package edificio;

public class Seccion {
	
	private static final int ANCHO = 5;
	private static final int ALTURA = 3;
	private static final double PORCENTAJE = 0.25;
	
	private Ventana[][] ventanas;
	private int cantVentanasConObstaculos;
	private int cantVentanasRotas;
	
	public Seccion(){
	}
	
	public Seccion(int cantVentanasRotas, int cantVentanasConObstaculos, int nroSeccion){
		this.cantVentanasRotas = cantVentanasRotas;
		this.cantVentanasConObstaculos = cantVentanasConObstaculos;
		this.ventanas = new Ventana [ANCHO][ALTURA];
		iniciarSeccion(nroSeccion);
	}
	
	public void iniciarSeccion(int nroSeccion) {
		boolean ventanaExclusiva = false;
		int auxCVCH = (int)( PORCENTAJE*cantVentanasConObstaculos);									//cantVentanasConHojas
		int auxCVR = cantVentanasRotas;
		int auxCVO = cantVentanasConObstaculos;
		int auxCTV = ANCHO * ALTURA;
		
		double aleatorioVRotas, aleatorioObstaculos, aleatorioObstaculoConHoja;
		boolean estaArreglado;
		boolean tieneObstaculo;
		boolean obstaculoHoja;
		for(int i = 0; i < ALTURA; i++){
			for(int j = 0; j < ANCHO; j++){
				aleatorioVRotas = Math.random();
				aleatorioObstaculos = Math.random();
				aleatorioObstaculoConHoja = Math.random();
				
				//System.out.println("[" + i + "][" + j + "] VRotas: " + aleatorioVRotas + "; Obstaculos: " + aleatorioObstaculos + "; ObstaculoConHoja: " + aleatorioObstaculoConHoja);
				estaArreglado = !(aleatorioVRotas < (double)auxCVR/auxCTV);
				tieneObstaculo = (aleatorioObstaculos < (double)auxCVO/auxCTV);
				obstaculoHoja = (aleatorioObstaculoConHoja < (double)auxCVCH/auxCVO);
				if(nroSeccion == 0){
					if((j == 2) && (i == 0)) {
						ventanas[j][i] = new VentanaPuerta(estaArreglado,false);
						ventanaExclusiva = true;
					}
					else
						if((j == 2) && (i == 1)) {
							ventanas[j][i] = new VentanaSemicircular(estaArreglado,false);
							ventanaExclusiva = true;
						}
				}
				if(!ventanaExclusiva) {
					if(tieneObstaculo){
						if(obstaculoHoja && estaArreglado){
							ventanas[j][i] = new VentanaHoja(estaArreglado, tieneObstaculo);
							auxCVCH--;
						}
						else
							ventanas[j][i] = new VentanaObstaculo(estaArreglado, tieneObstaculo);
						auxCVO--;
					}
					else
						ventanas[j][i] = new VentanaVidrio(estaArreglado, tieneObstaculo);
				}
				else {
					ventanaExclusiva = false;
				}
				if(!estaArreglado)
					auxCVR--;
				auxCTV--;
			}
		}
	}

	public static int getAncho(){
		return ANCHO;
	}
	public static int getAlto(){
		return ALTURA;
	}
	

	public boolean condicionVictoriaSeccion(){
		boolean condicion = true;
		for(int i = 0; ((i < ALTURA)&&(condicion)); i++){
			for (int j = 0; ((j < ANCHO)&&(condicion)); j++){
				condicion = ventanas[j][i].getEstaArreglado();
			}
		}
		return condicion;
	}
	
	public Ventana getVentana(int posX, int posY){
		return ventanas[posX][posY];
	}
	
	public Ventana[][] getMatrizVentanas(){
		return this.ventanas;
	}
}
