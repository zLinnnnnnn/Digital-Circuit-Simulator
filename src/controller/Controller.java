package controller;

import drawingAdapter.OrientationE;
import model.AbstractCircuitDiagram;
import model.Component;
import model.ComponentType;
import model.InPort;
import model.Link;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: A controller to interpret UI events to model</p>
 */


public class Controller implements ViewListenerI {
	
	private AbstractCircuitDiagram circuit;
	private Boolean adjustFlag;
	
	public Controller( AbstractCircuitDiagram circuit) {
		this.circuit = circuit ;
		adjustFlag = false;	}
		
	public void addComponent(ComponentType inType, double x, double y){
		circuit.addComponent(inType, x, y);}
	
	public void deleteComponent(){
		circuit.deleteComponent();}
	
	public void rotateComponent(OrientationE ori){
		circuit.rotateComponent(ori);}
	
	public int getListSize(){
		return circuit.getListSize();}
	
	public void addLink(double x1, double y1, double x2, double y2){	
		circuit.addLink(x1, y1, x2, y2);}
	
	public Link getLinkInCircuit(int i){
		return circuit.getLinkInCircuit(i);}
	
	public int getNumberOfLinks(){
		return circuit.getNumberOfLinks();}
	
	public void deleteLink(){
		circuit.deleteLink();}
	
	public void moveComponent(double x, double y){
		circuit.moveComponent(x, y);}
	
	public void moveLink(double x, double y){
		circuit.moveLink(x, y);}
	
	public void setAdjustFlag(boolean flag) {
		adjustFlag = flag;}
	
	public boolean checkAdjustFlag(){
		return adjustFlag;}

	public Component getComponetInCircuit(int i) {
		return circuit.getComponetInCircuit(i);}
	
	public InPort getPort(int i){
		return circuit.getPort(i);}
	
	public int getNumberOfPorts(){
		return circuit.getNumberOfPorts();}
	
	public Component getChosenComponent(){
		return circuit.getChosenComponent();}
	
	public void setChosenComponent(Component c){
		circuit.setChosenComponent(c);}
	
	public void setChosenLink(Link link){
		circuit.setChosenLink(link);}
	
	public Link getChosenLink(){
		return circuit.getChosenLink();}
	
	public void setEndPoint1(Link link,Component c){
		link.setConnectedComponet1(c);}
	
	public void setEndPoint2(Link link,Component c){
		link.setConnectedComponet2(c);}
	
	public void moveEndPoint(double mouseX, double mouseY){
		circuit.moveEndPoint(mouseX, mouseY);}

	public void clearDiagram(){
		circuit.clearDiagram();}


}

