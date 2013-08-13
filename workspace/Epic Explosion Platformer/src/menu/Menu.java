package menu;

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class Menu
{
    private static Menu menu = new Menu();
    public static int width = 1280;
    public static int height= 720;
    private static ArrayList<Boolean> screens = new ArrayList<Boolean>();
    //prevents other classes from instancing
    private Menu(){
    }
    //instance method
    public static Menu getInstance(){
        return menu;
    }
    public static void changeSize(int w,int h){
        width = w;
        height = h;
    }
    /**
     * 
     * @param s
     * @return the index assigned to the screen being added
     */
    public int addScreen(boolean on) {
    	screens.add(on);
    	return screens.size()-1;
    }
	public boolean isScreenVisible(int screenIndex) {
		return screens.get(screenIndex);
	}
	//public void setScreens(ArrayList<Boolean> screens) {
	//	Menu.screens = screens;
	//}
}
