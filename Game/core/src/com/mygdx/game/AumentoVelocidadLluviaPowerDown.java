package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AumentoVelocidadLluviaPowerDown implements PowerUp {
	private Texture textura;
	
	public AumentoVelocidadLluviaPowerDown () {
        this.textura = new Texture(Gdx.files.internal("velocidadlluvia.png"));
    } 
	
	@Override
	public void aplicarPowerUp(Tarro tarro,Lluvia lluvia) {
		lluvia.setVelocidad(2);
	}

	public void quitarPowerUp(Tarro tarro,Lluvia lluvia) {
		lluvia.setVelocidad(0.5);
	}
	
	public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }
	
	public void destruir() {
		textura.dispose();
	}
}