package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAmarilla extends Gota {
	Sound dropSound;
	
	public GotaAmarilla() {
		super(0, new Texture(Gdx.files.internal("dropYellow.png")), 0, 300);
		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	}
	
	public boolean accionColisionTarro(Tarro tarro) {
		tarro.sumarPuntos(30);
		return true;
	}
}
