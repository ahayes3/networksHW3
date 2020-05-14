package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

class Canvas(val app: DrawingApp, resX: Int, resY: Int) : Screen {
	private val batch = app.batch
	private val clearColor = Color.GRAY
	val pixmap = Pixmap(resX, resY, Pixmap.Format.RGB888)
	val camera = OrthographicCamera()
	val multiplexer = InputMultiplexer()
	val stage = Stage(ScreenViewport())
	private var texture: TextureRegion
	private val toolbox = Toolbox(app.skin, this)
	var selection = mutableSetOf<Coord>()
	var tool: Tool = toolbox.pencil
		set(value) {
			multiplexer.removeProcessor(2)
			multiplexer.addProcessor(value)
			field = value
		}
	var color:Color = Color.BLACK
	
	
	init {
		Gdx.input.inputProcessor = multiplexer
		
		camera.setToOrtho(false, Gdx.graphics.displayMode.width.toFloat(), Gdx.graphics.displayMode.height.toFloat())
		
		pixmap.setColor(Color.WHITE)
		pixmap.fill()
		pixmap.blending = Pixmap.Blending.None
		texture = TextureRegion(Texture(pixmap))
		texture.flip(false, true)
		
		camera.position.x = texture.regionWidth / 2F
		camera.position.y = texture.regionHeight / 2F
		
		
		multiplexer.addProcessor(stage)
		multiplexer.addProcessor(CommonInput(this))
		multiplexer.addProcessor(tool)
	}
	
	override fun hide() {}
	
	override fun show() {
		reposition()
	}
	
	override fun render(delta: Float) {
		
		val r = (toolbox.colorTable.cells[3].actor as Slider).value.toInt()
		val g = (toolbox.colorTable.cells[5].actor as Slider).value.toInt()
		val b = (toolbox.colorTable.cells[7].actor as Slider).value.toInt()
		val colorInt = (r shl 16) + (g shl 8) + b
		Color.rgb888ToColor(color,colorInt)
		
		val brushSize = (toolbox.colorTable.cells[1].actor as Slider).value
		
		(toolbox.colorTable.cells[0].actor as Label).setText("Brush size $brushSize")
		(toolbox.colorTable.cells[2].actor as Label).setText("R: $r")
		(toolbox.colorTable.cells[4].actor as Label).setText("G: $g")
		(toolbox.colorTable.cells[6].actor as Label).setText("B: $b")
		((toolbox.colorTable.cells[8].actor as Image).drawable as TextureRegionDrawable).tint(color)
		
		//drawing
		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		batch.projectionMatrix = camera.combined
		
		stage.act(delta)
		
		texture.texture.draw(pixmap, 0, 0)
		batch.begin()
		batch.draw(texture, 0F, 0F)
		tool.render(batch)
		batch.end()
		
		
		stage.draw()
		camera.update()
	}
	
	override fun pause() {
	}
	
	override fun resume() {}
	
	override fun resize(width: Int, height: Int) {
		stage.viewport.screenWidth = width
		stage.viewport.screenHeight = height
		reposition()
	}
	
	override fun dispose() {
		pixmap.dispose()
	}
	
	fun reposition() {
		toolbox.reposition()
	}
	
}