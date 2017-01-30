package model;

import view.ObserverI;
import java.util.*;

import drawingAdapter.OrientationE;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: A abstract class associate with controller and observers</p>
 */

public abstract class AbstractCircuitDiagram {
	
	private ArrayList<ObserverI> observers = new ArrayList<ObserverI>() ;
	
	public abstract Component getComponetInCircuit(int i);	
	public abstract void addComponent(ComponentType inType, double x, double y);
	public abstract void deleteComponent();
	public abstract void rotateComponent(OrientationE ori);	
	public abstract int getListSize();
	public abstract void addLink(double x1, double y1, double x2, double y2);	
	public abstract Link getLinkInCircuit(int i);
	public abstract int getNumberOfLinks();
	public abstract void deleteLink();
	public abstract Component getInput(int i);
	public abstract int getNumberOfInputs();
	public abstract Component getOutput();
	public abstract void moveComponent(double x, double y);
	public abstract void moveLink(double x, double y);
	public abstract InPort getPort(int i);	
	public abstract int getNumberOfPorts();
	public abstract void setChosenComponent(Component c);
	public abstract void setChosenLink(Link link);
	public abstract Component getChosenComponent();
	public abstract Link getChosenLink();	
	public abstract void moveEndPoint(double mouseX, double mouseY);
	public abstract void clearDiagram();
	
	/*
	 * Add a new observer
	 */
	public void attachObserver (ObserverI newObserver){
        observers.add( newObserver ) ;
        newObserver.updateObserver() ; }
	
	/*
	 * Remove an observer
	 */	
	public void dettachObserver (ObserverI observer){
		observers.remove(observer);}
	
	/*
	 * If there is event, notify all observers
	 */
	public void notifyObservers(){
		for(ObserverI obs : observers){
				obs.updateObserver();}	}
	

}
