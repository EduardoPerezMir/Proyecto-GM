package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class NivelMedio extends NivelDificultad {
	private Array<GotaBuena> gotasB; // Array para almacenar las gotas buenas de lluvia 
	private Array<GotaMala> gotasM; // Array para almacenar las gotas malas de lluvia
	
	public NivelMedio(ObjetosFactory crear, Tarro tarro) {
		super(crear, tarro);
		crearArrayGotas();
	}
	
	public void crearArrayGotas() {
		gotasB = new Array<GotaBuena>();
		gotasM = new Array<GotaMala>();
	}
	
	public void crearGotaDeLluvia(ObjetosFactory crear) {
		if(MathUtils.random(1,10) <= 5) 
			gotasB.add(crear.crearGotaBuena(425));
		else
			gotasM.add(crear.crearGotaMala(425));
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
	
	public void borrarGotaBuenaActual(GotaBuena gota) {
		gotasB.removeValue(gota, true);
		gota.destruir();
	}

	public void borrarGotaMalaActual(GotaMala gota) {
		gotasM.removeValue(gota, true);
	}
	
	public void borrarArrayGotas() {
		gotasB.clear(); 
		gotasM.clear();
	}
	
	public void setVelTarroAcordeNivel(Tarro tarro) {
		int velTarro = 500;
		tarro.setVelocidad(velTarro);
	}
	
	public void setBackgroundTextureGame(GameScreen gameScreen) {
		Texture backgroundTexture;
		backgroundTexture = new Texture(Gdx.files.internal("fondoMedio.jpg"));
		gameScreen.setBackgroundTexture(backgroundTexture);
	}
}