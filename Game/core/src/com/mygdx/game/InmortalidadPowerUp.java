package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InmortalidadPowerUp implements PowerUp {
	private Texture textura;
	
	public InmortalidadPowerUp () {
        this.textura = new Texture(Gdx.files.internal("inmortabilidad.png"));
    }
	
	@Override
    public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setInmortal(true);
    }
	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		tarro.setInmortal(false);
    }
	
	public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }
	
	public void destruir() {
		textura.dispose();
	}

}