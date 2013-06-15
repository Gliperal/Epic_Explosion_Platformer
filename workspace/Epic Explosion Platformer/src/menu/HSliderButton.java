package menu;

/**
 * Write a description of class SliderButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
public class HSliderButton
extends Clickable
{
    boolean on =false;
    int xMax;
    int xCur;
    int bS;
    int xStart;
    BufferedImage back;
    BufferedImage idle;
    BufferedImage over;
    BufferedImage clicked;
    Graphics g;
    //ymax sets how far the slider can go, set it to the size of the knob minus sY
    public HSliderButton(int x,int y,int sX,int sY,BufferedImage Idle,BufferedImage Over,BufferedImage Clicked,int xmax,int xstart){
        super(x,y,sX,sY);
        xMax = xmax;
        bS = (sX-xmax)/2;
        back = new BufferedImage(sX,sY,BufferedImage.TYPE_INT_RGB );
        g = back.getGraphics();
        g.setColor(new Color(200,200,200));
        g.fillRect(0,0,sX,sY);
        idle = Idle;
        over = Over;
        clicked = Clicked;
        xStart = xstart;
        if(x<=xstart+x&&xMax>=xstart)
            xCur = xstart+x;
        else{
            System.out.println("ERROR! start value for a SliderButton is to large or to small:"+x+"<="+xstart+"<="+xMax);
            xCur = x;
        }
    } 
    public HSliderButton(int x,int y,int sX,int sY,BufferedImage Idle,BufferedImage Over,BufferedImage Clicked,int xmax,int xstart,Color backColor){
        super(x,y,sX,sY);
        xMax = xmax;
        bS = (sX-xmax/2);
        back = new BufferedImage(sX,sY,BufferedImage.TYPE_INT_RGB );
        g = back.getGraphics();
        g.setColor(backColor);
        g.fillRect(0,0,sX,sY);
        idle = Idle;
        over = Over;
        xStart = xstart;
        clicked = Clicked;
        if(x<=xstart+x&&xMax>=xstart)
            xCur = xstart+x;
        else{
            System.out.println("ERROR! start value for a SliderButton is to large or to small:"+x+"<="+xstart+"<="+xMax);
            xCur = x;
        }
    }
    public void render(Graphics g,int x, int y){
        g.drawImage(back,super.x()+x,super.y()+y,null);
        if(xCur!=xStart+super.x()+x)
            xCur =xStart+super.x()+x;
        switch(super.over(x,y)){
            case idle: 
                g.drawImage(idle,xCur,super.y()+y,null);
                break;
            case over:
                g.drawImage(over,xCur,super.y()+y,null);
                break;
            case clicked:
                xCur = MenuDriver.mX()-bS;
                if(xCur>xMax+super.x()+x)
                    xCur=xMax+super.x()+x;
                if(xCur<super.x()+x)
                    xCur = super.x()+x;
                g.drawImage(clicked,xCur,super.y()+y,null);
                break;
            default:break;
        }
        xStart = xCur-super.x()-x;
    }
}
