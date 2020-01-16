package principal;

import java.io.Serializable;

import javax.swing.table.DefaultTableModel;;

public class ModeloTablaRanking  extends DefaultTableModel implements Serializable{

	private static final long serialVersionUID = 6L;

	public ModeloTablaRanking() {
		
		super(
				new Object[][] {
					{"Posicion","Nombre","Puntaje"},
					{"1°", null, null},
					{"2°", null, null},
					{"3°", null, null},
					{"4°", null, null},
					{"5°", null, null},
				},
				new String[] {
					"Posicion", "Nombre", "Puntaje"}
			);
		setRowCount(6);
		setColumnCount(3);
		
	}
	
	public boolean isCellEditable(int row, int column){
		return false;
	}
	
}