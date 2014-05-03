package models;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


public class Node implements Parcelable {


	private float x;
	private float y;
	private float width;
	private int number;
	private boolean selected;
	static int maxNodeCount;
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
	
	public static void resetCount(){
		maxNodeCount =0;
	}
	
	public void select(){
		this.selected = true;
	}
	
	public void deselect(){
		this.selected = false;
	}
	
	public boolean isSelected(){
		return this.selected;
	}
	
	public Node(float x, float y){
		this.x = x;
		this.y = y;
		this.width =45;
		this.number = maxNodeCount++;
		this.selected = false;
	}
	
	public Node(Parcel in){
        String[] data = new String[5];

        in.readStringArray(data);
        this.number = Integer.parseInt(data[0]);
        this.selected = Boolean.parseBoolean(data[1]);
        this.width = Float.parseFloat(data[2]);
        this.x =Float.parseFloat(data[3]);
        this.y =Float.parseFloat(data[4]);
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {Integer.toString(this.number),
                                            Boolean.toString(this.selected),
                                            Float.toString(this.width),
                                            Float.toString(this.x),
                                            Float.toString(this.y)});
    }
    
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Node createFromParcel(Parcel in) {
            return new Node(in); 
        }

        public Node[] newArray(int size) {
            return new Node[size];
        }
    };

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

}
