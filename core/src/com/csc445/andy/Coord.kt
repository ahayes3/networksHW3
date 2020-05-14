package com.csc445.andy

import com.badlogic.gdx.math.Vector2

data class Coord(var x:Int,var y:Int) {
	operator fun plus(b:Coord):Coord {
		return Coord(x+b.x,y+b.y)
	}
	fun add(x:Int,y:Int) {
		this.x += x
		this.y +=y
	}
	fun subtract(x:Int,y:Int) {
		this.x -= x
		this.y -=y
	}
	operator fun minus(b:Coord):Coord {
		return Coord(x - b.x, y - b.y)
	}
	
	override fun equals(other: Any?): Boolean {
		if(other is Coord)
			return (x == other.x && y == other.y)
		return false
	}
	fun getVector(): Vector2 {
		return Vector2(x.toFloat(),y.toFloat())
	}
}