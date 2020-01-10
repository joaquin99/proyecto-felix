package graficos;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edificio.*;
import entidades.*;
import juegoFelix.ControladorFelix;

public class DibujarEdificio extends JPanel{
	
	private Ventana[][] ventanas;
	private ArrayList <Enemigo> enemigos;
	private int nroSeccion;
	
	private ImgVentana imgVentana;
	private ImgFelix imgFelix;
	private ImgEdificio imgEd;
	private ImgRalph imgRalph;
	private ImgLadrillo imgLadrillo;
	private ImgPajaro imgPajaro;
	private ImgTarta imgTarta;
	private ControladorFelix controlPersonaje;
	
	//Prueba de mostrar info del nivel
	JLabel vidasFelix;
	JLabel puntosFelix;
	JLabel tiempoNivel;
	int tiempoRestante;
	JLabel nroNivelActual;
	
	private static DibujarEdificio INSTANCE;
	
	private DibujarEdificio() {
		
		//this.setLayout(null);
		
		System.out.println("Se repite");
		imgVentana = null; 
		try {
			imgVentana = new ImgVentana();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		imgEd = null;
		
		try {
			imgEd = new ImgEdificio();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		imgFelix = null; 
		try {
			imgFelix = new ImgFelix();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			imgRalph = new ImgRalph();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			imgLadrillo = new ImgLadrillo();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			imgPajaro = new ImgPajaro();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			imgTarta = new ImgTarta();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		//Se agrega el controladorFelix al nivel para permitir su control
		controlPersonaje = new ControladorFelix(ventanas);
		
		this.setSize(500, 500);
		this.setLocation(100,0);
		this.setVisible(true);
	}
	
	
	public static DibujarEdificio getInstance(){
	
		//System.out.println("INSTANCE DibujarEdificio: "+INSTANCE);
		if(INSTANCE == null)
			INSTANCE = new DibujarEdificio();
		return INSTANCE;
	}
	
	public void dibujar(Graphics g){
		
		//DibujarNivel.getInstance().addKeyListener(controladorFelix);
		switch (nroSeccion) { //Va a dibujar una version del edificio dependiendo de la seccion
		
		case 0:
			g.drawImage(imgEd.paredBase,0,0,null);break;
		case 1:
			g.drawImage(imgEd.paredMedio,0,0,null);break;
		case 2:
			g.drawImage(imgEd.paredTecho,0,0,null);break;
		default: System.out.println("Error al pasar la seccion");
		}
		
		ImgObstaculo imgObstaculo = null;
		try{
			imgObstaculo = new ImgObstaculo();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	
		for(int i = 0; i < 3; i++) {//Con los cambios en el if, evita dibujar las ventanas centrales en las primeras dos filas en la primer seccion
			for(int j = 0; j < 5; j++){
			//DibujarVentana ventana = null;
				if(nroSeccion == 0){
					if ((j == 2)&&(i == 0)){
						g.drawImage(quePuertaEs(ventanas[j][i]),128,244, null);	
					}
					else
						if((j == 2)&&(i == 1))
							g.drawImage(queVentanaSemicircularEs(ventanas[j][i]),128,186,null);
						else{
							g.drawImage(queVentanaEs(ventanas[j][i]),35+j*52,260-78*i,null);
							
							g.drawImage(quePanelEs(ventanas[j][i].getPaneles()[0]),45+52*j,291-78*i,null);
							g.drawImage(quePanelEs(ventanas[j][i].getPaneles()[1]),45+52*j,273-78*i,null);
							
							if(ventanas[j][i].getHayObstaculos()){
								for(int k = 0; k < ventanas[j][i].getObstaculos().length; k++){
									switch(ventanas[j][i].getObstaculos()[k].getDirObstaculo()){
										case ABAJO: g.drawImage(imgObstaculo.obstaculoMaceta,40+j*52,305-78*i,null); break;
										case ARRIBA: g.drawImage(imgObstaculo.obstaculoTecho,35+j*52,260-78*i,null); break;
									}
								}
							}
						}
			
				}
				else
					if(nroSeccion == 1){
						g.drawImage(queVentanaEs(ventanas[j][i]),37+j*50,275-70*i,null);
						g.drawImage(quePanelEs(ventanas[j][i].getPaneles()[0]),47+50*j,306-70*i,null);
						g.drawImage(quePanelEs(ventanas[j][i].getPaneles()[1]),47+50*j,288-70*i,null);
						if(ventanas[j][i].getHayObstaculos()){
							for(int k = 0; k < ventanas[j][i].getObstaculos().length; k++){
								switch(ventanas[j][i].getObstaculos()[k].getDirObstaculo()){
									case ABAJO: g.drawImage(imgObstaculo.obstaculoMaceta,42+j*50,315-70*i,null); break;
									case ARRIBA: g.drawImage(imgObstaculo.obstaculoTecho,37+j*50,275-70*i,null); break;
								}
							}
						}
					}
					else
						if (nroSeccion == 2){
							g.drawImage(queVentanaEs(ventanas[j][i]),37+j*50,245-78*i,null);

							g.drawImage(quePanelEs(ventanas[j][i].getPaneles()[0]),47+50*j,276-78*i,null);
							g.drawImage(quePanelEs(ventanas[j][i].getPaneles()[1]),47+50*j,258-78*i,null);
							if(ventanas[j][i].getHayObstaculos()){
								for(int k = 0; k < ventanas[j][i].getObstaculos().length; k++){
									switch(ventanas[j][i].getObstaculos()[k].getDirObstaculo()){
										case ABAJO: g.drawImage(imgObstaculo.obstaculoMaceta,42+j*50,290-78*i,null); break;
										case ARRIBA: g.drawImage(imgObstaculo.obstaculoTecho,37+j*50,245-78*i,null); break;
									}
								}
							}
						}
				
				//Dibuja la tarta
				if(ventanas[j][i].hayTarta()) {
					g.drawImage(imgTarta.tarta,47+50*j,276-78*i,null);
				}
				
			}
			
		}
		
		g.drawImage(imgFelix.movimiento, Felix.getInstance().posicionGrafica(nroSeccion).getPosX(), Felix.getInstance().posicionGrafica(nroSeccion).getPosY(), null);
		g.drawImage(imgRalph.movimiento, Ralph.getInstance().posicionGrafica(nroSeccion).getPosX(), Ralph.getInstance().posicionGrafica(nroSeccion).getPosY(), null);
		
		for(int i = 0; i < enemigos.size(); i++) {
			if (enemigos.get(i).daniar().equals(EstadosFelix.GOLPEADOLADRILLO)){
				g.drawImage(imgLadrillo.ladrillo, enemigos.get(i).posicionGrafica(nroSeccion).getPosX(), enemigos.get(i).posicionGrafica(nroSeccion).getPosY(), null);
			}
			else
				g.drawImage(imgPajaro.aleteo1, enemigos.get(i).posicionGrafica(nroSeccion).getPosX(), enemigos.get(i).posicionGrafica(nroSeccion).getPosY(), null);
		}
		
		//Prueba de mostrar info del jugador y el nivel
		actualizarDatos();
		
	}
	
//	@Override
//	public void paintComponent(Graphics g){
//		//super.paintComponents(g);
//		dibujar(g);
//	}
	
	
	
	public void actualizar(Ventana[][] ventanas, int nroSec, ArrayList <Enemigo> enemigos) {
		this.ventanas = ventanas;
		this.nroSeccion = nroSec;
		this.enemigos = enemigos;
		this.controlPersonaje.setMatrizVentanas(ventanas);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		dibujar(g);
	}

	public Image queVentanaEs(Ventana ventana){
		Image imagen = null;

		boolean hayVentanaIzquierda = false;
		if (ventana.getHayObstaculos()){
			for (int i = 0; ((i < ventana.getObstaculos().length)&&(!hayVentanaIzquierda)); i++){
				if(ventana.getObstaculos()[i].getDirObstaculo().equals(Direccion.IZQUIERDA)){
					hayVentanaIzquierda = true;
				}
			}
		}
		
		if(hayVentanaIzquierda)
			imagen = imgVentana.ventanaHojaIzquierda;
		else
			imagen = imgVentana.ventana;
		
		return imagen;
	}
	
	@Override
	public void paintComponents(Graphics g) {
		// TODO Auto-generated method stub
		//DibujarNivel.getInstance().addKeyListener(controlPersonaje);
		super.paintComponents(g);
		dibujar(g);
	}

	public Image quePuertaEs(Ventana ventana){
		Image imagen = null;
		
		switch (ventana.getCantPanelesRotos()){
			
		case 0: imagen = imgVentana.puertaArreglada; break;
			
		case 1: imagen = imgVentana.puerta1; break;
		
		case 2: imagen = imgVentana.puerta2; break;
			
		case 3: imagen = imgVentana.puerta3; break;
			
		}
		
		return imagen;
	}
	

	public Image queVentanaSemicircularEs(Ventana ventana){
		Image imagen = null;
		
		switch (ventana.getCantPanelesRotos()){
		
		case 0: imagen = imgVentana.ventanaSemicircularArreglada; break;

		case 1: imagen = imgVentana.ventanaSemicircular1; break;
		
		default: imagen = imgVentana.ventanaSemicircular2; break;
			
		}
		
		return imagen;
	}
	
	public Image quePanelEs(Panel panel){
		Image imagen = null;
		
		switch (panel.getEstado()){

		case SEMIRROTO: imagen = imgVentana.panelSemirroto; break;
		
		case SANO: imagen = imgVentana.panelArreglado; break;
		
		case ROTO: imagen = null;
			
		}
		
		return imagen;
	}
	
	//Retorna el ControladorFelix. Le permite a DibujarNivel agregarlo al frame
	public ControladorFelix getControlPersonaje() {
		return controlPersonaje;
	}
	
	//Muestra informacion sobre el estado del jugador y el nivel
	//Todavia no aparece en pantalla
	public void setInfoNivel(JLabel vidas,JLabel puntos,JLabel tiempo,JLabel nroNivel) {
		
		vidasFelix = vidas;
		puntosFelix = puntos;
		tiempoNivel = tiempo;
		nroNivelActual = nroNivel;
		
		System.out.println("Se inicio la info");
		
	}
	
	public void actualizarDatos() {
		if(vidasFelix != null) {
			vidasFelix.setText("Vidas: "+Felix.getInstance().getVidas());
		}
		if(puntosFelix != null) {
			puntosFelix.setText("Puntos: "+Felix.getInstance().getPuntos());
		}
	}
	
	public void setTiempoNivel(int tiempo) {
			tiempoRestante = tiempo;
			tiempoNivel.setText("Tiempo :"+tiempoRestante);

	}
	
	public void actualizarTiempo() {
		tiempoRestante--;
		tiempoNivel.setText("Tiempo: "+tiempoRestante);
	}
	
	public void setNroNivelActual(int nroNivel) {
		nroNivelActual.setText("Nivel: "+nroNivel);
	}
}
/*
*/