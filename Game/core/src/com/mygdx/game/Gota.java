package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

public abstract class Gota {
	private int tipoGota;
	private Texture textura;
	private int puntaje;
	private int velocidadCaida;
	private Rectangle forma;
	
	public Gota(int tipoGota, Texture textura, int puntaje, int velocidadCaida) {
		this.tipoGota = tipoGota;
		this.textura = textura;
		this.puntaje = puntaje;
		this.velocidadCaida = velocidadCaida;
		forma = new Rectangle();
		forma.x = MathUtils.random(0, 800 - 42);
		forma.y = 480;
		forma.width = 42;
		forma.height = 64; 
	}
	
	public int getTipoGota() {
		return tipoGota;
	}
	
	public void setTipoGota(int tipoGota) {
		this.tipoGota = tipoGota;
	}

	public Texture getTextura() {
		return textura;
	}

	public void setTextura(Texture textura) {
		this.textura = textura;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	public int getVelocidadCaida() {
		return velocidadCaida;
	}

	public void setVelocidadCaida(int velocidadCaida) {
		this.velocidadCaida = velocidadCaida;
	}

	public Rectangle getForma() {
		return forma;
	}

	public void setForma(Rectangle forma) {
		this.forma = forma;
	}

	public abstract boolean accionColisionTarro();
}
