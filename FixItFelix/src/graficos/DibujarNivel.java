package graficos;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entidades.Felix;
import juegoFelix.ControladorFelix;

public class DibujarNivel extends JFrame{
	
	public static DibujarNivel INSTANCE;
	
	private DibujarNivel(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(600, 600);

		DibujarEdificio de=DibujarEdificio.getInstance();
		
		//El controladorFelix creado en DibujarEdificio se agrega al frame
		this.addKeyListener(DibujarEdificio.getInstance().getControlPersonaje());
		System.out.println(de.getComponentCount());
		this.add(de);
		this.setResizable(false);
		this.setVisible(true);
		
		JPanel infoFelix = new JPanel();
		infoFelix.setSize(200,200);
		infoFelix.setLocation(0,500);
		add(infoFelix);
		//this.setComponentZOrder(infoFelix, 0);
		
		JLabel puntosFelix = new JLabel("Puntos: "+Felix.getInstance().getPuntos());
		infoFelix.add(puntosFelix);
		puntosFelix.setLocation(100,100);
		infoFelix.setComponentZOrder(puntosFelix, 0);
		
		JLabel vidasFelix = new JLabel("Puntos: "+Felix.getInstance().getVidas());
		infoFelix.add(vidasFelix);
		puntosFelix.setLocation(0,0);
		infoFelix.setComponentZOrder(vidasFelix, 0);
		
		JLabel tiempoNivel = new JLabel();
		infoFelix.add(tiempoNivel);
		infoFelix.setComponentZOrder(tiempoNivel, 0);
		
		JLabel nroNivelActual = new JLabel();
		infoFelix.add(nroNivelActual);
		infoFelix.setComponentZOrder(nroNivelActual, 0);
		
		de.setInfoNivel(vidasFelix, puntosFelix,tiempoNivel,nroNivelActual);
	
	}
	
	public static DibujarNivel getInstance(){
		//System.out.println("INSTANCE DibujarNivel: "+INSTANCE);
		if(INSTANCE == null){
			INSTANCE = new DibujarNivel();
		}
		return INSTANCE;
	}
	
	//Para eliminar la ventana despues de que termina el juego
	public void ocultar() {
		if(INSTANCE != null) {
			INSTANCE.setVisible(false);
			INSTANCE.dispose();
			INSTANCE = null;
		}
	}
	
}