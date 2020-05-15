package com.csc445.andy

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.utils.viewport.ScreenViewport
import kotlin.system.exitProcess

class Canvas(val app: DrawingApp, resX: Int, resY: Int,host:Boolean) : Screen {
	private val connection:ConnectionManager
	private val thread:Thread
	var strokeNum = 0
	
	private val batch = app.batch
	private val clearColor = Color.GRAY
	val pixmap = Pixmap(resX, resY, Pixmap.Format.RGB888)
	val camera = OrthographicCamera()
	val multiplexer = InputMultiplexer()
	val stage = Stage(ScreenViewport())
	private var texture: TextureRegion
	private val toolbox = Toolbox(app.skin, this)
	var selection = mutableSetOf<Coord>()
	private val sr:ShapeRenderer = ShapeRenderer()
	var tool: Tool = toolbox.pencil
		set(value) {
			multiplexer.removeProcessor(2)
			multiplexer.addProcessor(value)
			field = value
		}
	var color:Color = Color.BLACK
	/*todo
	1. create invite and join //done
	2. create thread for packet handling //in progress
	3. create packets for difference created in canvas
	4. ???
	5. profit
	 */
	
	init {
		camera.setToOrtho(false, Gdx.graphics.displayMode.width.toFloat(), Gdx.graphics.displayMode.height.toFloat())
		if(host) {
			connection = Host(this)
			thread = Thread(connection)
			thread.start()
			
			pixmap.setColor(Color.WHITE)
			pixmap.fill()
			pixmap.blending = Pixmap.Blending.None
			texture = TextureRegion(Texture(pixmap))
			texture.flip(false, true)
			
			camera.position.x = texture.regionWidth / 2F
			camera.position.y = texture.regionHeight / 2F
		}
		else {
			val client = Client(this)
			connection = client
			thread = Thread(connection)
			client.catchUp()
			
			
			
			//todo catch clients up
		}
		Gdx.input.inputProcessor = multiplexer
		
		
		
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
		
		val brushSize = (toolbox.colorTable.cells[1].actor as Slider).value.toInt()
		tool.size = brushSize
		(toolbox.colorTable.cells[0].actor as Label).setText("Brush size $brushSize")
		(toolbox.colorTable.cells[2].actor as Label).setText("R: $r")
		(toolbox.colorTable.cells[4].actor as Label).setText("G: $g")
		(toolbox.colorTable.cells[6].actor as Label).setText("B: $b")
		
		if(tool.strokeDone && tool.strokeSet.isNotEmpty()) {
			connection.giveStroke(tool.strokeSet)
			tool.strokeSet = mutableSetOf()
		}
		//drawing
		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a)
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		
		batch.projectionMatrix = camera.combined
		sr.projectionMatrix = stage.viewport.camera.combined
		
		stage.act(delta)
		
		this.texture.texture.draw(pixmap, 0, 0)
		batch.begin()
		batch.draw(texture, 0F, 0F)
		tool.render(batch)
		batch.end()
		sr.begin(ShapeRenderer.ShapeType.Filled)
		sr.color = color
		sr.rect(toolbox.colorTable.x,toolbox.colorTable.y - 11,60f,10f)
		sr.end()
		
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
		connection.end()
	}
	
	fun reposition() {
		toolbox.reposition()
	}
	fun exit() {
		app.dispose()
		exitProcess(0)
	}
}