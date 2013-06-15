package menu;
import utilities.*;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;

/**
 * Class Test - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class MenuDriver 
implements PackageControlObject
{
    // instance variables - replace the example below with your own
    long beginTime;
    BufferedImage Idle= null;
    BufferedImage Over=null;
    BufferedImage Clicked=null;
    BufferedImage menuBack=null;
    BufferedImage startIdle= null;
    BufferedImage startOver=null;
    BufferedImage startClicked=null;
    BufferedImage backScreen = null;
    Screen MainMenu;
    Screen GameScreen;
    Screen Options;
    Image offscreen;
    Graphics graphics;
    static int mX=55,mY=55;
    static boolean clicked = false;
    // kicks off the method
    public void init()
    {
    	menuBack = makeImage(menuBack, "/../Menu/menu.png");
    	backScreen = makeImage(backScreen,"/../Menu/menu.png");
    	startIdle = makeImage(startIdle,"/../Menu/templetidle.png");
    	startOver = makeImage(startOver,"/../Menu/templetover.png");
    	startClicked= makeImage(startClicked,"/../Menu/templetclicked.png");
        MainMenu = new Screen(menuBack,true);
        GameScreen= new Screen(backScreen,false);
        NamedBooleanButton startButton= new NamedBooleanButton(300,250,100,20,startIdle,startOver,startClicked);
        startButton.giveName("Start Game",10,13);
        MainMenu.addClickable(startButton);
        NamedBooleanButton loadButton= new NamedBooleanButton(300,290,100,20,startIdle,startOver,startClicked);
        loadButton.giveName("Load Game",10,13);
        //MainMenu.addClickable(loadButton);
        NamedBooleanButton quitButton= new NamedBooleanButton(300,330,100,20,startIdle,startOver,startClicked);
        quitButton.giveName("Quit Game",10,13);
        MainMenu.addClickable(quitButton);
        //Options = new Screen(Idle,true,200,200,200,200);
        //Options.addRenderable(new BooleanButton(50,50,10,10,Idle,Over,Clicked));
        //Options.addRenderable(new VSliderButton(60,60,10,100,Idle,Over,Clicked,90,0));
        //Options.addRenderable(new HSliderButton(160,160,100,10,Idle,Over,Clicked,90,0));
        //MainMenu.addRenderable(Options);
    }
    public void render(Graphics g)
    {
        MainMenu.render(g);
    }
    public static int mX(){
        return mX;
    }
    public static int mY(){
        return mY;
    }
    public static boolean clicked(){
        return clicked;
    }
	@Override
	public void onUpdate() {
    	Vector mI = UserInput.instance().getMousePosition();
    	mX =(int)(double)mI.getComponent(CoordinateAxis.x);
    	mY =(int)(double)mI.getComponent(CoordinateAxis.y);
    	clicked = UserInput.instance().mouseClicked();
    	//System.out.println("clicked:"+clicked);
    	//System.out.println(MainMenu.onscreen());
    	//System.out.println(MainMenu.status(0));
    	if(MainMenu.onscreen()){
    		if(MainMenu.status(1)==true){
    			System.out.println("Yolo");
    			System.exit(0);
    		}
    		if(MainMenu.status(0)==true){
    			MainMenu.onscreen(false);
    			MainMenu.Clickables.remove(0);
    	        NamedBooleanButton startButton= new NamedBooleanButton(300,250,100,20,startIdle,startOver,startClicked);
    	        startButton.giveName("Cont. Game",10,13);
    			MainMenu.addClickable(startButton);
    			
    			Frame.switchControlObject(new platformer.PlatformerFrame());
    		}
    		
    	}
    	else{
    	}
	}
	private BufferedImage makeImage(BufferedImage foo, String lol){
		String applicationDirectory = new PublicVariables().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
    	try {
			applicationDirectory = URLDecoder.decode(applicationDirectory, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	File Foo = new File(applicationDirectory + lol);
    	System.out.println(applicationDirectory);
    	System.out.println(Foo.exists());
        try {
            foo =  ImageIO.read(Foo);
        } catch (IOException ex) {
            System.out.println("Image not here");
        }
		return foo;
		
	}
}
