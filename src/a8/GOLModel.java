package a8;

import java.util.Random;
import java.util.Arrays;

public class GOLModel extends java.util.Observable implements java.io.Serializable {
    private int col;
    private int row;
    private int delay;
    private boolean lifeArr[][];
    private int countSteps;
    private boolean isChange;
        
   
    
    public GOLModel(int col, int row, int delay) {
        
        
        this.col = col;
        this.row = row;
        this.delay = delay;
        countSteps = 0;
        lifeArr = new boolean[this.col][this.row];
        isChange = true;    
    };

    public void setRandom() {
        countSteps = 0;
        
        Random rand = new Random(); 
        for(int i = 0; i < this.col; i++) {
            for(int j = 0; j < this.row; j++) {
                lifeArr[i][j] = rand.nextBoolean();
            }
        }
    }
    
    public void stepLife() {
        boolean[][] newLife = new boolean[this.col][this.row];
        
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                int count1 = 0;
                boolean tempBol = lifeArr[i][j];
                boolean[] tempBol2 = new boolean[8];

                if ((i == 0) || (j == 0)) {
                	tempBol2[0] = false;
                } else {
                	tempBol2[0] = lifeArr[i - 1][j - 1];
                }

                if (i == 0) {
                	tempBol2[1] = false;
                } else {
                	tempBol2[1] = lifeArr[i - 1][ j];
                }

                if ((i == 0) || (j == this.row - 1)) {
                	tempBol2[2] = false;
                } else {
                	tempBol2[2] = lifeArr[i - 1][ j + 1];
                }

                if (j == this.row - 1) {
                	tempBol2[3] = false;
                } else {
                	tempBol2[3] = lifeArr[i][j + 1];
                }
                
                if ((i == this.col - 1) || (j == this.row - 1)) {
                	tempBol2[4] = false;
                } else {
                	tempBol2[4] = lifeArr[i + 1][ j + 1];
                }

                if (i == this.col - 1) {
                	tempBol2[5] = false;
                } else {
                	tempBol2[5] = lifeArr[i + 1][ j];
                }

                if ((i == this.col - 1) || (j == 0)) {
                	tempBol2[6] = false;
                } else {
                	tempBol2[6] = lifeArr[i + 1][ j - 1];
                }

                if ((i == 0) || (j == 0)) {
                	tempBol2[7] = false;
                } else {
                	tempBol2[7] = lifeArr[i][j - 1];
                }

                for (int z = 0; z < 8; z++) {
                	if (tempBol2[z]) {
                		count1++;
                	}
                }
                    
                if (tempBol) {
                	newLife[i][j] = false;
                	if((count1 == 2) || (count1 == 3)) {
                		newLife[i][j] = true;
                	}
                } else {
                	newLife[i][j] = lifeArr[i][j];
                	if (count1 == 3) {
                		newLife[i][j] = true;
                	}
                }
            }
        }
            
        if(this.countSteps == 0) {
        	isChange = true;
        } else {
        	if(equals(this.lifeArr, newLife)) {
        		isChange = false;
        	}
        }
        
        this.countSteps = this.countSteps + 1;
        lifeArr = newLife;
    }

    public void stepLifeTorus() {
        boolean[][] newLife = new boolean[this.col][this.row];
        
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                int count1 = 0;
                boolean tempBol = lifeArr[i][j];
                boolean[] tempBol2 = new boolean[8];

                if ((i == 0) || (j == 0)) {
                	tempBol2[0] = lifeArr[this.col - 1][j];
                } else {
                	tempBol2[0] = lifeArr[i - 1][j - 1];
                }

                if (i == 0) {
                	tempBol2[1] = lifeArr[this.col - 1][j];
                } else {
                	tempBol2[1] = lifeArr[i - 1][ j];
                }

                if ((i == 0) || (j == this.row - 1)) {
                	tempBol2[2] = lifeArr[this.col - 1][j];
                } else {
                	tempBol2[2] = lifeArr[i - 1][j + 1];
                }

                if (j == this.row - 1) {
                	tempBol2[3] = lifeArr[i][0];
                } else {
                	tempBol2[3] = lifeArr[i][j + 1];
                }
                
                if ((i == this.col - 1) || (j == this.row - 1)) {
                	tempBol2[4] = lifeArr[i][0];
                } else {
                	tempBol2[4] = lifeArr[i + 1][ j + 1];
                }

                if (i == this.col - 1) {
                	tempBol2[5] = lifeArr[0][j];
                } else {
                	tempBol2[5] = lifeArr[i + 1][ j];
                }

                if ((i == this.col - 1) || (j == 0)) {
                	tempBol2[6] = lifeArr[0][j];
                } else {
                	tempBol2[6] = lifeArr[i + 1][ j - 1];
                }

                if ((i == 0) || (j == 0)) {
                	tempBol2[7] = lifeArr[i][this.row - 1];
                } else {
                	tempBol2[7] = lifeArr[i][j - 1];
                }

                for (int k = 0; k < 8; k++) {
                	if (tempBol2[k]) {
                		count1++;
                	}
                }
                    
                if (tempBol) {
                	newLife[i][j] = false;
                	if((count1 == 2) || (count1 == 3)) {
                		newLife[i][j] = true;
                	}
                } else {
                	newLife[i][j] = lifeArr[i][j];
                	if (count1 == 3) {
                		newLife[i][j] = true;
                	}
                }
            }
        }
            
        if (this.countSteps == 0) {
        	isChange = true;
        } else {
        	if (equals(this.lifeArr, newLife)) {
        		isChange = false;
        	}
        }
        
        this.countSteps = this.countSteps + 1;
        lifeArr = newLife;
    }
    
    
    
    public boolean[][] getArray() {
       
        return lifeArr.clone();
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
    
    public void setOption(int col, int row, int delay) {
        this.col = col;
        this.row = row;
        this.delay = delay;
        countSteps = 0;
        lifeArr = new boolean[this.col][this.row];
    }
    
    public void changeCell(int i, int j) {
        lifeArr[i][j] = !lifeArr[i][j];
    }
    
    public void setClear() {
        for (int i = 0; i < this.col; i++) {
            for (int j = 0; j < this.row; j++) {
                countSteps = 0;
                lifeArr[i][j] = false;    
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
