package menu;

/**
 * @author Devan
 */
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import java.util.*;
public class Screen implements Renderable
{
    static Menu menu = Menu.getInstance();
    public final int width = Menu.width;
    public final int height= Menu.height;
    int pos = 0;
    int posX,posY;
    int sizeX,sizeY;
    BufferedImage Background;
    ArrayList<Clickable>Clickables = new ArrayList<Clickable>();
    ArrayList<Screen>Screens = new ArrayList<Screen>();
    //makes a screen for a specific place
    public Screen(BufferedImage background,boolean on,int x, int y,int sY,int sX){
        menu.getScreens().add(on);
        pos = menu.getScreens().size()-1;
        Image img = background.getScaledInstance(sX, sY, BufferedImage.SCALE_SMOOTH);
        Background= new BufferedImage(sX,sY,BufferedImage.TYPE_INT_RGB);
        Background.getGraphics().drawImage(img,0,0,null);
        posX = x;
        posY = y;
        sizeX=sX;
        sizeY=sY;
    }
    //makes a screen that fits the entire screen and strechs the image if its to small
    public Screen(BufferedImage background,boolean on){
        posX =0;
        posY =0;
        sizeX=width;
        sizeY=height;
        menu.getScreens().add(on);
        pos= menu.getScreens().size()-1;
        Image img = background.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        Background= new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Background.getGraphics().drawImage(img,0,0,null);
    }
    //returns whether or not it can be shown on screen
    public boolean onscreen(){
        menu =Menu.getInstance();
        boolean on = menu.getScreens().get(pos);
        return on;
    }
    public void update(BufferedImage background){
        Image img = background.getScaledInstance(sizeX, sizeY, BufferedImage.SCALE_FAST);
        Background= new BufferedImage(sizeX,sizeY,BufferedImage.TYPE_INT_RGB);
        Background.getGraphics().drawImage(img,0,0,null);
    }
    //sets if it should bee shown on screen
    public void onscreen(boolean on){
        menu =Menu.getInstance();
        menu.getScreens().set(pos,on);
    }
    public void addClickable(Clickable lol){
    	System.out.println("Added");
        Clickables.add(lol);
    }
    public void addScreen(Screen lol){
        Screens.add(lol);
    }
    public int y(){
        return posY;
    }
    public int x(){
        return posX;
    }
    public boolean status(int i){
    	//System.out.println("Clickables:"+Clickables.size());
    	//System.out.println("i:"+i);
    	if(i<Clickables.size()){
            if(Clickables.get(i).over(posX,posY)==State.clicked)
                return true;
            //System.out.println("Not clicked!");
            return false;
            }
        //System.out.println("Requist for clickables is to large!");
        return false;
        }    
    //renders stuff when the position of the screen is not needed I.E. The main menu
    public void render(Graphics g){
        if(onscreen()){
            g.drawImage(Background,posX,posY,null);
            for(int i=0;i<Screens.size();i++){
                Screens.get(i).render(g,posX,posY);
            }
            for(int i=0;i<Clickables.size();i++){
                Clickables.get(i).render(g,posX,posY);
            }
        }
    }
    //renders stuff when the position of the screen is needed
    public void render(Graphics g,int x, int y){
        if(onscreen()){
            g.drawImage(Background,posX+x,posY+y,null);
            for(int i=0;i<Screens.size();i++){
                Screens.get(i).render(g,posX+x,posY+y);
            }
            for(int i=0;i<Clickables.size();i++){
                Clickables.get(i).render(g,posX+x,posY+y);
            }
        }
    }
}
