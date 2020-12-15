	package Clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Fondo extends ObjetoJuego{
	private String nombreImagen2;
	
	public Fondo(int x, int y, String nombreImagen, String nombreImagen2, int velocidad) {
		super(x, y, nombreImagen, velocidad);
		this.nombreImagen2 = nombreImagen2;
		this.ancho = (int)Juego.imagenes.get("mansion").getWidth();
		this.alto = (int)Juego.imagenes.get("mansion").getHeight();
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(this.nombreImagen), this.x, this.y);
		//graficos.drawImage(Juego.imagenes.get(this.nombreImagen2), this.x, this.y - alto);
	}

	@Override
	public void mover() {
		if(Juego.derecha) {
			x-=velocidad;
		}
		if(Juego.izquierda) {
			x+=velocidad;
		}
		if(Juego.arriba) {
			y+=velocidad;
		}
		if(Juego.abajo) {
			y-=velocidad;
		}
	}

}
