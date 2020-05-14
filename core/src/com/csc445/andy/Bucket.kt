package com.csc445.andy

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

class Bucket(canvas:Canvas) : Tool(canvas,0) {
	fun paint() {
		TODO("Not yet implemented")
	}
	
	override fun render(batch:SpriteBatch) {}
	
	override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun toString(): String {
		return "Bucket"
	}
}