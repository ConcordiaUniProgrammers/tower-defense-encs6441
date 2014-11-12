package bullet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import core.domain.maps.GridCell;

public class Bullet {
	
	public enum State { MOVING, FINISHED }
	State state = State.MOVING;
	
	public State getState() { return state; }
	
	Point startPoint;
	Point endPoint;
	Point currentPoint;
	double movingSpeed = 0; // number of pixels in one millisecond 
GridCell target;
	// parameters for y= ax + b
	double a; 
	double b;
	double x1;
	double y1;
	double x2;
	double y2;
	
	public Bullet(Point start, Point end, GridCell newtarget) {
		startPoint = start;
		endPoint = end;
		currentPoint = start;
		x1 = startPoint.x;
		y1 = startPoint.y;
		x2 = endPoint.x;
		y2 = endPoint.y;

		a = (y2 - y1) / (x2 - x1);
		b = y1 - x1 * ((y2 - y1) / (x2 - x1));
		pathLength = length(startPoint, endPoint);
		target = newtarget;
	}
	
	private double length(Point start, Point end) {
		return Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
	}

	double pathLength = 0.0;
	
	public void updatePosition(int millisecPassed){
	
		x1 = currentPoint.x;
		y1 = currentPoint.y;
		
		double step = movingSpeed * millisecPassed;
		
		double angle = Math.atan(a);

		int buletX = 0;
		if(startPoint.x > endPoint.x)
			buletX = (int) (x1 - (step * Math.cos(angle)));
		else
			buletX = (int) (x1 + (step * Math.cos(angle)));
			
		int buletY = 0;
		if(startPoint.y > endPoint.y)
			buletY = (int) (y1 - (step * Math.sin(angle)));
		else
			buletY = (int) (y1 + (step * Math.sin(angle)));
		
		
		if(currentPoint.x/30+1 == endPoint.x/30 +1 && currentPoint.y/30+1 == endPoint.y/30 +1)
			//state = State.FINISHED;
			target.containsBullet = true;
		currentPoint = new Point(buletX, buletY);
		
		
		//if(Math.abs(currentPoint.x - endPoint.x) < 5 && Math.abs(currentPoint.y - endPoint.y) < 5)
		//	currentPoint = startPoint;
		if(length(startPoint, currentPoint) >= pathLength){
			state = State.FINISHED;
			target.containsBullet = false;
		}
	}
		
	public void draw(Graphics g){ 
		g.setColor(Color.black);
		g.fillOval(currentPoint.x, currentPoint.y, 12, 12);
		g.setColor(Color.white);
		g.fillOval(currentPoint.x, currentPoint.y, 10, 10);
		g.setColor(Color.red);
		g.fillOval(currentPoint.x, currentPoint.y, 8, 8);
	}
	
	public void setSpeed(double newspeed){
		movingSpeed = newspeed / 1000;
		
	}

}