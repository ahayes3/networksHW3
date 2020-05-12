package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport

class Canvas(val app:DrawingApp,val resX:Int,val resY:Int): Screen {
	private val batch = app.batch
	private val clearColor = Color.LIGHT_GRAY
	private val pixmap = Pixmap(resX,resY,Pixmap.Format.RGB888)
	private val camera = OrthographicCamera()
	private val stage = Stage(ScreenViewport())
	private var texture:Texture
	init {
		Gdx.input.inputProcessor = MyInputProcessor()
		camera.setToOrtho(false, resX.toFloat(),resY.toFloat())
		pixmap.setColor(Color.WHITE)
		pixmap.fill()
		texture = Texture(pixmap)
	}
	override fun hide() {}
	
	override fun show() {}
	
	override fun render(delta: Float) {
		
		
		//drawing
		Gdx.gl.glClearColor(clearColor.r,clearColor.g,clearColor.b,clearColor.a)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		batch.projectionMatrix = camera.combined
		
		batch.begin()
		batch.draw(texture,0F,0F)
		batch.end()
		
		camera.update()
		
	}
	
	override fun pause() {
	}
	
	override fun resume() {}
	
	override fun resize(width: Int, height: Int) {}
	
	override fun dispose() {
		pixmap.dispose()
	}
	
}