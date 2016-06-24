package com.neo.neoapp.socket.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.neo.neoandroidlib.NeoSocketSerializableUtils;
import com.neo.neoandroidlib.NeoThreadPool;
import com.neo.neoapp.NeoAppSetings;

public class NeoAyncSocketServer {
	
	private Selector selector;
	private ServerSocketChannel server;
	private boolean isRunning;
	
	
	NeoAyncSocketServer(){
		isRunning = true;
		try {
			server = ServerSocketChannel.open();
			server.configureBlocking(false);
			server.socket().bind(new InetSocketAddress(NeoAppSetings.port));
			selector = Selector.open();
			server.register(selector, SelectionKey.OP_ACCEPT);
			NeoThreadPool.getThreadPool().execute(
					new AcceptTask());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public boolean send(SocketChannel client,Object content){
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
		isRunning = false;
		try {
			server.close();
			selector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private class AcceptTask implements Runnable{

		public AcceptTask() {
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning){
				try {
					selector.select();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				for (SelectionKey sk:selector.selectedKeys()){
					selector.selectedKeys().remove(sk);
					if (sk.isAcceptable()){
						ServerSocketChannel serch = (ServerSocketChannel) sk.channel();
						SocketChannel client;
						try {
							client = serch.accept();
							client.configureBlocking(false);
							client.write(ByteBuffer.wrap(new String("hello client").getBytes()));
							client.register(selector, SelectionKey.OP_READ);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					if (sk.isReadable()){
						NeoThreadPool.getThreadPool().execute(
								new RecvTask((SocketChannel) sk.channel()));
					}
				}
			}
		}
		
	}
	
	private class RecvTask implements Runnable{
		private SocketChannel clent = null;
		
		public RecvTask(SocketChannel clent){
			this.clent = clent;
		}

		@Override
		public void run() {
			ByteBuffer bf = ByteBuffer.allocate(1024);
			// TODO Auto-generated method stub
			bf.clear();
			try {
				this.clent.read(bf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Object object = NeoSocketSerializableUtils.byteArrayToObject(new byte[bf.capacity()]);
		
		}
		
	}
	
}
