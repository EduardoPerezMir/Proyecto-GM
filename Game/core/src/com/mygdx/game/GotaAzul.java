package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAzul extends Gota {
	Sound dropSound;
	
	public GotaAzul() {
		super(0, new Texture(Gdx.files.internal("drop.png")), 0, 300);
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	}
	
	public boolean accionColisionTarro(Tarro tarro) {
		tarro.sumarPuntos(10);
		return true;
	}
}
