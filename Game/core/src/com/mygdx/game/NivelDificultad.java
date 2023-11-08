package com.mygdx.game;

public interface NivelDificultad {
	public Gota crearGotaDeLluvia();
	public void setVelTarroAcordeNivel(Tarro tarro);
	public void setVelLluviaInicialAcordeNivel(Lluvia lluvia);
	public void setBackgroundTextureGame(GameScreen gameScreen);
}
