package com.csc445.andy

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align

class Toolbox(val skin: Skin,val canvas: Canvas) {
	var x:Float =0F
	var y:Float =0F
	val table = Table(skin)
	val pencil = Pencil(canvas)
	val eraser = Eraser()
	val rectangleSelect = RectangleSelect()
	val bucket = Bucket()
	
	init {
		val pencilDrawable = TextureRegionDrawable(Texture("pencil.png"))
		pencilDrawable.setMinSize(30f,30f)
		val eraserDrawable  = TextureRegionDrawable(Texture("eraser.png"))
		eraserDrawable.setMinSize(30f,30f)
		val rectangleDrawable = TextureRegionDrawable(Texture("rectangle.png"))
		rectangleDrawable.setMinSize(30f,30f)
		val bucketDrawable = TextureRegionDrawable(Texture("bucket.png"))
		bucketDrawable.setMinSize(30f,30f)
		
		val pencilButton = ImageButton(pencilDrawable,pencilDrawable.tint(Color.WHITE))
		val eraserButton = ImageButton(eraserDrawable,eraserDrawable.tint(Color.WHITE))
		val rectangleSelectButton = ImageButton(rectangleDrawable,rectangleDrawable.tint(Color.WHITE))
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
		
		cell = table.add(rectangleSelectButton).expandX()
		cell.actor.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				canvas.tool = rectangleSelect
			}
		})
		
		cell = table.add(bucketButton).expandX()
		cell.actor.addListener(object:ClickListener() {
			override fun clicked(event: InputEvent?, x: Float, y: Float) {
				canvas.tool = bucket
			}
		})
		
		table.debug()
		canvas.stage.addActor(table)
	}
	
	fun reposition() {
		table.setSize(canvas.stage.width/10,canvas.stage.height/3)
		table.setPosition(0f,canvas.stage.height - table.height)
	}
	
}