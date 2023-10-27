package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

	public class GameLluviaMenu extends Game {
		private SpriteBatch batch;
		private BitmapFont font;
		private BitmapFont font2;
		private BitmapFont font3;
		private int higherScore;

		public void create() {
			batch = new SpriteBatch();
			font = new BitmapFont(); // use libGDX's default Arial font
			font2 = new BitmapFont();
			font2.setColor(255, 0, 255, 50);
			font3 = new BitmapFont();
			font3.setColor(255, 255, 0, 255);;
			this.setScreen(new MainMenuScreen(this));
		}

		public void render() {
			super.render(); // important!
		}

		public void dispose() {
			batch.dispose();
			font.dispose();
		}

		public SpriteBatch getBatch() {
			return batch;
		}

		public BitmapFont getFont() {
			return font;
		}
		
		public BitmapFont getFont2() {
			return font2;
		}
		
		public BitmapFont getFont3() {
			return font3;
		}

		public int getHigherScore() {
			return higherScore;
		}

		public void setHigherScore(int higherScore) {
			this.higherScore = higherScore;
		}
	}
