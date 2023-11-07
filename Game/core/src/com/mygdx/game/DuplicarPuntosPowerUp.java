package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DuplicarPuntosPowerUp implements PowerUp {
    private Texture textura; // Textura para el power-up

    // Constructor: inicializa la textura con la imagen del power-up
    public DuplicarPuntosPowerUp() {
        this.textura = new Texture(Gdx.files.internal("duplicarpuntos.png"));
    }

    // Aplica el power-up para duplicar los puntos del tarro
    @Override
    public void aplicarPowerUp(Tarro tarro, NivelDificultad lluvia) {
        tarro.setAumentoPuntos(true);
    }

    // Quita el efecto del power-up, restaurando los puntos normales del tarro
    public void quitarPowerUp(Tarro tarro, NivelDificultad lluvia) {
        tarro.setAumentoPuntos(false);
    }

    // Dibuja el power-up en pantalla en la posición (x, y)
    public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y);
    }

    // Libera los recursos al destruir el power-up
    public void destruir() {
        textura.dispose();
    }
}
