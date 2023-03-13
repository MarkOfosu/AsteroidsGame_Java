import java.awt.Color;
import java.awt.Graphics;

import sun.net.www.content.text.Generic;


/**
* Creates asteroids that are placed at random positions on the screen
* @author Mark Ofosu
* Date: 09/27/2021
*/
public class Asteroid {
	public double x, y;                 //current position of this asteroid
	private double dx, dy;              //displacement (delta) dx and dy for next position
	public int size = 20;                // initial size 20 pixels
	
	public Asteroid (double ix, double iy, double idx, double idy) {      //constructor
		x = ix;
		y = iy;
		dx = idx;
		dy = idy;
	}
	
	public void move() {      //move to next position
		x += dx;
		y += dy;
		cleanAsteroids();
		
	}
	
	public void paint (Graphics g) {                   //Hey asteroid, paint yourself
		g.setColor(Color.blue);                       // as a blue circle
		g.drawOval((int) x, (int) y,  size, size);    //must be int coordinates
		
	}
	
	public void hit() {            
		if (size > 0) {
		size = size - 4;	                       // Been hit, shrink size
		}
	}
	
	public boolean nearTo (double tx, double ty) {                              //use pythagorean theorem to determin distance between to points
		double distance = Math.sqrt((x - tx) *(x - tx) + (y - ty) *(y - ty));   // Is (tx, ty) too close to 'this'?
		return distance < 10;
		                                                                        // x, y are coordinates of 'this'
		                                                                        // tx, ty are 'other sender' coordinates
	}
	
	public void cleanAsteroids() { // remove asteroids that goes out of the screen and 
		for (int i = 0; i < AsteroidsGame.asteroids.size(); i ++) {
			
			if (x > 500 || x < 0 || y > 400 || y < 0 || AsteroidsGame.asteroids.size()<=0 ) { // remove asteroids if it is out of frame or if  its size is 0
			AsteroidsGame.asteroids.remove(this);
			
			}
		}
	}

}
