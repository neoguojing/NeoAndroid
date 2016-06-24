package com.neo.neoapp.socket.client;

import java.io.IOException;


import java.net.InetSocketAddress;

import java.net.SocketAddress;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import com.neo.neoandroidlib.NeoSocketSerializableUtils;
import com.neo.neoandroidlib.NeoThreadPool;
import com.neo.neoapp.NeoAppSetings;

public class NeoAyncSocketClient {

	private String Localhost = "127.0.0.1";
	private SocketChannel client = null;
	private Selector select = null;
	private boolean isRunning = false;
	
	public NeoAyncSocketClient(){
		isRunning = true;
		try {
			select = Selector.open();
			SocketAddress addr = new InetSocketAddress(Localhost,NeoAppSetings.port);
			client = SocketChannel.open(addr);
			client.configureBlocking(false);
			client.register(select, SelectionKey.OP_READ);
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public NeoAyncSocketClient(String addr){
		isRunning = true;
		try {
			select = Selector.open();
			SocketAddress saddr = new InetSocketAddress(addr,NeoAppSetings.port);
			client = SocketChannel.open(saddr);
			client.configureBlocking(false);
			client.register(select, SelectionKey.OP_READ);
			receive();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void receive(){
		NeoThreadPool.getThreadPool().execute(
				new RecvTask());
	}
	
	private class RecvTask implements Runnable {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			ByteBuffer bf = ByteBuffer.allocate(1024);
			while(isRunning){
				try {
					select.select();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (SelectionKey sk:select.selectedKeys()){
					select.selectedKeys().remove(sk);
					if (sk.isReadable()){
						SocketChannel sc = (SocketChannel) sk.channel();
						bf.clear();
						try {
							sc.read(bf);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Object object = NeoSocketSerializableUtils.byteArrayToObject(new byte[bf.capacity()]);
					}
				}
			}
		}
		
		
	}
	
	public boolean send(Object content){
		byte[] bytes = NeoSocketSerializableUtils.objectToByteArray(content);
		
		if (bytes==null)
			return false;
		
		try {
			client.write(ByteBuffer.wrap(bytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public void close(){
		try {
			
			isRunning = false;
			client.close();
			select.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

