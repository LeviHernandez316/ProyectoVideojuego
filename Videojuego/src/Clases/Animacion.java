package Clases;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;

public class Animacion {
	private double duracion;
	private Rectangle coordenada[];
	
	public Animacion(double duracion, Rectangle coordenada[]) {
		super();
		this.duracion = duracion;
		this.coordenada = coordenada;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Rectangle[] getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Rectangle[] coordenada) {
		this.coordenada = coordenada;
	}

	public Rectangle calcularFrameActual(double t) {
		int cantidadFrames = this.coordenada.length;
		int indiceFrameActual = (int)(t%(cantidadFrames*duracion)/duracion);
		return coordenada[indiceFrameActual];
	}
	
	
}
