import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This is an shooting-style video arcade game
 * The goal of the game is to protect a space station.
 * @Rockets: Rockets are are shot at asteroids and when it hits asteroids, it reduces their size
 * @Asteroids: Creates asteroids that are placed at random positions on the screen, reduces in size when it is hit
 * @Station: Draws itself, checks when asteroids hits it, increases hit when it gets hit,and moves the canon left or right.
 * @author Mark Ofosu
 * Date: 09/27/2021
 */
public class  AsteroidsGame extends Frame {
	private  int FrameWidth = 500;
	private int FrameHeight = 400;

	
	static public void main (String [ ] args) throws Exception { 
		AsteroidsGame world = new AsteroidsGame( ); 
		world.setVisible(true);
		world.run( );
		
			
	}
	
	public AsteroidsGame ( ) {
		setTitle("Asteroids Game"); setSize(FrameWidth, FrameHeight);
		setSize(500, 400);
		addKeyListener (new keyDown( ));
		addWindowListener(new CloseQuit());
	}
	
	public void run () throws Exception { // move pieces
////		while(true) {
//		boolean run = true;
			
		Timer time = new Timer();
			
		TimerTask task = new TimerTask() {
		public int counter = 1005;
				
		@Override
		public void run() {
					
			counter --;
			clock  = Integer.toString(counter/10); //parses counter as a string 
			if ((counter/10)==0){
				System.out.print("Game Over");
				System.exit(0); //close game when countdown gets to zero
			
			}	
			
			movePieces( );
			repaint( );
		}
		};
			time.scheduleAtFixedRate(task, 100, 100); // sleeps for 100mili secs and calls timer
			
		
		}
//	}		
//			try { // pause 100 milliseconds in order
//				Thread.sleep(100); // to create animation illusion
//			} catch (Exception e) {
////				 throw(e);
//			}
//	}// more details later...
	
	
		
	public static ArrayList <Asteroid> asteroids = new ArrayList<>( );
	public  static ArrayList <Rocket> rockets = new ArrayList<>( );
	public static  String clock;
	
	// Station position middle of baseline
	private Station station = new Station (FrameWidth/2, FrameHeight-20);
		
		
	public void paint (Graphics g) {

		station.paint(g);
//			<< start with the beginning of the asteroids container >> ;
		int position = 0;
		while (position < asteroids.size() ) { //keep iterating through so far as there is more asteroids in the container
			Asteroid rock = (Asteroid) asteroids.get(position) ; // must (cast)
			rock.paint(g);
			position +=1;
		}
//			<< sart with the beginning of the rockets container >> ;
		position = 0;
		while (position < rockets.size ()) { //while there is more rockets, keep painting.
			Rocket rock = (Rocket) rockets.get(position);      //must (cast)
			rock.paint(g);
			position += 1;
		}
	
	}
		
	private void movePieces ( ) {
		// create a random new asteroid – 30% of the time
		if (Math.random( ) < 0.3) {
			Asteroid newRock = new Asteroid(
					FrameWidth * Math.random( ), 20,
					10 * Math.random( ) - 5, 3 + 3 * Math.random( ));
			asteroids.add(newRock); // add asteroid into asteroids container
		}
		
		
		// then move everything
//			<< start with the beginning of the asteroids container >> ;
		int position = 0;
		while (position < asteroids.size() ) {
			Asteroid rock = (Asteroid) asteroids.get(position) ;
			rock.move( );
			station.checkHit(rock);
			position += 1;
		}
		
//			<< start with the beginning of the rockets container >> ;
		position = 0;
		while (position < rockets.size()) {
			Rocket rock = (Rocket) rockets.get(position) ;
			rock.move(asteroids);
			position += 1;
		}
	}
		
	private class keyDown extends KeyAdapter {
		public void keyPressed (KeyEvent e) {
			char key = e.getKeyChar( );
			switch (key) {
				case 'j': station.moveLeft( ); break; // turn left
				case 'k': station.moveRight( ); break; // turn right
				case ' ': station.fire(rockets); break; // space: fire
				case 'q': System.exit(0);               // q: quit
			}
		}
	}
	
	public class CloseQuit extends WindowAdapter {// system exit function
		public void windowClosing (WindowEvent e) {
			System.exit(0);
		}
	}	
	
}
		
	
	
	
			
			
		
	
		
	


			
