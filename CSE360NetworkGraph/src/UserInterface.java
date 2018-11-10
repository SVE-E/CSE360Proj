import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.IOException;

public class UserInterface extends JFrame {

	
	private JLabel labelActivities, labelDuration, labelDurationError, labelDoneError, labelPredecesor;
	private JTextField activitiesTxt, durationTxt, predecesorTxt;
	private JButton plusBtn, doneBtn, helpBtn, aboutBtn, restartBtn;
	private JCheckBox startingCheckBox, criticalCheckBox;
	private JTextArea currentActivityTextArea;
	private JScrollPane scroll;
	private JList list;
	private int[] myIntArray = new int[3];
	
	//string to tokenize
	
	
	
	private String activitiesString;
	private String durationString;
	private String predecesorString;
	private int durationInt;
	private Boolean checkBln, criticalBln;
	private Object selected[];
	NodeOperator op = new NodeOperator();
	DefaultListModel model;
	
	public UserInterface()
	{
		
		
		
//checkbox
		startingCheckBox = new JCheckBox("Starting");
		startingCheckBox.addActionListener(new checkListener());
		startingCheckBox.setSelected(true);
		startingCheckBox.setEnabled(false);
		checkBln=true;
		
		criticalCheckBox = new JCheckBox("Critical Path");
		criticalCheckBox.addActionListener(new checkListener());
		criticalCheckBox.setSelected(false);
		criticalCheckBox.setEnabled(true);
		criticalBln = false;
	
//LABELS	

	labelActivities = new JLabel("Activities");
	labelDuration = new JLabel("Duration");
	labelPredecesor = new JLabel("Predecesor");

	labelActivities = new JLabel("Enter Activity Name: ");
	labelDuration = new JLabel("Enter Duration: ");

	labelDurationError = new JLabel("");
	labelDurationError.setForeground(Color.red);

	//BUTTONS
	plusBtn = new JButton("Add/Edit");
	doneBtn = new JButton("Done");
	helpBtn = new JButton("Help");
	restartBtn = new JButton("Restart");
	aboutBtn = new JButton("About");
	
	

	//TEXTFIELDS
	activitiesTxt = new JTextField(15);
	durationTxt = new JTextField(15);
	predecesorTxt = new JTextField(15);
	predecesorTxt.setEnabled(false);
	
	
	currentActivityTextArea = new JTextArea(15,15);
	currentActivityTextArea.setWrapStyleWord(true);
	currentActivityTextArea.setLineWrap(true);
	currentActivityTextArea.setEditable(false);
	
	model = op.getNodeModel();
	list = new JList(model);
	list.setVisibleRowCount(0);
	
	list.addListSelectionListener(new ListListener());
	setSize(300,300);
	setVisible(true);
	
	

	
	JPanel panelA = new JPanel();
	JPanel panelB = new JPanel();
	JPanel panelC = new JPanel();
	JPanel panelD = new JPanel();
	JPanel borderA = new JPanel();
	//row 1
	panelA.setLayout(new GridLayout(4,2));
	panelA.add(labelActivities);
	panelA.add(activitiesTxt);
	
	//row 2
	panelA.add(labelDuration);
	panelA.add(durationTxt);
	
	//row 3
	panelA.add(labelPredecesor);
	panelA.add(predecesorTxt);
	//row4
	panelA.add(startingCheckBox);
	panelA.add(labelDurationError);
	
	
	
	panelB.setLayout(new GridLayout(1,2));
	panelB.add(plusBtn);
	panelB.add(list);
	
	panelC.add(doneBtn);
	panelC.add(helpBtn);
	panelC.add(aboutBtn);
	panelC.add(restartBtn);
	panelC.add(criticalCheckBox);
	
	panelD.setLayout(new GridLayout(1,1));
	panelD.add(currentActivityTextArea);
	
	borderA.setLayout(new GridLayout(4,1));
	borderA.add(panelA);
	borderA.add(panelB);
	borderA.add(panelC);
	borderA.add(panelD);
	
	this.add(borderA);
	
	//Button listeners
	ButtonListener addListener = new ButtonListener();
	plusBtn.addActionListener(addListener);
	
	DoneListener donelist = new DoneListener();
	doneBtn.addActionListener(donelist);
	
	AboutListener aboutlist = new AboutListener();
	aboutBtn.addActionListener(aboutlist);
	
	helpBtn.addActionListener(new HelpListener());
	
	restartBtn.addActionListener(new RestartListener());
	
	selected= new Object[0];


	
	}
	
	public class ButtonListener implements ActionListener
	{
	public void actionPerformed(ActionEvent event)
	{
		String[] values;
		if(event.getSource() == plusBtn) {
		activitiesString = activitiesTxt.getText();
		durationString = durationTxt.getText();
		predecesorString = predecesorTxt.getText();
		
		//the values of predecesors separated by comma
		values = predecesorString.split(",");
		for(int i = 0; i<values.length; i++)
		{
			System.out.println(values[i]);
		}
		
		if(activitiesString.isEmpty() || durationString.isEmpty()) {
			labelDurationError.setText("Make sure all fields completed");
			return;
		
		}
		try
		{
		durationInt = Integer.parseInt(durationTxt.getText());
		}
		 catch(NumberFormatException ex ) {
	 		 
	 		 labelDurationError.setText("Please enter an integer for duration"); 
	 	
	 		 return;//error
	 	 }
		labelDurationError.setText("");
		//activitiesString;
		//durationInt
		//checkBln
			
		if(!checkBln && predecesorString.isEmpty()) {
			labelDurationError.setText("Please enter preceding actvities");
			return;
		}
		
		
		
	
		op.addNode(activitiesString, durationInt, values, checkBln);
		model = op.getNodeModel();
		list.setModel(model);
		
		//list.setVisibleRowCount(allNodes.size()+1);
		list.clearSelection();
		
		startingCheckBox.setEnabled(true);
		
		}
		
	}
	}
	
	
	public class ListListener implements ListSelectionListener
	{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			selected = list.getSelectedValues();
		}
		
	}
	
	public class checkListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(startingCheckBox.isSelected() == true) {
				  checkBln = true;
				  predecesorTxt.setEnabled(false);
				  predecesorTxt.setText("");
				  
			} else if (startingCheckBox.isSelected() == false) {
					checkBln = false;
					predecesorTxt.setEnabled(true);
					  predecesorTxt.setText("");
			}
			
			if(criticalCheckBox.isSelected()) {
				criticalBln = true;
			}
			else {
				criticalBln = false;
			}
			
			
		}
		
	}
	
	public class DoneListener implements ActionListener
	{
	public void actionPerformed(ActionEvent event)
	{
		currentActivityTextArea.setText("");
		op.setUpNexts();
		if(op.checkForLoop() == false) {
			if(op.checkForDisconnect() == false) {
				
			
				ArrayList<Path> paths = op.getAllPaths();
				if(criticalBln) {
					int i = 0;
					do {
						currentActivityTextArea.append(paths.get(i).toString()+"\n");
						i++;
					}while ((i < paths.size()) && (paths.get(i).getLength() == paths.get(i-1).getLength()));
				}
				else {
					for(int i = 0; i< paths.size();i++) {
						currentActivityTextArea.append(paths.get(i).toString()+"\n");
					}
				}
			}else {
				labelDurationError.setText("Disconnect in diagram");
			}
		}else {
			labelDurationError.setText("Loop exists");
		}
	
	}
	}
	
	public class RestartListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			op.clearAll();
			model.clear();
			currentActivityTextArea.setText("");
			durationTxt.setText("");
			activitiesTxt.setText("");
			selected = new Object[0];
			startingCheckBox.setEnabled(false);
			startingCheckBox.setSelected(true);
			checkBln=true;
			labelDurationError.setText("");
		}
		
	}
	public class AboutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			JOptionPane.showMessageDialog(null, "CSE 360 Release 1 made by Oscar Amaya, Seve Esposito, and Urgi.\n This program allows users to create a network diagram and see all paths.\n Users are able to enter each activity with its duration and required preceding activities.\n The program will return all paths and their lengths.");
		}
		
	}
	
	public class HelpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			JOptionPane.showMessageDialog(null, "How to use network diagram traverser:\n 1. Enter Activity name and duration (integer) \n 2. If activity has no proceding activities check Starting \n"
					+ "3. Select proceding activities using list on right\n (hold CTRL to select multiple)\n"
					+ "4. Click Done when finished to recieve output\n"
					+ "5. Click Restart to clear data and start again ");
		}
		
	}
	
	
	
}
