package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AumentoTamañoTarroPowerDown implements PowerUp {
	private Texture textura;
	
	public AumentoTamañoTarroPowerDown () {
        this.textura = new Texture(Gdx.files.internal("tamañotarro.png"));
    } 
	
	@Override
	public void aplicarPowerUp(Tarro tarro, Lluvia lluvia) {
		tarro.tamañoTarroGrande(true);
	}

	public void quitarPowerUp(Tarro tarro, Lluvia lluvia) {
		tarro.tamañoTarroGrande(false);
	}
	
	public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }
	
	public void destruir() {
		textura.dispose();;
	}
}
