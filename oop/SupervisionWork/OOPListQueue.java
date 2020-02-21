package uk.ac.cam.jsc89.oop.SupervisionWork;

public class OOPListQueue implements Queue {

	private OOPLinkedList List1;
	private OOPLinkedList List2;

	public OOPListQueue() {
		List1 = new OOPLinkedList();
		List2 = new OOPLinkedList();
	}
	
	@Override
	public int takeHead() throws Exception {
		if (List1.length() == 0) {
			Normalise();
		}
		return List1.remove();
	}
	
	@Override
	public void addTo(int x) {
		List2.add(x);
	}	

	public void Normalise() throws Exception {
		if (List2.length == 0) {
			throw new Exception("Queue is Empty");
		} else {
			for(int i = 0;i < List2.length();i++) {
				List1.add(List2.remove());
			}
		}
	}
}