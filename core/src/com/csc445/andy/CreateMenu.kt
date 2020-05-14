package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.ScreenViewport

class CreateMenu(val app:DrawingApp):Screen {
	private val stage:Stage = Stage(ScreenViewport())
	//todo switch names from default
	private val table = Table(app.skin)
	private val resX:TextField = TextField("100",app.skin)
	private val resY:TextField = TextField("100",app.skin)
	private val create:TextButton = TextButton("Create",app.skin)
	private val label: Label = Label("Set resolutions correctly",app.skin)
	init {
		
		label.color = Color(1F,0F,0F,0F)
		Gdx.input.inputProcessor = stage
		resX.addListener(object:InputListener() {
			override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
				stage.keyboardFocus = resX
				return true
			}
			
			override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
				if(keycode == Input.Keys.ENTER && resX.text.matches(Regex("\\d+")))
					stage.keyboardFocus = null
				else if(keycode == Input.Keys.ENTER && resX.text.matches(Regex("-\\d+"))) {
					stage.keyboardFocus = null
					resX.text = resX.text.substring(1 until resX.text.length)
				}
				else if(keycode == Input.Keys.ENTER) {
					stage.keyboardFocus = null
					resX.text = "invalid"
				}
				return true
			}
		})
		resY.addListener(object:InputListener() {
			override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
				stage.keyboardFocus = resY
				return true
			}
			
			override fun keyDown(event: InputEvent?, keycode: Int): Boolean {
				if(keycode == Input.Keys.ENTER && resY.text.matches(Regex("\\d+")))
					stage.keyboardFocus = null
				else if(keycode == Input.Keys.ENTER && resY.text.matches(Regex("-\\d+"))) {
					stage.keyboardFocus = null
					resY.text = resY.text.substring(1 until resY.text.length)
				}
				else if(keycode == Input.Keys.ENTER) {
					stage.keyboardFocus = null
					resY.text = "invalid"
				}
				return true
			}
		})
		create.addListener(object:InputListener() {
			override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
				if(resX.text.matches(Regex("\\d+")) && resY.text.matches(Regex("\\d+")))
					create()
				else
					label.color = Color.RED
				return true
			}
		})
		recenter()
		val background = Pixmap(table.width.toInt(),table.height.toInt(),Pixmap.Format.RGBA8888)
		background.setColor(Color.LIGHT_GRAY)
		background.fill()
		table.background = TextureRegionDrawable(Texture(background))
		background.dispose()
		table.add(Label("X resolution",app.skin))
		//table.add(Label("X resolution",app.skin))
		table.add(resX)
		table.row()
		table.add(Label("Y resolution",app.skin))
		table.add(resY)
		
		stage.addActor(label)
		stage.addActor(create)
		stage.addActor(table)
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
		stage.viewport.update(width,height,true)
		recenter()
	}
	
	override fun dispose() {
		stage.dispose()
	}
	
	private fun create() {
		this.dispose()
		app.screen = Canvas(app,resX.text.toInt(),resY.text.toInt())
	}
	
	private fun recenter() {
		label.setSize(stage.width/3,stage.height/10)
		label.setPosition(stage.width/2 - label.width/2,stage.height *(9/10F) - label.height)
		
		table.setSize(stage.width/2,stage.height/5)
		table.setPosition(stage.width/2F - table.width/2F,(stage.height * (8/10F)) - table.height)
//		resX.setSize(stage.width / 1.5F, stage.height / 10)
//		resX.setPosition(stage.width / 2 - resX.width / 2, stage.height - 2*stage.height / 10)
//
//		resY.setSize(stage.width / 1.5F, stage.height / 10)
//		resY.setPosition(stage.width / 2 - resY.width / 2, stage.height - 3*stage.height / 10)
		
		create.setSize(stage.width/5,stage.height/10)
		create.setPosition(stage.width -create.width,0F)
	}
	
}