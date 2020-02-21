package uk.ac.cam.jsc89.oop.tick5;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class GUILife extends JFrame implements ListSelectionListener {

	private World mWorld;
   	private PatternStore mStore;
	private ArrayList<World> mCachedWorlds = new ArrayList<>();
	private GamePanel mGamePanel;
	private JButton mPlayButton = new JButton("Play");
	private boolean mPlaying;
	private java.util.Timer mTimer;
    
    public GUILife(PatternStore ps) throws Exception {
    	super("Game of Life");
    	mStore=ps;
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setSize(1024,768);
    	
    	add(createPatternsPanel(),BorderLayout.WEST);
    	add(createControlPanel(),BorderLayout.SOUTH);
    	add(createGamePanel(),BorderLayout.CENTER);
	}
	
	private void addBorder(JComponent component, String title) {
    	Border etch = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    	Border tb = BorderFactory.createTitledBorder(etch,title);
    	component.setBorder(tb);
	}

	private JPanel createGamePanel() {
    	mGamePanel = new GamePanel();
    	addBorder(mGamePanel,"Game Panel");
    	mGamePanel.display(mWorld);
    	return mGamePanel;
	}

	private JPanel createPatternsPanel() {
    	JPanel patt = new JPanel();
    	patt.setLayout(new BorderLayout());
    	addBorder(patt,"Patterns");
    	DefaultListModel pDefaultList = new DefaultListModel();
    	List<Pattern> names = mStore.getPatternsNameSorted();
        for (Pattern p : names) {
            pDefaultList.addElement(p);
        }
    	JList pList = new JList(pDefaultList);
    	pList.addListSelectionListener(this);
    	patt.add(new JScrollPane(pList));
    	return patt; 
	}

	private JPanel createControlPanel() throws CloneNotSupportedException {
    	JPanel ctrl =  new JPanel();
    	addBorder(ctrl,"Controls");
    	ctrl.setLayout(new GridLayout(1,3));
    	
    	JButton b = new JButton("< Back");
    	b.addActionListener(e->moveBack());
    	ctrl.add(b);
    	
    	mPlayButton.addActionListener(e->runOrPause());
    	ctrl.add(mPlayButton);
    	
    	b = new JButton("Forward >");
    	b.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			try{
    				if (mPlaying) {
						runOrPause();
					}
	    			moveForward();
	    		} catch(CloneNotSupportedException ex) {
	    			ex.printStackTrace();
	    		}
    		}
    	});
    	ctrl.add(b);
    	return ctrl;
	}
	
	
	
	private void moveBack() {
		if (mPlaying) {
			runOrPause();
		}
		if (mWorld.getGenerationCount()==0) {
            mWorld = mCachedWorlds.get(0);
        }
        else {
            mWorld = mCachedWorlds.get(mWorld.getGenerationCount()-1);
        } 
        mGamePanel.display(mWorld);
	}
	
	private void moveForward() throws CloneNotSupportedException {
		if (mWorld.getGenerationCount()+2<=mCachedWorlds.size()) {
            mWorld = mCachedWorlds.get(mWorld.getGenerationCount()+1);
        } else {
            mCachedWorlds.add(mWorld.getGenerationCount()+1,copyWorld(true));
            mCachedWorlds.get(mWorld.getGenerationCount()+1).nextGeneration();
            mWorld = mCachedWorlds.get(mWorld.getGenerationCount()+1);
        }
        mGamePanel.display(mWorld);
	}
	
	@Override
    public void valueChanged(ListSelectionEvent e) {
    	if (mPlaying) {
			runOrPause();
		}
        JList<Pattern> list = (JList<Pattern>) e.getSource();
		Pattern p = list.getSelectedValue();
        try{
        	if (mCachedWorlds != null) {mCachedWorlds.clear();}
        	if ((p.getWidth())*(p.getHeight()) <=64) { // checking board size
            	mCachedWorlds.add(0,new PackedWorld(p));
                mWorld = (PackedWorld)mCachedWorlds.get(0);
           } else {
                mCachedWorlds.add(0,new ArrayWorld(p));
           		mWorld = mCachedWorlds.get(0);
                   }
      		mGamePanel.display(mWorld);
      	} catch(Exception exc) {
      		exc.printStackTrace();
      	}
    }
    
    private void runOrPause() {
    	if (mPlaying) {
    	    mTimer.cancel();
    	    mPlaying=false;
    	    mPlayButton.setText("Play");
    	}
    	else {
    	    mPlaying=true;
    	    mPlayButton.setText("Stop");
    	    mTimer = new java.util.Timer(true);
    	    mTimer.scheduleAtFixedRate(new TimerTask() {
    	        @Override
    	        public void run() {
    	        	try{
    	            	moveForward();
    	            } catch(CloneNotSupportedException e){
    	            	e.printStackTrace();
    	            }
        	    }
        	}, 0, 500);
    	}
	}
	
	private World copyWorld(boolean useCloning) throws CloneNotSupportedException{
    	World copyW = null;
   		if (useCloning == false) {
   			if (mWorld instanceof ArrayWorld) {
   				copyW = new ArrayWorld((ArrayWorld)mWorld);
   			}
   			else if (mWorld instanceof PackedWorld) {
   				copyW = new PackedWorld((PackedWorld)mWorld);
   			}
   		} else {
   			if (mWorld instanceof ArrayWorld) {
   				copyW = (ArrayWorld)mWorld.clone();
   			}
   			else if (mWorld instanceof PackedWorld) {
   				copyW = (PackedWorld)mWorld.clone();
   			}
   		}
   	return copyW;
	}

    public static void main(String[] args) throws Exception {
    	PatternStore ps = new PatternStore("http://www.cl.cam.ac.uk/teaching/1617/OOProg/ticks/life.txt");
        GUILife gui = new GUILife(ps);
        gui.setVisible(true);

    }

}