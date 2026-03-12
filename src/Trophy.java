import java.awt.*;

public class Trophy {
    //all the variables used for trophy
    int width;
    int height;
    Rectangle hitbox;
    String name;
    Image image;
    double dy;
    int xpos;
    int ypos;
    //this code tells the game what values to give for position, size, and speed.
    public Trophy(int xposInput, int yposInput, double dyInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        //this code creates the hitbox
        hitbox = new Rectangle(xpos,ypos,width,height);
    }
    //this code is how the trophy moves.
        public void move(){
            ypos += dy;
            if(ypos > 350 || ypos < 250){
                dy = -dy;
            }
            //this code makes the hotbox follow where the trophy is going.
            hitbox = new Rectangle(xpos, ypos, width, height);
        }
        }
