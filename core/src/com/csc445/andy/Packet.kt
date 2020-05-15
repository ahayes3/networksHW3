package com.csc445.andy

import com.badlogic.gdx.graphics.Pixmap
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
	fun mustard(strokeNum:Int,id:Long):ByteBuffer { //you ask for ketchup and get mustard. its as disappointing as this project
		val buff = ByteBuffer.allocate(14)
		buff.putShort(2)
		buff.putInt(strokeNum)
		buff.putLong(id)
		buff.flip()
		return buff
	}
	fun idReq(id:Long):ByteBuffer {
		val buff = ByteBuffer.allocate(10)
		buff.putShort(3)
		buff.putLong(id)
		buff.flip()
		return buff
	}
	fun allPixel(pixmap: Pixmap):ByteBuffer {
		val buff = ByteBuffer.allocate(pixmap.pixels.capacity() + 2)
		buff.putShort(4)
		buff.put(pixmap.pixels)
		buff.flip()
		return buff
	}
}

/*
packet format:

opcodes
1 = catchup
2 = catchup response discard if strokenum <= to yours contains strokenum
3 = idrewq
5 = draw


draw: this is no longer correct now its opcode, strokenum, brushsize, x1,y1,...,xn,yn  maybe it gets split into multiple packets if it does then just give it the same strokenum probably
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