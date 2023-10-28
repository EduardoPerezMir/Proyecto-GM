package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DuplicarPuntosPowerUp implements PowerUp {
	private Texture textura;
	
	public DuplicarPuntosPowerUp () {
        this.textura = new Texture(Gdx.files.internal("duplicarpuntos.png"));
    } 
	
	@Override
	public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setAumentoPuntos(true);
	}

	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setAumentoPuntos(false);
	}
	
	public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }
	
	public void destruir() {
		textura.dispose();
	}
}