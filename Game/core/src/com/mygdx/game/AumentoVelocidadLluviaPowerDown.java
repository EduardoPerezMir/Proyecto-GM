package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AumentoVelocidadLluviaPowerDown implements PowerUp {
    private Texture textura;

    // Constructor para cargar la textura del power-up
    public AumentoVelocidadLluviaPowerDown() {
        this.textura = new Texture(Gdx.files.internal("velocidadlluvia.png"));
    }

    // Método para aplicar el power-up y aumentar la velocidad de la lluvia
    @Override
    public void aplicarPowerUp(Tarro tarro, Lluvia lluvia) {
        lluvia.setVelocidad(2);
    }

    // Método para quitar el power-up y restaurar la velocidad de la lluvia
    public void quitarPowerUp(Tarro tarro, Lluvia lluvia) {
        lluvia.setVelocidad(0.5);
    }

    // Método para dibujar el power-up en pantalla
    public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }

    // Método para liberar los recursos de la textura cuando ya no se necesite
    public void destruir() {
        textura.dispose();
    }
}
