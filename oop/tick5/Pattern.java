package uk.ac.cam.jsc89.oop.tick5;

public class Pattern implements Comparable<Pattern>  {

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
    
    public Pattern(Pattern p) {
    	mName = p.getName();
    	mAuthor = p.getAuthor();
    	mHeight = p.getHeight();
    	mWidth = p.getWidth();
    	mStartCol = p.getStartCol();
    	mStartRow = p.getStartRow();
    	mCells = p.getCells();
    }
    
    @Override
    public String toString() {
    	return (mName + " ("+ mAuthor+")");
    }

    public Pattern(String format) throws Exception {
    	inputchecker(format);
    	String[] nformat = format.split(":");
    	mName = nformat[0];
    	mAuthor = nformat[1];
   		try{mHeight=Integer.parseInt(nformat[3]);
   		} catch(NumberFormatException e) 
   			{throw new PatternFormatException("Invalid pattern format: Could not interpret the width height as a number ('"+nformat[3]+"' given).");}
		try{mWidth=Integer.parseInt(nformat[2]);
		} catch(NumberFormatException e) 
   			{throw new PatternFormatException("Invalid pattern format: Could not interpret the height field as a number ('"+nformat[2]+"' given).");}
		try{mStartCol = Integer.parseInt(nformat[4]);} catch(NumberFormatException e) 
   			{throw new PatternFormatException("Invalid pattern format: Could not interpret the startX field as a number ('"+nformat[4]+"' given).");}
		try{mStartRow = Integer.parseInt(nformat[5]);} catch(NumberFormatException e) 
   			{throw new PatternFormatException("Invalid pattern format: Could not interpret the startY field as a number ('"+nformat[5]+"' given).");}
		mCells = nformat[6];
		checkmCells();
		
		//System.out.println("Name: "+ getName());
    	//System.out.println("Author: "+ getAuthor());
    	//System.out.println("Height: "+ getHeight());
    	//System.out.println("Width: "+ getWidth());
    	//System.out.println("StartCol: "+ getStartCol());
    	//System.out.println("StartRow: "+ getStartRow());
    	//System.out.println("Pattern: "+ getCells());
    }

    public void initialise(World world) throws Exception {
    	checkmCells();
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
    
    private void inputchecker(String input) throws Exception {
    	if (input == "") {throw new PatternFormatException("Please specify a pattern.");}
    	if (input.split(":").length != 7) {
    		throw new PatternFormatException("Invalid pattern format: Incorrect number of fields in pattern (found " +input.split(":").length+ ".");
    	}
    }
    
    private void checkmCells() throws PatternFormatException {
    	try{
    		String[] x = mCells.split(" ");
    		char[][] position = new char[x.length][(x[0].toCharArray()).length];
    		for(int c = 0; c < x.length; c++) {
   				position[c]= x[c].toCharArray();
   			}
   		} catch(Exception e) {
   				throw new PatternFormatException("Invalid pattern format: Malformed pattern '"+mCells+"'.");
   			}
   	}
    
    @Override
	public int compareTo(Pattern o) {
   		return getName().compareTo(o.getName());
	}
    
    public static void main(String[] args) throws Exception {
    	Pattern p = new Pattern (args[0]);
    	
    }
}