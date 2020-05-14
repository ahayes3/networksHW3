package com.csc445.andy

import com.badlogic.gdx.InputProcessor

abstract class Tool(val canvas:Canvas) : InputProcessor {
	override fun scrolled(amount: Int): Boolean {
		return false
	}
	
	override fun keyDown(keycode: Int): Boolean {
		return false
	}
	
	override fun keyTyped(character: Char): Boolean {
		return false
	}
	
	override fun keyUp(keycode: Int): Boolean {
		return false
	}
}