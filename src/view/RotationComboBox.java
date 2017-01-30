package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import controller.Controller;
import drawingAdapter.OrientationE;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: a combo box to select orientation of a component</p>
 */	

public class RotationComboBox  extends JComboBox<String>
								implements ItemListener
{  
	
	private static final long serialVersionUID = -124613389428086607L;
	
	Controller controller;
	
	public RotationComboBox(Controller c){
		
		this.controller = c;
		String[] orientation = {"EAST","NORTH","SOUTH","WEST"};
		addItem(orientation[0]);
		addItem(orientation[1]);
		addItem(orientation[2]);
		addItem(orientation[3]);
		
		addItemListener(this);		
	}
	
	
	 public void itemStateChanged(ItemEvent e){
		 if(e.getStateChange() == ItemEvent.SELECTED){
			 if(getSelectedItem()=="EAST"){
				 controller.rotateComponent(OrientationE.EAST);
			 }
			 else if(getSelectedItem()=="NORTH"){
				 controller.rotateComponent(OrientationE.NORTH);
			 }
			 else if(getSelectedItem()=="SOUTH"){
				 controller.rotateComponent(OrientationE.SOUTH);
			 }
			 else controller.rotateComponent(OrientationE.WEST);
		 }
	 }
	    	   
	       

	
	
}
