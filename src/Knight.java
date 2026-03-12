import java.awt.*;

public class Knight{
    //all the variables used for the knight
    String name;
    Image aliveImage;
    Image deadImage;
    int xpos;
    int ypos;
    double dx;
    double dy;
    int width;
    int height;
    Rectangle hitbox;
    boolean isAlive = true;
    boolean right = false;
    boolean left = false;
    boolean up = false;
    int floorHeight;


    public Knight() {
        //this code sets the hitbox
        hitbox = new Rectangle(xpos, ypos, width, height);

    }
//this code tells the game what to use for position, speed, and size.
    public Knight(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;
        //this code sets the hitbox
        hitbox = new Rectangle(xpos+20, ypos+10, width-40, height-40);
    }
//this code is how the knight moves
    public void move(int stage) {
        if (xpos >= 1000) {
            xpos = 1000;
        }

        if (ypos > 600) {
            ypos = 600;

        }
        if (xpos < 0) {
            xpos = 0;
        }
        if (ypos < 0) {
            ypos = 649;
        }
        //this code makes the hitbox follow the knight.
        hitbox = new Rectangle(xpos-20, ypos-20, width-20, height-20);
        //The next 2 "if statements" are how the knight moves right and left.
        if (right) {
            xpos = xpos + (int) dx;
        }
        if (left) {
            xpos = xpos - (int) dx;
        }

        ypos = (int) ((double)ypos + dy);
//the next 3 "if statements" set the floor height for each stage.
        if(stage == 0){
            floorHeight = 600;
        }
        if(stage == 1){
            floorHeight = 570;
        }
        if(stage == 2){
            floorHeight = 500;
        }

        //the next 2 "if statements" are how Jump mechanic works
        if(dy < 4){
            System.out.println(ypos);
            dy += 0.2;
        }
        if(ypos > floorHeight){
            ypos = floorHeight;
        }
            }
        }



