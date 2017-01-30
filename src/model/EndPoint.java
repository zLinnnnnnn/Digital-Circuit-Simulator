package model;	

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: end-point of link, each link has two end-points</p>
 */

public class EndPoint {
	
	double x,y;
	Component connectedComponent;
	int portNumber;

	public EndPoint(double x, double y){
		this.x = x;
		this.y = y;
		connectedComponent = null ;}

	public void setConnectedComponet(Component c){ connectedComponent = c;} 
	public Component getConnectedComponent(){ return connectedComponent; }
	public int checkPortNumber(){ return portNumber; }
	
	/*
	 * Get the position differences between link's position and connected component's position
	 * in order to move end-point with link
	 */
	public double getDifferenceX(){
		double diff = connectedComponent.getX()-x;
		return diff; }
	
	public double getDifferenceY(){
		double diff = connectedComponent.getY()-y;
		return diff; }
	

}
