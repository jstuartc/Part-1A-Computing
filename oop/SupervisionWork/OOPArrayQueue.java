package uk.ac.cam.jsc89.oop.SupervisionWork;

public class OOPArrayQueue implements Queue {
	
	private OOPArrayList mQueue;
	private int head;
	private int end;
	
	public OOPArrayQueue() {
		head = 0;
		end = 0;
		mQueue = new OOPArrayList();
	}
	
	@Override
	public int takeHead() throws Exception {
		if (end-head == 0) {
			throw new Exception("Empty Queue");
		} else {
			return mQueue.get(head++);
		}
	}
	
	@Override
	public void addTo(int x) {
		mQueue.add(end++,x);
	}
}