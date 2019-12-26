package juegoFelix;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class TablaNoEncontradaException extends ClassNotFoundException {

	private static final String rutaTabla = "tablaRanking.dat";
	
	public void resolver() {
		
		try{
		File file = new File(rutaTabla);
        file.createNewFile();
		ObjectOutputStream guardaTabla = new ObjectOutputStream(new FileOutputStream(rutaTabla));
		guardaTabla.writeObject(new ModeloTablaRanking());
		guardaTabla.close();
		}
		catch(IOException e) {
			JOptionPane.showMessageDialog(null,"Se ha producido un error al cargar la tabla");
		}
	}
}
