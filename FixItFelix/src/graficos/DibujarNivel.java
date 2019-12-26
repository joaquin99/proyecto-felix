package graficos;

import javax.swing.JFrame;

import juegoFelix.ControladorFelix;

public class DibujarNivel extends JFrame{
	
	public static DibujarNivel INSTANCE;
	
	private DibujarNivel(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(600, 600);
		DibujarEdificio de=DibujarEdificio.getInstance();
		System.out.println(de.getComponentCount());
		this.add(de);
		this.setVisible(true);
		
		//this.repaint();
	}
	
	public static DibujarNivel getInstance(){
		if(INSTANCE == null){
			INSTANCE = new DibujarNivel();
		}
		return INSTANCE;
	}
}