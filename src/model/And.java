package model;

import java.util.ArrayList;

import drawingAdapter.AWTDrawingAdapter;
import drawingAdapter.OrientationE;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: And gate</p>
 */

public class And extends ComponentKind{
	
	ComponentType name = ComponentType.AND;
	double width;
	double height;
	int inPortNumber = 2;
	Link connectedLink;
	OrientationE orientation;
	ArrayList<InPort> inport = new ArrayList<InPort>();
	OutPort outport;
	
	//Constructor
	public And (double x, double y){
		this.x = x;
		this.y = y;		
		width = 50;
		height = 50;	
		orientation = OrientationE.EAST;	
		for(int i = 0; i<inPortNumber; i++){
			InPort input = new InPort(this,x-width/2-3, y-height/2+17*(i+1));
			inport.add(input);}
		outport = new OutPort(this,x+width/2+3,y);}		
	
	public ComponentType getName(){	return name; }
	public double getX(){ return x; }
	public double getY(){ return y; }
	public double getWidth(){ return width;	}
	public double getHeight(){ return height; }
	public int getInPortNumber(){ return inPortNumber; }
	public OrientationE getOrientation(){ return orientation; }
	public void setConnectedLink(Link link){ connectedLink = link; }
	public Link getConnectedLink(){	return connectedLink; }
	
	public void moveComponent(double x, double y){
		this.x = x;
		this.y = y;
		//Ports move with component
		inport.get(0).setX(x-width/2-3);
		inport.get(0).setY(y-height/2+17);
		inport.get(1).setX(x-width/2-3);
		inport.get(1).setY(y-height/2+17*2);
		outport.setX(x+width/2+2);
		outport.setY(y); }
	
	public void rotateComponent(OrientationE ori){ 
		this.orientation = ori; 
		if(ori==OrientationE.SOUTH){
			inport.get(0).setX(x-15);
			inport.get(0).setY(y-height/2-3);
			inport.get(1).setX(x+15);
			inport.get(1).setY(y-height/2-3);
			outport.setX(x);
			outport.setY(y+height/2+3); }
		else if(ori==OrientationE.NORTH){
			inport.get(0).setX(x-15);
			inport.get(0).setY(y+height/2+3);
			inport.get(1).setX(x+15);
			inport.get(1).setY(y+height/2+3);
			outport.setX(x);
			outport.setY(y-height/2-3); }
		else if(ori==OrientationE.WEST){
			inport.get(0).setX(x+width/2+3);
			inport.get(0).setY(y-15);
			inport.get(1).setX(x+width/2+3);
			inport.get(1).setY(y+15);
			outport.setX(x-width/2-3);
			outport.setY(y); } }
	
	public PortI getPort(ComponentType portKind){
		switch (portKind) {
        case OUTPORT:
            return outport;
        case INPORT1:
            return inport.get(0); 
        case INPORT2:
            return inport.get(1);
        default : { System.out.println("wrong #portKind");return null;	}}}
	
	public ThreeValuedLogic getResult(){
		ThreeValuedLogic result = ThreeValuedLogic.TRUE;
		if(inport.get(0).getValue()==ThreeValuedLogic.TRUE && inport.get(1).getValue()==ThreeValuedLogic.TRUE){
			result = ThreeValuedLogic.TRUE; }
		else if(inport.get(0).getValue()==ThreeValuedLogic.UNDEFINED){
			result = ThreeValuedLogic.UNDEFINED; }
		else if(inport.get(1).getValue()==ThreeValuedLogic.UNDEFINED){
			result = ThreeValuedLogic.UNDEFINED; }
		else result = ThreeValuedLogic.FALSE;
		outport.setValue(result);
		return result; }

	@Override
	public ThreeValuedLogic getInput(ThreeValuedLogic input) {
		// TODO Auto-generated method stub
		return ThreeValuedLogic.FALSE;}
	
	public void drawComponent(AWTDrawingAdapter adapter){
		adapter.drawAndShape(x, y, width, height, orientation);
		adapter.drawCricle(inport.get(0).getX(), inport.get(0).getY(), 3);
		adapter.drawCricle(inport.get(1).getX(), inport.get(1).getY(), 3);
		adapter.drawCricle(outport.getX(), outport.getY(), 3); }

	
}
	


