package model;

import controller.Controller;
import drawingAdapter.AWTDrawingAdapter;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: link that connecting between components</p>
 */

public class Link {
	
	ComponentType name = ComponentType.LINK;
	double x1,y1,x2,y2;
	EndPoint endPoint1, endPoint2;
	
	public Link (double x1, double y1, double x2, double y2 ){
		this.x1 = x1;
		this.y1 = y1;	
		this.x2 = x2;
		this.y2 = y2;
		endPoint1 = new EndPoint(x1,y1);
		endPoint2 = new EndPoint(x2,y2); }		
		
	public ComponentType getName(){	return name; }	
	public double getX1(){return x1;};
	public double getX2(){return x2;};
	public double getY1(){return y1;};
	public double getY2(){return y2;};
	
	public void moveLink(double x, double y){	
		double diffX = this.x2 - this.x1;
		double diffY = this.y2 - this.y1;
		this.x1 = x;
		this.y1 = y;	
		this.x2 = x + diffX;
		this.y2 = y + diffY;	
		/*
		 * Move endPoint/endPoints of link
		 */
		if(endPoint1.getConnectedComponent()!=null){
			double width = endPoint1.getConnectedComponent().getWidth();		
			endPoint1.getConnectedComponent().moveComponent(x-width/2, y);; }
		if(endPoint2.getConnectedComponent()!=null){
			double width = endPoint2.getConnectedComponent().getWidth();		
			endPoint2.getConnectedComponent().moveComponent(this.x2+width/2, this.y2);; } }
	
	public void adjustLink(double x, double y, Controller controller){
		x2 = x;
		y2 = y; }

	/*
	 * Check if there is connected component with link
	 */
	public boolean checkEndPoint1(){
		if(endPoint1.getConnectedComponent()!=null){ return true; }
		else return false; }
	
	public boolean checkEndPoint2(){
		if(endPoint2.getConnectedComponent()!=null){ return true; }
		else return false; }
	
	public void setConnectedComponet1(Component c){ endPoint1.setConnectedComponet(c);;	}
	public void setConnectedComponet2(Component c){ endPoint2.setConnectedComponet(c);; }
	public Component getConnectedComponent1(){ return endPoint1.getConnectedComponent(); }
	public Component getConnectedComponent2(){ return endPoint2.getConnectedComponent(); }
	
	/*
	 * Get position differences for moving endpoint1 and endpoint2 with link
	 */
	public double getDifferenceX1(){
		double diff = endPoint1.getConnectedComponent().getX()-x1;
		return diff; }
	
	public double getDifferenceY1(){
		double diff = endPoint1.getConnectedComponent().getX()-y1;
		return diff; }
	
	public double getDifferenceX2(){
		double diff = endPoint2.getConnectedComponent().getX()-x1;
		return diff; }
	
	public double getDifferenceY2(){
		double diff = endPoint2.getConnectedComponent().getX()-y1;
		return diff; }
	
	public void drawLink(AWTDrawingAdapter adapter){
		adapter.drawLine(x1, y1, x2, y2); }	
	
}
