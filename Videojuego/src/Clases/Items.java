package Clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Items extends ObjetoJuego {
	private int cantidadPuntos;
	private boolean capturado=false;

	public Items(int x, int y, String nombreImagen, int velocidad, int cantidadPuntos) {
		super(x, y, nombreImagen, velocidad);
		this.cantidadPuntos = cantidadPuntos;
		this.ancho = (int)Juego.imagenes.get("cofre1").getWidth();
		this.alto = (int)Juego.imagenes.get("cofre1").getHeight();
	}
	
	public int getCantidadPuntos() {
		return cantidadPuntos;
	}

	public void setCantidadPuntos(int cantidadPuntos) {
		this.cantidadPuntos = cantidadPuntos;
	}

	public boolean isCapturado() {
		return capturado;
	}

	public void setCapturado(boolean capturado) {
		this.capturado = capturado;
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen), this.x, this.y);
		//graficos.setStroke(Color.RED);
		//graficos.strokeRect(x, y, ancho, alto);
	}
	

	@Override
	public void mover() {
		
	}
	
	public Rectangle obtenerRectangulo() {
		return new Rectangle(x, y, ancho, alto);
	}

}
