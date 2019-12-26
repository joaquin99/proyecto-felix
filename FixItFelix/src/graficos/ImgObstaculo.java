package graficos;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgObstaculo {
	
	private String obstaculoMacetaDireccion = "imagenes/obstaculos/macetero.png";
	private String obstaculoTechoDireccion = "imagenes/obstaculos/slice22_22.png";
	
	public Image obstaculoMaceta;
	public Image obstaculoTecho;
	
	public ImgObstaculo() throws IOException {
		
		URL obstaculoMacetaURL = getClass().getClassLoader().getResource(obstaculoMacetaDireccion);
		obstaculoMaceta = ImageIO.read(obstaculoMacetaURL);
		
		URL obstaculoTechoURL = getClass().getClassLoader().getResource(obstaculoTechoDireccion);
		obstaculoTecho = ImageIO.read(obstaculoTechoURL);
	}
	
}
