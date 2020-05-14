package com.csc445.andy

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class DrawingApp : Game() {
    lateinit var batch: SpriteBatch
    lateinit var font:BitmapFont
    lateinit var skin: Skin

    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        skin = Skin(Gdx.files.internal("clean-crispy/skin/clean-crispy-ui.json"))
        this.setScreen(MainMenu(this))
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
        skin.dispose()
        screen.dispose()
    }
}