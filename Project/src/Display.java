/* David Liang
 * Intro CS
 * Project
 * 
 * 
 * Below is a prototype of FlappyBird.
 * This class uses the PApplet to display the program and load the images while also 
 * create the Bird object for the game. Additionally, this class also checks if the 
 * game has started and uses the mousePressed() to have the bird "fly" against gravity.
 * 
 */

//imports Processing
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Display extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Display");
	}

	//Initializes the game state as 1 as the start menu.
	int game = 1;
	
	
	//Initializes the images used and possibly implemented later on.
	PImage start, back1, back2, bird1, bird2, ready, tube1, tube2, ground;
	
	
	//Initializes the tube x coordinates and random y coordinates for later.
	int tubex, tubex2;
	int rand1 = -200;
	int rand2 = 300;
	int rand3 = -150;
	int rand4 = 400;
	
	
	//Initializes the xcor and ycor of the images, score is possible implementation later.
	int x = -100, y;
	int score = 0;
	static int highscore = 0;

	
	//Initializes the flappy bird.
	Bird flappy;
	
	
	
	// Necessary to set the size of the Window
	public void settings() {
		size(400, 600); 
		
		// size(288,511); Possible later implementation to change the background/size.
		/*
		 * if (random(0,1) > .5) background(back1); else background(back2);
		 */
	}

	// Loads the Images and sets the starting tube positions
	public void setup() {
		start = loadImage("startscreen.jpeg");
		back2 = loadImage("background2.png");
		bird1 = loadImage("bird1.png");
		bird2 = loadImage("sbird.png");
		back1 = loadImage("background1.png");
		ready = loadImage("getready.jpg");
		tube1 = loadImage("tube1.png");
		tube2 = loadImage("tube2.png");
		ground = loadImage("ground.png");
	
		//Resizes the images for the resolution.
		imageMode(CENTER);
		back1.resize(500, 800);
		bird1.resize(32, 60);
		start.resize(400, 800);
		ready.resize(500, 900);
		ground.resize(400, 100);

		//Sets the background, tube xcors, and creates the bird.
		image(back1, width / 2, height / 2);
		tubex = width;
		tubex2 = width - 120;
		flappy = new Bird(width/4,y,1,0.5);
	}

	
	// uses the images to create the world and wraps it around, also generates tubes
	public void draw() {
		//This checks if the game is at the start screen or not.
		if (game == 0) {
			
			//Wraps the game around, but I'm not sure what x value would be better.
			if (x == -600)
				x = 0;
			
			//Loads the images at their specific coordinates
			imageMode(CORNER);
			image(back1, x, 0);
			image(back1, x + back1.width, 0);
			image(bird2, flappy.getX(),flappy.getY());
			image(tube1, tubex, rand1);
			image(tube2, tubex, rand2);
			image(tube1, tubex2, rand3);
			image(tube2, tubex2, rand4);
			image(ground, 0, height - 50);
			
			//Moves the tubes and background in accordance to the speed
			tubex -= 1;
			tubex2 -= 1;
			x -= 2;
			
			//Changes the velocity according to the gravity and changes the bird ycor.
			flappy.setV(flappy.getV()+ flappy.getG());
			flappy.setY(flappy.getY()+ (int)flappy.getV());
			
			//Checks if the tubes are at the leftmost side to generate new tubes.
			if (tubex == 0) {
				tubex = width + 50;
				rand1 = height - (int) random(700, 800);
				rand2 = height - (int) random(250, 320);
			}
			if (tubex2 == 0) {
				tubex2 = width + 5;
				rand3 = height - (int) random(700, 800);
				rand4 = height - (int) random(250, 320);
			}
			
			//Unsuccessful implementation of collision.
			if (flappy.getX() == tubex || flappy.getX() == tubex2) {
				if (flappy.getY() == 0 || flappy.getY() == height || flappy.getY() == rand1 || flappy.getY() == rand2 || flappy.getY() == rand3 || flappy.getY() == rand4)
					game = 1;
			}
			
		}
		
		//If the game has not started, it loads the start screen image.
		else {
			imageMode(CENTER);
			image(start, width / 2, height / 2);
		}
	}

	
	// checks if the user is at the start menu and also sets causes the bird to "fly"
	public void mousePressed() {
		if (game == 1)
			game = 0;
		flappy.setV(-5);
		flappy.setY(flappy.getY()+ (int)flappy.getV());
	}

}