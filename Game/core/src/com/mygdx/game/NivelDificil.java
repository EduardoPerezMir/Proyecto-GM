package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class NivelDificil extends NivelDificultad {

	public NivelDificil(final GameLluviaMenu game, int dificultad, IdiomaStrategy idioma, ObjetosFactory crear) {
		super(game,dificultad,idioma,crear, new Texture(Gdx.files.internal("fondoDificil.jpg")));

	}
	
	public void inicializarNivel(Lluvia lluvia) {
        setBackgroundTexture(new Texture(Gdx.files.internal("fondoFacil.jpg")));
        lluvia.crear(300);
	}
}