package com.csc445.andy

abstract class ConnectionManager(val canvas:Canvas) : Runnable {
	@Volatile var stopped = false
	@Volatile var strokeNum = 0
	var lastStroke:MutableSet<Coord>? = null
	fun end() {
		stopped = true
	}
	fun giveStroke(set:MutableSet<Coord>) {
		lastStroke = set
		strokeNum++
	}
}

//I probably shouldn't be trying to use threads when i have no idea how to use them