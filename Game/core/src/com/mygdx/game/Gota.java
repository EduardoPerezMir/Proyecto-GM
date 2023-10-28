package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.MathUtils;

public abstract class Gota {
	private Texture textura;
	private int puntaje;
	private int velocidadCaida;
	private Rectangle forma;
	
	public Gota() {
		velocidadCaida = 300;
		textura = null;
		puntaje = 0;
		forma = new Rectangle();
		forma.x = MathUtils.random(0, 800 - 42);
		forma.y = 480;
		forma.width = 42;
		forma.height = 64;
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
	
	public abstract int accionColisionTarro(Tarro tarro);
	
	public int verificarColisionTarro(Tarro tarro) {
		if(forma.overlaps(tarro.getArea())) {
			return accionColisionTarro(tarro);
		}
		
	    return 0;
	}	
	
	public void dibujarGota(SpriteBatch batch) {
		  batch.draw(textura, forma.x, forma.y);
	}
}
