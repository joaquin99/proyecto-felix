package juegoFelix;

import javax.swing.table.DefaultTableModel;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DefaultTableModel tabla = new DefaultTableModel(
				new Object[][] {
					{"Posici�n","Nombre","Puntaje"},
					{"1�", null, null},
					{"2�", null, null},
					{"3�", null, null},
					{"4�", null, null},
					{"5�", null, null},
				},
				new String[] {
					"New column", "New column", "New column"}
			);
		
		VistaMenu menu = new VistaMenu();
		menu.mostrar();
	}

}
