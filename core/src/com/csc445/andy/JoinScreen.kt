package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.ui.List
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport

class JoinScreen(app:DrawingApp) : Screen {
	val stage = Stage(ScreenViewport())
	val label = Label("Group address: ",app.skin)
	val field = TextField("",app.skin)
	val back = TextButton("Back",app.skin)
	val join = TextButton("Join",app.skin)
	val errLabel = Label("Invalid ip address",app.skin)
	init {
		Gdx.input.inputProcessor = stage
		reposition()
		errLabel.isVisible = false
		back.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				dispose()
				app.screen = MainMenu(app)
			}
		})
		join.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				if(field.text.matches(Regex("\\d+\\.\\d+\\.\\d+\\.\\d+"))) {
					dispose()
					app.screen = Canvas(app,-1,-1,false)
				}
			}
		})
		stage.addActor(errLabel)
		stage.addActor(label)
		stage.addActor(field)
		stage.addActor(back)
		stage.addActor(join)
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
		errLabel.setSize(stage.width/3,stage.height/10)
		errLabel.setPosition(stage.width/2 - errLabel.width/2,stage.height *(9f/10))
		label.setSize(stage.width/3,stage.height/10)
		label.setPosition(stage.width/3 - label.width/2,stage.height * (7f/10))
		field.setSize(stage.width/3,stage.height/10)
		field.setPosition(stage.width * (2f/3) - field.width/2,stage.height *(7f/10))
		back.setSize(stage.width/5,stage.height/10)
		back.setPosition(0f,0f)
		join.setSize(back.width,back.height)
		join.setPosition(stage.width - join.width,0f)
	}
}