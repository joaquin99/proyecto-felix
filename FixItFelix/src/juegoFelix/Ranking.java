package juegoFelix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Ranking {
	
	private final int TAMANIO_RANKING = 5;
	private ArrayList<Integer> arregloPuntajes;
	private String rutaTabla = new String("tablaRanking.dat");
	private ModeloTablaRanking tabla;
	
	
	public Ranking(){
		this.arregloPuntajes = new ArrayList<Integer>();
		
		try {
			cargarTabla();
		} catch (TablaNoEncontradaException e) {
			// AgregaUnaTabla y se inicializa
			e.resolver();
			this.tabla = new ModeloTablaRanking();
		} catch (ArchivoTablaNoEncontradoException e) {
			// Agrega una this.tabla, se inicializa y crea el archivo
			e.resolver();
			this.tabla = new ModeloTablaRanking();
		}finally {
		}
		
		for(int i = 1;i < this.tabla.getRowCount();i++) {
			System.out.println( (String) this.tabla.getValueAt(i, 1));
			if(this.tabla.getValueAt(i, 2) != null)
				arregloPuntajes.add(Integer.parseInt( (String) this.tabla.getValueAt(i,2)));		
		}
	}
	
	
	//Recupera la this.tabla del archivo, ingresa un nuevo jugador si 
	public void actualizarRanking(String nombre,int puntaje, DatosEstadistica datos) {
		
		boolean agrego = false;
		for(int i = 0;((agrego == false) && (i < arregloPuntajes.size()));i++){
			System.out.println("Està chequeando si merece entrar");
			if(arregloPuntajes.get(i) < puntaje){
				System.out.println("Merece entrar");
				int ultimo;
				if(arregloPuntajes.size() == TAMANIO_RANKING)
					ultimo = TAMANIO_RANKING-1;
				else
					ultimo = arregloPuntajes.size();
				
				for(int k = ultimo; k > i; k--){	
					this.tabla.setValueAt(this.tabla.getValueAt(k, 1), k+1, 1);
					this.tabla.setValueAt(this.tabla.getValueAt(k, 2), k+1, 2);
				}
				
				this.tabla.setValueAt(nombre, i+1, 1);
				this.tabla.setValueAt(String.valueOf(puntaje), i+1, 2);
				datos.contadorJugadores++;
				agrego = true;
				arregloPuntajes.add(i,puntaje);
			}
		}
		
		if(arregloPuntajes.size() == 0){
			System.out.println("Se va a cargar la this.tabla");
			this.tabla.setValueAt(nombre, 1, 1);
			this.tabla.setValueAt(String.valueOf(puntaje), 1, 2);
			datos.contadorJugadores++;
			arregloPuntajes.add(0,puntaje);
		}
		else if(agrego == false && arregloPuntajes.size() < TAMANIO_RANKING){
			this.tabla.setValueAt(nombre, arregloPuntajes.size()+1, 1);
			this.tabla.setValueAt(String.valueOf(puntaje), arregloPuntajes.size()+1, 2);
			datos.contadorJugadores++;
			arregloPuntajes.add(0,puntaje);
		
		}
		
		if(arregloPuntajes.size() > TAMANIO_RANKING)
			arregloPuntajes.remove(TAMANIO_RANKING);
		
		try {
		//Carga la tabla actualizada
		ObjectOutputStream guardaTabla = new ObjectOutputStream(new FileOutputStream(rutaTabla)); 
		guardaTabla.writeObject(this.tabla);
		guardaTabla.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			ArchivoTablaNoEncontradoException noArchivo = new ArchivoTablaNoEncontradoException();
			noArchivo.resolver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Se ha producido un error al actualizar tabla");
		}
				
	}
	
		//Retorna la this.tabla
	public ModeloTablaRanking getTabla() {

			return this.tabla;
	}
		
	public void cargarTabla() throws TablaNoEncontradaException,ArchivoTablaNoEncontradoException{
		
		System.out.println("Se va a cargar la this.tabla");
		ObjectInputStream leeTabla;
		try {
			leeTabla = new ObjectInputStream(new FileInputStream(rutaTabla));
			this.tabla = (ModeloTablaRanking) leeTabla.readObject();//
			System.out.println(this.tabla.getValueAt(0,0));
			leeTabla.close();
			System.out.println("Se va a cargar la this.tabla");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			ArchivoTablaNoEncontradoException noArchivo = new ArchivoTablaNoEncontradoException();
			throw noArchivo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			TablaNoEncontradaException noTabla = new TablaNoEncontradaException();
			throw noTabla;
		}
		

	}
	
}	