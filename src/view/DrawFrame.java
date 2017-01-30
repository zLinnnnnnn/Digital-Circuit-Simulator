package view;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import model.*;
import controller.Controller;
import controller.ViewListenerI;

/**
 * <p>ENGI9874 Project</p>
 * <p>@author Zhaoyan Lin</p>
 * <p>Description: a frame that user could interact with</p>
 */	

public class DrawFrame extends JFrame
					   implements ObserverI, ViewI{
	
	private static final long serialVersionUID = 8552555080720844242L;
	private AbstractCircuitDiagram circuit;
	private DrawPanel panel ;
	private Controller controller ;
	private JButton andButton = new JButton("And") ;
	private JButton orButton = new JButton("Or") ;		
	private JButton notButton = new JButton("Not") ;	
	private JButton linkButton = new JButton("Link") ;
	private JButton adjustButton = new JButton("AdjustLink") ;
	private JButton inputButton = new JButton("Input") ;
	private JButton outputButton = new JButton("Output") ;
	private JButton deleteButton = new JButton("Delete") ;
	private JButton resetButton = new JButton("Reset");
	private RotationComboBox rotateComboBox;
	
	public DrawFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		/*
		 * Define function of each button
		 */
		andButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.addComponent(ComponentType.AND, 150.0, 150.0); ;}  
		} ) ;	
		orButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.addComponent(ComponentType.OR, 200.0, 200.0);;
			}} ) ;	
		notButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.addComponent(ComponentType.NOT, 300.0,250.0);;
			}} ) ;	
		linkButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.addLink(300, 100, 400, 120);;;
			}} ) ;	
		adjustButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.setAdjustFlag(true);;;
			}} ) ;	
		inputButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.addComponent(ComponentType.INPUT, 100.0, 100.0);;
			}} ) ;	
		outputButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.addComponent(ComponentType.OUTPUT, 500.0,100.0);;
			}} ) ;			
		deleteButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				if(controller.getChosenComponent()!=null)
					controller.deleteComponent();
				else if(controller.getChosenLink()!=null)
					controller.deleteLink();
				else System.out.println("Nothing is selected");;
			}} ) ;		
		resetButton.addActionListener( new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				controller.clearDiagram();			
			}} ) ;	
		
		
		JToolBar toolBar = new JToolBar() ;
		toolBar.add( andButton ) ;
		toolBar.add( orButton ) ;
		toolBar.add( notButton ) ;
		toolBar.add( inputButton ) ;
		toolBar.add( outputButton ) ;
		toolBar.add( linkButton ) ;
		toolBar.add( adjustButton ) ;
		toolBar.add( deleteButton );
		toolBar.add( resetButton );			
		add( toolBar, BorderLayout.SOUTH ) ;
		
		circuit = new CircuitDiagram();
		controller = new Controller(circuit);
		circuit.attachObserver(this);
		panel = new DrawPanel(controller,circuit);
		add( panel ) ; 
		setSize( 700,500 ) ;
		setLocation(300, 50); 
		setVisible( true ) ;
		
		rotateComboBox = new RotationComboBox(controller) ;
		toolBar.add( rotateComboBox );
		rotateComboBox.setBorder(BorderFactory.createTitledBorder("Select orientation"));
		
	}
	
	public static void main(String[] args) { new DrawFrame() ; }
	
	public void updateObserver(){
		
	}

	@Override
	public void addListener(ViewListenerI controller) {
		// TODO Auto-generated method stub	
	}


}
	