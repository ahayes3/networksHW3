package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.math.Vector3

class CommonInput(val canvas:Canvas) : InputProcessor {
	private var rightDown = false
	
	override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
		if(rightDown) {
			val unprojected = canvas.camera.unproject(Vector3(screenX.toFloat(), screenY.toFloat(), 0F))
			canvas.camera.position.x -= unprojected.x
			canvas.camera.position.y -= unprojected.y
			
		}
		return false
	}
	
	override fun keyTyped(character: Char): Boolean {
		return false
	}
	
	override fun scrolled(amount: Int): Boolean {
		canvas.camera.zoom += amount*3*Gdx.graphics.getDeltaTime();
		if(canvas.camera.zoom <0.05)
			canvas.camera.zoom = 0.05f;
		return true
	}
	
	override fun keyUp(keycode: Int): Boolean {
		return false
	}
	
	override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
		return false
	}
	
	override fun keyDown(keycode: Int): Boolean {
		return false
	}
	
	override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if(Input.Buttons.RIGHT == button)
			rightDown = true
		return false
	}
	override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if(Input.Buttons.RIGHT == button)
			rightDown = false
		return false
	}
}