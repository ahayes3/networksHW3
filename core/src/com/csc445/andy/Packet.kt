package com.csc445.andy

import java.nio.ByteBuffer

object Packet {
	fun invite(): ByteBuffer {
		TODO()
	}
	fun draw(time:Float,color:Int,set:Set<Short>): ByteBuffer {
		TODO()
	}
	fun ketchup():ByteBuffer { //haha catch up - ketchup its funny because its a pun haha ketchup packet
		val buff = ByteBuffer.allocate(2)
		buff.putShort(1)
		buff.flip()
		return buff
	}
	fun mustard(strokeNum:Int):ByteBuffer { //you ask for ketchup and get mustard. its as disappointing as this project
		val buff = ByteBuffer.allocate(6)
		buff.putShort(2)
		buff.putInt(strokeNum)
		buff.flip()
		return buff
	}
}

/*
packet format:

opcodes
1 = catchup
2 = catchup response discard if strokenum <= to yours contains strokenum
4 = draw


draw: this is no longer correct
opcode:Short = 4
timeStamp:Float
int:Color  i know it wastes 1 byte
x1:Short
y1:Short
.
.
.
xn:Short
yn:Short

fill:
opcode = 4
timestamp
int:Color
 */

//kill me