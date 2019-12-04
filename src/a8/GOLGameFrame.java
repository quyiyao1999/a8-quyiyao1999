package a8;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GOLGameFrame extends JFrame implements Runnable {
    private GOLModel model;
    GridView grid;
    private boolean isRun = false;
    private boolean isTorus = false;
    protected JButton buttonRun;
    protected JButton buttonstep;
    protected JButton buttonRandom;
    protected JButton buttonTorus;
    protected JButton buttonClear;
    
    public GOLGameFrame(GOLModel m) {
        super();
        this.model = m;
        grid = new GridView(model);
        
        getContentPane().add(BorderLayout.CENTER, grid);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
       
        buttonRun = new JButton("Run");         
        buttonRun.addActionListener( new ActionListener() {
        	public void actionPerformed( ActionEvent e) {
        		if(isRun) {
        			stopGame();
        		} else {
        			startGame();   
    			}
        	} 
        });
        panel.add(buttonRun);
        
        buttonstep = new JButton("step"); 
        buttonstep.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		step();
    		}
        });
        panel.add(buttonstep);
        
        buttonRandom = new JButton("Random");
        buttonRandom.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		setRandom();
            }
        });
        panel.add(buttonRandom);
        
        buttonTorus = new JButton("torusOFF");
        buttonTorus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	isTorus();
            }
        }); 
        panel.add(buttonTorus);

        buttonClear = new JButton("Clear");
        buttonClear.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {              
        		stopGame();
        		model.setClear();
        		grid.repaint();
        	}
        });
        panel.add(buttonClear);
        
        getContentPane().add(BorderLayout.NORTH, panel);
        
        this.setSize(grid.getPreferredSize());
        this.setResizable(true);
        
        WindowListener listener = new WindowAdapter() {
            public void windowClosing(WindowEvent w) {
                isRun = false;
            }
        };
        this.addWindowListener(listener);
    }
    
   
    
    public GOLModel getModel() {
        return model;
    }
    
    public void startGame() {
    	isRun = true;
    	Thread t = new Thread(this);
    	t.start();
    	buttonRun.setText("Stop");
    }
    
    public void step() {	
    	isRun = true;
    	Thread t = new Thread(this);
    	t.start();
    	setTimeout(() -> isRun = false, 5);  	
    }
    
    public void stopGame() { 
    	isRun = false;
        buttonRun.setText("Run");
    }
    
    public void setRandom() {
        model.setRandom();
        grid.repaint();
    }
    
    public void run() {
       if (!isTorus) {
        while(isRun) {          
            try {
                model.stepLife();
                grid.repaint();          
                
                Thread.sleep(model.getDelay());
                if(model.getChange() == false) {
                    this.stopGame();
                }
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
       }
       else if (isTorus) {
    	   while(isRun) {          
               try {
                   model.stepLifeTorus();                 
                   grid.repaint();             
                   
                   Thread.sleep(model.getDelay());
                   if(model.getChange() == false) {
                       this.stopGame();
                   }
               } catch(InterruptedException ex) {
                   ex.printStackTrace();
               }
           }
       }
    }
    
    public  void isTorus() {
    	if (!isTorus) {
    		isTorus = true;
    		buttonTorus.setText("torusON");

    	}
    	else if (isTorus) {
    		isTorus = false;
    		buttonTorus.setText("torusOFF");
        
        
    	}
    }
    
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
