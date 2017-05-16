package com.gamedev;

import com.badlogic.gdx.Game;
import com.gamedev.screens.GameMenu;
import com.gamedev.styles.GameGraphics;

public class Arcanoid extends Game {
	
	@Override
	public void create () {
		GameGraphics gameGraphics = new GameGraphics();
		this.setScreen(new GameMenu(this, gameGraphics));
	}
}
