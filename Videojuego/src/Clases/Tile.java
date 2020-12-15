package Clases;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;

public class Tile extends ObjetoJuego{
	private int xImagen;
	private int yImagen;
	private int tipoTile;
	
	public Tile(int tipoTile, int x, int y, String nombreImagen, int velocidad, int ancho, int alto) {
		super(x, y, nombreImagen, velocidad);
		this.ancho = ancho;
		this.alto = alto;
		
		switch(tipoTile) {
		case 1:
			this.xImagen = 123;
			this.yImagen = 124;
			break;
		}
	}

	public int getxImagen() {
		return xImagen;
	}

	public void setxImagen(int xImagen) {
		this.xImagen = xImagen;
	}

	public int getyImagen() {
		return yImagen;
	}

	public void setyImagen(int yImagen) {
		this.yImagen = yImagen;
	}

	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen), xImagen, yImagen, ancho, alto, x, y, ancho, alto); 
	}

	@Override
	public void mover() {
	}
	
	
}
