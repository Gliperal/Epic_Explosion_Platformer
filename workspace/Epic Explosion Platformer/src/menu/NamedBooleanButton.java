package menu;

/**
 * Write a description of class NamedBooleanButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class NamedBooleanButton
extends BooleanButton
{
    String name = "";
    int x = 0;
    int y = 0;
    public NamedBooleanButton(int x, int y, int sX, int sY,BufferedImage idle, BufferedImage over, BufferedImage clicked){
        super(x,y,sX,sY,idle,over,clicked);
    }
    public void giveName(String Name, int posX, int posY){
        x = posX;
        y = posY;
        name = Name;
    }
    public void changeName(String Name){
    	name = Name;
    }
    public void render(Graphics g, int X, int Y){
        super.render(g,X,Y);
        g.drawString(name,x+X+super.x(),y+Y+super.y());
    }
}
