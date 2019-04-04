package Simulation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import Shop.Checkout;
import Shop.Customer;
import View.View;

public class Scheduler {
	private List<Checkout> checkouts;
	private int maxNumberOfServers;
	private View myView;
	private int currentPeakHour = 0;

	public Scheduler(int maxNumberOfServers, View view) {
		this.myView = view;
		this.maxNumberOfServers = maxNumberOfServers;
		checkouts = new LinkedList<Checkout>();

		int i = 0;
		while (i < maxNumberOfServers) {
			Checkout checkoutTemp = new Checkout(i + 1, myView);
			checkouts.add(checkoutTemp);
			i++;
		}
	}

	public boolean checkIfPeakHourChanged() {
		boolean state = false;
		int numberOfCustomersTemp = 0;
		Iterator<Checkout> myIt = checkouts.iterator();

		while (myIt.hasNext()) {
			Checkout checkoutTemp = myIt.next();
			numberOfCustomersTemp += checkoutTemp.getCustomers().size();
		}

		if (numberOfCustomersTemp > currentPeakHour) {
			state = true;
			currentPeakHour = numberOfCustomersTemp;
		}

		return state;
	}

	public boolean checkIfQueuesAreEmpty() {
		boolean empty = true;
		Iterator<Checkout> myIt = checkouts.iterator();

		myIt = checkouts.iterator();
		while (myIt.hasNext()) {
			Checkout checkTemp = myIt.next();
			if (checkTemp.getCustomers().size() != 0)
				empty = false;
		}

		return empty;
	}

	public void addTask(Customer customer, View myView) throws InterruptedException {
		Iterator<Checkout> myIt = checkouts.iterator();
		int idOfCheckoutWithMinimumOfCustomers = -1;
		AtomicInteger nrOfCustomers = new AtomicInteger(Checkout.MAX_CUSTOMERS + 1);
		while (myIt.hasNext()) {
			Checkout checkTemp = myIt.next();
			if (checkTemp.returnNrPers().get() < nrOfCustomers.get()) {
				idOfCheckoutWithMinimumOfCustomers = checkTemp.getId();
				nrOfCustomers.set(checkTemp.getCustomers().size());
			}
		}

		myIt = checkouts.iterator();
		while (myIt.hasNext()) {
			Checkout checkTemp = myIt.next();
			if (checkTemp.getId() == idOfCheckoutWithMinimumOfCustomers) {
				checkouts.get(checkTemp.getId() - 1).addCustomer(customer);
				break;
			}
		}
	}

	public List<Checkout> getCheckouts() {
		return checkouts;
	}

	public int getMaxNumberOfServers() {
		return maxNumberOfServers;
	}

	public void stopCheckouts() {
		Iterator<Checkout> myIt = checkouts.iterator();
		while (myIt.hasNext()) {
			myIt.next().setRunningFalse();
		}
	}

}

