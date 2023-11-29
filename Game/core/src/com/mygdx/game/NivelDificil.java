package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class NivelDificil extends NivelDificultad {
	private Array<GotaBuena> gotasB; // Array para almacenar las gotas buenas de lluvia 
	private Array<GotaMala> gotasM; // Array para almacenar las gotas malas de lluvia 
	
	public NivelDificil(ObjetosFactory crear, Tarro tarro) {
		super(crear, tarro);
		crearArrayGotas();
	}
	
	public void crearArrayGotas() {
		gotasB = new Array<GotaBuena>();
		gotasM = new Array<GotaMala>();
	}
	
	public void crearGotaDeLluvia(ObjetosFactory crear) {
		if(MathUtils.random(1,10) <= 4) 
			gotasB.add(crear.crearGotaBuena(450));
		else
			gotasM.add(crear.crearGotaMala(450));
	} 
	

	public GotaBuena getGotaBuenaActual(int i) {
		return gotasB.get(i);
	}
	
	public GotaMala getGotaMalaActual(int i) {
		return gotasM.get(i);
	}
	
	public int getCantGotasBuenas() {
		return gotasB.size;
	}
	
	public int getCantGotasMalas() {
		return gotasM.size;
	}
	
	public void borrarGotaBuenaActual(int i) {
		getGotaBuenaActual(i).destruir();
		gotasB.removeIndex(i);
	}

	public void borrarGotaMalaActual(int i) {
		getGotaMalaActual(i).destruir();
		gotasM.removeIndex(i);
	}
	
	public void borrarArrayGotas() {
		gotasB.clear(); 
		gotasM.clear();
	}
	
	public void setVelTarroAcordeNivel(Tarro tarro) {
		int velTarro = 475;
		tarro.setVelocidad(velTarro);
	}
	
	public void setBackgroundTextureGame(GameScreen gameScreen) {
		Texture backgroundTexture;
		backgroundTexture = new Texture(Gdx.files.internal("fondoDificil.jpg"));
		gameScreen.setBackgroundTexture(backgroundTexture);
	}
}