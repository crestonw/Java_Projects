/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Course Lecture Slides and Example Code and Textbook, stackoverflow.com 
 */


// Import Statements
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Assignment7 {

	private static Boolean finished = false;
	private static final LinkedBlockingQueue<String> stringQ = new LinkedBlockingQueue<String>(100);

	public static void main(String[] args) throws InterruptedException {

		Producer producer2 = new Producer("Producer 2");
		Producer producer1 = new Producer("Producer 1");
		Consumer consumer1 = new Consumer("Consumer 1");
		Consumer consumer2 = new Consumer("Consumer 2");
		Consumer consumer3 = new Consumer("Consumer 3");
		Consumer consumer4 = new Consumer("Consumer 4");


		ExecutorService executor = Executors.newCachedThreadPool();

		executor.submit(producer2);
		executor.submit(producer1);
		executor.submit(consumer1);
		executor.submit(consumer2);
		executor.submit(consumer3);
		executor.submit(consumer4);

		executor.shutdown();

		if (executor.awaitTermination(15, TimeUnit.SECONDS)) {

			System.out.println("Summary:");
			System.out.printf("\'%s\' produces %d events.\n", producer2.getName(), producer2.getSize());
			System.out.printf("\'%s\' produces %d events.\n", producer1.getName(), producer1.getSize());
			System.out.printf("\'%s\' consumes %d events.\n", consumer1.getName(), consumer1.getConsumed());
			System.out.printf("\'%s\' consumes %d events.\n", consumer2.getName(), consumer2.getConsumed());
			System.out.printf("\'%s\' consumes %d events.\n", consumer3.getName(), consumer3.getConsumed());
			System.out.printf("\'%s\' consumes %d events.\n", consumer4.getName(), consumer4.getConsumed());
		}

	}

	public static class Producer implements Runnable {

		private int size = 0;
		private String _name;

		public Producer(String name) {

			_name = name;
		}

		public int getSize() {
			
			return size;
		}
		public String getName() {

			return _name;
		}

		@Override
		public void run() {

			try {
				
				Random generator = new Random();

				for (int i = 1; i <= 1000; i++) {

					Double dub = generator.nextDouble();

					stringQ.put(dub.toString());
					size++;

					if ((i % 100) == 0) {
						System.out.printf("\'%s\': %d  events produced\n", _name, i);
					}
					
					if (i == 1000) {
						finished = true;
					}
				}
			}

			catch (InterruptedException e) {

				e.printStackTrace();

			}


		}

	}
	public static class Consumer implements Runnable {

		private String _name;
		private static final int MAX_WAIT_MS = 10;
		private int consumed = 0;

		public Consumer(String name) {

			_name = name;

		}

		public String getName() {

			return _name;

		}

		public int getConsumed() {

			return consumed;

		}

		@Override
		public void run() {

			try {

				Random generator = new Random();

				while ((!finished) || (!stringQ.isEmpty())) {
					
				
					Thread.sleep(generator.nextInt(MAX_WAIT_MS));
					
					
					if (stringQ.poll(generator.nextInt(MAX_WAIT_MS), TimeUnit.MILLISECONDS) != null) {

						consumed++;
						
					}


					if ((consumed % 100) == 0) {

						System.out.printf("\'%s\': %d events consumed\n", _name, consumed);

					}	


				}
			}

			catch (InterruptedException e) {

				e.printStackTrace();

			}
		}

	}

}
