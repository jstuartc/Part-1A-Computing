package uk.ac.cam.jsc89.oop.SupervisionWork;

public class MyClass implements Cloneable {
	private String mName;
	private int[] mData;

// Copy constructor
    public MyClass(MyClass toCopy) {
    	this.mName = toCopy.mName;
    	this.mData = new int[mData.length];
    	for (int i = 0;i<toCopy.mData.length;i++){
    		this.mData[i]=toCopy.mData[i];
    	}
    }
//Clone
	@Override
    public Object clone() throws CloneNotSupportedException {
    	MyClass clone = (MyClass)super.clone();
    	for (int i = 0;i<this.mData.length;i++){
    		clone.mData[i]=this.mData[i];
    	}
    return clone;
    }
}