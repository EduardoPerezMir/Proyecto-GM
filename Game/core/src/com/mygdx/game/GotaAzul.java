package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAzul extends Gota {
	private Sound dropSound; // Las Gotas Amarillas tienen sonido propio
	
	public GotaAzul(int velocidadCaida) {
		super(velocidadCaida);
		// Se asigna el sonido y la textura de la gota azul desde los assets.
		setTextura(new Texture(Gdx.files.internal("drop.png")));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		// Se asigna un puntaje.
		setPuntaje(10);
	}
	
	// Implementación concreta del método abstracto.
	public int accionColisionTarro(Tarro tarro) {
		dropSound.play();
		tarro.sumarPuntos(getPuntaje());
		return 1;
	}
}
