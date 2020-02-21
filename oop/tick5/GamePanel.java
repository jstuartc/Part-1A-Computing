package uk.ac.cam.jsc89.oop.tick5;

import java.awt.Color;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    private World mWorld = null;

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        g.setColor(java.awt.Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());


        if (mWorld != null) {
        	int boardLength;
        	if (this.getHeight()<this.getWidth()) {
        		boardLength = this.getHeight();
        		}
        	else {
        		boardLength = this.getWidth();
        		}
        
        	int rowN = mWorld.getHeight();
        	int colN = mWorld.getWidth();
        
        
        
        
        
        	/*int cellLength;
        	if (this.getHeight()/mWorld.getHeight()>this.getWidth()/mWorld.getWidth()) {
    			cellLength = this.getWidth()/mWorld.getWidth();
    		} else {
    			cellLength = this.getHeight()/mWorld.getHeight();
    		}
    		*/
    		
    		for (int row = 0; row < mWorld.getHeight(); row++) { 
      			for (int col = 0; col < mWorld.getWidth(); col++) {
      				if (mWorld.getCell(col, row)) {
      					g.setColor(Color.BLACK);
      					g.fillRect(col*boardLength/colN, row*boardLength/rowN, (((col+1)*boardLength/colN)-(col*boardLength/colN)), ((row+1)*boardLength/rowN)-(row*boardLength/rowN));
      				} else {
      					g.setColor(Color.WHITE);
      					g.fillRect(col*boardLength/colN, row*boardLength/rowN, (((col+1)*boardLength/colN)-(col*boardLength/colN)), ((row+1)*boardLength/rowN)-(row*boardLength/rowN));
      				}
      				g.setColor(Color.LIGHT_GRAY);
      				g.drawRect(col*boardLength/colN, row*boardLength/rowN, (((col+1)*boardLength/colN)-(col*boardLength/colN)), ((row+1)*boardLength/rowN)-(row*boardLength/rowN));
    			}
    		}
    		
    		g.setColor(Color.BLACK);
    		g.drawString("Generation: " + mWorld.getGenerationCount(), 0,this.getHeight()-5);
		}    
    }
    
   

    public void display(World w) {
        mWorld = w;
        repaint();
    }
}