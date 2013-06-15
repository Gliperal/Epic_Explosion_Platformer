package menu;

/**
 * Write a description of interface Renderable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Graphics;
public interface Renderable
{
    public void render(Graphics g);
    public void render(Graphics g,int x,int y);
    public int x();
    public int y();
}
