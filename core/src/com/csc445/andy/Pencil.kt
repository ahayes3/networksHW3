package com.csc445.andy

import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

class Pencil(canvas:Canvas) : Tool(canvas) {
	var size = 1
	var mouseDown = false
	var lastPos = Vector2()
	override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if(button == Input.Buttons.LEFT)
			mouseDown = false
		return false
	}
	
	override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
		if(mouseDown) {
			val unprojected = canvas.camera.unproject(Vector3(screenX.toFloat(),screenY.toFloat(),0f))
			
			//draw line between las pos and current pos with width of size
		}
		
		return false
	}
	
	override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if(button == Input.Buttons.LEFT) {
			mouseDown = true
			val unprojected = canvas.camera.unproject(Vector3(screenX.toFloat(),screenY.toFloat(),0f))
			lastPos.set(unprojected.x,unprojected.y)
		}
		return false
	}
	
}