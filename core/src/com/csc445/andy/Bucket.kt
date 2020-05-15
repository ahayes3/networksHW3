package com.csc445.andy

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import kotlin.math.max

class Bucket(canvas:Canvas) : Tool(canvas,max(canvas.pixmap.width,canvas.pixmap.height)*2) {
	
	override fun render(batch:SpriteBatch) {}
	
	
	override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {return false}
	
	override fun mouseMoved(screenX: Int, screenY: Int): Boolean {return false}
	
	override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {return false}
	
	override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if(button == Input.Buttons.LEFT) {
			strokeDone = true
			strokeSet.clear()
			val vec = mousePos(screenX.toFloat(), screenY.toFloat())
			if(vec.x >=0 && vec.x < canvas.pixmap.width && vec.y >=0 && vec.y < canvas.pixmap.height) {
				canvas.pixmap.setColor(canvas.color)
				canvas.pixmap.fill()
				strokeSet.add(Coord((canvas.pixmap.width/2f).toInt(),(canvas.pixmap.height/2f).toInt()))
			}
		}
		return false
	}
	
	override fun toString(): String {
		return "Bucket"
	}
}