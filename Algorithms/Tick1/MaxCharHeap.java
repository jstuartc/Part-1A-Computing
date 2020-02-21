package uk.ac.cam.jsc89.Algorithms.Tick1;
import uk.ac.cam.rkh23.Algorithms.Tick1.MaxCharHeapInterface;
import uk.ac.cam.rkh23.Algorithms.Tick1.EmptyHeapException;

public class MaxCharHeap implements MaxCharHeapInterface {
	
	private char[] mHeap;
	private int mLength = 0;
	
	public MaxCharHeap(String s) {
		mHeap = new char[0];
		s = s.toLowerCase();
		mHeap = s.toCharArray();
		mLength = mHeap.length;
		for (int i= mLength-1; i>-1;i--) {
			Heapchecker(i);
		}
		
	}
	
	public char getMax() throws EmptyHeapException {
		if (mLength == 0) {
			throw new EmptyHeapException();
		}
		
		final char max = mHeap[0];
		mHeap[0]= mHeap[mLength-1];
		char[] Temp = new char[--mLength];
		
		for(int i = 0; i<mLength;i++) {
		Temp[i] = mHeap[i];
		}
		mHeap = Temp;
		
		Heapchecker(0);
		System.out.println(mHeap);
		return max;
		
	}
	
	public void insert(char i) {
		i= java.lang.Character.toLowerCase(i);
		char[] Temp = new char[++mLength];
		for(int j = 0; j<mLength-1;j++) {
			Temp[j] = mHeap[j];
			}
		mHeap = Temp;
		Temp[mLength-1] = i;
		
		int k = mLength-1;	
		while (k>0) {
			int m = (k-1)/2;
			
			final char child = mHeap[k];
			final char parent = mHeap[m];
			
			if (child<parent) {break;}
			mHeap[k] = parent;
			mHeap[m] = child;
			
			k = m;
		
		}
	}
	
	public int getLength() {
		return mLength;
	}


	public void Heapchecker(int k) {
		int left = k*2+1;
		if (left<mLength) {
		
			int right = left+1;
			int child = left;
			if (right < mLength) {
				if(mHeap[left]<mHeap[right])
				child = right;
			} 
			
			final char swap1 = mHeap[k];
			final char swap2 = mHeap[child];
			if (swap1<swap2) {
				mHeap[k] = swap2;
				mHeap[child] = swap1;
			}
				
			Heapchecker(child);
		}
	}
	
	public static void main (String[] args) throws Exception {
		MaxCharHeap m = new MaxCharHeap(args[0]);

		System.out.println(m.getLength());
		for(int i=0;i<m.mHeap.length;i++)
			System.out.print(m.mHeap[i]);
		System.out.println(m.getMax());
		System.out.println(m.getMax());
	}
}