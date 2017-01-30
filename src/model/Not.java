package model;

import drawingAdapter.AWTDrawingAdapter;
import drawingAdapter.OrientationE;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: Not gate</p>
 */

public class Not extends ComponentKind{
	
	ComponentType name = ComponentType.NOT;
	double width;
	double height;
	int inPortNumber = 1;
	OrientationE orientation;
	Link connectedLink;
	InPort inport;
	OutPort outport;
	
	public Not (double x, double y){
		this.x = x;
		this.y = y;		
		width = 20;
		height = 30;	
		orientation = OrientationE.EAST;	
		inport = new InPort(this,x-width/2-3,y);
		outport = new OutPort(this,x+width/2+3,y);	}		
	
	public ComponentType getName(){ return name; }
	public double getX(){ return x; }
	public double getY(){ return y;	}
	public double getWidth(){ return width; }
	public double getHeight(){ return height; }
	public int getInPortNumber(){ return inPortNumber;	}
	public OrientationE getOrientation(){ return orientation; }
	
	public void rotateComponent(OrientationE ori){ 
		this.orientation = ori; 
		if(ori==OrientationE.NORTH){
			inport.setX(x);
			inport.setY(y+height/2+3);
			outport.setX(x);
			outport.setY(y-height/2-3); }
		else if(ori==OrientationE.SOUTH){
			inport.setX(x);
			inport.setY(y-height/2-3);
			outport.setX(x);
			outport.setY(y+height/2+3);
		}
		else if(ori==OrientationE.WEST){
			inport.setX(x+width/2+3);
			inport.setY(y);
		}
	}	
	
	public void moveComponent(double x, double y){
		this.x = x;
		this.y = y;
		//Ports move with component
		inport.setX(x-width/2-3);
		inport.setY(y);
		outport.setX(x+width/2+3);
		outport.setY(y); }
	
	public PortI getPort(ComponentType portKind){
		switch (portKind) {
        case OUTPORT:
            return outport;
        case INPORT1:
            return inport; 
        default : { System.out.println("wrong #portKind");return null;} } }
	
	public void setConnectedLink(Link link){ connectedLink = link; }
	public Link getConnectedLink(){ return connectedLink; }
	
	public ThreeValuedLogic getResult(){
		ThreeValuedLogic result;
		if(inport.getValue() ==ThreeValuedLogic.TRUE){
			result = ThreeValuedLogic.FALSE;
		}
		else if(inport.getValue() == ThreeValuedLogic.FALSE){
			result = ThreeValuedLogic.TRUE;
		}
		else result = ThreeValuedLogic.UNDEFINED;
		outport.setValue(result);
		return result; }

	@Override
	public ThreeValuedLogic getInput(ThreeValuedLogic input) {
		// TODO Auto-generated method stub
		return ThreeValuedLogic.FALSE; }
	
	public void drawComponent(AWTDrawingAdapter adapter){
		adapter.drawNotShape(x, y, width, height, orientation);
		adapter.drawCricle(inport.getX(), inport.getY(), 3); }
	
}
	