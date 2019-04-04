package View;
import Shop.Checkout;
import Shop.Customer;
import Simulation.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class View extends JFrame {
	private boolean startFirstTime = true;
	JLabel arrivingTime;
	JLabel servireTime;
	JLabel nrOfCheckouts;
	JLabel simulationTime;
	JLabel nrPersoane;
	JLabel minimum1;
	JLabel minimum2;
	JLabel maximum1;
	JLabel maximum2;
	JLabel queueList;
	public JLabel totalTimeOnView;
	JLabel[] queues;
	public JTextField[] clientsInQueues;
	JPanel[] panelsForQueues;
	Border[] borders;

	JTextField min1;
	JTextField max1;
	JTextField min2;
	JTextField max2;
	JTextField checkouts;
	JTextField personNumbers;
	JTextField simulation;

	public JTextArea logArea;
	JScrollPane scrollBar;

	JPanel finalPanel;
	JPanel mainPanel;
	JPanel panelLeft;
	JPanel panelMiddle;
	JPanel panelRight;

	JPanel arrivingHelp;
	JPanel serviceHelp;
	JPanel clientsHelp;
	JPanel checkoutsHelp;
	JPanel simulationHelp;

	JPanel panelForCentering0;
	JPanel panelForCentering1;
	JPanel panelForCentering2;
	JPanel panelForCentering3;

	JButton startButton;

	public int minArrTime = 0;
	public int maxArrTime = 0;
	public int minProcTime = 0;
	public int maxProcTime = 0;
	public int checkNum = 0;
	public int simulTime = 0;
	public int clientsNum = 0;

	public View() {
		arrivingTime = new JLabel("Arriving time of clients: ");
		servireTime = new JLabel("Servire time of clients: ");
		nrOfCheckouts = new JLabel("Checkouts number: ");
		simulationTime = new JLabel("Simulation time: ");
		nrPersoane = new JLabel("Number of clients: ");
		minimum1 = new JLabel("minimum");
		maximum1 = new JLabel("maximum");
		minimum2 = new JLabel("minimum");
		totalTimeOnView = new JLabel("Real time: ");
		maximum2 = new JLabel("maximum");
		queueList = new JLabel("List of checkouts");
		min1 = new JTextField(7);
		max1 = new JTextField(7);
		min2 = new JTextField(7);
		max2 = new JTextField(7);
		checkouts = new JTextField(5);
		personNumbers = new JTextField(5);
		simulation = new JTextField(5);

		startButton = new JButton("Start");

		logArea = new JTextArea(30, 40);
		scrollBar = new JScrollPane(logArea);
		scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		panelLeft = new JPanel();
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));

		panelMiddle = new JPanel();
		panelMiddle.setLayout(new BoxLayout(panelMiddle, BoxLayout.Y_AXIS));

		panelRight = new JPanel();

		finalPanel = new JPanel();

		arrivingHelp = new JPanel();
		arrivingHelp.setLayout(new BoxLayout(arrivingHelp, BoxLayout.X_AXIS));

		serviceHelp = new JPanel();
		serviceHelp.setLayout(new BoxLayout(serviceHelp, BoxLayout.X_AXIS));

		checkoutsHelp = new JPanel();
		checkoutsHelp.setLayout(new BoxLayout(checkoutsHelp, BoxLayout.X_AXIS));

		clientsHelp = new JPanel();
		clientsHelp.setLayout(new BoxLayout(clientsHelp, BoxLayout.X_AXIS));

		simulationHelp = new JPanel();
		simulationHelp.setLayout(new BoxLayout(simulationHelp, BoxLayout.X_AXIS));

		panelForCentering0 = new JPanel();
		panelForCentering0.setLayout(new FlowLayout());
		panelForCentering0.add(queueList);

		panelForCentering1 = new JPanel();
		panelForCentering1.setLayout(new FlowLayout());
		panelForCentering1.add(arrivingTime);

		panelForCentering2 = new JPanel();
		panelForCentering2.setLayout(new FlowLayout());
		panelForCentering2.add(servireTime);

		panelForCentering3 = new JPanel();
		panelForCentering3.setLayout(new FlowLayout());
		panelForCentering3.add(startButton);

		arrivingHelp.setLayout(new FlowLayout());
		arrivingHelp.add(Box.createRigidArea(new Dimension(5, 0)));
		arrivingHelp.add(minimum1);
		arrivingHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		arrivingHelp.add(min1);
		arrivingHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		arrivingHelp.add(maximum1);
		arrivingHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		arrivingHelp.add(max1);
		arrivingHelp.add(Box.createRigidArea(new Dimension(5, 0)));

		serviceHelp.setLayout(new FlowLayout());
		serviceHelp.add(Box.createRigidArea(new Dimension(5, 0)));
		serviceHelp.add(minimum2);
		serviceHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		serviceHelp.add(min2);
		serviceHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		serviceHelp.add(maximum2);
		serviceHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		serviceHelp.add(max2);
		serviceHelp.add(Box.createRigidArea(new Dimension(5, 0)));

		checkoutsHelp.setLayout(new FlowLayout());
		checkoutsHelp.add(Box.createRigidArea(new Dimension(5, 0)));
		checkoutsHelp.add(nrOfCheckouts);
		checkoutsHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		checkoutsHelp.add(checkouts);
		checkoutsHelp.add(Box.createRigidArea(new Dimension(5, 0)));

		clientsHelp.setLayout(new FlowLayout());
		clientsHelp.add(Box.createRigidArea(new Dimension(5, 0)));
		clientsHelp.add(nrPersoane);
		clientsHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		clientsHelp.add(personNumbers);
		clientsHelp.add(Box.createRigidArea(new Dimension(5, 0)));

		simulationHelp.setLayout(new FlowLayout());
		simulationHelp.add(Box.createRigidArea(new Dimension(5, 0)));
		simulationHelp.add(simulationTime);
		simulationHelp.add(Box.createRigidArea(new Dimension(3, 0)));
		simulationHelp.add(simulation);
		simulationHelp.add(Box.createRigidArea(new Dimension(5, 0)));

		panelRight.add(scrollBar);

		panelMiddle.add(panelForCentering0);

		panelLeft.add(Box.createRigidArea(new Dimension(0, 8)));
		panelLeft.add(panelForCentering1);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 1)));
		panelLeft.add(arrivingHelp);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 1)));
		panelLeft.add(panelForCentering2);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 1)));
		panelLeft.add(serviceHelp);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 1)));
		panelLeft.add(checkoutsHelp);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 1)));
		panelLeft.add(clientsHelp);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 1)));
		panelLeft.add(simulationHelp);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 1)));
		panelLeft.add(panelForCentering3);
		panelLeft.add(Box.createRigidArea(new Dimension(0, 5)));

		mainPanel.add(panelLeft);
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL));
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL));
		mainPanel.add(panelMiddle);
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL));
		mainPanel.add(new JSeparator(SwingConstants.VERTICAL));
		mainPanel.add(panelRight);

		serviceHelp.add(Box.createRigidArea(new Dimension(5, 0)));

		this.setTitle("Simulation of shopping checkout");
		this.setSize(1500, 580);
		this.setLocation(760, 390);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}

	public void updateGui() {
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void startTheAction(Thread t) {
		t.start();
	}

	public void addButtonListener(final Thread t) {
		startButton.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				if (startFirstTime == true) {
					startFirstTime = false;
					String minArrivingTime = min1.getText();
					String maxArrivingTime = max1.getText();
					String minProcessingTime = min2.getText();
					String maxProcessingTime = max2.getText();
					String checkoutsNum = checkouts.getText();
					String simulationTime = simulation.getText();
					String clientsNumb = personNumbers.getText();

					try {
						minArrTime = Integer.parseInt(minArrivingTime);
						maxArrTime = Integer.parseInt(maxArrivingTime);
						minProcTime = Integer.parseInt(minProcessingTime);
						maxProcTime = Integer.parseInt(maxProcessingTime);
						checkNum = Integer.parseInt(checkoutsNum);
						simulTime = Integer.parseInt(simulationTime);
						clientsNum = Integer.parseInt(clientsNumb);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null,
								"Data not introduced correctly, characters are not allowed!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					if (minArrTime > 0 && maxArrTime > 0 && minProcTime > 0 && maxProcTime > 0 && checkNum > 0
							&& simulTime > 0 && clientsNum > 0 && minArrTime < maxArrTime && minProcTime < maxProcTime) {
						queues = new JLabel[checkNum];
						for (int i = 0; i < checkNum; i++) {
							String nameOfQueue = "Checkout nr " + (i + 1);
							queues[i] = new JLabel(nameOfQueue);
						}

						clientsInQueues = new JTextField[checkNum];
						for (int i = 0; i < checkNum; i++) {
							clientsInQueues[i] = new JTextField(25);
						}

						borders = new Border[checkNum];
						for (int i = 0; i < checkNum; i++) {
							borders[i] = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
							clientsInQueues[i].setBorder(borders[i]);
						}

						panelsForQueues = new JPanel[checkNum + 1];
						for (int i = 0; i < checkNum; i++) {
							panelsForQueues[i] = new JPanel();
							panelsForQueues[i].setLayout(new FlowLayout());
							panelsForQueues[i].add(queues[i]);
							panelsForQueues[i].add(clientsInQueues[i]);

							panelMiddle.add(panelsForQueues[i]);
						}
						panelsForQueues[checkNum] = new JPanel();
						panelsForQueues[checkNum].setLayout(new FlowLayout());
						panelsForQueues[checkNum].add(totalTimeOnView);

						panelMiddle.add(panelsForQueues[checkNum]);
						updateGui();
						startTheAction(t);
					} else {
						JOptionPane.showMessageDialog(null,
								"Please introduce positive integers as data information, greater than 0!",
								"Invalid data", JOptionPane.ERROR_MESSAGE);
						startFirstTime = true;
					}
				}
			}
		});

	}
}

