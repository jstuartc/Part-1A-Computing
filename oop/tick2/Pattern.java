package uk.ac.cam.jsc89.oop.tick2;

public class Pattern {

    private String mName;
    private String mAuthor;
    private int mWidth;
    private int mHeight;
    private int mStartCol;
    private int mStartRow;
    private String mCells;
    
    public String getName() {
       return mName;
    }
    
    public String getAuthor() {
       return mAuthor;
    }
    
    public int getWidth() {
       return mWidth;
    }
    
    public int getHeight() {
       return mHeight;
    }
    
    public int getStartCol() {
       return mStartCol;
    }
    
    public int getStartRow() {
       return mStartRow;
    }
    
    public String getCells() {
       return mCells;
    }

    public  Pattern(String format) {
    	String[] nformat = format.split(":");
    	mName = nformat[0];
    	mAuthor = nformat[1];
   		mHeight=Integer.parseInt(nformat[3]);
		mWidth=Integer.parseInt(nformat[2]);
		mStartCol = Integer.parseInt(nformat[4]);
		mStartRow = Integer.parseInt(nformat[5]);
		mCells = nformat[6];
		
		System.out.println("Name: "+ getName());
    	System.out.println("Author: "+ getAuthor());
    	System.out.println("Height: "+ getHeight());
    	System.out.println("Width: "+ getWidth());
    	System.out.println("StartCol: "+ getStartCol());
    	System.out.println("StartRow: "+ getStartRow());
    	System.out.println("Pattern: "+ getCells());
    }

    public void initialise(World world) {
       
       String[] x = mCells.split(" ");
   		char[][] position = new char[x.length][(x[0].toCharArray()).length];
   		
   		for(int i = 0; i < mHeight; i++) {
    		for(int j = 0; j < mWidth; j++) {
    			world.setCell(i,j,false);
   			}
    	}
    	
   		for(int c = 0; c < x.length; c++) {
   			position[c]= x[c].toCharArray();
   			for(int d = 0; d < position[c].length; d++) {
   				if (position[c][d] =='1') { 
   					world.setCell(d+mStartRow,c+mStartCol,true);
   				}
   			
   			}
   		}
    }
    
    public static void main(String[] args) {
    	Pattern p = new Pattern (args[0]);
    	
    }
}