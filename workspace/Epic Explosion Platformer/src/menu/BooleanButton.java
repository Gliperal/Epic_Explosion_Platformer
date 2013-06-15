package menu;

/**
 * Write a description of class BooleanButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.image.BufferedImage;
import java.awt.Graphics;
public class BooleanButton 
extends Clickable 
{
    BufferedImage Idle=null;
    BufferedImage Over=null;
    BufferedImage Clicked=null;
    public BooleanButton(int x,int y,int sX,int sY, BufferedImage idle,BufferedImage over,BufferedImage clicked){
        super(x,y,sX,sY);
        Idle = idle;
        Over = over;
        Clicked = clicked;
    }
    public void render(Graphics g,int x, int y){
        switch(super.over(x,y)){
            case idle: 
                g.drawImage(Idle,super.x()+x,super.y()+y,null);
                break;
            case over:
                g.drawImage(Over,super.x()+x,super.y()+y,null);
                break;
            case clicked:
                g.drawImage(Clicked,super.x()+x,super.y()+y,null);
                break;
            default:break;
        }
    }
}
