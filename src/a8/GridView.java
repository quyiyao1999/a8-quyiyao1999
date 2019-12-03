/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package a8;

import javax.swing.*;


import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class GridView extends GOLView {
    final static int INIT_X = 1;
    final static int INIT_Y = 1;
    final static int SIZE = 30;

    public GridView(GOLModel model) {
        super(model);        
        updateDisplay();
        
       
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
              
            	int i = e.getPoint().x / SIZE;
                int j = e.getPoint().y / SIZE;
               
            
                j = (e.getPoint().y - j)/ SIZE;
                i = (e.getPoint().x - i)/ SIZE;
             
                
                getModel().changeCell(i, j);
                
                repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {
            
            }

            @Override
            public void mouseReleased(MouseEvent e) {
          
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	
            }
        });
    }
    
    public void paintComponent( Graphics graphic) {
        super.paintComponent(graphic );
        Graphics2D graphics2D = (Graphics2D)graphic;
        
        int col = getModel().getCol();
        int row = getModel().getRow();
        
        boolean [][] mainArr = model.getArray();
        
        for(int i = 0; i <col; i++) {
            for(int j = 0; j<row; j++) {
                if(mainArr[i][j]) {
                    graphics2D.setColor(Color.RED);
                } else {
                    graphics2D.setColor(Color.WHITE);
                }           
                graphics2D.fillRect(INIT_X + i*SIZE + i, INIT_Y + j*SIZE + j, SIZE,  SIZE);
            }
        }

    }
    
    public void updateDisplay() {
        repaint();
    }
    
    public Dimension getPreferredSize() {        
        int col = getModel().getCol();
        int row = getModel().getRow();
        return new Dimension(col*SIZE + col + 2*INIT_X + 8, row*SIZE + row + INIT_Y + 70);
    }
    
}
