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
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExtraClient {

	// The shared port number between the server and client.
	private static final int PORT = 1337;
	private static Boolean quit = false;
	// An input for the user is created to catch for later use.
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static Socket socket = null;
	private static ObjectOutputStream outputStream = null;
	private static ObjectInputStream inputStream = null;
	private static Boolean start = false;

	// Main method
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException{

		//Try-Catch Clauses.
		try {

			// The IP Address for this computer is obtained and then used to create a client socket.
			InetAddress address = InetAddress.getLocalHost();
			socket = new Socket(address, PORT);


			// An ObjectOutputStream is created to send the list obtained from the client to the server.
			outputStream = new ObjectOutputStream(socket.getOutputStream());

			// An ObjectInputStream is created to receive the prime numbers list from the server.
			inputStream = new ObjectInputStream(socket.getInputStream());
			
			
			Input user = new Input();
			Looper loop = new Looper();			
			
			Thread t1 = new Thread(user);
			Thread t2 = new Thread(loop);
			
			t1.run();
			t1.join();
			t2.run();
			
			
		} 
		catch (IOException ex) {
			System.out.println();
		}

		finally {
			try {
				// All streams and sockets are closed.
				if (in != null) in.close();
				if (outputStream != null) outputStream.close();
				if (inputStream != null) inputStream.close();
				if (socket != null && !socket.isClosed()) socket.close();

			}
			catch (IOException ex) {
				System.out.println();
			}
		}
	}

	public static class Input implements Runnable {

		public void run() {

			try {

				// Prompt
				System.out.println("Enter '!' to start and stop, '#' to quit:");
				String input = in.readLine().trim();

				if(input.equals("#")) {

					quit = true;
					return;
				} 

				if(input.equals("!") && start) {

					System.out.println("Enter '!' to start and stop, '#' to quit:");
					input = in.readLine().trim();
				}

				if(input.equals("!")) {

					start = true;
					System.out.println(start);
				}
			}


			catch(IOException ex) {
				System.out.println();
			}
		}

	}

	public static class Looper implements Runnable {

		public void run() {

			try {
				// A while loop for the client, it's finished whenever the user enters a "!".
				while(!quit) {

					while(start) {

						list.clear();

						Random gen = new Random();

						for(int i = 0; i < 5; i++) {

							// Randomly generated int in the interval [2, 100).
							int integer = gen.nextInt(99 - 2) + 2;

							// Otherwise add user input to the list.
							list.add(integer);

						}


						outputStream.writeObject(list);
						outputStream.flush();

						// Print out the sent list.
						System.out.printf("Send: %s%n", list);

						Object primes = inputStream.readObject();
						// Print out the prime list received.
						System.out.printf("Receive: %s%n", primes);

						Thread.sleep(1000);
					}
				}
			}
			catch (IOException | ClassNotFoundException | InterruptedException ex) {
				ex.printStackTrace();
			} 

		}
	}
}

