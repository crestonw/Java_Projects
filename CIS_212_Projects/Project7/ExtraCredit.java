/* Author: Creston Wilson
 * References: Professor Lei Jiao, CIS212 Course Lecture Slides and Example Code and Textbook, stackoverflow.com 
 */


// Import Statements
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;




public class ExtraCredit {

	private static final Random generator = new Random();
	private static final int maxEvents = 1000;
	private static final LinkedList<String> stringL = new LinkedList<String>();
	private static Boolean finished = false;
	private static int events = 1;

	public synchronized static int getEvents() {

		return events;

	}


	public static void main(String[] args) throws InterruptedException {


		Producer producer2 = new Producer("Producer 2", stringL);
		Producer producer1 = new Producer("Producer 1", stringL);
		Consumer consumer1 = new Consumer("Consumer 1", stringL);
		Consumer consumer2 = new Consumer("Consumer 2", stringL);
		Consumer consumer3 = new Consumer("Consumer 3", stringL);
		Consumer consumer4 = new Consumer("Consumer 4", stringL);



		ExecutorService executor = Executors.newCachedThreadPool();

		executor.execute(producer2);
		executor.execute(producer1);
		executor.execute(consumer1);
		executor.execute(consumer2);
		executor.execute(consumer3);
		executor.execute(consumer4);

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
		private LinkedList<String> _list;

		public Producer(String name, LinkedList<String> list) {

			_list = list;
			_name = name;
		}

		public int getSize() {

			return size;
		}
		public String getName() {

			return _name;
		}

		@Override
		public void run(){

			try {

				for (; getEvents() <= maxEvents; blockingAdd()) {

					Double dub = generator.nextDouble();

					blockingPut(_list, dub.toString());
					size++;

					if ((getEvents() % 100) == 0) {
						System.out.printf("\'%s\': %d  events produced\n", _name, getEvents());

						Thread.currentThread().wait(1000);

					}

					if (getEvents() == maxEvents) {
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
		private LinkedList<String> _list;

		public Consumer(String name, LinkedList<String> list) {

			_list = list;
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

				while ((!finished) || (!_list.isEmpty())) {


					Thread.sleep(generator.nextInt(MAX_WAIT_MS));

					blockingRemove(_list);
					consumed++;

					if ((consumed % 100) == 0) {

						System.out.printf("\'%s\': %d events consumed\n", _name, consumed);

					}	

				}

				_list.notify();

			}

			catch (InterruptedException e) {

				e.printStackTrace();

			}
		}

	}

	public synchronized static void blockingPut(LinkedList<String> list, String string) throws InterruptedException {

		while(getEvents() < maxEvents) {

			list.add(string);

		}

	}
	public synchronized static void blockingRemove(LinkedList<String> list) throws InterruptedException {

		while(!list.isEmpty()) {

			list.remove();

		}


	}


	public synchronized static void blockingAdd() throws InterruptedException {

		events += 1;

	}

}
