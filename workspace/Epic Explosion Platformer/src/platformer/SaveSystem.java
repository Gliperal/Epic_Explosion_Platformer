package platformer;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utilities.Printer;
import utilities.PublicVariables;

public class SaveSystem {
	/*
	public static void main(String[] args) {
		String appDir = PublicVariables.getApplicationDirectory();
		byte[] bar = SaveSystem_FileModifier.loadMapFile(appDir + "../../../a.txt");
		for(int i = 0; i < bar.length; i++) {
			bar[i]++;
		}
		SaveSystem_FileModifier.writeFile(appDir + "../../../b.txt", bar);
	}
	*/
	
	public static void loadMapFile(String mapDirectory) {
		Printer.println("Loading from " + mapDirectory);
		PublicVariables.takeInformations(SaveSystem_FileModifier.loadInformationFile(mapDirectory + "mapinfo.txt"));
		byte[] map = SaveSystem_FileModifier.loadMapFile(mapDirectory + "map.bin");
		map = bmpToBin(mapDirectory + "map.bmp");
		
		int mapWidth = PublicVariables.mapWidth;
		
		int mapScale = PublicVariables.mapScale;
		
		int y = 0;
		boolean done = false;
		while(!done) {
			for(int x = 0; x < mapWidth; x++) {
				byte[] entityAtLocation = new byte[3];
				try {
					int location = (y*mapWidth + x)*3;	// data location
					entityAtLocation[0] = map[location];
					entityAtLocation[1] = map[location + 1];
					entityAtLocation[2] = map[location + 2];
				} catch(ArrayIndexOutOfBoundsException e) {
					done = true;
					break;
				}
				if(entityAtLocation[0] == 0 && entityAtLocation[1] == 0 && entityAtLocation[2] == 0) {
					Platforms.instance().addPlatform(new Platform(x*mapScale, y*mapScale, mapScale, mapScale), x, y);
				}
			}
			y++;
		}
	}
	
	public static byte[] bmpToBin(String bmpFilename) {
		byte[] pixels = new byte[0];
		try {
			BufferedImage image = ImageIO.read(new File(bmpFilename));
			pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		} catch (IOException e) {}
		return pixels;
	}
}
