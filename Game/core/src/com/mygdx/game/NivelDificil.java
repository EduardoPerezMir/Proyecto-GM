package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class NivelDificil extends NivelDificultad {

	public NivelDificil(final GameLluviaMenu game, int dificultad, IdiomaStrategy idioma, ObjetosFactory crear) {
		super(game,dificultad,idioma,crear);

	}
	
	public void inicializarFondo() {
        setBackgroundTexture(new Texture(Gdx.files.internal("fondoDificil.jpg")));
	}
	public void inicializarVelocidadLluvia(Lluvia lluvia) {
        lluvia.crear(300);
        lluvia.setTiempoEntrePowers(18.0f);
	}
	public void inicializarVelocidadTarro(Tarro tarro) {
        tarro.setVelocidad(410);
	}
}