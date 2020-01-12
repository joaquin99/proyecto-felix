package juegoFelix;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edificio.Ventana;
import entidades.Direccion;
import entidades.Felix;
import graficos.DibujarNivel;

public class ControladorFelix implements KeyListener{
	
	private Ventana[][] matrizVentanas;
	private int movido = 0;
	
	public ControladorFelix() {
	}
	
	public ControladorFelix(Ventana[][] matrizVentanas) {
		this.setMatrizVentanas(matrizVentanas);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int key = arg0.getKeyCode();
		System.out.println("Se intenta realizar un movimiento hacia ");
		boolean mover = true;
		switch(key) {
		
		case KeyEvent.VK_LEFT: Felix.getInstance().setDireccion(Direccion.IZQUIERDA); break;
		
		case KeyEvent.VK_RIGHT:Felix.getInstance().setDireccion(Direccion.DERECHA); break;
		
		case KeyEvent.VK_UP: Felix.getInstance().setDireccion(Direccion.ARRIBA); break;
		
		case KeyEvent.VK_DOWN: Felix.getInstance().setDireccion(Direccion.ABAJO); break;
				
		case KeyEvent.VK_SPACE: 
			mover = false;
			Felix.getInstance().arreglarVentana(matrizVentanas[Felix.getInstance().getPos().getPosX()][Felix.getInstance().getPos().getPosY()]);
			for(int i = 0;i < matrizVentanas.length;i++) {
				for(int j = 0;j < matrizVentanas[i].length;j++) {
					if(matrizVentanas[i][j].hayTarta())
						System.out.print("T ");
					else if(matrizVentanas[i][j].getEstaArreglado())
						System.out.print("O ");
					else
						System.out.print("X ");
				}
				System.out.println();
						
			}
			break;
		
		case KeyEvent.VK_G: mover = false;
			arreglarTodo();
		}
		if (mover == true) {
			System.out.println("Se movio");
			movido++;
			System.out.println("Se movio "+movido+" veces");
			Felix.getInstance().mover(matrizVentanas);
			Felix.getInstance().setDireccion(Direccion.ABAJO);
		}
		DibujarNivel.getInstance().repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
	public void setMatrizVentanas(Ventana[][] matrizVentanas) {
		this.matrizVentanas = matrizVentanas; 
	}
	
	
	//Arregla todas las ventanas automaticamente
	private void arreglarTodo() {
		int i,j;
		for(j = 0;j < matrizVentanas.length;j++)
			for(i = 0;i < matrizVentanas[j].length;i++) {
				while(! matrizVentanas[j][i].getEstaArreglado())
					Felix.getInstance().arreglarVentana(matrizVentanas[j][i]);
			}
	}
}
