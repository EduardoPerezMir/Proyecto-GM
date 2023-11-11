package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAmarilla extends Gota {
	private Sound dropSound; // Las Gotas Amarillas tienen sonido propio
	
	public GotaAmarilla(int velocidadCaida) {
		super(velocidadCaida);
		// Se asigna el sonido y la textura de la gota amarilla desde los assets.
		setTextura(new Texture(Gdx.files.internal("dropYellow.png")));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("dropYellow.mp3"));
		// Se asigna un puntaje.
		setPuntaje(30);
	}
	
	// Implementación concreta del método abstracto.
	public int accionColisionTarro(Tarro tarro) {
		dropSound.play();
		tarro.sumarPuntos(getPuntaje());
		return 1;
	}
}
