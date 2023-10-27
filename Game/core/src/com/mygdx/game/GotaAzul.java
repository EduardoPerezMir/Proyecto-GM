package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAzul extends Gota {
	Sound dropSound;
	
	public GotaAzul() {
		super(new Texture(Gdx.files.internal("drop.png")), 10, 300);
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	}
	
	public boolean accionColisionTarro(Tarro tarro) {
		dropSound.play();
		tarro.sumarPuntos(getPuntaje());
		return true;
	}
}
