package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAzul extends Gota {
	Sound dropSound;
	
	public GotaAzul() {
		setTextura(new Texture(Gdx.files.internal("drop.png")));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		setPuntaje(10);
	}
	
	public int accionColisionTarro(Tarro tarro) {
		dropSound.play();
		tarro.sumarPuntos(getPuntaje());
		return 1;
	}
}
