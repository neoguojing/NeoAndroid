package com.neo.neoapp.socket.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import com.neo.neoapp.NeoAppSetings;

public class NeoSocketClient {

	private String Localhost = "127.0.0.1";
	private Socket client = null;
	
	public NeoSocketClient(){
		try {
			client = new Socket(Localhost,NeoAppSetings.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public NeoSocketClient(String addr){
		try {
			client = new Socket(addr,NeoAppSetings.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public boolean send(Object content){
		ObjectOutputStream sender = null;
		try {
			sender = new ObjectOutputStream(client.getOutputStream());
			sender.writeObject(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				sender.flush();
				sender.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return true;
	}
	
	public void close(){
		try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
