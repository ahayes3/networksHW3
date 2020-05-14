package com.csc445.andy

import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

abstract class Tool(val canvas: Canvas,var size:Int) : InputProcessor {
	abstract fun render(batch: SpriteBatch)
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
	
	fun mousePos(screenX: Float, screenY: Float): Vector2 {
		val unprojected = canvas.camera.unproject(Vector3(screenX, screenY, 0f))
		return Vector2(unprojected.x, unprojected.y)
	}
	
	fun mousePixel(screenX: Float, screenY: Float): Coord {
		val unprojected = canvas.camera.unproject(Vector3(screenX,screenY,0f))
		return Coord(unprojected.x.toInt(), unprojected.y.toInt())
	}
	
	fun gcd(a:Int,b:Int):Int {
		if(b==0)
			return a
		return gcd(b,a%b)
	}
}