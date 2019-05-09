/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Course Lecture Slides and Example Code and Textbook, stackoverflow.com 
 */

// Import Statements
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ExtraServer {

	// Initialize private variables for Server class.
	private static ArrayList<Integer> list;
	private static final int PORT = 1337;

	// Main method.
	public static void main(String[] args) throws EOFException {	

		// Initialize local variables.
		ServerSocket serverSocket = null;
		Socket socket = null;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;

		// Try-Catch Clause.
		try {

			// Server socket is created at given port number.
			serverSocket = new ServerSocket(PORT);

			// Waiting for a client.
			socket = serverSocket.accept();
			
			// The server is always open for clients.
			while(true) {


				// Once we have a client, the server obtains an input object from the client and saves the value for further use.
				inputStream = new ObjectInputStream(socket.getInputStream());
				list = (ArrayList<Integer>) inputStream.readObject();

				// An ObjectOutputStream is established between server and client so that the primes list can be sent to the client.
				outputStream = new ObjectOutputStream(socket.getOutputStream());
				outputStream.writeObject(findPrimes(list));
				outputStream.flush();

				// The connections to the client are closed, but the server socket is left open for potential clients.
				if(socket.isClosed()) {
					
					serverSocket.close();
					
				}

			}
		}

		catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		} 
	}

	// This method sorts through a list given and returns a new list of primes obtained from the argument list.
	public static ArrayList<Integer> findPrimes(ArrayList<Integer> array){

		// New empty prime list.
		ArrayList<Integer> primeArray = new ArrayList<Integer>(1000);

		// Loops through the items in the ArrayList given as an argument.
		for(Integer integer: array) {

			// Counter and Switch
			Boolean prime = true;
			int i = 2;
			
			// Each number is checked to see if they're prime or not.
			while(prime && i <= integer) {

				if(integer == 2) {
					prime = false;
					break;
				}
				
				if((integer % i == 0) && (i != integer)) {
					prime = false;
					break;
				}

				i++;
			}
			
			// All the primes are added to the prime list.
			if(prime) {
				primeArray.add(integer);
			}
			
		}

		// The prime list is returned.
		return primeArray;	
	}
}
