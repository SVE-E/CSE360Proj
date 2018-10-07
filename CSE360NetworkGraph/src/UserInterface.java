import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.IOException;

public class UserInterface extends JFrame {

	
	private JLabel labelActivities, labelDuration, labelDurationError, labelDoneError;
	private JTextField activitiesTxt, durationTxt;
	private JButton plusBtn, doneBtn, helpBtn, aboutBtn, restartBtn;
	private JCheckBox startingCheckBox;
	private JLabel currentActivityTextArea;
	private JScrollPane scroll;
	
	
	private String activitiesString;
	private String durationString;
	private int durationInt;
	private Boolean checkBln;
	
	public UserInterface()
	{
		
//checkbox
		startingCheckBox = new JCheckBox("Starting");
		
	
//LABELS	
	labelActivities = new JLabel("Activities");
	labelDuration = new JLabel("Duration");
	labelDurationError = new JLabel("Duration must be an integer");
	labelDoneError = new JLabel("You must add an activity");
	
	//BUTTONS
	plusBtn = new JButton("Add");
	doneBtn = new JButton("Done");
	helpBtn = new JButton("Help");
	restartBtn = new JButton("Restart");
	aboutBtn = new JButton("About");
	
	//TEXTFIELDS
	
	activitiesTxt = new JTextField(15);
	durationTxt = new JTextField(15);
	
	currentActivityTextArea = new JLabel("Current Activities	");

	
	JPanel panelA = new JPanel();
	JPanel panelB = new JPanel();
	JPanel panelC = new JPanel();
	JPanel borderA = new JPanel();
	
	panelA.setLayout(new GridLayout(3,2));
	panelA.add(labelActivities);
	panelA.add(activitiesTxt);
	panelA.add(activitiesTxt);
	
	panelA.add(labelDuration);
	panelA.add(durationTxt);
	panelA.add(startingCheckBox);
	
	
	panelB.setLayout(new GridLayout(1,2));
	panelB.add(plusBtn);
	panelB.add(currentActivityTextArea);
	
	panelC.add(doneBtn);
	panelC.add(helpBtn);
	panelC.add(aboutBtn);
	panelC.add(restartBtn);
	
	borderA.add(panelA, BorderLayout.NORTH);
	borderA.add(panelB, BorderLayout.CENTER);
	borderA.add(panelC, BorderLayout.SOUTH);
	
	this.add(borderA);
	
	ButtonListener addListener = new ButtonListener();
	plusBtn.addActionListener(addListener);
	
	DoneListener donelist = new DoneListener();
	doneBtn.addActionListener(donelist);
	
	
	if(startingCheckBox.isSelected() == true) {
	  checkBln = true;
	} else if (startingCheckBox.isSelected() == false) {
		checkBln = false;
	}
	
	

	
	}
	public class ButtonListener implements ActionListener
	{
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == plusBtn) {
		activitiesString = activitiesTxt.getText();
		durationString = durationTxt.getText();
		try
		{
		durationInt = Integer.parseInt(durationTxt.getText());
		}
		 catch(NumberFormatException ex ) {
	 		 
	 		 labelDurationError.setText("Please enter a number for the book version and price.");  //error
	 	 }
		if(activitiesString.isEmpty() || durationString.isEmpty()) {
			
			labelDurationError.setText("You must fill out every field");	
		}
		}
	}
	}
	public class DoneListener implements ActionListener
	{
	public void actionPerformed(ActionEvent event)
	{
	
	}
	}
	
}


