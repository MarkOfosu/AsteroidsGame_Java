import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
/**
* Creates  Rockets that are are shot at asteroids and when it hits asteroids, it reduces their size
*Checks to see if it has hit an asteroid, if so , it calls hit on the asteroid.
* @author Mark Ofosu
* Date: 09/27/2021
*/
public class Rocket {
	public double x, y;          //current position coordinates
	private double dx, dy;       // idx and idy computed from canon direction
	
	public Rocket (double ix, double iy, double idx, double idy) {
		x = ix;
		y = iy;
		dx = idx;
		dy = idy;
	}
	
	public void move (ArrayList<Asteroid> asteroids) {
		
		x += dx; y += dy; // move ‘this’ rocket
		
		//<< start with the beginning of the list of asteroids >>
		int position = 0;
		while ( position < asteroids.size() ) {
			Asteroid rock = (Asteroid) asteroids.get(position) ; // must (cast)
			if (rock.nearTo(x, y))
				rock.hit( ); // hit if too close
			position += 1;
			cleanRockets();
		}
	}
	
	public void paint (Graphics g) {
		g.setColor(Color.red);                            //draw self: red circle inside
		g.fillOval((int) x, (int) y, 5, 5);               // a 5 by 5 bounding rectangle
		                                                  // (x, y) is the upper left corner
	}

	public void cleanRockets() { //remove rockets that goes off the screen
		for (int i = 0; i < AsteroidsGame.rockets.size(); i ++) {
			
		if (x > 500 || x < 0 || y > 400 || y < 0 ) { // if the rocket goes beyond the frame, remove it.
			AsteroidsGame.rockets.remove(this);
			
		
			
		}
	}
		
}
}
