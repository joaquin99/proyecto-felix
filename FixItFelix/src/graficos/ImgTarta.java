package graficos;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgTarta {

	private String tartaDireccion = "imagenes/pastel/slice12_12.png";
	public Image tarta;
	
	public ImgTarta() throws IOException {
		URL tartaURL = getClass().getClassLoader().getResource(tartaDireccion);
		tarta = ImageIO.read(tartaURL);
	}
	
}
