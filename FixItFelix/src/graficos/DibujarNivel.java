package graficos;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		
		//this.repaint();

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