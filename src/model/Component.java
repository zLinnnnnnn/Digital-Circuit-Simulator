package model;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: component interface</p>
 */

import drawingAdapter.AWTDrawingAdapter;
import drawingAdapter.OrientationE;

public interface Component {
	
	public ComponentType getName();
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
	public int getInPortNumber();
	public PortI getPort(ComponentType portKind);
	public OrientationE getOrientation();	
	public void moveComponent(double x, double y);	
	public void rotateComponent(OrientationE ori);	
	public void setConnectedLink(Link link);
	public Link getConnectedLink();	
	public void drawComponent(AWTDrawingAdapter adapter);
	ThreeValuedLogic getInput(ThreeValuedLogic input);
	public ThreeValuedLogic getResult();

}
