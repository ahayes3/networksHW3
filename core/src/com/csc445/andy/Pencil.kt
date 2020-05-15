package com.csc445.andy

import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

class Pencil(canvas:Canvas,size:Int) : Tool(canvas,size) {
	var mouseDown = false
	var lastPos:Vector2? = null
	
	constructor(canvas:Canvas):this(canvas,1) {}
	
	override fun render(batch: SpriteBatch) {}
	
	override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if(button == Input.Buttons.LEFT) {
			mouseDown = false
			lastPos = null
			strokeDone = true
		}
		return false
	}
	
	override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
		return false
	}
	fun paint(vec:Vector2) {
		canvas.pixmap.fillCircle(vec.x.toInt(),vec.y.toInt(),size)
	}
	fun singlePaint(vec:Vector2) {
		canvas.pixmap.setColor(canvas.color)
		canvas.pixmap.fillCircle(vec.x.toInt(),vec.y.toInt(),size)
	}
	override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
		if(mouseDown && lastPos != null) {
			val pixel = mousePos(screenX.toFloat(),screenY.toFloat())
			
			val alphaStep = size / (8 * pixel.dst(lastPos))
			var a = 0f
			while(a < 1f) {
				val lerped = lastPos!!.lerp(pixel,a)
				paint(lerped)
				a += alphaStep
			}
			this.lastPos = pixel
			
		}
		return false
	}
	
	override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
		if(button == Input.Buttons.LEFT) {
			strokeDone = false
			strokeSet.clear()
			mouseDown = true
			lastPos = mousePos(screenX.toFloat(),screenY.toFloat())
			singlePaint(lastPos!!)
		}
		return false
	}
	
	override fun toString(): String {
		return "Pencil - Size: $size"
	}
}