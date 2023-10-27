package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AumentoVelocidadPowerUp implements PowerUp {
	private Texture textura;
	
	public AumentoVelocidadPowerUp (Texture textura) {
        this.textura = textura;
    } 
	
	@Override
	public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setVelocidad(1.5);
	}

	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setVelocidad(0.6666666);
	}
	
	public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }
}