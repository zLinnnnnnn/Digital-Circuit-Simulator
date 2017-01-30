package model;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: in-port of component</p>
 */

public class InPort implements PortI {
	
	double x,y;
	Component owner;
	PortI connectedPort;
	
	public InPort(Component c, double x, double y ){
		this.x = x;
		this.y = y;
		owner = c;
		connectedPort = null; }
	
	public void connectPort(PortI port){ connectedPort = port;	}
	public double getX(){ return x; }
	public double getY(){ return y; }
	public void setX(double x){	this.x = x; }
	public void setY(double y){	this.y = y;}
	public ThreeValuedLogic getValue(){	return connectedPort.getValue(); }	
	public Component getOwner(){ return owner; }
    public PortI getConnectedPort() { return connectedPort; }
	

}
