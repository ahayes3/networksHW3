package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport

class Canvas(val app:DrawingApp,val resX:Int,val resY:Int): Screen {
	private val batch = app.batch
	private val clearColor = Color.GRAY
	val pixmap = Pixmap(resX,resY,Pixmap.Format.RGB888)
	val camera = OrthographicCamera()
	val multiplexer = InputMultiplexer()
	val stage:Stage
	private var texture:Texture
	private val toolbox:Toolbox
	var tool:Tool
	
	
	init {
		Gdx.input.inputProcessor = multiplexer
		camera.setToOrtho(false, Gdx.graphics.displayMode.width.toFloat(), Gdx.graphics.displayMode.height.toFloat())
		stage = Stage(ScreenViewport())
		pixmap.setColor(Color.WHITE)
		pixmap.fill()
		texture = Texture(pixmap)
		camera.position.x = texture.width/2F
		camera.position.y = texture.height/2F
		toolbox = Toolbox(app.skin, this)
		tool = toolbox.pencil
		
		multiplexer.addProcessor(stage)
		multiplexer.addProcessor(CommonInput(this))
		multiplexer.addProcessor(0,tool)
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
		
		stage.act(delta)
		stage.draw()
		
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
		toolbox.reposition()
	}
	
}