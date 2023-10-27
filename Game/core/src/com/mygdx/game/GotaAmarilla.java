package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAmarilla extends Gota {
	Sound dropSound;
	
	public GotaAmarilla() {
		super(new Texture(Gdx.files.internal("dropYellow.png")), 30, 300);
		dropSound = Gdx.audio.newSound(Gdx.files.internal("dropYellow.mp3"));
	}
	
	public boolean accionColisionTarro(Tarro tarro) {
		dropSound.play();
		tarro.sumarPuntos(getPuntaje());
		return true;
	}
}
