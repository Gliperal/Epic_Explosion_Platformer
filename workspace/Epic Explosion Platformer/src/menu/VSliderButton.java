package menu;

/**
 * Write a description of class VSliderButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
public class VSliderButton
extends Clickable
{
    boolean on =false;
    int yCur;
    int yMax;
    int bS;
    int yStart;
    BufferedImage back;
    BufferedImage idle;
    BufferedImage over;
    BufferedImage clicked;
    Graphics g;
    //ymax is the maximum amount of movement the slider can do, make sure to set this to the to the size of the
    public VSliderButton(int x,int y,int sX,int sY,BufferedImage Idle,BufferedImage Over,BufferedImage Clicked,int ymax,int ystart){
        super(x,y,sX,sY);
        yMax = ymax;
        bS = (sY-ymax)/2;
        back = new BufferedImage(sX,sY,BufferedImage.TYPE_INT_RGB );
        g = back.getGraphics();
        g.setColor(new Color(200,200,200));
        g.fillRect(0,0,sX,sY);
        idle = Idle;
        over = Over;
        clicked = Clicked;
        yStart = ystart;
        if(y<=ystart+y&&yMax>=ystart)
            yCur = ystart+y;
        else{
            System.out.println("ERROR! start value for a SliderButton is to large or to small:"+x+"<="+ystart+"<="+yMax);
            yCur = y;
        }
    } 
    public VSliderButton(int x,int y,int sX,int sY,BufferedImage Idle,BufferedImage Over,BufferedImage Clicked,int ymax,int ystart,Color backColor){
        super(x,y,sX,sY);
        yMax = ymax;
        back = new BufferedImage(sX,sY,BufferedImage.TYPE_INT_RGB );
        g = back.getGraphics();
        g.setColor(backColor);
        bS=(sY-ymax);
        g.fillRect(0,0,sX,sY);
        idle = Idle;
        ystart += y;
        over = Over;
        clicked = Clicked;
        if(x<=ystart+y&&yMax>=ystart)
            yCur = ystart+y;
        else{
            System.out.println("ERROR! start value for a SliderButton is to large or negitive:"+x+"<="+ystart+"<="+yMax);
            yCur = y;
        }
    }
    public void render(Graphics g,int x, int y){
        g.drawImage(back,super.x()+x,super.y()+y,null);
        if(yCur!=yStart+super.y()+y)
            yCur=yStart+super.y()+y;
        switch(super.over(x,y)){
            case idle: 
                g.drawImage(idle,super.x()+x,yCur,null);
                break;
            case over:
                g.drawImage(over,super.x()+x,yCur,null);
                break;
            case clicked:
                yCur = MenuDriver.mY()-bS;
                if(yCur>yMax+super.y()+y)
                    yCur=yMax+super.y()+y;
                if(yCur<super.y()+y)
                    yCur=super.y()+y;
                g.drawImage(clicked,super.x()+x,yCur,null);
                break;
            default:break;
        }
        yStart=yCur-super.y()-y;
    }
}
