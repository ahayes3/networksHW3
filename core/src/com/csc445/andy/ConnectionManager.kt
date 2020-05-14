package com.csc445.andy

abstract class ConnectionManager(val canvas:Canvas) : Runnable {
	abstract fun end()
}