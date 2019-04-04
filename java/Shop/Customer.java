package Shop;


public class Customer {
	private int arrivalTime;
	private int finishTime;
	private int processingPerioad;
	private int id;

	public Customer(int arrivalTime, int processingPerioad, int id) {
		super();
		this.arrivalTime = arrivalTime;
		this.processingPerioad = processingPerioad;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getProcessingPerioad() {
		return processingPerioad;
	}

	public void setProcessingPerioad(int processingPerioad) {
		this.processingPerioad = processingPerioad;
	}

}
