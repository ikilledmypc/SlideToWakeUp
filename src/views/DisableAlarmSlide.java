package views;


import java.util.ArrayList;
import java.util.List;

import models.Connection;
import models.Node;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DisableAlarmSlide extends View {
	private List<Node> nodes;
	private List<Connection> connections;
	private Connection currentConnection;
	private Paint nodepaint;
	private Paint selectedNodePaint;
	private Paint nodeTextPaint;
	private Paint connectionPaint;
	
	private int nextNode;
	public DisableAlarmSlide(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.nodes = new ArrayList<Node>();
		this.connections = new ArrayList<Connection>();
		nodepaint = new Paint();
		nodepaint.setColor(Color.WHITE);
		nodeTextPaint = new Paint();
		nodeTextPaint.setTextAlign(Align.CENTER);
		nodeTextPaint.setTextSize(50);
		nodeTextPaint.setColor(Color.BLACK);
		selectedNodePaint = new Paint();
		selectedNodePaint.setColor(Color.GREEN);
		connectionPaint = new Paint();
		connectionPaint.setColor(Color.BLACK);
		connectionPaint.setStrokeWidth(10);
		nextNode =0;
	}
	
	public DisableAlarmSlide(Context context) {
		super(context);
		this.nodes = new ArrayList<Node>();
		nodepaint = new Paint(Color.BLUE);
		nodeTextPaint = new Paint(Color.BLACK);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		for(Connection c:this.connections){
			canvas.drawLine(c.getX1(), c.getY1(), c.getX2(),c.getY2(), connectionPaint);			
		}
		if(currentConnection != null) canvas.drawLine(currentConnection.getX1(), currentConnection.getY1(), currentConnection.getX2(), currentConnection.getY2(), connectionPaint);
		
		for(Node n:this.nodes){
			if(n.isSelected()){
				canvas.drawCircle(n.getX(),n.getY(),(float) (n.getWidth()*1.5),selectedNodePaint);
			}
			canvas.drawCircle(n.getX(),n.getY(),n.getWidth(),nodepaint);
			canvas.drawText(n.getNumber()+"", n.getX(), n.getY()+15, nodeTextPaint);
		}
		
		
			
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me){
		if(me.getAction() ==MotionEvent.ACTION_DOWN){ 
			for(Node n:this.nodes){
				if(Math.sqrt(Math.pow(me.getX() - n.getX(), 2) + Math.pow(me.getY() - n.getY(), 2))<n.getWidth() && n.getNumber()==0){
					n.select();
					currentConnection = new Connection(n.getX(),n.getY(),me.getX(),me.getY());					
					nextNode++;
				}
			}
		}
		
		if(me.getAction()== MotionEvent.ACTION_MOVE && nodes.get(0).isSelected()){
			for(Node n:this.nodes){
				if(Math.sqrt(Math.pow(me.getX() - n.getX(), 2) + Math.pow(me.getY() - n.getY(), 2))<n.getWidth() ){
					if(n.getNumber()==nextNode){
						n.select();
						currentConnection.setX2(n.getX());
						currentConnection.setY2(n.getY());
						this.connections.add(currentConnection);
						currentConnection = new Connection(n.getX(),n.getY(),me.getX(),me.getY());
						if(n.getNumber() == nodes.size()-1){
							disable();
						}
						nextNode++;
					}					
				}
			}
			currentConnection.setX2(me.getX());
			currentConnection.setY2(me.getY());
			redraw();
		}
		
		if(me.getAction() == MotionEvent.ACTION_UP){
			deselectAll();
		}
		redraw();
		return true;
	}
	
	public void deselectAll(){
		for(Node n: this.nodes){
			n.deselect();
			nextNode=0;
		}
		this.connections.clear();
		this.currentConnection = null;
	}
	
	public void setNodes(List<Node> arrayList){
		this.nodes = arrayList;
	}
	
	public void clearNodes(){
		this.nodes.clear();
		Node.resetCount();
		redraw();
	}
	
	public void disable(){
		Activity host = (Activity) this.getContext();
		AlertDialog.Builder builder = new AlertDialog.Builder(host);
		builder.setTitle("ERROR").setMessage("YOUR PHONE WILL NOW BE WHIPED CLEAN");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	           }
	       });
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               // User clicked OK button
	           }
	       });
		Dialog d = builder.create();
		d.show();
	}
	
	public void redraw(){
		this.invalidate();
	}


}
