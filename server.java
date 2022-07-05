import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner ;
import java.util.stream.Collectors;
import java.util.*;
public class server { 

	public static void main(String[] args) {
		try { 
			ServerSocket serverSocket = new ServerSocket(5000); 
			System.out.println(" The Server is booted up and waiting for clietn to  connect");
			while(true)
			{
			Socket clientSocket= serverSocket.accept(); 
			session clientSession =new session (clientSocket); 
			clientSession.start();
			}
			}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	} 
	

}
