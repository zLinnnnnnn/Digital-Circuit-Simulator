package model;

import drawingAdapter.AWTDrawingAdapter;
import drawingAdapter.OrientationE;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: input component</p>
 */

public class Input extends ComponentKind {
	
	ComponentType name = ComponentType.INPUT;
	double width = 30;
	double height = 30;
	Link connectedLink;
	int inPortNumber = 1;
	OrientationE orientation;
	InPort in;
	OutPort out;
	
	public Input (double x, double y){
		this.x = x;
		this.y = y;		
		orientation = OrientationE.EAST;	
		in = new InPort(this,x,y);
		out = new OutPort(this,x,y);
		out.setValue(ThreeValuedLogic.UNDEFINED); }		
	
	public ComponentType getName(){	return name; }
	public double getX(){ return x; }	
	public double getY(){ return y; }
	public double getWidth(){ return width; }
	public double getHeight(){ return height; }
	public int getInPortNumber(){ return inPortNumber; }
	public OrientationE getOrientation(){ return orientation; }
	
	public PortI getPort(ComponentType portKind){
		switch (portKind) {
        case OUTPORT:
            return out;
        case INPORT1:
            return in; 
        default : { System.out.println("wrong #portKind");return null;	} } }
	
	public void setConnectedLink(Link link){ connectedLink = link; }
	public Link getConnectedLink(){ return connectedLink; }
	
	public void moveComponent(double x, double y){
		this.x = x;
		this.y = y;}
	
	public void rotateComponent(OrientationE ori){ this.orientation = ori; }
	
	public ThreeValuedLogic getInput(ThreeValuedLogic input){		
		out.value = input;	
		return out.value; }

	public ThreeValuedLogic getResult() { return out.value; }
	
	public void drawComponent(AWTDrawingAdapter adapter){
		adapter.drawRect(x, y, width, height); }

}
