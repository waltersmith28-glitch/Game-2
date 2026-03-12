import java.awt.*;
import java.util.Random;

public class Meteor {
    //all the variables used for the meteors
    String name;
    Image image;
    int xpos;
    int ypos;
    double dy;
    int width;
    int height;
    Rectangle hitbox;
    Random random = new Random();
//this next code sets the hitbox size
    public Meteor() {
        hitbox = new Rectangle(xpos+35, ypos+25, width-60, height-30);

    }
//this next code tells the game what values to use for size, position, and speed.
    public Meteor(int xposInput, int yposInput, double dyInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        //this next code makes the hitboxes follow the meteor
        hitbox = new Rectangle(xpos, ypos, width, height);
    }
//This next code is how the meteors move and get reset at the top randomly when they reach the bottom.
    public void move() {
        ypos = ypos + (int) dy;
        if (ypos > 750) {
            ypos = 0;
            xpos = random.nextInt(1000);
        }
        hitbox = new Rectangle(xpos-30, ypos-30, width-30, height-30);
    }
}


