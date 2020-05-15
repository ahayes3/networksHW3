package com.csc445.andy

import java.net.*
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel

class Host(canvas:Canvas) : ConnectionManager(canvas) {
	val group = Inet4Address.getByName("224.0.0.1")
	val buff = ByteBuffer.allocate(512)
	val ni = NetworkInterface.getNetworkInterfaces().toList().firstOrNull { it.name != "lo" }
	val channel = DatagramChannel.open(StandardProtocolFamily.INET)
			.setOption(StandardSocketOptions.SO_REUSEADDR,true)
			.bind(InetSocketAddress(group,8080))
			.setOption(StandardSocketOptions.IP_MULTICAST_IF,ni)
	init {
		channel.configureBlocking(false)
		channel.join(group,ni)
	}
	override fun run() {
		println(Thread.currentThread().name)
		while(!stopped) {
			channel.receive(buff)
			buff.flip()
			//todo use whatever i just read
			
			//todo write my strokes if i have any
			
			
		}
		
	}
	
}