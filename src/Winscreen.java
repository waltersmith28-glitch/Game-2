import java.awt.*;

public class Winscreen {
    //all the variables for "Winscreen"
    int width;
    int height;
    String name;
    Image image;
    int xpos;
    int ypos;
    //this code tells the game what values to put for position and size.
    public Winscreen(int xposInput, int yposInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        width = widthInput;
        height = heightInput;
    }
}
