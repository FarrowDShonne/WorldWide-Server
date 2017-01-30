package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.SwingUtilities;

import client.ClientGUI;

public class Server {
	
	public ServerSocket serverSocket;
	public Socket connection;
	public ObjectOutputStream outputStream;
	public ObjectInputStream inputStream;
	
	public Server(){
		startServer();
	}
	
	public void startServer(){
		try{
			serverSocket = new ServerSocket(6581, 10);
			while(true){
				try{
					waitForConnection();
					setupStreams();
					whileChatting();
				}catch(EOFException eof){
					System.out.println("Fdsfsfsf");
					
				}finally{
					closeStreams();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}



	



	public void waitForConnection() throws IOException {
		connection = serverSocket.accept();
		System.out.println("Now Connected to " + connection.getInetAddress().getHostName());
		
	}
	
	
	public void setupStreams() throws IOException{
		outputStream = new ObjectOutputStream(connection.getOutputStream());
		outputStream.flush();
		
		inputStream = new ObjectInputStream(connection.getInputStream());

	}
	

	
	public void closeStreams() {
		try{
			outputStream.close();
			inputStream.close();
			connection.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void whileChatting() throws IOException{
		String message;
		while(true){
			try{
				message = (String) inputStream.readObject();
				showMessage(message);
			}catch(ClassNotFoundException e){
				
			}
		}
		
	}
	
	public  void sendMessage(String message){
		try{
			outputStream.writeObject(message);
			outputStream.flush();
			showMessage("\n" + message);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void showMessage(String text){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						ClientGUI.incomingMessagesGUI.append(text);
					}
				}
				);
		
	}
	

	

	

}
