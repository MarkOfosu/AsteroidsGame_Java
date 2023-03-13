import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
* Creates  Draws itself, checks when asteroids hits it, increases hit when it gets hit,and moves 
* @author Mark Ofosu
* Date: 09/27/2021
*/
public class Station {
	public Station (double ix, double iy) {
		x = ix; y = iy; 
	}
	
	private double angle = Math.PI / 2.0; // public static final double PI 3.141592653589793d
	private int hits = 0; // So, angle is initialized to 90 degrees
	private final double x;
	private final double y;
	
	public void moveLeft( ) {    //Moves the gun to the left
		angle = angle + 0.3; 
	}
	public void moveRight( ) { // Moves gun to the right
		angle = angle - 0.3;
	}

	
	public void fire (ArrayList<Rocket> rockets) {
		double cosAngle = Math.cos(angle);
		double sinAngle = Math.sin(angle);
	    // rocket goes same direction as gun is pointing
	    // length of Rocket Launcher is 20; size of rocket is 5; (20 – 5 = 15)
		Rocket r = new Rocket(x + 15 * cosAngle, y - 15 * sinAngle,
				5 * cosAngle, - 5 * sinAngle);
	    rockets.add(r) ;
	}
	
	public void checkHit (Asteroid rock) {  // if asteroid is within range, reduce size
		if (rock.nearTo((double) x, (double) y))
			hits += rock.size;
	}
	
	public void paint (Graphics g) {
		// paint rocket launcher (length 20 pixels)
		g.setColor (Color.red);
		double lv = 20 * Math.sin(angle); // launcher tip vertical coordinate
		double lh = 20 * Math.cos(angle); // launcher tip horizontal coordinate
		
		// (x, y) is launcher base, (x+lh, y-lv) is tip of launcher
		g.drawLine((int) x, (int) y, (int) (x + lh), (int) (y - lv));
		
		// display updated score
		g.drawString("hits: " + hits, (int) (x + -240), (int) (y - 5));
		
		g.drawString("Timer: " + AsteroidsGame.clock +" Seconds", (int) (x + -240), (int) (y -20));
		
		g.drawOval(230, 380, 40, 10); // draw base station 
	}
	
}
