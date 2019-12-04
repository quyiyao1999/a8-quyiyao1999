package a8;

import java.util.Random;
import java.util.Arrays;

public class GOLModel extends java.util.Observable implements java.io.Serializable {
    private int col;
    private int row;
    private int delay;
    
    private int lowBirth;
    private int lowSurvive;
    private int highBirth;
    private int highSurvive;
    
    private boolean life[][];
    private int countSteps;
    private boolean isChange;
        
    public GOLModel(int col, int row, int delay, int lowBirth, int lowSurvive, int highBirth, int highSurvive) {
        this.col = col;
        this.row = row;
        this.delay = delay;
        this.lowBirth = lowBirth;
        this.lowSurvive = lowSurvive;
        this.highBirth = highBirth;
        this.highSurvive = highSurvive;
        
        countSteps = 0;
        life = new boolean[this.col][this.row];
        isChange = true;    
    };

    public void setRandom() {
        countSteps = 0;
        
        Random rand = new Random(); 
        for(int i = 0; i < this.col; i++) {
            for(int j = 0; j < this.row; j++) {
                life[i][j] = rand.nextBoolean();
            }
        }
    }
    
    public void stepLife() {
        boolean[][] newLife = new boolean[this.col][this.row];
        
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                int count1 = 0;
                boolean tempNeighbor = life[i][j];
                boolean[] tempNeighbor2 = new boolean[8];

                if ((i == 0) || (j == 0)) {
                	tempNeighbor2[0] = false;
                } else {
                	tempNeighbor2[0] = life[i - 1][j - 1];
                }

                if (i == 0) {
                	tempNeighbor2[1] = false;
                } else {
                	tempNeighbor2[1] = life[i - 1][j];
                }

                if ((i == 0) || (j == this.row - 1)) {
                	tempNeighbor2[2] = false;
                } else {
                	tempNeighbor2[2] = life[i - 1][j + 1];
                }

                if (j == this.row - 1) {
                	tempNeighbor2[3] = false;
                } else {
                	tempNeighbor2[3] = life[i][j + 1];
                }
                
                if ((i == this.col - 1) || (j == this.row - 1)) {
                	tempNeighbor2[4] = false;
                } else {
                	tempNeighbor2[4] = life[i + 1][j + 1];
                }

                if (i == this.col - 1) {
                	tempNeighbor2[5] = false;
                } else {
                	tempNeighbor2[5] = life[i + 1][j];
                }

                if ((i == this.col - 1) || (j == 0)) {
                	tempNeighbor2[6] = false;
                } else {
                	tempNeighbor2[6] = life[i + 1][j - 1];
                }

                if ((i == 0) || (j == 0)) {
                	tempNeighbor2[7] = false;
                } else {
                	tempNeighbor2[7] = life[i][j - 1];
                }

                for (int z = 0; z < 8; z++) {
                	if (tempNeighbor2[z]) {
                		count1++;
                	}
                }
                    
                if (tempNeighbor) {
                	newLife[i][j] = false;
                	if((count1 >= lowBirth) && (count1 <= highBirth)) {
                		newLife[i][j] = true;
                	}
                } else {
                	newLife[i][j] = life[i][j];
                	if (count1 >= lowSurvive && count1 <= highSurvive) {
                		newLife[i][j] = true;
                	}
                }
            }
        }
            
        if (this.countSteps == 0) {
        	isChange = true;
        } else {
        	if(equals(this.life, newLife)) {
        		isChange = false;
        	}
        }
        
        this.countSteps = this.countSteps + 1;
        life = newLife;
    }

    public void stepLifeTorus() {
        boolean[][] newLife = new boolean[this.col][this.row];
        
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                int count1 = 0;
                boolean tempNeighbor = life[i][j];
                boolean[] tempNeighbor2 = new boolean[8];

                if ((i == 0) || (j == 0)) {
                	tempNeighbor2[0] = life[this.col - 1][j];
                } else {
                	tempNeighbor2[0] = life[i - 1][j - 1];
                }

                if (i == 0) {
                	tempNeighbor2[1] = life[this.col - 1][j];
                } else {
                	tempNeighbor2[1] = life[i - 1][ j];
                }

                if ((i == 0) || (j == this.row - 1)) {
                	tempNeighbor2[2] = life[this.col - 1][j];
                } else {
                	tempNeighbor2[2] = life[i - 1][j + 1];
                }

                if (j == this.row - 1) {
                	tempNeighbor2[3] = life[i][0];
                } else {
                	tempNeighbor2[3] = life[i][j + 1];
                }
                
                if ((i == this.col - 1) || (j == this.row - 1)) {
                	tempNeighbor2[4] = life[i][0];
                } else {
                	tempNeighbor2[4] = life[i + 1][ j + 1];
                }

                if (i == this.col - 1) {
                	tempNeighbor2[5] = life[0][j];
                } else {
                	tempNeighbor2[5] = life[i + 1][ j];
                }

                if ((i == this.col - 1) || (j == 0)) {
                	tempNeighbor2[6] = life[0][j];
                } else {
                	tempNeighbor2[6] = life[i + 1][ j - 1];
                }

                if ((i == 0) || (j == 0)) {
                	tempNeighbor2[7] = life[i][this.row - 1];
                } else {
                	tempNeighbor2[7] = life[i][j - 1];
                }

                for (int k = 0; k < 8; k++) {
                	if (tempNeighbor2[k]) {
                		count1++;
                	}
                }
                
                if (tempNeighbor) {
                	newLife[i][j] = false;
                	if((count1 >= lowBirth) && (count1 <= highBirth)) {
                		newLife[i][j] = true;
                	}
                } else {
                	newLife[i][j] = life[i][j];
                	if (count1 >= lowSurvive && count1 <= highSurvive) {
                		newLife[i][j] = true;
                	}
                }
            }
        }
            
        if (this.countSteps == 0) {
        	isChange = true;
        } else {
        	if (equals(this.life, newLife)) {
        		isChange = false;
        	}
        }
        
        this.countSteps = this.countSteps + 1;
        life = newLife;
    }
    
    
    
    public boolean[][] getArray() {    
        return life.clone();
    }
    
    public int getCol() {
        return col;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getDelay() {
        return delay;
    }
    
    public void setCustomedNum(int col, int row, int delay, int lowBirth, int lowSurvive, int highBirth, int highSurvive) {
        this.col = col;
        this.row = row;
        this.delay = delay;
        
        this.lowBirth = lowBirth;
        this.lowSurvive = lowSurvive;
        this.highBirth = highBirth;
        this.highSurvive = highSurvive;
        
        countSteps = 0;
        life = new boolean[this.col][this.row];
    }
    
    public void changeCell(int i, int j) {
        life[i][j] = !life[i][j];
    }
    
    public void setClear() {
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                countSteps = 0;
                life[i][j] = false;    
            }
        }
        countSteps = 0;
    }
    
    public boolean getChange() {
        return isChange;
    }
    
    public int getSteps() {
        return countSteps;
    }
    
    public boolean equals(boolean[][] array1, boolean[][] array2) {
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                if(array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
