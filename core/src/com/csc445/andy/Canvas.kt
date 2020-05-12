package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport

class Canvas(val app:DrawingApp,val resX:Int,val resY:Int): Screen {
	private val batch = app.batch
	private val clearColor = Color.LIGHT_GRAY
	val pixmap = Pixmap(resX,resY,Pixmap.Format.RGB888)
	val camera = OrthographicCamera()
	val multiplexer = InputMultiplexer()
	private val stage = Stage(ScreenViewport())
	private var texture:Texture
	init {
		multiplexer.addProcessor(CommonInput(this))
		
		
		Gdx.input.inputProcessor = CommonInput(this)
		camera.setToOrtho(false, Gdx.graphics.displayMode.width.toFloat(), Gdx.graphics.displayMode.height.toFloat())
		pixmap.setColor(Color.WHITE)
		pixmap.fill()
		texture = Texture(pixmap)
		camera.position.x = texture.width/2F
		camera.position.y = texture.height/2F
	}
	override fun hide() {}
	
	override fun show() {
		reposition()
	}
	
	override fun render(delta: Float) {
		
		//drawing
		Gdx.gl.glClearColor(clearColor.r,clearColor.g,clearColor.b,clearColor.a)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		batch.projectionMatrix = camera.combined
		
		texture = Texture(pixmap)
		batch.begin()
		texture.draw(pixmap,0,0)
		batch.draw(texture,0F,0F)
		batch.end()
		
		camera.update()
		
	}
	
	override fun pause() {
	}
	
	override fun resume() {}
	
	override fun resize(width: Int, height: Int) {
		camera.viewportWidth = width.toFloat()
		camera.viewportHeight = height.toFloat()
	}
	
	override fun dispose() {
		pixmap.dispose()
	}
	
	fun reposition() {
	
	}
	
}