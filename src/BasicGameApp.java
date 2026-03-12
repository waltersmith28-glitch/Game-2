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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;
    int stage = 0;

//these are all the variables in the BasicGameApp
    Knight knight;
    Meteor meteor1;
    Meteor meteor2;
    Meteor meteor3;
    Meteor meteor4;
    Rock rock1;
    Rock rock2;
    Rock rock3;
    Trophy trophy;
    Image[] backgrounds = new Image[3];
    Image gameoverscreen;
    boolean gameover;
    boolean win = false;
    Winscreen winscreen;
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
        knight = new Knight(0, 600, 5.5, 0, 75, 75);
        knight.name = "Knight";
        //where the knight is drawn
        knight.aliveImage = Toolkit.getDefaultToolkit().getImage("Knight.jpg");
        knight.deadImage = Toolkit.getDefaultToolkit().getImage("grave.png");
        //where the "game over" screen is drawn
        gameoverscreen = Toolkit.getDefaultToolkit().getImage("game.jpg");
        //where the meteors get their speed and position
        meteor1 = new Meteor(random.nextInt(1000), 0, 9, 90, 90);
        meteor2 = new Meteor(random.nextInt(1000), 0, 11, 75, 75);
        meteor3 = new Meteor(random.nextInt(1000), 0, 8, 125, 125);
        meteor4 = new Meteor(random.nextInt(1000), 0, 7, 100, 100);
        //this code tells the game where the file is for the image
        meteor1.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
        meteor2.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
        meteor3.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
        meteor4.image = Toolkit.getDefaultToolkit().getImage("Meteor.png");
        //where the rocks get their speed, size, and position
        rock1 = new Rock(200, 570, 100, 100);
        rock2 = new Rock(400, 570, 100, 100);
        rock3 = new Rock(600, 570, 100, 100);
        //this code tells the game where the file is for the image
        rock1.image = Toolkit.getDefaultToolkit().getImage("rock.png");
        rock2.image = Toolkit.getDefaultToolkit().getImage("rock.png");
        rock3.image = Toolkit.getDefaultToolkit().getImage("rock.png");
        //where the trophy gets speed, size, and position.
        trophy = new Trophy(450,300,1,100,100);
        //this code tells the game where the file is for the image
        trophy.image = Toolkit.getDefaultToolkit().getImage("trophy.png");
        //where the backgrounds get drawn, using an array
        backgrounds[0] = Toolkit.getDefaultToolkit().getImage("Grass.jpg");
        backgrounds[1] = Toolkit.getDefaultToolkit().getImage("CastleHall.png");
        backgrounds[2] = Toolkit.getDefaultToolkit().getImage("win.jpeg");
        //where the winscreen gets its size and position
        winscreen = new Winscreen(450,300,200,200);
        //where the code tells the game where the file is for the image
        winscreen.image = Toolkit.getDefaultToolkit().getImage("youwin.png");

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

    //this is where everything gets told to move
    public void moveThings() {
        knight.move(stage);
        meteor1.move();
        meteor2.move();
        meteor3.move();
        meteor4.move();
        trophy.move();
        //call the move() code for each object
    }

    //this is where my code checks for collisions
    public void checkCollisions() {
        if (stage == 0) {
            if (meteor1.hitbox.intersects(knight.hitbox)) {
                knight.dx = 0;
                knight.isAlive = false;
                gameover = true;
            }
            if (meteor2.hitbox.intersects(knight.hitbox)) {
                knight.dx = 0;
                knight.isAlive = false;
                gameover = true;
            }
            if (meteor3.hitbox.intersects(knight.hitbox)) {
                knight.dx = 0;
                knight.isAlive = false;
                gameover = true;
            }
            if (meteor4.hitbox.intersects(knight.hitbox)) {
                knight.dx = 0;
                knight.isAlive = false;
                gameover = true;
            }
            }
            if(stage==1) {
                if (rock1.hitbox.intersects(knight.hitbox)) {
                    knight.dx = 0;
                    knight.isAlive = false;
                    gameover = true;
                    System.out.println('j');
                }
                if (rock2.hitbox.intersects(knight.hitbox)) {
                    knight.dx = 0;
                    knight.isAlive = false;
                    gameover = true;
                    System.out.println('l');
                }
                if (rock3.hitbox.intersects(knight.hitbox)) {
                    knight.dx = 0;
                    knight.isAlive = false;
                    gameover = true;
                    System.out.println('o');
                }
            }
    }

    //This is where everything is rendered on the screen with a position. Also, where the stage(level) gets changed
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //this "if statement" tells the game to draw the first level, and tells the game to
        // switch the background when the knight reaches a specific point.
        if (stage == 0) {
            g.drawImage(backgrounds[stage], 0, 0, WIDTH, HEIGHT, null);
        }
        if (knight.hitbox.contains(740, 630)) {
            stage = 1;
            knight.ypos = 570;
            knight.xpos = 1;

        }
        //This "if statement" tells the game to change the level and draw a new background.
        //it also adds the rocks into stage 1 and makes the meteors dissapear.
        if (stage == 1) {
            g.drawImage(backgrounds[stage], 0, 0, WIDTH, HEIGHT, null);
            meteor1.hitbox = null;
            meteor2.hitbox = null;
            meteor3.hitbox = null;
            meteor4.hitbox = null;
            g.drawImage(rock1.image, rock1.xpos, rock1.ypos, rock1.width, rock1.height, null);
            g.drawImage(rock2.image, rock2.xpos, rock2.ypos, rock2.width, rock2.height, null);
            g.drawImage(rock3.image, rock3.xpos, rock3.ypos, rock3.width, rock3.height, null);

            //this "if statement" changes the stage to stage 2 and teleport the knight to the left
            //of the screen.
            if (knight.hitbox.contains(950, 570)) {
                stage = stage + 1;
                knight.xpos = 100;
                knight.ypos = 500;
            }
        }
        //This "if statement" draws the background for stage 2.
        if (stage == 2) {
            g.drawImage(backgrounds[stage], 0, 0, WIDTH, HEIGHT, null);
            g.drawImage(trophy.image, trophy.xpos, trophy.ypos, trophy.width, trophy.height, null);
        }
//This "if statement" tells the game to draw the alive image when isAlive is true.
        if (knight.isAlive) {
            g.drawImage(knight.aliveImage, knight.xpos, knight.ypos, knight.width, knight.height, null);
        }
        //the next 4 "if statements" tell the game to draw the meteors when the stage is set to 0.
        if (stage == 0) {
            g.drawImage(meteor1.image, meteor1.xpos, meteor1.ypos, meteor1.width, meteor1.height, null);
        }
        if (stage == 0) {
            g.drawImage(meteor2.image, meteor2.xpos, meteor2.ypos, meteor2.width, meteor2.height, null);
        }
        if (stage == 0) {
            g.drawImage(meteor3.image, meteor3.xpos, meteor3.ypos, meteor3.width, meteor3.height, null);
        }
        if (stage == 0) {
            g.drawImage(meteor4.image, meteor4.xpos, meteor4.ypos, meteor4.width, meteor4.height, null);
        }
        //this "if statement" tells the game to draw the gameover screen if "gameover" is true.
        if (gameover == true) {
            g.drawImage(gameoverscreen, 0, 0, WIDTH, HEIGHT, null);
        }
        //this "if statement" tells the game to draw the Winscreen if "win" is true.
        if(win == true){
            g.drawImage(winscreen.image, winscreen.xpos, winscreen.ypos, winscreen.width, winscreen.height, null);
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
        canvas.addMouseListener(this);
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

    //this is how the knight moves with the keys
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if (key == 65) {
            knight.left = true;
        }
        if (key == 68) {
            knight.right = true;
        }


    }

    //this is the code telling the knight to stop when you let go of the key
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 65) {
            knight.left = false;
        }
        if (key == 68) {
            knight.right = false;
        }
        //this code is how I am able to jump. The (knight.ypos > 500) is to make sure you can double but not triple jump
        if (knight.ypos > 500) {
            if (key == 32) {
                knight.dy = -4;
            }
        }
        }


        @Override
        public void mouseClicked (MouseEvent e){

        }
        //this code tells the game to make "win" true when the trophy is pressed
        @Override
        public void mousePressed (MouseEvent e){
            int mouseX = e.getX();
            int mouseY = e.getY();
            if(stage==2){
                if(trophy.hitbox.contains(mouseX,mouseY)){
                    win = true;
                }
            }
            }

    @Override
        public void mouseReleased (MouseEvent e){

        }

        @Override
        public void mouseEntered (MouseEvent e){

        }

        @Override
        public void mouseExited (MouseEvent e){

        }

}