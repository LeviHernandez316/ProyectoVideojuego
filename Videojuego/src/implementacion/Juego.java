package implementacion;

import java.util.ArrayList;
import java.util.HashMap;

import Clases.Fondo;
import Clases.Items;
import Clases.JugadorAnimado;
import Clases.JugadorAnimado2;
import Clases.JugadorAnimado3;
import Clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Juego extends Application{
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private int x = 0;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	private JugadorAnimado2 jugadorAnimado2;
	private JugadorAnimado3 jugadorAnimado3;
	private Fondo fondo;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean izquierda;
	public static boolean derecha;
	public static boolean espacio;
	
	public static boolean w;
	public static boolean s;
	public static boolean a;
	public static boolean d;
	public static boolean q;
	
	public static boolean i;
	public static boolean k;
	public static boolean j;
	public static boolean l;
	public static boolean u;
	
	
	public static HashMap<String, Image> imagenes;
	private Tile tile;
	private Items item;
	private Items item2;
	
	private ArrayList<Tile> tiles;
	
	private int tilemap[][] = {
			{0, 0, 0, 0, 0, 0, 0, 0,},
			{0, 0, 0, 0, 0, 0, 0, 0,},
			{0, 0, 0, 0, 0, 0, 0, 0,},
			{0, 0, 0, 1, 0, 0, 0, 0,}
	};
	
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage ventana) throws Exception {
		inicializarComponentes();
		gestionEventos();
		
		ventana.setScene(escena);
		ventana.setTitle("Super Juego");
		ventana.show();
		cicloJuego();
	}
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer () {

			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicial)/1000000000.0;
				//System.out.println(t);
				actualizarEstado(t);
				pintar();
			}
			
		};
		
		animationTimer.start();	
	}
	
	public void actualizarEstado(double t) {
		//jugador.mover();
		jugadorAnimado.verificarColisionesItem(item);
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover();
		
		jugadorAnimado2.verificarColisionesItem(item);
		jugadorAnimado2.calcularFrame(t);
		jugadorAnimado2.mover();
		
		jugadorAnimado3.verificarColisionesItem(item);
		jugadorAnimado3.calcularFrame(t);
		jugadorAnimado3.mover();
		//fondo.mover();
	}
	
	public void inicializarComponentes() {
		imagenes = new HashMap<String,Image>();
		cargarImagenes();
		//jugador = new Jugador(300, 184, "wolverine", 3, 0);
		jugadorAnimado = new JugadorAnimado(489, 484, "wolverine2", 3, 0, "descanso");
		jugadorAnimado2 = new JugadorAnimado2(1368, 460, "cyclop", 3, 0, "descanso");
		jugadorAnimado3 = new JugadorAnimado3(114, 301, "magneto", 3, 0, "descanso");
		fondo = new Fondo(0, 0, "mansion", "mansion2", 5);
		inicializarTiles();
		item = new Items(834, 446, "cofre1", 0, 1);
		item2 = new Items(834, 446, "cofre2", 0, 1);
		
		root = new Group ();
		escena = new Scene(root, 1500, 579);
		lienzo = new Canvas(1500, 579);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();
	}
	
	public void inicializarTiles() {
		tiles = new ArrayList<Tile>();
		for(int i=0; i<tilemap.length; i++) {
			for(int j=0; j<tilemap[i].length; j++) {
				if (tilemap[i][j]!=0) {
					this.tiles.add(new Tile(tilemap[i][j], (j*42) - 2, i*42,"partes", 0, 42, 42));
				}
			}
		}
	}
	public void cargarImagenes() {
		imagenes.put("wolverine", new Image("imagen4.png"));
		imagenes.put("mansion", new Image("mapafinal3.png"));
		imagenes.put("mansion2", new Image("tilemap2.png"));
		imagenes.put("partes", new Image("mapafinal3.png"));
		imagenes.put("wolverine2", new Image("wolverine2.png"));
		imagenes.put("cofre1", new Image("cofre1.png"));
		imagenes.put("cofre2", new Image("cofre2.png"));
		imagenes.put("cyclop", new Image("cyclop2.png"));
		imagenes.put("magneto", new Image("magneto2.png"));
		
	}
	
	public void pintar() {
		//graficos.fillRect(0, 0, 100, 100);
		fondo.pintar(graficos);
		if (item.isCapturado()) {
			item2.pintar(graficos);
		}else {
			item.pintar(graficos);
		}
		jugadorAnimado.pintar(graficos);
		jugadorAnimado2.pintar(graficos);
		jugadorAnimado3.pintar(graficos);
		//graficos.drawImage(imagenes.get("partes"), 616, 0, 90, 90, 516, 0, 90, 90);
		for(int i=0; i<tiles.size(); i++) {
			tiles.get(i).pintar(graficos);
		}
		graficos.fillText("vidas: "+jugadorAnimado.getVidas(), 20, 20);
		//jugador.pintar(graficos);
		
		
		
	}
	 
	public void gestionEventos() {
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()) {
				case "RIGHT":
					derecha = true;
					jugadorAnimado.setDireccion(1);
					jugadorAnimado.setXpantalla(0);
					break;
				case "LEFT":
					izquierda = true;
					jugadorAnimado.setDireccion(-1);
					jugadorAnimado.setXpantalla(1);
					break;
				case "UP":
					arriba = true;
					break;
				case "DOWN":
					abajo = true;
					break;
				case "SPACE":
					espacio = true;
					break;
				case "D":
					d = true;
					jugadorAnimado2.setDireccion(1);
					jugadorAnimado2.setXpantalla(0);
					break;
				case "A":
					a = true;
					jugadorAnimado2.setDireccion(-1);
					jugadorAnimado2.setXpantalla(1);
					break;
				case "W":
					w = true;
					break;
				case "S":
					s = true;
					break;
				case "Q":
					q = true;
					break;
				case "L":
					l = true;
					jugadorAnimado3.setDireccion(1);
					jugadorAnimado3.setXpantalla(0);
					break;
				case "J":
					j = true;
					jugadorAnimado3.setDireccion(-1);
					jugadorAnimado3.setXpantalla(1);
					break;
				case "I":
					i = true;
					break;
				case "K":
					k = true;
					break;
				case "U":
					u = true;
					break;
				}
			}
			
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()) {
				case "RIGHT":
					derecha = false;
					break;
				case "LEFT":
					izquierda = false;
					break;
				case "UP":
					arriba = false;
					break;
				case "DOWN":
					abajo = false;
					break;
				case "SPACE":
					espacio = false;
					break;
				case "D":
					d = false;
					break;
				case "A":
					a = false;
					break;
				case "W":
					w = false;
					break;
				case "S":
					s = false;
					break;
				case "Q":
					q = false;
					break;
				case "L":
					l = false;
					break;
				case "J":
					j = false;
					break;
				case "I":
					i = false;
					break;
				case "K":
					k = false;
					break;
				case "U":
					u = false;
					break;
				}
			}
			
		});
	}

}
