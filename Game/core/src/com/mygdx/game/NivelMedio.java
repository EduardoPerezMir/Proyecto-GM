package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class NivelMedio extends NivelDificultad {

	public NivelMedio(final GameLluviaMenu game, int dificultad, IdiomaStrategy idioma, ObjetosFactory crear) {
		super(game,dificultad,idioma,crear);
	}
	
	public void inicializarFondo() {
        setBackgroundTexture(new Texture(Gdx.files.internal("fondoMedio.jpg")));
	}
	public void inicializarVelocidadLluvia(Lluvia lluvia) {
        lluvia.crear(250);
        lluvia.setTiempoEntrePowers(15.0f);
	}
	public void inicializarVelocidadTarro(Tarro tarro) {
        tarro.setVelocidad(445);
	}
	
}