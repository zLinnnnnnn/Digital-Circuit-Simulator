package model;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: interface of inport and outport</p>
 */	

public interface PortI {
	
	public void connectPort(PortI port);
	public double getX();
	public double getY();
	public void setX(double x);
	public void setY(double y);
	public ThreeValuedLogic getValue();
	public PortI getConnectedPort();

}
