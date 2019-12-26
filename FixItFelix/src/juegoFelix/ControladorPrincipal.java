package juegoFelix;

public class ControladorPrincipal{

	private Jugar juego;
	private VistaMenu menu;
	
	public ControladorPrincipal(VistaMenu menu) {
		this.menu = menu;
	}
	
	public void iniciarJuego() {
		menu.ocultar();
		juego = new Jugar(this,menu.getNivel());
		
	}
	
	public void volverAlMenu() {
		menu.mostrar();
		
	}
	
	protected static void terminarApp(){
		System.out.println("Acaba de cerrar la aplicacion");
		System.exit(0);
	}

}
/*
public ControladorPrincipal(VistaMenu menu){
	
	TimerTask transicionModo = new TimerTask(){
		
		public void run(){
			if(menu.estaOculta()){
				NuevoJugar juego = new NuevoJugar();
			}
			else if(juego != null && !juego.getEnJuego()){
				menu.mostrar();
				juego = null;
			}
			else if(juego == null && menu.estaOculta())
				NuevoPrincipal.terminarApp();
		}
	};
	
	this.schedule(transicionModo,0,1000);
}
*/