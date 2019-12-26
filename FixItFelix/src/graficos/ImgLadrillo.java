package graficos;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgLadrillo {
	private String ladrilloDireccion = "imagenes/rocas/slice10_10.png";
	public Image ladrillo;
	
	public ImgLadrillo() throws IOException {
		URL ladrilloURL = getClass().getClassLoader().getResource(ladrilloDireccion);
		ladrillo = ImageIO.read(ladrilloURL);
	}

}
