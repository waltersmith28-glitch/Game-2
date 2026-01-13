//Basic Game Application
// Basic Object, Image, Movement
// Threaded

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class BasicGameApp implements Runnable, KeyListener{

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;


    Knight knight;
    Meteor meteor1;
    Meteor meteor2;
    Meteor meteor3;
    Meteor meteor4;
    Image background;
    Image gameoverscreen;
    boolean gameover;
    Random random = new Random();

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }


    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor

        setUpGraphics();
        knight = new Knight(500, 600, 5.5, 5.5, 75, 75);
        knight.name = "Knight";
        knight.aliveImage = Toolkit.getDefaultToolkit().getImage("Knight.jpg");
        knight.deadImage = Toolkit.getDefaultToolkit().getImage("grave.png");
        gameoverscreen = Toolkit.getDefaultToolkit().getImage("game.jpg");
        meteor1 = new Meteor(random.nextInt(1000),0,9,90,90);
        meteor2 = new Meteor(random.nextInt(1000),0,11,75,75);
        meteor3 = new Meteor(random.nextInt(1000), 0,8,125,125);
        meteor4 = new Meteor(random.nextInt(1000),0,7,100,100);
        meteor1.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
        meteor2.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
        meteor3.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
        meteor4.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
      background = Toolkit.getDefaultToolkit().getImage("Grass.jpg");
        ;
        ;  //variable and objects
        //create (construct) the objects needed for the game

    } // end BasicGameApp constructor


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();
            checkCollisions();// paint the graphics
            pause(10); // sleep for 10 ms
        }
    }

    public void moveThings() {
        knight.move();
        meteor1.move();
        meteor2.move();
        meteor3.move();
        meteor4.move();
        //call the move() code for each object
    }

    public void checkCollisions() {
        if(meteor1.hitbox.intersects(knight.hitbox)){
            knight.dx = 0;
            knight.isAlive = false;
            gameover = true;
        }
        if(meteor2.hitbox.intersects(knight.hitbox)){
            knight.dx = 0;
            knight.isAlive = false;
            gameover = true;
        }
        if(meteor3.hitbox.intersects(knight.hitbox)){
            knight.dx = 0;
            knight.isAlive = false;
            gameover = true;
        }
        if(meteor4.hitbox.intersects(knight.hitbox)){
            knight.dx = 0;
            knight.isAlive = false;
            gameover = true;
        }

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw the images
        g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
        if (knight.isAlive) {
            g.drawImage(knight.aliveImage, knight.xpos, knight.ypos, knight.width, knight.height, null);
        }
        else {
            g.drawImage(knight.deadImage, knight.xpos, knight.ypos, knight.width, knight.height, null);
        }
        if(knight.isAlive){
            g.drawImage(meteor1.image, meteor1.xpos, meteor1.ypos, meteor1.width, meteor1.height, null);
        }
        if(knight.isAlive) {
            g.drawImage(meteor2.image, meteor2.xpos, meteor2.ypos, meteor2.width, meteor2.height, null);
        }
        if(knight.isAlive) {
            g.drawImage(meteor3.image, meteor3.xpos, meteor3.ypos, meteor3.width, meteor3.height, null);
        }
        if(knight.isAlive) {
            g.drawImage(meteor4.image, meteor4.xpos, meteor4.ypos, meteor4.width, meteor4.height, null);
        }
        if (gameover == true){
            g.drawImage(gameoverscreen, 0, 0, WIDTH,HEIGHT,null);
        }
        //g.fillRect(knight.xpos+20, knight.ypos+10, knight.width-40, knight.height-40);
        g.dispose();
        bufferStrategy.show();
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        canvas.addKeyListener(this);
        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if(key == 65){
            knight.left = true;
        }
        if(key == 68){
            knight.right = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if(key == 65){
            knight.left = false;
        }
        if(key == 68) {
            knight.right = false;
        }

    }



}