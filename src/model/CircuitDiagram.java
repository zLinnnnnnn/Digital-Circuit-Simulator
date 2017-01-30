package model;

import java.util.ArrayList;

import drawingAdapter.OrientationE;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: A diagram that handles components, links and their operations</p>
 */

public class CircuitDiagram extends AbstractCircuitDiagram{
	

	public ArrayList<Component> componentList = new ArrayList<Component>();
	public ArrayList<Link> links = new ArrayList<Link>();
	public ArrayList<Component> inputs = new ArrayList<Component>();
	public ArrayList<InPort> ports = new ArrayList<InPort>();
	public Component output;
	private Component currentChosenComponent;
	private Link currentChosenLink;
	
	
	/*
	 * Add a component to the diagram
	 */
	public void addComponent(ComponentType inType, double x, double y){
		
		Component comp;
        switch (inType) {
            case AND:
                comp = new And(x, y);
                break;
            case OR:
                comp = new Or(x, y);
                break;  
            case NOT:
                comp = new Not(x, y);
                break;   
            case INPUT:{
                comp = new Input(x, y);
                inputs.add(comp);}
                break;  
            case OUTPUT:{
                comp = new Output(x, y);
                output = comp;}
                break;  
            default : {	comp = null; System.out.println("Component not found");	}  }
        
        componentList.add(comp);  
        
        /*
         * Put in-port/in-ports of the component in an ArrayList
         */
        if(comp.getInPortNumber()==1){
        	ports.add((InPort) comp.getPort(ComponentType.INPORT1)); }
        else if(comp.getInPortNumber()==2){
        	ports.add((InPort) comp.getPort(ComponentType.INPORT1));
        	ports.add((InPort) comp.getPort(ComponentType.INPORT2)); }     
            notifyObservers(); }	
	
	/*
	 * Delete a component
	 */
	public void deleteComponent(){ 
		componentList.remove(currentChosenComponent);
		currentChosenComponent = null;
		notifyObservers();}
	
	public void rotateComponent(OrientationE ori){
		currentChosenComponent.rotateComponent(ori);
		notifyObservers();}
	
	public Component getComponetInCircuit(int i){ return componentList.get(i);} 
	public Component getInput(int i){ return inputs.get(i); }
	public int getNumberOfInputs(){	return inputs.size(); }	
	public Component getOutput(){ return output; }
	public int getListSize(){ return componentList.size(); }
	
	public void addLink(double x1, double y1, double x2, double y2){
		Link link = new Link (x1,y1,x2,y2);
		links.add(link);
		notifyObservers(); }
	
	public Link getLinkInCircuit(int i){ return links.get(i);}
	public int getNumberOfLinks(){ return links.size(); }
	
	public void deleteLink(){ 
		links.remove(currentChosenLink);
		notifyObservers(); }
	
	public void moveComponent(double x, double y){
		currentChosenComponent.moveComponent(x, y); }
	
	public void moveLink(double x, double y){
		currentChosenLink.moveLink(x, y); }
	
	public InPort getPort(int i){
		return ports.get(i); }
	
	public int getNumberOfPorts(){
		return ports.size(); }
		
	public void setChosenComponent(Component c){
		currentChosenComponent = c;
		notifyObservers();	}
	
	public void setChosenLink(Link link){
		currentChosenLink = link;
		notifyObservers(); }
	
	public Component getChosenComponent(){
		return currentChosenComponent; }
	
	public Link getChosenLink(){
		return currentChosenLink; }
	
	public void moveEndPoint(double mouseX, double mouseY){
		
		if(currentChosenLink.checkEndPoint1()){
			double diffX = currentChosenLink.getDifferenceX1();
			double diffY = currentChosenLink.getDifferenceY1();
			currentChosenLink.getConnectedComponent1().moveComponent(mouseX+diffX, mouseY+diffY);
			notifyObservers(); }
		
		if(currentChosenLink.checkEndPoint2()){
			double diffX = currentChosenLink.getDifferenceX2();
			double diffY = currentChosenLink.getDifferenceY2();
			currentChosenLink.getConnectedComponent2().moveComponent(mouseX+diffX, mouseY+diffY);
			notifyObservers(); } }
	
	public void clearDiagram(){
		//Clear all components
		int size = getListSize();
		for(int i = size-1; i >= 0; i-- ){				
			setChosenComponent(getComponetInCircuit(i));
			deleteComponent();	
			setChosenComponent(null); }
		//Clear all links
		int sizeOfLinkList = getNumberOfLinks();
		for(int j = sizeOfLinkList-1; j >=0; j-- ){
			setChosenLink(getLinkInCircuit(j));
			deleteLink();
			setChosenLink(null); }	}	

}
