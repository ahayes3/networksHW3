package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.List
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
import com.badlogic.gdx.utils.viewport.ScreenViewport

class JoinScreen(app:DrawingApp) : Screen {
	val stage = Stage(ScreenViewport())
	val list = List<String>(app.skin)
	val scrollPane = ScrollPane(list)
	init {
		Gdx.input.inputProcessor = stage
		reposition()
		
		list.items.distinct()
		scrollPane.setSmoothScrolling(false)
		
		stage.addActor(scrollPane)
	}
	
	override fun hide() {}
	
	override fun show() {
		reposition()
	}
	
	override fun render(delta: Float) {
		Gdx.gl.glClearColor(1f,1f,1f,1f)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		stage.act(delta)
		stage.draw()
		
	}
	
	override fun pause() {}
	
	override fun resume() {}
	
	override fun resize(width: Int, height: Int) {
		reposition()
	}
	
	override fun dispose() {
		stage.dispose()
	}
	fun reposition() {
		list.setSize(stage.width/2,100000f)
		scrollPane.setSize(stage.width/2,stage.height/2)
		scrollPane.setPosition(stage.width/2 - scrollPane.width/2,stage.height - 10 - scrollPane.height)
		scrollPane.setBounds(scrollPane.x,scrollPane.y,scrollPane.width,scrollPane.height)
	}
}