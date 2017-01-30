package model;

import drawingAdapter.AWTDrawingAdapter;
import drawingAdapter.OrientationE;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: output of the diagram</p>
 */	

public class Output extends ComponentKind {
	
	ComponentType name = ComponentType.OUTPUT;
	double width;
	double height;
	int inPortNumber = 1;
	OrientationE orientation;
	Link connectedLink;
	InPort in;
	OutPort out;
	ThreeValuedLogic value;
	
	public Output (double x, double y){
		this.x = x;
		this.y = y;		
		width = 30;
		height = 30;	
		orientation = OrientationE.EAST;	
		in = new InPort(this,x-width/2-3,y);
		out = new OutPort(this,x+width/2+3,y); }	
	
	public ComponentType getName(){ return name; }
	public double getX(){ return x; }
	public double getY(){ return y; }
	public double getWidth(){ return width; }
	public double getHeight(){ return height; }
	public int getInPortNumber(){ return inPortNumber; }
	public OrientationE getOrientation(){ return orientation; }
	
	public void moveComponent(double x, double y){
		this.x = x;
		this.y = y;
		//Ports move with component
		in.setX(x-width/2-3);
		in.setY(y);
		out.setX(x+width/2+3);
		out.setY(y); }
	
	public void rotateComponent(OrientationE ori){ this.orientation = ori; }
	
	public PortI getPort(ComponentType portKind){
		switch (portKind) {
        case OUTPORT:
            return out;
        case INPORT1:
            return in; 
        default : { System.out.println("wrong #portKind");return null;	} } }
	
	public void setConnectedLink(Link link){ connectedLink = link; }
	public Link getConnectedLink(){ return connectedLink; }
	
	public ThreeValuedLogic getResult(){
		out.setValue(in.getValue());
		System.out.println(in.getValue());
		return in.getValue(); }

	@Override
	public ThreeValuedLogic getInput(ThreeValuedLogic input) {
		// TODO Auto-generated method stub
		return ThreeValuedLogic.FALSE; }
	
	public void drawComponent(AWTDrawingAdapter adapter){
		adapter.drawRect(x, y, width, height);
		adapter.drawCricle(in.getX(), in.getY(), 3); }

}
