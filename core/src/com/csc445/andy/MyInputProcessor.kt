package com.csc445.andy

import com.badlogic.gdx.InputProcessor

class MyInputProcessor : InputProcessor {
	override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {}
	
	override fun mouseMoved(screenX: Int, screenY: Int): Boolean {}
	
	override fun keyTyped(character: Char): Boolean {}
	
	override fun scrolled(amount: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun keyUp(keycode: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun keyDown(keycode: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		TODO("Not yet implemented")
	}
	
}