package graficos;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImgRalph {
	private String movimientoDireccion = "imagenes/ralph/slice147_@.png";
	private String golpearDireccion = "imagenes/ralph/slice164_@.png";
	public Image movimiento;
	public Image golpear;
	
	public ImgRalph() throws IOException {
		URL movimientoURL = getClass().getClassLoader().getResource(movimientoDireccion);
		movimiento = ImageIO.read(movimientoURL);
		URL golpearURL = getClass().getClassLoader().getResource(golpearDireccion);
		golpear = ImageIO.read(golpearURL);
	}

}
