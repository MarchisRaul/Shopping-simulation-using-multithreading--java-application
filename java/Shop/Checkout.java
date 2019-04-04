package Shop;
import Simulation.Scheduler;
import Simulation.SimulationManager;
import View.View;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;



public class Checkout implements Runnable {
	public final static int MAX_CUSTOMERS = 10;
	private BlockingQueue<Customer> customers;
	private AtomicInteger waitingPeriod;
	private AtomicInteger freeCheckout;
	private AtomicInteger nrPers;
	private View myView;
	private boolean running = false;
	private boolean forcedBySimulationTime = false;
	private int id;
	Thread myThread;

	public Checkout(int id, View view) {
		customers = new ArrayBlockingQueue<Customer>(MAX_CUSTOMERS);
		waitingPeriod = new AtomicInteger(0);
		freeCheckout = new AtomicInteger(0);
		this.id = id;
		this.myView = view;
		nrPers = new AtomicInteger(0);
		myThread = new Thread(this);
		setRunningTrue();
		myThread.start();
	}

	public void addCustomer(Customer customer) {

		nrPers.incrementAndGet();

		customer.setFinishTime(customer.getArrivalTime() + customer.getProcessingPerioad() + waitingPeriod.get());
		customers.offer(customer);

		SimulationManager
				.setAverageServiceTime(SimulationManager.getAverageServiceTime() + customer.getProcessingPerioad());
		SimulationManager.setAverageWaitingTime(SimulationManager.getAverageWaitingTime() + waitingPeriod.get());

		waitingPeriod.set(waitingPeriod.get() + customer.getProcessingPerioad());

		myView.logArea.append("A sosit clientul " + customer.getId() + " la casa " + this.getId() + " cu arrival time "
				+ customer.getArrivalTime() + " si service time " + customer.getProcessingPerioad() + "\n");

		String tempString = myView.clientsInQueues[this.getId() - 1].getText();
		tempString = tempString + " O";
		myView.clientsInQueues[this.getId() - 1].setText(tempString);
	}

	public void addClientToGui() {
		int helpAfis = 0;
		String tempString = "";
		while (helpAfis < customers.size()) {
			tempString = tempString + " O";
			helpAfis++;
		}
		myView.clientsInQueues[this.getId() - 1].setText(tempString);
	}

	
	public void run() {
		while (running) {
			while (customers.isEmpty()) {
				try {
					myThread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				freeCheckout.incrementAndGet();
			}
			while (customers.isEmpty() == false) { // && forcedBySimulationTime == false (daca se vrea ca exact la
													// finalul simularii, sa se opreasca aplicatia)
				Customer customerTemp = null;
				customerTemp = customers.peek();
				//waitingPeriod.set(waitingPeriod.get() - customerTemp.getProcessingPerioad());
				try {
					myThread.sleep(customerTemp.getProcessingPerioad() * 1000);
				} catch (InterruptedException e) {
					System.out.println("");
				}
				waitingPeriod.set(waitingPeriod.get() - customerTemp.getProcessingPerioad());
				customerTemp = customers.poll();
				if (customerTemp != null)
					nrPers.decrementAndGet();
				myView.logArea.append("Clientul " + customerTemp.getId() + " a plecat din casa " + this.getId()
						+ ", avand timpul final " + customerTemp.getFinishTime() + "\n");
				addClientToGui();
			}
		}
	}

	public int getId() {
		return id;
	}

	public BlockingQueue<Customer> getCustomers() {
		return customers;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public AtomicInteger returnNrPers() {
		return nrPers;
	}

	public void setRunningTrue() {
		running = true;
		forcedBySimulationTime = false;
	}

	public void setRunningFalse() {
		SimulationManager.setEmptyQueuesTime(SimulationManager.getEmptyQueuesTime() + freeCheckout.get());
		forcedBySimulationTime = true;
		running = false;
	}

}

