package principal;

import javax.swing.JFrame;

import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import controladores.ControladorPrincipal;

import javax.swing.JComboBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.Image;

import javax.imageio.ImageIO;



public class VistaMenu{

	private ControladorPrincipal controladorApp;
	
    private final int dimXRanking=480;
    private final int dimYRanking=400;
    
    private final int dimXMenu=600;
    private final int dimYMenu=600;
    
    private final int dimXConfiguracion = 500;
    private final int dimYConfiguracion = 500;
    
    private final int dimXComoJugar = 600;
    private final int dimYComoJugar = 400;
    
    private JFrame frame;
    private boolean estaEnMenu = true;
    
    
    private JButton btnReglasDelJuego;
    private JButton btnRanking;
    private JButton btnVolverAlMenu;
    private JButton btnEstadistica;
    private JButton btnConfiguracion;
    
    //Es necesario actualizar el modelo de la tabla (archivo) cada vez que se termina el juego
    private JPanel panelMenu;
    private JPanel panelRanking;
    private JTable tablaRanking;
    private Ranking accederModeloTabla = new Ranking();
    

    //Muestra informacion de como jugar
    private JPanel panelComoJugar;
    private JLabel titulo;
    private JLabel instrucciones;

    
    //Permite configurar el nivel inicial del juego
    private JPanel panelConfiguracion;
    private int nivel = 1;
    private JComboBox seleccionNivel;
    
    
    //Muestra informacion sobre: cantidad de veces que se ejecutola aplicacion, 
    private JPanel panelEstadistica;
    private int contadorJugar=0;
    private int contadorIniciado=0;
    private DatosEstadistica datos;
    private JLabel vecesQueSeJugo;
    private JLabel vecesIniciado;
    private JLabel cantJugadores;
    
    
    /**
     * Launch the application.
     */
    public void mostrar() {
        frame.setVisible(true);
        estaEnMenu = true;
        
    }
    
    public void ocultar(){
    	frame.setVisible(false);
    	estaEnMenu = false;
    }
    
    public boolean estaOculta(){
    	return !estaEnMenu;
    }
    
    public int getNivel(){
    	return nivel;
    }
    
    public void agregarControladorApp(ControladorPrincipal controladorApp) {
    	this.controladorApp = controladorApp;
    }

    /**
     * Create the application.
     */
    public VistaMenu()  {
        
        frame = new JFrame();
        frame.setTitle("Fix It Felix Jr.");
        frame.setBounds(100, 100, dimXMenu, dimYMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        iniciarPanelMenu();
        iniciarPanelRanking();
        iniciarPanelComoJugar();
        iniciarPanelEstadistica();
        iniciarPanelConfiguracion();
        frame.addWindowListener(new WindowAdapter(){

            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
            }

            public void windowClosing(WindowEvent e) {
                // TODO Auto-generated method stub
                /*
                try{
                    PrintWriter actualizar = new PrintWriter(new File("datosEstadistica.txt"));
                    actualizar.println(++contadorIniciado);
                actualizar.close();
                }catch(FileNotFoundException e0){
                    e0.printStackTrace();
                }
                */
                try{
                    ObjectOutputStream actualizar = new ObjectOutputStream(new FileOutputStream("datos.out"));
                    datos.contadorIniciado++;
                    actualizar.writeObject(datos);
                    actualizar.close();
                    estaEnMenu = false;
                    controladorApp.terminarApp();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
            }
                            
        });
        frame.add(panelMenu);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void iniciarPanelMenu() {
        
        final int dimXBoton = 120;
        final int dimYBoton = 30;
        
        panelMenu = new JPanel(){
        
            private String[] menuDirecciones={"imagenes/letras/slice30_30.png",
            "imagenes/letras/slice32_32.png",
            "imagenes/letras/slice34_34.png",
            "imagenes/letras/slice32_32.png",
            "imagenes/letras/slice39_39.png",
            "imagenes/letras/slice30_30.png",
            "imagenes/letras/slice33_33.png",
            "imagenes/letras/slice35_35.png",
            "imagenes/letras/slice37_37.png",
            "imagenes/letras/slice34_34.png"};
                
            private Image menu;
            public void paintComponent(Graphics g){ 
               try {
                int i = 0;
                for(String menuDireccion:menuDirecciones){
                System.out.println("Se esta graficando");
		URL menuURL = getClass().getClassLoader().getResource(menuDireccion);
		menu = ImageIO.read(menuURL);
		if(i <= 4)
		  if(i >= 3)
		      g.drawImage(menu, 50*i+50+120, 100, null);
		  
		  else
		       g.drawImage(menu, 50*i+120, 100, null);
		else
		   g.drawImage(menu, 50*(i-5)+120, 150, null);
		 
		
		System.out.println("Graficado");
		i++;
		}
		
		
		                } 
                catch (IOException e) {
	       System.out.println("Problemas leyendo la imagen");
	       System.out.println("Motivo: " + e.getLocalizedMessage());
	       } 
              
            }
            
        };
        frame.setBackground(Color.BLACK);
        panelMenu.setLayout(null);
       
        
        btnReglasDelJuego = new JButton("Reglas del Juego");
        btnReglasDelJuego.setBounds((int) frame.getBounds().getWidth()/5, 300, dimXBoton, dimYBoton);
        btnReglasDelJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Carga el panelMenu del top 5
                frame.remove(panelMenu);
                frame.setBounds(100,100,dimXComoJugar,dimYComoJugar);
                frame.add(panelComoJugar,BorderLayout.CENTER);
                frame.repaint();
                
            }
        });
        panelMenu.add(btnReglasDelJuego);
        
        
        JButton btnJugar = new JButton("Quiero Jugar!");
        btnJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //contadorJugar++;
                datos.contadorJugado++;
                vecesQueSeJugo.setText("Veces que se eligio jugar: "+datos.contadorJugado);
                
                //Pasa al juego
                controladorApp.iniciarJuego();
                ocultar();
                
            }
        });
        btnJugar.setBounds((int)( frame.getBounds().getWidth()/5 +130), 300, dimXBoton, dimYBoton);
        panelMenu.add(btnJugar);
        
        btnRanking = new JButton("TOP 5");
        btnRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Carga el panelMenu del top 5
                frame.remove(panelMenu);
                frame.setBounds(100,100,dimXRanking,dimYRanking);
                frame.add(panelRanking,BorderLayout.CENTER);
                frame.repaint();
            }
        });
        btnRanking.setBounds((int) (frame.getBounds().getWidth()/5 + 260), 300, dimXBoton, dimYBoton);
        panelMenu.add(btnRanking);
        
        btnEstadistica = new JButton("Estadistica");
        btnEstadistica.setBounds((int) frame.getBounds().getWidth()/5, 400, dimXBoton, dimYBoton);
        btnEstadistica.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Carga el panelMenu del top 5
            	actualizarEstadistica();
                frame.remove(panelMenu);
                frame.setBounds(100,100,dimXRanking,dimYRanking);
                frame.add(panelEstadistica,BorderLayout.CENTER);
                frame.repaint();
            }
        });
        panelMenu.add(btnEstadistica);
        
        btnConfiguracion = new JButton("Configuracion");
        btnConfiguracion.setBounds((int) frame.getBounds().getWidth()/5 + 130, 400, dimXBoton, dimYBoton);
        btnConfiguracion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Carga el panel de configuracion
                frame.remove(panelMenu);
                frame.setBounds(100,100,dimXConfiguracion,dimYConfiguracion);
                frame.add(panelConfiguracion,BorderLayout.CENTER);
                frame.repaint();
            }
        });
        panelMenu.add(btnConfiguracion);
    }
    
    private void iniciarPanelRanking() {
        panelRanking = new JPanel();
        panelRanking.setLayout(new BorderLayout());
        tablaRanking = new JTable();
        panelRanking.add(tablaRanking,BorderLayout.CENTER);
        tablaRanking.setBounds(0, 0, 434, 194);
        
        tablaRanking.setModel(accederModeloTabla.getTabla());
        
        //tablaRanking.setModel(new DefaultTableModel());
        
        
        btnVolverAlMenu = new JButton("Volver al Menu");
        btnVolverAlMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.remove(panelRanking);
                frame.setBounds(100, 100, dimXMenu, dimYMenu);
                frame.add(panelMenu,BorderLayout.CENTER);
                frame.repaint();
            }
        });
        btnVolverAlMenu.setBounds(10, 210, 116, 23);
        panelRanking.add(btnVolverAlMenu,BorderLayout.SOUTH);
    }
    
    private void iniciarPanelComoJugar() {
        panelComoJugar = new JPanel() {
        	
        	private String instrucciones = "imagenes/Instrucciones.png";
        	
        	public void dibujarInstrucciones(Graphics g) {
        		
        		try {
        			URL instruccionesURL = getClass().getClassLoader().getResource(instrucciones);
        			Image imagenInstrucciones = ImageIO.read(instruccionesURL);
        			g.drawImage(imagenInstrucciones, 0, 0, null);
        		}
        		catch(Exception e) {
        			e.printStackTrace();
        		}
        		
        		
        	} 
        	
        	@Override
        	protected void paintComponent(Graphics arg0) {
        		// TODO Auto-generated method stub
        		super.paintComponent(arg0);
        		dibujarInstrucciones(arg0);
        	}
        };
        panelComoJugar.setLayout(new BorderLayout());
        
        titulo = new JLabel("Fix It Felix");
        panelComoJugar.add(titulo,BorderLayout.NORTH);
        titulo.setFont(new Font(titulo.getFont().getName(),titulo.getFont().getStyle(),titulo.getFont().getSize()+10));
        
        btnVolverAlMenu = new JButton("Volver al Menu");
        btnVolverAlMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.remove(panelComoJugar);
                frame.setBounds(100, 100, dimXMenu, dimYMenu);
                frame.add(panelMenu,BorderLayout.CENTER);
                frame.repaint();
            }
        });
        btnVolverAlMenu.setBounds(10, 210, 116, 23);
        panelComoJugar.add(btnVolverAlMenu,BorderLayout.SOUTH);
    }
    
    private void iniciarPanelEstadistica(){
        
        
        try{
        	FileInputStream archivoDatosEstadistica = new FileInputStream("datos.out"); 
            ObjectInputStream archivoEstadistica = new ObjectInputStream(archivoDatosEstadistica);
            datos = (DatosEstadistica) archivoEstadistica.readObject();
            archivoEstadistica.close();
        }catch(FileNotFoundException e){
            
            try{
            	/*
                System.out.println("Se van a leer los datos del archivo");
                ObjectOutputStream actualizar = new ObjectOutputStream(new FileOutputStream("datos.out"));
                actualizar.writeObject(new DatosEstadistica());
                */
                datos = new DatosEstadistica();
                File nuevoArchivo = new File("datos.out");
                nuevoArchivo.createNewFile();
            }
            catch(IOException e2){
                System.out.println("Ocurrio un error inesperado en la lectura/escritura");
            }
        }catch(ClassNotFoundException e){
        	datos = new DatosEstadistica();
            File nuevoArchivo = new File("datos.out");
            try{nuevoArchivo.createNewFile();}
            catch(IOException e1) {
            	e1.printStackTrace();
            }
        } catch (IOException e) {
        	datos = new DatosEstadistica();
            File nuevoArchivo = new File("datos.out");
            try{nuevoArchivo.createNewFile();}
            catch(IOException e1) {
            	e1.printStackTrace();
            }
        }
        
        
        panelEstadistica = new JPanel(){};
        panelEstadistica.setLayout(new BorderLayout());
        
        //vecesQueSeJugo = new JLabel("Veces que se eligio jugar: "+contadorJugar);
        vecesQueSeJugo = new JLabel("Veces que se eligio jugar: "+datos.contadorJugado);
        panelEstadistica.add(vecesQueSeJugo,BorderLayout.CENTER);
        
        btnVolverAlMenu = new JButton("Volver al Menu");
        btnVolverAlMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.remove(panelEstadistica);
                frame.setBounds(100, 100, dimXMenu, dimYMenu);
                frame.add(panelMenu,BorderLayout.CENTER);
                frame.repaint();
            }
        });
        btnVolverAlMenu.setBounds(10, 210, 116, 23);
        panelEstadistica.add(btnVolverAlMenu,BorderLayout.SOUTH);
        
        vecesIniciado = new JLabel("Veces que se inicio el juego: "+datos.contadorIniciado);
        panelEstadistica.add(vecesIniciado,BorderLayout.NORTH);
        
        cantJugadores = new JLabel("Jugadores que entraron en el ranking: "+datos.contadorJugadores);
        panelEstadistica.add(cantJugadores,BorderLayout.EAST);
        
    }
    
    private void iniciarPanelConfiguracion() {

        
        panelConfiguracion = new JPanel(){
          
        //Pegar grafico del modulo I
            
        };
        panelConfiguracion.setLayout(null);
        
        String[] opciones = {"Nivel 1","Nivel 2","Nivel 3","Nivel 4","Nivel 5","Nivel 6","Nivel 7","Nivel 8","Nivel 9","Nivel 10"};
        seleccionNivel = new JComboBox<String>(opciones);
        
        seleccionNivel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Carga el panelMenu del top 5
                nivel = seleccionNivel.getSelectedIndex()+1;
                System.out.println(nivel);
            }
        });
        seleccionNivel.setBounds(150,150,200, 100);
        panelConfiguracion.add(seleccionNivel,BorderLayout.CENTER);
        
        
        btnVolverAlMenu = new JButton("Volver al Menu");
        btnVolverAlMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.remove(panelConfiguracion);
                frame.setBounds(100, 100, dimXMenu, dimYMenu);
                frame.add(panelMenu,BorderLayout.CENTER);
                frame.repaint();
            }
        });
        btnVolverAlMenu.setBounds(150, 400, 200, 25);
        panelConfiguracion.add(btnVolverAlMenu,BorderLayout.SOUTH);
        
        
        frame.add(panelConfiguracion);
    
    }
    
    public void actualizarRanking(Jugador nuevoJugador) {
    	accederModeloTabla.actualizarRanking(nuevoJugador.getNombre(), nuevoJugador.getPuntos(), datos);
    }
    
    private void actualizarEstadistica() {
    	if(cantJugadores != null && vecesIniciado != null) {
    		cantJugadores.setText("Jugadores que entraron en el ranking: "+datos.contadorJugadores);
    		vecesIniciado.setText("Veces que se inicio el juego: "+datos.contadorIniciado);
    	}
    }
}