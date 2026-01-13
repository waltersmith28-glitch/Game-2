import java.awt.*;
import java.util.Random;

public class Meteor {
    String name;
    Image image;
    int xpos;
    int ypos;
    double dy;
    int width;
    int height;
    Rectangle hitbox;
    Random random = new Random();

    public Meteor() {
        hitbox = new Rectangle(xpos+35, ypos+25, width-60, height-30);

    }

    public Meteor(int xposInput, int yposInput, double dyInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;

        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move() {
        ypos = ypos + (int) dy;
        if (ypos > 750) {
            ypos = 0;
            xpos = random.nextInt(1000);
        }
        hitbox = new Rectangle(xpos-30, ypos-30, width-30, height-30);
    }
}


