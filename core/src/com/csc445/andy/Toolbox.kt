package com.csc445.andy

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align

class Toolbox(val skin: Skin,val canvas: Canvas) {
	var x:Float =0F
	var y:Float =0F
	val file = TextButton("File",skin)
	val table = Table(skin)
	val colorTable = Table(skin)
	val pencil = Pencil(canvas)
	val eraser = Eraser(canvas)
	val bucket = Bucket(canvas)
	val fileTable = Table(skin)
	
	
	init {
		file.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				fileTable.isVisible = !fileTable.isVisible
			}
		})
		canvas.stage.addActor(file)
		
		val pencilDrawable = TextureRegionDrawable(Texture("pencil.png"))
		pencilDrawable.setMinSize(30f,30f)
		val eraserDrawable  = TextureRegionDrawable(Texture("eraser.png"))
		eraserDrawable.setMinSize(30f,30f)
		val bucketDrawable = TextureRegionDrawable(Texture("bucket.png"))
		bucketDrawable.setMinSize(30f,30f)
		
		val pencilButton = ImageButton(pencilDrawable,pencilDrawable.tint(Color.WHITE))
		val eraserButton = ImageButton(eraserDrawable,eraserDrawable.tint(Color.WHITE))
		val bucketButton = ImageButton(bucketDrawable,bucketDrawable.tint(Color.WHITE))
		
		reposition()
		val background = Pixmap(table.width.toInt(),table.height.toInt(),Pixmap.Format.RGBA8888)
		background.setColor(Color.LIGHT_GRAY)
		background.fill()
		
		table.pad(0f)
		table.align(Align.top)
		table.background = TextureRegionDrawable(Texture(background))
		background.dispose()
		table.color = Color.LIGHT_GRAY
		
		var cell = table.add(pencilButton).expandX()
		cell.actor.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				canvas.tool = pencil
			}
		})
		
		cell = table.add(eraserButton).expandX()
		cell.actor.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				canvas.tool = eraser
			}
		})
		
		table.row()
		
		cell = table.add(bucketButton).expandX()
		cell.actor.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				canvas.tool = bucket
			}
		})
		
		canvas.stage.addActor(table)
		
		var label = colorTable.add(Label("Brush size",skin))
		label.minWidth(30f)
		label.maxWidth(60f)
		colorTable.row()
		var slider = colorTable.add(Slider(1f,1000f,1f,false,skin))
		slider.minWidth( 60f)
		slider.minHeight(10f)
		colorTable.row()
		
		
		
		
		colorTable.add(Label("",skin))
		colorTable.row()
		slider = colorTable.add(Slider(0f,255f,1f,false,skin))
		slider.actor.color = Color(1f,0f,0f,.5f)
		slider.minWidth(60f)
		slider.minHeight(10f)
		colorTable.row()
		colorTable.add(Label("",skin))
		colorTable.row()
		slider = colorTable.add(Slider(0f,255f,1f,false,skin))
		slider.actor.color = Color(0f,1f,0f,.5f)
		slider.minWidth(60f)
		slider.minHeight(10f)
		colorTable.row()
		colorTable.add(Label("",skin))
		colorTable.row()
		slider = colorTable.add(Slider(0f,255f,1f,false,skin))
		slider.actor.color = Color(0f,0f,1f,.5f)
		slider.minWidth(60f)
		slider.minHeight(10f)
		colorTable.align(Align.top)
		//colorTable.debug()
		canvas.stage.addActor(colorTable)
		
		var button = fileTable.add(TextButton("invite",skin))
		button.actor.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				//todo
			}
		})
		fileTable.row()
		button = fileTable.add(TextButton("exit",skin))
		button.actor.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				canvas.exit()
			}
		})
		
		canvas.stage.addActor(fileTable)
	}
	
	fun reposition() {
		file.scaleBy(1f,.2f)
		file.setSize(60f,20f)
		file.setPosition(0f,canvas.stage.height - file.height - 2)
		fileTable.setSize(60f,60f)
		fileTable.setPosition(0f,file.y - fileTable.height)
		
		table.setSize(60f,60f)
		table.setPosition(canvas.stage.width - table.width,canvas.stage.height - table.height)
		val background = Pixmap(table.width.toInt(),table.height.toInt(),Pixmap.Format.RGB888)
		background.setColor(Color.LIGHT_GRAY)
		background.fill()
		table.background = TextureRegionDrawable(Texture(background))
		background.dispose()
		colorTable.setSize(60f,120f)
		colorTable.setPosition(canvas.stage.width - colorTable.width,table.y-colorTable.height)
		
		
	}
	
}