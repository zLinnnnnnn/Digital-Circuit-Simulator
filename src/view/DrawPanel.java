package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import drawingAdapter.*;
import controller.*;
import model.AbstractCircuitDiagram;
import model.ComponentType;
import model.ThreeValuedLogic;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: draw components in the diagram</p>
 */	

public class DrawPanel extends JPanel
					 implements ObserverI, ViewI,
					 MouseListener, MouseMotionListener{
		
	private static final long serialVersionUID = 7458176312364133683L;
	private Controller controller ;	
	private final ArrayList<ViewListenerI> Listeners = new ArrayList<ViewListenerI>();
	private JPopupMenu popupMenu1,popupMenu2;
	
	public DrawPanel (Controller controller, AbstractCircuitDiagram circuit){
		this.controller = controller;
		circuit.attachObserver(this);
		setVisible( true ) ;
		addMouseListener(this);
		addMouseMotionListener(this);
		repaint();	
		
		//Construct first JPopupMenu
		popupMenu1 = new JPopupMenu();   
		JMenuItem item1 = new JMenuItem("true"); 
		JMenuItem item2 = new JMenuItem("false");
		JMenuItem item3 = new JMenuItem("link");
        popupMenu1.add(item1);  
        popupMenu1.add(item2);
        popupMenu1.add(item3);
        
        //Define function of each item
		item1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.getChosenComponent().getInput(ThreeValuedLogic.TRUE);
				repaint(); }} ) ;	
		
		item2.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.getChosenComponent().getInput(ThreeValuedLogic.FALSE);
				repaint(); }} ) ;
		
		item3.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double x1 = controller.getChosenComponent().getX();
				double y1 = controller.getChosenComponent().getY();	
				double width = controller.getChosenComponent().getWidth();
				controller.addLink(x1+width/2, y1, x1+width/2+2, y1+2);
				int numberOfLinks = controller.getNumberOfLinks();
				controller.setEndPoint1(controller.getLinkInCircuit(numberOfLinks-1), controller.getChosenComponent());
				controller.getChosenComponent().setConnectedLink(controller.getLinkInCircuit(numberOfLinks-1));
				controller.setChosenLink(controller.getLinkInCircuit(numberOfLinks-1));	
				controller.setChosenComponent(null);
				controller.setAdjustFlag(true);
				repaint(); }} ) ;
		
		//Construct second popupMenu
		popupMenu2 = new JPopupMenu();   
		JMenuItem item4 = new JMenuItem("link");
        popupMenu2.add(item4);  
		item4.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double x1 = controller.getChosenComponent().getX();
				double y1 = controller.getChosenComponent().getY();	
				double width = controller.getChosenComponent().getWidth();
				controller.addLink(x1+width/2, y1, x1+width/2+2, y1+2);
				int numberOfLinks = controller.getNumberOfLinks();
				controller.setEndPoint1(controller.getLinkInCircuit(numberOfLinks-1), controller.getChosenComponent());
				controller.getChosenComponent().setConnectedLink(controller.getLinkInCircuit(numberOfLinks-1));
				controller.setChosenLink(controller.getLinkInCircuit(numberOfLinks-1));	
				controller.setChosenComponent(null);
				controller.setAdjustFlag(true);
				repaint();	}} ) ; }
	
	
	
	public void updateObserver(){ repaint();}	
	public void addListener( ViewListenerI listener) { Listeners.add( listener ) ; } 
	
	
	public void paintComponent( Graphics g ) {			
		super.paintComponent(g) ;
		if( !(g instanceof Graphics2D) )
			throw new AssertionError("Needs Graphics2D support." ) ;
		Graphics2D g2d = (Graphics2D) g ;	
		WorldToViewI worldToView = new WorldToView();
		AWTDrawingAdapter adapter = new AWTDrawingAdapter(g2d, worldToView);	
		adapter.setFillColor(255, 150, 153);//Set component color
		
		//Draw all components in diagram	
		int size = controller.getListSize();
		for(int i = 0; i < size; i++ ){			
			controller.getComponetInCircuit(i).drawComponent(adapter); }
		
		//Draw all links in diagram
		int sizeOfLinkList = controller.getNumberOfLinks();
		for(int j = 0; j < sizeOfLinkList; j++ ){ 
			controller.getLinkInCircuit(j).drawLink(adapter); }
		 
		//Notify user that current selected thing
		if(controller.getChosenComponent()!=null){
			adapter.drawText("One component is selected", 60, 30, 100, 30);
			//Display input and output value
			if(controller.getChosenComponent().getName()==ComponentType.INPUT){
				double x = controller.getChosenComponent().getX();
				double y = controller.getChosenComponent().getY();
				double width = controller.getChosenComponent().getWidth();
				double height = controller.getChosenComponent().getHeight();
				if(controller.getChosenComponent().getResult()==ThreeValuedLogic.TRUE){
					adapter.setTextColor(0, 255, 0);
					adapter.drawText("true", x, y-30, height+10, width+10);	}
				else if(controller.getChosenComponent().getResult()==ThreeValuedLogic.FALSE){
					adapter.setTextColor(255, 0, 0);
					adapter.drawText("false", x, y-30, height+10, width+10); }
				else{
					adapter.setTextColor(255, 0, 0);
					adapter.drawText("undefined", x, y-30, height+10, width+10); }}
			else if(controller.getChosenComponent().getName()==ComponentType.OUTPUT){
				if(controller.getChosenComponent().getPort(ComponentType.INPORT1).getConnectedPort()!=null){
					double x = controller.getChosenComponent().getX();
					double y = controller.getChosenComponent().getY();
					double width = controller.getChosenComponent().getWidth();
					double height = controller.getChosenComponent().getHeight();
					
					if(controller.getChosenComponent().getResult()==ThreeValuedLogic.TRUE){
						adapter.setTextColor(0, 255, 0);
						adapter.drawText("true", x, y-30, height+10, width+10);	}
					else if(controller.getChosenComponent().getResult()==ThreeValuedLogic.FALSE){
						adapter.setTextColor(255, 0, 0);
						adapter.drawText("false", x, y-30, height+10, width+10);}
					else {
						adapter.setTextColor(255, 0, 0);
						adapter.drawText("undefined", x, y-30, height+10, width+10); } } } }
		
		else if(controller.getChosenLink()!=null){
			adapter.drawText("One link is selected", 60, 30, 100, 30);	}
		
		
		//Draw text to tell user whether components and link are connecting together
		if(controller.getChosenLink()!=null){
			if(controller.getChosenLink().checkEndPoint1()){
				adapter.drawText("Connecting port 1", 60, 50, 100, 30);	}
			
			if(controller.getChosenLink().checkEndPoint2()){
				adapter.drawText("Connecting port 2", 60, 70, 100, 30); } } }
	
	
	/*
	 * Control mouse action
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */

	@Override
	public void mouseDragged(MouseEvent e) {
		if(controller.getChosenComponent()!=null)
			controller.moveComponent(e.getX(), e.getY());
					
		if (controller.getChosenLink()!=null){
			controller.moveLink(e.getX(), e.getY());
		}
		repaint(); }

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		 * After clicking adjust link button, one end of link would move with mouse move
		 */
		if(controller.getChosenLink()!=null&&controller.getChosenComponent()==null&&controller.checkAdjustFlag()){
			controller.getChosenLink().adjustLink(e.getX(), e.getY(), controller);		
			repaint(); } }

	@Override
	public void mouseClicked(MouseEvent e) {
	
		/*
		 * Left click of mouse
		 */
		if (e.getButton() == MouseEvent.BUTTON1){	
		
		/*
		 * Select one component
		 */
			int size = controller.getListSize();
			for(int i = 0; i < size; i++ ){
				double x = controller.getComponetInCircuit(i).getX();
				double y = controller.getComponetInCircuit(i).getY();
				double width = controller.getComponetInCircuit(i).getWidth();
				double height = controller.getComponetInCircuit(i).getHeight();
			
				if((e.getX()>x-(width/2))&&(e.getX()<x+(width/2))&&(e.getY()>y-(height/2))&&(e.getY()<y+(height/2))){				
				controller.setChosenComponent(controller.getComponetInCircuit(i));
				System.out.println("One component is selected");
				controller.setChosenLink(null); }	}
		
		/*
		 * Select one link
		 */
		if(!controller.checkAdjustFlag()){
			int sizeOfLinkList = controller.getNumberOfLinks();
			for(int j = 0; j < sizeOfLinkList; j++ ){
				double x1 = controller.getLinkInCircuit(j).getX1();
				double y1 = controller.getLinkInCircuit(j).getY1();
				double x2 = controller.getLinkInCircuit(j).getX2();
				double y2 = controller.getLinkInCircuit(j).getY2();
				
				if((e.getX()>x1)&&(e.getX()<x2)&&(e.getY()>y1)&&(e.getY()<y2)){
					controller.setChosenLink(controller.getLinkInCircuit(j));
					System.out.println("One link is selected");
					controller.setChosenComponent(null);
					repaint(); }	
				if((e.getX()>x1)&&(e.getX()<x2)&&(e.getY()<y1)&&(e.getY()>y2)){
					controller.setChosenLink(controller.getLinkInCircuit(j));
					System.out.println("One link is selected");
					controller.setChosenComponent(null);
					repaint(); } } }
		
		/*
		 * If there is a click when adjusting link, stop adjusting
		 */
		if(controller.getChosenLink()!=null&&controller.getChosenComponent()==null&&controller.checkAdjustFlag()){
			/*
			 * Traverse all ports to check coincidence
			 */
			for(int k = 0; k < controller.getNumberOfPorts(); k++ ){
				double x = controller.getPort(k).getX();	
				double y = controller.getPort(k).getY();
				//If click at an InPort, connect two endPoints together
				if((e.getX()>x-3)&&(e.getX()<x+3)&&(e.getY()>y-3)&&(e.getY()<y+3)){
					controller.getChosenLink().setConnectedComponet2(controller.getPort(k).getOwner());
					controller.getChosenLink().getConnectedComponent1().getPort(ComponentType.OUTPORT).connectPort(controller.getPort(k));
					controller.getPort(k).connectPort(controller.getChosenLink().getConnectedComponent1().getPort(ComponentType.OUTPORT));
					repaint(); } }
			
			controller.setAdjustFlag(false); } }
	
		/*
		 * Right click of mouse
		 */
	
		if ((e.getButton() == MouseEvent.BUTTON3)){
			if(controller.getChosenComponent().getName() ==ComponentType.INPUT){
				popupMenu1.show(e.getComponent(), e.getX(), e.getY()); }
			else popupMenu2.show(e.getComponent(), e.getX(), e.getY()); } }

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub	
	}
	
}


	
	

