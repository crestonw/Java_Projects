/* Author: Creston Wilson

 * References: Professor Lei Jiao, CIS212 Course Lecture Slides and Example Code and Textbook, stackoverflow.com 
 */

// Import Statements
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

	// The shared port number between the server and client.
	private static final int PORT = 1337;

	// Main method
	public static void main(String[] args){

		// Initialize all the local variables.
		Socket socket = null;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;
		BufferedReader in = null;
		Boolean finished = false;
		ArrayList<String> list = new ArrayList<String>() ;

		//Try-Catch Clauses.
		try {

			// The IP Address for this computer is obtained and then used to create a client socket.
			InetAddress address = InetAddress.getLocalHost();
			socket = new Socket(address, PORT);
			
			// An input for the user is created to catch for later use.
			in = new BufferedReader(new InputStreamReader(System.in));

			// A while loop for the client, it's finished whenever the user enters a "!".
			while(!finished) {

				// Prompt
				System.out.println("Enter an integer ('!' to send):");
				String value = in.readLine().trim();

				// Exit loop if satisfied.
				if(value.equals("!")) {

					finished = true;
					break;

				}

				// Otherwise add user input to the list.
				list.add(value);

			}

			// An ObjectOutputStream is created to send the list obtained from the client to the server.
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(list);
			outputStream.flush();
			
			// Print out the sent list.
			System.out.printf("Send: %s%n", list);

			// An ObjectInputStream is created to receive the prime numbers list from the server.
			inputStream = new ObjectInputStream(socket.getInputStream());
			Object primes = inputStream.readObject();
			
			// Print out the prime list received.
			System.out.printf("Receive: %s%n", primes);

			// All streams and sockets are closed.
			in.close();
			outputStream.close();
			inputStream.close();
			socket.close();
		} 

		catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} 
	}
}