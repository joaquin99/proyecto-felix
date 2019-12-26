package graficos;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImgFelix {
	private String movimientoDireccion = "imagenes/felix/slice102_@.png";
	private String arregloDireccion = "imagenes/felix/slice135_@.png";
	private String pastelDireccion = "imagenes/felix/slice89_89.png";
	private String golpeadoDireccion = "imagenes/felix/slice292_@.png";
	public Image movimiento;
	public Image arreglo;
	public Image pastel;
	public Image golpeado;

	public ImgFelix() throws IOException {
		URL movimientoURL = getClass().getClassLoader().getResource(movimientoDireccion);
		movimiento = ImageIO.read(movimientoURL);
		URL arregloURL = getClass().getClassLoader().getResource(arregloDireccion);
		arreglo = ImageIO.read(arregloURL);
		URL pastelURL = getClass().getClassLoader().getResource(pastelDireccion);
		pastel = ImageIO.read(pastelURL);
		URL golpeadoURL = getClass().getClassLoader().getResource(golpeadoDireccion);
		golpeado = ImageIO.read(golpeadoURL);
	}

	public void dibujar(Image img) {
		img.getGraphics().drawImage(img, 50, 50, null);
	}
}