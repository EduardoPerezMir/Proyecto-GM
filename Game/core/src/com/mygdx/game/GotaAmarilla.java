package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;

public class GotaAmarilla extends Gota {
	Sound dropSound;
	
	public GotaAmarilla() {
		setTextura(new Texture(Gdx.files.internal("dropYellow.png")));
		dropSound = Gdx.audio.newSound(Gdx.files.internal("dropYellow.mp3"));
		setPuntaje(30);
	}
	
	public int accionColisionTarro(Tarro tarro) {
		dropSound.play();
		tarro.sumarPuntos(getPuntaje());
		return 1;
	}
}
