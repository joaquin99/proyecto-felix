package juegoFelix;

import javax.swing.table.DefaultTableModel;

public class Controller {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DefaultTableModel tabla = new DefaultTableModel(
				new Object[][] {
					{"Posición","Nombre","Puntaje"},
					{"1º", null, null},
					{"2º", null, null},
					{"3º", null, null},
					{"4º", null, null},
					{"5º", null, null},
				},
				new String[] {
					"New column", "New column", "New column"}
			);
		
		VistaMenu menu = new VistaMenu();
		menu.mostrar();
	}

}
