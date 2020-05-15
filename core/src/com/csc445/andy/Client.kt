package com.csc445.andy

import com.badlogic.gdx.graphics.Pixmap
import java.net.*
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import java.nio.channels.MembershipKey
import kotlin.random.Random

class Client(canvas:Canvas) : ConnectionManager(canvas) {
	val group = Inet4Address.getByName("224.0.0.1")
	val sendAddress = InetSocketAddress(group.toString(),8080)
	var buff = ByteBuffer.allocate(512)
	val ni = NetworkInterface.getNetworkInterfaces().toList().firstOrNull { it.name != "lo" }
	val channel = DatagramChannel.open(StandardProtocolFamily.INET).setOption(StandardSocketOptions.SO_REUSEADDR,true).bind(InetSocketAddress(group,8080))
			.setOption(StandardSocketOptions.IP_MULTICAST_IF,ni)
	val key: MembershipKey
	val id = Random.nextLong()
	lateinit var pixmap: Pixmap
	init {
		key = channel.join(group,ni)
		channel.configureBlocking(false)
	}
	override fun run() {
		while(!stopped) {
			channel.receive(buff)
			buff.flip()
			val opcode:Int = buff.getShort(0).toInt()
			when (opcode) {
				1 -> channel.send(Packet.mustard(strokeNum,id),sendAddress)
				2 -> buff.clear()//discard
				3 -> {
					if(buff.getLong(2) == id) {
						channel.send(Packet.allPixel(canvas.pixmap),sendAddress)
					}
				}
			}
		}
	}
	fun catchUp() {
		var something = false
		var responses = mutableSetOf<ByteBuffer>()
		while(!something) {
			val req = Packet.ketchup()
			//send request
			channel.send(req,sendAddress)
			var most:ByteBuffer? = null
			do {
				channel.receive(buff)
				buff.flip()
				//responses
				if(buff.limit() != 0 ) {
					responses.add(buff)
					buff = ByteBuffer.allocate(512)
				}
				for(i in responses) {
					if(most == null || i.getInt(2) > most.getInt(2))
						most = i
				}
			}while(responses.isEmpty() || buff.limit() != 0)
			channel.send(Packet.idReq(most!!.getLong()),sendAddress)
			val tmpBuff = ByteBuffer.allocate()
			
		}
	}
	fun caughtUpPixmap() {
	
	}
}