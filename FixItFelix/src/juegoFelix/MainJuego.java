package juegoFelix;


public class MainJuego {

	private static ControladorPrincipal controladorApp;
	
	public static void main(String[] args){
		
		
		VistaMenu menu = new VistaMenu();
		controladorApp = new ControladorPrincipal(menu);
		menu.agregarControladorApp(controladorApp);
		menu.mostrar();
	}
	
}
