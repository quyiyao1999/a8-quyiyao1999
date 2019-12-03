package a8;

import java.awt.Color;

import java.util.*;
import java.awt.*;

import javax.swing.JPanel;
import javax.swing.border.*;

public abstract class GOLView extends JPanel implements Observer {
    protected GOLModel model;
    
    public GOLView(GOLModel observableModel) {
        if(observableModel == null) {
            throw new NullPointerException(); 
        }
        
        model = observableModel;
        model.addObserver(this);
        
        setBackground(Color.gray);
        this.setBorder(null);
        setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
    }
    
    public GOLModel getModel() {
        return model;
    }
    
    protected abstract void updateDisplay();
    
    @Override
    
    public void update(Observable observable, Object object) {
        updateDisplay();
    }
        
}
