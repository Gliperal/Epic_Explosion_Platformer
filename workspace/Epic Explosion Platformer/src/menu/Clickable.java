package menu;

import java.awt.Graphics;
public abstract class Clickable
implements Renderable
{
    private State state = State.idle;
    private int posX, posY, sizeX, sizeY;
    public Clickable (int x,int y,int sX,int sY){
        posX = x;
        posY = y;
        sizeX = sX;
        sizeY = sY;
    }
    public State over(int X,int Y){
        int x = sizeX+posX+X;
        int y = sizeY+posY+Y;
        if(
                posX+X<=MenuDriver.mX()
                &&MenuDriver.mX()<=x
                &&posY+Y<=MenuDriver.mY()
                &&MenuDriver.mY()<=y
        ) {
            if(MenuDriver.clicked()){
                return State.clicked;
            }
            return State.over;
        }
        return State.idle;
    }
    public State state(){
        return state;
    }
    public int x(){
        return posX;
    }
    public int y(){
        return posY;
    }
    public void x(int X){
        posX = X;
    }
    public void y(int Y){
        posY = Y;
    }
    public void render(Graphics g){}
    public void render(Graphics g, int x, int y){}
}
