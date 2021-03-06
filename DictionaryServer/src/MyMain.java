import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyMain{
	public static void main (String[] args) {
		
	  Map<String, String> words = new HashMap<>();
	  words.put("apple", "ផ្លែប៉ោម");
	  words.put("book", "សៀវភៅ");
	  words.put("car", "រថយន្ត");
	  
	  try {
		  // binding connection
		System.out.println("Binding connection...");
		ServerSocket serverSocket = new ServerSocket(1024);
		// waiting to accept connection
		
			System.out.println("Wating for a user...");
			Socket connection = serverSocket.accept();
			
				// loading input stream
				InputStream input = connection.getInputStream();
				Scanner reader = new Scanner(input);
				
				// loading output stream
				String enWord = reader.nextLine();
				do {
					String khWord = words.get(enWord);
					if(khWord == null) {
						khWord = "not found";
					}
					OutputStream output = connection.getOutputStream();
					PrintWriter writer = new PrintWriter(output, true);
					writer.println(khWord);
					
					// prevent error No line found
//					if(reader.hasNextLine()) {
//						enWord = reader.nextLine();	
//					}
					enWord = reader.nextLine();
				}while(!enWord.equals("//close"));
//				while(!enWord.equals("//close")) {
//					String khWord = words.get(enWord);
//					if(khWord == null) {
//						khWord = "not found";
//					}
//					OutputStream output = connection.getOutputStream();
//					PrintWriter writer = new PrintWriter(output, true);
//					writer.println(khWord);
//					
//					// prevent error No line found
//					if(reader.hasNextLine()) {
//						enWord = reader.nextLine();	
//					}
////					enWord = reader.nextLine();
//				}
				reader.close();
				connection.close();
				serverSocket.close();		
		
	  } catch (IOException e) {
	    	// TODO Auto-generated catch block
		  e.printStackTrace();
	  }	
	  
	  
	}	
}
