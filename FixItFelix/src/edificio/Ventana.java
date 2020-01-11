package edificio;

import java.util.Random;

public abstract class Ventana {

	private boolean estaArreglado;
	private boolean hayObstaculos;
	private Panel[] paneles;
	private int cantPaneles;
	private int cantPanelesRotos;
	private int puntos;
	private boolean tarta;
	
	
	public Ventana(){
	}
	
	public Ventana(boolean estaArreglado, boolean hayObstaculo, int cantPaneles){
		this.estaArreglado = estaArreglado;
		this.hayObstaculos = hayObstaculo;
		this.cantPaneles = cantPaneles;
		this.paneles = new Panel[cantPaneles];
		this.cantPanelesRotos = generarPaneles();
		this.tarta = false;
	}
	
	public boolean getEstaArreglado() {
		return estaArreglado;
	}

	public void setEstaArreglado(boolean estado) {
		this.estaArreglado = estado;
	}


	public boolean getHayObstaculos() {
		return hayObstaculos;
	}

	public void setHayObstaculos(boolean hayObstaculos) {
		this.hayObstaculos = hayObstaculos;
	}
	
	
	public Panel[] getPaneles() {
		return paneles;
	}

	/*public void setPaneles(Panel[] paneles) {
		this.paneles = paneles;
	}*/
	
	
	public int getCantPanelesRotos() {
		return cantPanelesRotos;
	}

	public void setCantPanelesRotos(int cantPanelesRotos) {
		this.cantPanelesRotos = cantPanelesRotos;
	}
	
	
	public int getPuntos() {
		return puntos;
	}
	
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	
	public boolean hayTarta() {
		return tarta;
	}

	public void setTarta(boolean tarta) {
		this.tarta = tarta;
	}	

	
	public boolean tieneObstaculos(){
		return false;
	}
	
	
	public Obstaculo[] getObstaculos(){
		return null;
	}
		

	private int generarPaneles(){
		EstadosPanel state = EstadosPanel.SANO;
		Random rnd = new Random();
		int aux = 0;
		
		if(!estaArreglado){					//En caso de que la ventana no esté arreglada
			switch(rnd.nextInt(2)){						//Posibles estados de un panel roto
				case 0: state = EstadosPanel.ROTO; break;
				case 1: state = EstadosPanel.SEMIRROTO; 
			}
			paneles[0] = new Panel(state);				//Con el seteo del primer panel me aseguro que la ventana tenga al menos un panel roto
			aux++;
			
			for(int i = 1; i < cantPaneles; i++){	//Para el resto de los paneles se generará el estado de forma aleatoria
				switch(rnd.nextInt(3)){
				
					case 0: state = EstadosPanel.SANO; break;
					
					case 1: aux++;
							state = EstadosPanel.SEMIRROTO; break;
					
					case 2: aux++;
							state = EstadosPanel.ROTO;
				}
				paneles[i] = new Panel(state);
			}
		}
		else{											//En caso de que la ventana esté arreglada, todas tendran estado SANO
			for(int i = 0; i < this.cantPaneles; i++){
				paneles[i] = new Panel(state);
			}
		}
		
		return aux;
	}
	
	public void arreglar() {
		boolean fix = false;
		int indice = 0;
		if(cantPanelesRotos != 0){
			for(int i = 0; ((!fix) && (i < cantPaneles)); i++){		
				if(!paneles[i].getEstado().equals(EstadosPanel.SANO)){	  
					setPuntos(paneles[i].getPuntos());				
					paneles[i].arreglarPanel();
					fix = true;
					indice = i;
				}
			}
			if(paneles[indice].getEstado().equals(EstadosPanel.SANO)){
				cantPanelesRotos--;
				if (cantPanelesRotos == 0)
					setEstaArreglado(true);
			}
		}

	}
}
