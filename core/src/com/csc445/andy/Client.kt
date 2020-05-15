package com.csc445.andy

import java.net.*
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

class Client(canvas:Canvas) : ConnectionManager(canvas) {
	val group = Inet4Address.getByName("224.0.0.1")
	val buff = ByteBuffer.allocate(512)
	val ni = NetworkInterface.getNetworkInterfaces().toList().firstOrNull { it.name != "lo" }
	val channel = DatagramChannel.open(StandardProtocolFamily.INET).setOption(StandardSocketOptions.SO_REUSEADDR,true).bind(InetSocketAddress(group,8080))
			.setOption(StandardSocketOptions.IP_MULTICAST_IF,ni)
	init {
		channel.configureBlocking(false)
	}
	override fun run() {
		while(!stopped) {
			channel.receive(buff)
			buff.flip()
		}
	}
	fun catchUp() {
		var something = false
		
		while(!something) {
			val req = Packet.ketchup()
		}
	}
}