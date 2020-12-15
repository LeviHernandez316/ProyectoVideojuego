package Clases;

import java.util.ArrayList;
import java.util.HashMap;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado extends ObjetoJuego{
	private int vidas;
	private HashMap<String, Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion = 1;
	private int xpantalla = 0;
	
	

	public JugadorAnimado(int x, int y, String nombreImagen, int velocidad, int vidas, String animacionActual) {
		super(x, y, nombreImagen, velocidad);
		this.vidas = vidas;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}
	
	public String getAnimacionActual() {
		return animacionActual;
	}

	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}
	
	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	
	public int getXpantalla() {
		return xpantalla;
	}

	public void setXpantalla(int xpantalla) {
		this.xpantalla = xpantalla;
	}
	
	public void inicializarAnimaciones() {
		Rectangle coordenadasCorrer[] = {
				new Rectangle(500, 446, 88, 101),
				new Rectangle(591, 450, 106, 101),
				new Rectangle(705, 450, 89, 105),
				new Rectangle(796, 450, 89, 107),
				new Rectangle(885, 460, 96, 101)
		};
		
		Animacion animacionCorrer = new Animacion(0.1,coordenadasCorrer);
		animaciones.put("correr", animacionCorrer);
		
		Rectangle coordenadasDescanso [] = {
				new Rectangle(140, 5, 99, 111),
				new Rectangle(245, 10, 101, 106),
				new Rectangle(350, 10, 100, 106)
		};
		Animacion animacionDescanso = new Animacion(0.1,coordenadasDescanso);
		animaciones.put("descanso", animacionDescanso);
		
	}
	
	public void calcularFrame(double t) {
		Rectangle coordenadas = animaciones.get(animacionActual).calcularFrameActual(t);
		this.xImagen = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.anchoImagen = (int)coordenadas.getWidth();
		this.altoImagen = (int)coordenadas.getHeight();
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(x + (anchoImagen*xpantalla), y, direccion*anchoImagen, altoImagen);
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen, anchoImagen, altoImagen, x + (anchoImagen*xpantalla)/2, y, (direccion*anchoImagen)/2, (altoImagen)/2);
		//graficos.setStroke(Color.RED);
		//graficos.strokeRect(x, y, anchoImagen/2, altoImagen/2);
	}
	
	@Override
	public void mover() {
		//if (x>=700) {
		//	x=-80;
		//}
		if (Juego.derecha) {
			animacionActual = "correr";
			//x += velocidad;
		}else if (Juego.izquierda) {
			animacionActual = "correr";
			//x -= velocidad;
		}else if (Juego.arriba) {
			animacionActual = "correr";
			//y -= velocidad;
		}else if (Juego.abajo) {
			animacionActual = "correr";
			//y += velocidad;
		}else {
			animacionActual = "descanso";
		}
		
		
		if (Juego.derecha) {
			if(x>=1368||((x>279&x<405)&y>157)||((x<236&x>165&y>250)&y<295)||((x<525&x>450&y>93)&y<256)
				||(x>621&x<819&y<320&y>93)||(x>535&x<813&y>320&y<460)||(x>535&x<813&y>320&y<460)
				||(x>948&x<1107&y<463&y>235)||(x>1116&x<1369&y<238&y>190)||(x>1335&x<1369&y<191&y>129)) {
				x += 0;
			}else {
				x += velocidad;
			}
			System.out.println("x: "+x);
		}
		if (Juego.izquierda) {
			if((x<=35)||(x<492&y>325)||((x<408&x>282)&y>157)||(x>168&x<240&y<295&y>116)
				||(x>453&x<611&y<253&y>112)||(x>453&x<528&y<113&y>93)||(x>726&x<816&y<460&y>93)
				||(x>1107&x<1284&y<460&y>235)||(x>750&x<903&y<418&y>93)||(x>957&x<1068&y<205&y>93)) {
				x -= 0;
			}else {
				x -= velocidad;
			}
			System.out.println("x: "+x);
		}
		if (Juego.arriba) {			
			if(y<=94||(y>116&y<256&x>32&x<171)||(y>116&y<298&x>165&x<236)||(y>112&y<259&x>453&x<609)
				||(y>319&y<463&x>537&x<813)||(y>235&y<463&x>951&x<1281)||(y>93&y<421&x>812&x<900)
				||(y>235&y<421&x>1280&x<1369)||(y>93&y<208&x>899&x<1065)||(y>93&y<133&x>1064&x<1369)) {
				y -= 0;
			}else {
				y -= velocidad;
			}
			System.out.println("y: "+y);
		}
		if (Juego.abajo) {
			if((y >= 484)||(y>319&x<489)||(y>154&(x<405&x>282))||((x<237&x>32)&y>112&y<252)
				||(y>109&y<256&x<609&x>453)||(y>318&y<460&x<628&x>543)||(y>232&y<322&x<1369&x>951)
				||(y>187&y<322&x<1369&x>1116)) {
				y += 0;
			}else {
				y += velocidad;
			}
			System.out.println("y: "+y);
		}
	}
	
	public void verificarColisionesItem(Items item) {
		if(Juego.espacio &this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
			//System.out.println("Estan colisionando");
			item.setCapturado(true);
		}
	}
}
