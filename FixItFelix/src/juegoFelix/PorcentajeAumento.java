package juegoFelix;

public abstract class PorcentajeAumento {

	private static int redondear(double n) {
		double decimal = n - (int)(n);
		
		if(decimal < 0.5)
			return (int) n;
		else
			return (int)(n) + 1;
	}
	
	//Calcula aumento del 10 por ciento en entero
	public static int calcularAumento(int base,int n) {
		int resultado = base;
		for(int i = 1;i <= n;i++) {
			resultado = redondear((double)(resultado + 0.1*resultado));
		}
		return resultado;
	}

}
