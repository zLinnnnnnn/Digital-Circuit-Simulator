package controller;

import drawingAdapter.OrientationE;
import model.Component;
import model.ComponentType;
import model.InPort;
import model.Link;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: Interface of controller</p>
 */

public interface ViewListenerI {
	
	public void addComponent(ComponentType inType, double x, double y);
	public void deleteComponent();
	public void rotateComponent(OrientationE ori);
	public int getListSize();
	public void addLink(double x1, double y1, double x2, double y2);
	public Link getLinkInCircuit(int i);
	public int getNumberOfLinks();
	public void deleteLink();
	public void moveComponent(double x, double y);
	public void moveLink(double x, double y);
	public abstract void setAdjustFlag(boolean flag);
	public abstract boolean checkAdjustFlag();
	public InPort getPort(int i);
	public int getNumberOfPorts();
	public Component getComponetInCircuit(int i);
	public void setChosenComponent(Component c);
	public void setChosenLink(Link link);
	public Component getChosenComponent();
	public Link getChosenLink();
	public void setEndPoint1(Link link,Component c);
	public void setEndPoint2(Link link,Component c);
	public void moveEndPoint(double mouseX, double mouseY);
	public void clearDiagram();	

}
