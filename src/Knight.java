import java.awt.*;

public class Knight{
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


    public Knight() {
        hitbox = new Rectangle(xpos, ypos, width, height);

    }

    public Knight(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput) {
        xpos = xposInput;
        ypos = yposInput;
        dx = dxInput;
        dy = dyInput;
        width = widthInput;
        height = heightInput;

        hitbox = new Rectangle(xpos+20, ypos+10, width-40, height-40);
    }

    public void move() {
        //xpos = xpos + (int)dx;
        //ypos = ypos + (int) dy;
        if (xpos >= 1000) {
            xpos = 1;
        }

        if (ypos >= 650) {
            ypos = 0;

        }
        if (xpos < 0) {
            xpos = 999;
        }
        if (ypos < 0) {
            ypos = 649;
        }
        hitbox = new Rectangle(xpos-20, ypos-20, width-20, height-20);

        if (right) {
            xpos = xpos + (int) dx;
        }
        if (left) {
            xpos = xpos - (int) dx;
        }
    }
}

