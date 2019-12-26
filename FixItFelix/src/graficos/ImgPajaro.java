package graficos;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImgPajaro {
	private String aleteo1Direccion = "imagenes/pajaro/slice08_08.png";
	private String aleteo2Direccion = "imagenes/pajaro/slice09_09.png";
	
	public Image aleteo1;
	public Image aleteo2;

	public ImgPajaro() throws IOException {
		URL aleteo1URL = getClass().getClassLoader().getResource(aleteo1Direccion);
		aleteo1 = ImageIO.read(aleteo1URL);
		URL aleteo2URL = getClass().getClassLoader().getResource(aleteo2Direccion);
		aleteo2 = ImageIO.read(aleteo2URL);
	}

}
