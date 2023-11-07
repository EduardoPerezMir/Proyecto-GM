package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InmortalidadPowerUp implements PowerUp {
	private Texture textura; // Variable para almacenar la textura del power-up de inmortalidad
    
    // Constructor de la clase
    public InmortalidadPowerUp () {
        this.textura = new Texture(Gdx.files.internal("inmortabilidad.png")); // Carga la textura desde un archivo interno llamado inmortabilidad.png
    }
    
    // Método para aplicar el power-up de inmortalidad a un "Tarro" (posiblemente un jugador) y a la "Lluvia" (posiblemente un entorno)
    @Override
    public void aplicarPowerUp(Tarro tarro, NivelDificultad lluvia) {
        tarro.setInmortal(true); // Establece al tarro como inmortal
    }
    
    // Método para quitar el efecto del power-up de inmortalidad al "Tarro" y a la "Lluvia"
    public void quitarPowerUp(Tarro tarro, NivelDificultad lluvia) {
        tarro.setInmortal(false); // Quita la inmortalidad al tarro
    }
    
    // Método para dibujar la textura del power-up en una posición (x, y) específica usando un SpriteBatch
    public void dibujar(SpriteBatch batch, float x, float y) {
        batch.draw(textura, x, y); // Dibuja la textura en las coordenadas (x, y) proporcionadas
    }
    
    // Método para liberar los recursos de la textura cuando el power-up es destruido o ya no se necesita
    public void destruir() {
        textura.dispose(); // Libera la memoria utilizada por la textura
    }
}