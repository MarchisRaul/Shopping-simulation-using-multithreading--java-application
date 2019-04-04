package Simulation;
import View.View;
import Shop.Checkout;
import Shop.Customer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;



public class SimulationManager implements Runnable {
	private List<Customer> generatedCustomers;
	private View myView;
	private Scheduler scheduler;
	private Thread myThread;
	private int realTimeCounter = 0;

	private static float averageWaitingTime = 0.0f;
	private static int totalClients = 0;
	private static float averageServiceTime = 0.0f;
	private static float emptyQueuesTime = 0.0f;
	private static int totalQueues = 0;
	private static int peakHour = 0;

	public SimulationManager(View myView) {
		super();
		this.myView = myView;
		generatedCustomers = new LinkedList<Customer>();
		myThread = new Thread(this);
		myView.addButtonListener(myThread);
	}

	private void initializeInstances() {
		generateNRandomCustomers();
		totalClients = myView.clientsNum;
		totalQueues = myView.checkNum;
		scheduler = new Scheduler(myView.checkNum, myView);
	}

	private void generateNRandomCustomers() {
		int minArrival = myView.minArrTime;
		int maxArrival = myView.maxArrTime;
		int minProcessing = myView.minProcTime;
		int maxProcessing = myView.maxProcTime;
		int totalNumberOfClients = myView.clientsNum;

		int randomArrival = 0;
		int randomProcessing = 0;
		int i = 0;
		while (i < totalNumberOfClients) {
			Random r = new Random();
			randomArrival = r.nextInt((maxArrival - minArrival) + 1) + minArrival;
			randomProcessing = r.nextInt((maxProcessing - minProcessing) + 1) + minProcessing;
			Customer customer = new Customer(randomArrival, randomProcessing, i + 1);
			generatedCustomers.add(customer);

			i++;
		}
		Collections.sort(generatedCustomers, new Comparator<Customer>() {

			
			public int compare(Customer o1, Customer o2) {
				return o1.getArrivalTime() - o2.getArrivalTime();
			}
		});

	}

	public void checkForPeakHour() {
		if (scheduler.checkIfPeakHourChanged() == true)
			peakHour = realTimeCounter;
	}

	public static void calculateAverageTimes() {
		averageWaitingTime = averageWaitingTime / totalClients;
		averageServiceTime = averageServiceTime / totalClients;
		emptyQueuesTime = emptyQueuesTime / totalQueues;
	}

	public void printResults() {
		calculateAverageTimes();

		String waitingTimeAverage = "Average waiting time: " + averageWaitingTime + ".\n";
		String serviceTimeAverage = "Average service time: " + averageServiceTime + ".\n";
		String queuesEmptyTime = "Average time while queues were empty: " + emptyQueuesTime + ".\n";
		String peakHourTime = "Checkouts were the busiest at " + peakHour + " in real time.\n";
		String intervalTime = "All these happened between " + 0 + " and " + myView.simulTime;
		JOptionPane.showMessageDialog(null,
				waitingTimeAverage + serviceTimeAverage + queuesEmptyTime + peakHourTime + intervalTime,
				"End of simulation time", JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean checkIfQueuesAreEmpty() {
		return scheduler.checkIfQueuesAreEmpty();
	}

	
	public void run() {
		initializeInstances();
		while (realTimeCounter <= myView.simulTime) {
			checkForPeakHour();
			myView.totalTimeOnView.setText("" + realTimeCounter);

			Iterator<Customer> myIt = generatedCustomers.iterator();
			while (myIt.hasNext()) {
				Customer temp = myIt.next();
				if (temp.getArrivalTime() == realTimeCounter)
					try {
						scheduler.addTask(temp, myView);
					} catch (InterruptedException e) {
						System.out.println("");
					}
			}
			try {
				myThread.sleep(1000);
			} catch (InterruptedException e) {
			}
			realTimeCounter++;
		}
		scheduler.stopCheckouts();
		checkForPeakHour();
		while (true) {
			if (checkIfQueuesAreEmpty() == true) {
				printResults();
				break;
			}
		}
	}

	public static float getAverageWaitingTime() {
		return averageWaitingTime;
	}

	public static void setAverageWaitingTime(float averageWaitingTime) {
		SimulationManager.averageWaitingTime = averageWaitingTime;
	}

	public static float getAverageServiceTime() {
		return averageServiceTime;
	}

	public static void setAverageServiceTime(float averageServiceTime) {
		SimulationManager.averageServiceTime = averageServiceTime;
	}

	public static float getEmptyQueuesTime() {
		return emptyQueuesTime;
	}

	public static void setEmptyQueuesTime(float emptyQueuesTime) {
		SimulationManager.emptyQueuesTime = emptyQueuesTime;
	}

	public static int getPeakHour() {
		return peakHour;
	}

	public static void setPeakHour(int peakHour) {
		SimulationManager.peakHour = peakHour;
	}

}
