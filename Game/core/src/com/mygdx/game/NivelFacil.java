package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class NivelFacil extends NivelDificultad{

	public NivelFacil(final GameLluviaMenu game, int dificultad, IdiomaStrategy idioma, ObjetosFactory crear) {
		super(game,dificultad,idioma,crear);
		 
	}
	
	public void inicializarFondo() {
        setBackgroundTexture(new Texture(Gdx.files.internal("fondoFacil.jpg")));
	}
	public void inicializarVelocidadLluvia(Lluvia lluvia) {
        lluvia.crear(200);
        lluvia.setTiempoEntrePowers(12.0f);
	}
	public void inicializarVelocidadTarro(Tarro tarro) {
        tarro.setVelocidad(475);
	}
    
}