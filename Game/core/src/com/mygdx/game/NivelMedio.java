package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class NivelMedio extends NivelDificultad {

	public NivelMedio(final GameLluviaMenu game, int dificultad, IdiomaStrategy idioma, ObjetosFactory crear) {
		 super(game,dificultad,idioma,crear, new Texture(Gdx.files.internal("fondoMedio.jpg")));
	}
	
	public void inicializarNivel(Lluvia lluvia) {
        setBackgroundTexture(new Texture(Gdx.files.internal("fondoFacil.jpg")));
        lluvia.crear(250);

	}
}