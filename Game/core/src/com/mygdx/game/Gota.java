package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

public abstract class Gota {
	// Atributos que serán heredados por las clases hijas
	private Texture textura;
	private int puntaje;
	private int velocidadCaida;
	private Rectangle forma;
	
	public Gota(int velocidadCaida) {
		this.velocidadCaida = velocidadCaida; // Atributo pre-definido, todas las gotas tendrán esta velocidad de caída.
		textura = null;
		puntaje = 0;
		forma = new Rectangle(); // Atributo pre-definido, todas las gotas deberán tener estas dimensiones y posición
		forma.x = MathUtils.random(0, 800 - 42);
		forma.y = 480;
		forma.width = 32;
		forma.height = 48;
	}
	
	//Getters & Setters
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
	
	public float getFormaPosX() {
		return forma.x;
	}
	
	public void setFormaPosX(float posX) {
		this.forma.x = posX;
	}
	
	public float getFormaPosY() {
		return forma.y;
	}
	
	public void setFormaPosY(float posY) {
		this.forma.y = posY;
	}
	
	// Método Abstracto
	public abstract int accionColisionTarro(Tarro tarro);
	
	// Verifica si una Gota en particular colisionó con el tarro
	public int verificarColisionTarro(Tarro tarro, float pond) {
		if(forma.overlaps(tarro.getArea())) {
			return accionColisionTarro(tarro);
		}
		
		forma.y = forma.y - velocidadCaida * pond;
		
		if (forma.y + forma.height < 0)
			return 2;
		
	    return 0;
	}
	
	// La Gota se dibuja a sí misma.
	public void dibujarGota(SpriteBatch batch) {
		  batch.draw(textura, forma.x, forma.y);
	}
	
	// Se libera memoria.
	public void destruir() {
		textura.dispose();
	}	
}
