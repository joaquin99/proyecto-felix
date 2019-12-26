package graficos;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImgVentana {
	private String ventanaDireccion = "imagenes/ventanas_y_panel/slice103_@.png";
	private String ventanaHojaIzquierdaDireccion = "imagenes/ventanas_y_panel/slice107_@.png";
	private String ventanaCerradaDireccion = "imagenes/ventanas_y_panel/slice105_@.png";
	private String puertaArregladaDireccion = "imagenes/semicirculares/slice600_@.png";
	private String puerta1Direccion = "imagenes/semicirculares/slice598_@.png";
	private String puerta2Direccion = "imagenes/semicirculares/slice596_@.png";
	private String puerta3Direccion = "imagenes/semicirculares/slice592_@.png";
	private String ventanaSemicircularArregladaDireccion = "imagenes/semicirculares/slice602_@.png";
	private String ventanaSemicircular1Direccion = "imagenes/semicirculares/slice603_@.png";
	private String ventanaSemicircular2Direccion = "imagenes/semicirculares/slice605_@.png";
	private String panelArregladoDireccion = "imagenes/ventanas_y_panel/slice02_02.png";
	private String panelSemirrotoDireccion = "imagenes/ventanas_y_panel/panel_semirroto.png";
	
	public Image ventana;
	public Image ventanaHojaIzquierda;
	
	public Image ventanaCerrada;
	public Image puertaArreglada;
	public Image puerta1;
	public Image puerta2;
	public Image puerta3;
	public Image ventanaSemicircularArreglada;
	public Image ventanaSemicircular1;
	public Image ventanaSemicircular2;
	public Image panelArreglado;
	public Image panelSemirroto;
	
	public ImgVentana() throws IOException {
		
		URL ventanaURL = getClass().getClassLoader().getResource(ventanaDireccion);
		ventana = ImageIO.read(ventanaURL);
		
		URL ventanaHojaIzquierdaURL = getClass().getClassLoader().getResource(ventanaHojaIzquierdaDireccion);
		ventanaHojaIzquierda = ImageIO.read(ventanaHojaIzquierdaURL);
		
		URL ventanaCerradaURL = getClass().getClassLoader().getResource(ventanaCerradaDireccion);
		ventanaCerrada = ImageIO.read(ventanaCerradaURL);
		
		URL puertaArregladaURL = getClass().getClassLoader().getResource(puertaArregladaDireccion);
		puertaArreglada = ImageIO.read(puertaArregladaURL);
		
		URL puerta1URL = getClass().getClassLoader().getResource(puerta1Direccion);
		puerta1 = ImageIO.read(puerta1URL);
		
		URL puerta2URL = getClass().getClassLoader().getResource(puerta2Direccion);
		puerta2 = ImageIO.read(puerta2URL);
		
		URL puerta3URL = getClass().getClassLoader().getResource(puerta3Direccion);
		puerta3 = ImageIO.read(puerta3URL);
		
		URL ventanaSemicircularArregladaURL = getClass().getClassLoader().getResource(ventanaSemicircularArregladaDireccion);
		ventanaSemicircularArreglada = ImageIO.read(ventanaSemicircularArregladaURL);
		
		URL ventanaSemicircular1URL = getClass().getClassLoader().getResource(ventanaSemicircular1Direccion);
		ventanaSemicircular1 = ImageIO.read(ventanaSemicircular1URL);
		
		URL ventanaSemicircular2URL = getClass().getClassLoader().getResource(ventanaSemicircular2Direccion);
		ventanaSemicircular2 = ImageIO.read(ventanaSemicircular2URL);
		
		URL panelArregladoURL = getClass().getClassLoader().getResource(panelArregladoDireccion);
		panelArreglado = ImageIO.read(panelArregladoURL);
		
		URL panelSemirrotoURL = getClass().getClassLoader().getResource(panelSemirrotoDireccion);
		panelSemirroto = ImageIO.read(panelSemirrotoURL);
		
		//this.panelSemirroto.getScaledInstance(30, 26, Image.SCALE_SMOOTH);
	}
}
