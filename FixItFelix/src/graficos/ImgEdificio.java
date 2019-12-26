package graficos;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImgEdificio {
	private String paredBaseDireccion = "imagenes/edificio/edificio_150_seccion1.png";
	private String paredMedioDireccion = "imagenes/edificio/edificio_150_seccion2.png";
	private String paredTechoDireccion = "imagenes/edificio/edificio_150_seccion3.png";
	public Image paredBase;
	public Image paredMedio;
	public Image paredTecho;
	
	public ImgEdificio() throws IOException {
		URL paredBaseURL = getClass().getClassLoader().getResource(paredBaseDireccion);
		paredBase = ImageIO.read(paredBaseURL);
		URL paredMedioURL = getClass().getClassLoader().getResource(paredMedioDireccion);
		paredMedio = ImageIO.read(paredMedioURL);
		URL paredTechoURL = getClass().getClassLoader().getResource(paredTechoDireccion);
		paredTecho = ImageIO.read(paredTechoURL);
	}
}