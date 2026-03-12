import java.awt.*;

public class Rock {
    //all the variables used for the rocks
    String name;
    Image image;
    int xpos;
    int ypos;
    double dx;
    int width;
    int height;
    Rectangle hitbox;
//this code tells the game what values to use for position and size
    public Rock(int xposInput, int yposInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        width = widthInput;
        height = heightInput;
        //this code creates the hitbox
        hitbox = new Rectangle(xpos+20, ypos+20, width-50, height-50);
    }
}

