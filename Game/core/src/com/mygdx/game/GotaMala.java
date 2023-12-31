package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GotaMala {
    public int accionColisionTarro(Tarro tarro);

    public int verificarColisionTarro(Tarro tarro, float pond);

    public void dibujarGota(SpriteBatch batch);

    public void destruir();
}
