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
		scrollPane.setSize(stage.width/2,50f)
		list.setSize(stage.width/2,150f)
		scrollPane.setPosition(100f,100f)
		stage.addActor(scrollPane)
	}
	
	override fun hide() {
		TODO("Not yet implemented")
	}
	
	override fun show() {
		TODO("Not yet implemented")
	}
	
	override fun render(delta: Float) {
		Gdx.gl.glClearColor(1f,1f,1f,1f)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		
		
	}
	
	override fun pause() {
		TODO("Not yet implemented")
	}
	
	override fun resume() {
		TODO("Not yet implemented")
	}
	
	override fun resize(width: Int, height: Int) {
		TODO("Not yet implemented")
	}
	
	override fun dispose() {
		TODO("Not yet implemented")
	}
	
}