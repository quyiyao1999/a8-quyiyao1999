package a8;

import javax.swing.*;
import java.awt.event.*; 
import java.awt.*; 
import java.io.*;
import java.lang.Object;


/*  Basic Intro:
	1. Type in the dimensions and delay you want, click "save" and then click "game", the GameFrame will then open.
	2. In the GameFrame, the button "TorusOff" means that at that moment, Torus mode is off. 
	If you click it, it will change to "TorusOn" and this will turn on torus mode.
	3. To play the game, either set manually, or click "random" and the click "run" or "step"
	4. If you click "run" first and then want the program to go step by step, click "stop" first and then click "step"
*/
public class GameOfLife extends JFrame {

    private JButton buttonRun;
    private JButton buttonSave;
    
    private JLabel labelCol;
    private JLabel labelRow;
    private JLabel labelDelay;
    private JLabel labelLowBirth;
    private JLabel labelLowSurvive;
    private JLabel labelHighBirth;
    private JLabel labelHighSurvive;
    
    private JTextField textCol;
    private JTextField textRow;
    private JTextField textDelay;
    private JTextField textLowBirth;
    private JTextField textLowSurvive;
    private JTextField textHighBirth;
    private JTextField textHighSurvive;
   
    private GOLModel model;
    
    public GameOfLife() {  
    	model = new GOLModel(15, 15, 500, 2, 3, 3, 3);
    
    	this.setSize(400,400);
    	buttonRun = new JButton("Run");
    	buttonRun.setBounds(100, 300, 40, 60);
    	this.add(buttonRun); 
    	
    	buttonSave = new JButton("Save");
    	buttonSave.setBounds(250,300,40,60);
    	this.add(buttonSave);
    	
    	labelCol = new JLabel();
    	labelRow = new JLabel();
    	labelDelay = new JLabel();
    	labelLowBirth = new JLabel();
    	labelLowSurvive = new JLabel();
    	labelHighBirth = new JLabel();
    	labelHighSurvive = new JLabel();
    	
    	labelCol.setText("Columns");
    	labelRow.setText("Rows");
    	labelDelay.setText("Delay in ms");
    	labelLowBirth.setText("Low Birth");
    	labelLowSurvive.setText("Low Survive");
    	labelHighBirth.setText("High Birth");
    	labelHighSurvive.setText("High Survive");

    	labelCol.setBounds(80, 0, 90, 90);
    	labelRow.setBounds(80,40,90,90);
    	labelDelay.setBounds(80,80,90,90);
    	
    	labelLowBirth.setBounds(80,110,90,90);
    	labelLowSurvive.setBounds(80,140,90,90);
    	labelHighBirth.setBounds(80,170,90,90);
    	labelHighSurvive.setBounds(80,200,90,90);
    	
    	this.add(labelCol);
    	this.add(labelRow);
    	this.add(labelDelay);
    	this.add(labelLowBirth);
    	this.add(labelLowSurvive);
    	this.add(labelHighBirth);
    	this.add(labelHighSurvive);
    	
    	textCol = new JTextField();
    	textRow = new JTextField();
    	textDelay = new JTextField();
    	textLowBirth = new JTextField();
    	textLowSurvive = new JTextField();
    	textHighBirth = new JTextField();
    	textHighSurvive = new JTextField();
    	
    	textCol.setText(Integer.toString(model.getCol()));
    	textRow.setText(Integer.toString(model.getRow()));
    	textDelay.setText(Integer.toString(model.getDelay()));
    	textLowBirth.setText("2");
    	textLowSurvive.setText("3");
    	textHighBirth.setText("3");
    	textHighSurvive.setText("3");
    	
    	textCol.setBounds(180, 35, 60, 20);
    	textRow.setBounds(180,75,60,20);
    	textDelay.setBounds(180,115,60,20);
    	textLowBirth.setBounds(180, 145, 60, 20);
    	textLowSurvive.setBounds(180, 175, 60, 20);
    	textHighBirth.setBounds(180, 205, 60, 20);
    	textHighSurvive.setBounds(180, 235, 60, 20);  	

    	this.add(textCol);
    	this.add(textRow);
    	this.add(textDelay);
    	this.add(textLowBirth);
    	this.add(textLowSurvive);
    	this.add(textHighBirth);
    	this.add(textHighSurvive);
        
    	buttonRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                buttonRunActionPerformed(evt);
            }
        });
        
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        
    	this.setLayout(null);
    	this.setVisible(true);
      
    }
    
    private void buttonRunActionPerformed(ActionEvent evt) {
        GOLGameFrame lifeFrame = new GOLGameFrame(model);    
        lifeFrame.setVisible(true);
    }

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {
            model.setCustomedNum(this.getCol(), this.getRow(), this.getDelay(), this.getLowBirth(), this.getLowSurvive(), this.getHighBirth(), this.getHighSurvive());            
    }
 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {                
                new GameOfLife().setVisible(true);
            }
        });
    }
 
    public int getCol() {        
        return Integer.parseInt(textCol.getText());
    }
    
    public int getRow() {
        return Integer.parseInt(textRow.getText());
    }
    
    public int getDelay() {
        return Integer.parseInt(textDelay.getText());
    }
    
    public int getLowBirth() {        
        return Integer.parseInt(textLowBirth.getText());
    }
    
    public int getLowSurvive() {
        return Integer.parseInt(textLowSurvive.getText());
    }
    
    public int getHighBirth() {
        return Integer.parseInt(textHighBirth.getText());
    }
    
    public int getHighSurvive() {        
        return Integer.parseInt(textHighSurvive.getText());
    }
    
}
