package views;

import java.util.ArrayList;
import java.util.List;

import models.Node;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SlideScreenView extends View {
	private ArrayList<Node> nodes;
	private Paint nodepaint;
	private Paint nodeTextPaint;
	public SlideScreenView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.nodes = new ArrayList<Node>();
		Node.resetCount();
		nodepaint = new Paint();		
		nodepaint.setColor(Color.WHITE);
		nodeTextPaint = new Paint();
		nodeTextPaint.setTextAlign(Align.CENTER);
		nodeTextPaint.setTextSize(50);
		nodeTextPaint.setColor(Color.BLACK);
	}
	
	public SlideScreenView(Context context) {
		super(context);
		this.nodes = new ArrayList<Node>();
		nodepaint = new Paint(Color.BLUE);
		nodeTextPaint = new Paint(Color.BLACK);
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		super.onDraw(canvas);
		for(Node n:this.nodes){
			canvas.drawCircle(n.getX(),n.getY(),n.getWidth(),nodepaint);
			canvas.drawText(n.getNumber()+"", n.getX(), n.getY()+15, nodeTextPaint);
		}
			
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent me){
		if(me.getAction() ==me.ACTION_DOWN){
			super.onTouchEvent(me);
			this.nodes.add(new Node(me.getX(),me.getY()));
			redraw();
		}
		return true;
	}
	
	public ArrayList<Node> getNodes(){
		return this.nodes;
	}
	
	public void clearNodes(){
		this.nodes.clear();
		Node.resetCount();
		redraw();
	}
	
	public void redraw(){
		this.invalidate();
	}

}
