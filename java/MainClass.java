import Simulation.SimulationManager;
import View.View;

public class MainClass {

	public static void main(String[] args) {
		View view = new View();
		SimulationManager simulationManager = new SimulationManager(view);
	}

}

