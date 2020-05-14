package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import kotlin.system.exitProcess

class MainMenu(app: DrawingApp) : Screen {
	private val stage = Stage(ScreenViewport())
	val exit = TextButton("Exit", app.skin)
	val create = TextButton("Create",app.skin)
	val join = TextButton("Join",app.skin)
	init {
		Gdx.input.inputProcessor = stage
		recenter()
		
		join.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				app.screen.dispose()
				app.screen = JoinScreen()
			}
		})
		exit.addListener(object:InputListener() {
			override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
				app.dispose()
				exitProcess(0)
			}
		})
		create.addListener(object:InputListener() {
			override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
				app.screen.dispose()
				app.screen = CreateMenu(app)
				return true
			}
		})
		
		stage.addActor(join)
		stage.addActor(exit)
		stage.addActor(create)
	}
	
	override fun hide() {}
	
	override fun show() {
		recenter()
	}
	
	override fun render(delta: Float) {
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		stage.act(delta)
		stage.draw()
	}
	
	override fun pause() {}
	
	override fun resume() {}
	
	override fun resize(width: Int, height: Int) {
		stage.viewport.update(width, height, true)
		recenter()
	}
	
	override fun dispose() {
		stage.dispose()
	}
	
	private fun recenter() {
		exit.setPosition(stage.width / 2 - exit.width / 2, stage.height / 10)
		exit.setSize(stage.width / 5, stage.height / 10)
		create.setPosition(stage.width / 2 - create.width / 2, stage.height - stage.height / 10)
		create.setSize(stage.width / 5, stage.height / 10)
	}
	
}