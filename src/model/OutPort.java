package model;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: out-port of component</p>
 */	

public class OutPort implements PortI {
	
	double x,y;
	Component owner;
	PortI connectedPort;
	ThreeValuedLogic value;
	
	public OutPort (Component c, double x, double y){
		this.x = x;
		this.y = y;
		owner = c;
		connectedPort = null; }
	
	public void connectPort(PortI port){ connectedPort = port; }
	public double getX(){ return x; }
	public double getY(){ return y; }
	public void setX(double x){ this.x = x; }
	public void setY(double y){	this.y = y; }
	public void setValue(ThreeValuedLogic value){ this.value = value; }
	public ThreeValuedLogic getValue(){ return owner.getResult(); }
	public PortI getConnectedPort() { return connectedPort; }
		
}
