package platformer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import utilities.Printer;
import utilities.PublicVariables;

public class SaveSystem_FileModifier {
	/**
	 * Imports a file as a string of bytes. Code modeled off of http://www.javapractices.com/topic/TopicAction.do?Id=245
	 * 
	 * @param filename The name of the file to be parsed
	 * @return An array of bytes contained in the file
	 */
	public static byte[] loadMapFile(String filename) {
		// Creating file from which to be read
		File file = new File(filename);
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			ERROR("File not found: " + filename);
		}
		
		// Creating byte[] return array
		int numberOfBytes = (int) file.length();
		byte[] returnList = new byte[numberOfBytes];
		try {
			// Reading file
			inputStream.read(returnList, 0, numberOfBytes);
			// closing input stream
			inputStream.close();
		} catch (IOException e) {
			ERROR("I/O Exception");
		}
		
		return returnList;
	}
	
	public static ArrayList<String> loadInformationFile(String filename) {
		// Creating file from which to be read
		File file = new File(filename);
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			ERROR("File not found: " + filename);
		}
		
		ArrayList<String> returnList = new ArrayList<String>();
		try {
			while(true) {
				String line = input.readLine();
				if(line == null) break;
				returnList.add(line);
			}
		} catch (IOException e) {
			ERROR("I/O Exception");
		}
		
		return returnList;
	}
	
	public static void writeFile(String filename, byte[] data) {
		// Creating file from which to write
		File file = new File(filename);
		OutputStream outputStream = null;
		try {
			outputStream = new BufferedOutputStream(new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			ERROR("File not found: " + filename);
		}

		try {
			// writing file
			outputStream.write(data, 0, data.length);
			// closing output stream
			outputStream.close();
		} catch (IOException e) {
			ERROR("I/O Exception");
		}
	}
	
	public static void ERROR(String s) {
		while(true) {
			try {
				Thread.sleep(400);
			} catch (InterruptedException e1) {}
			System.out.println("ERROR!!! " + s);
		}
	}
}
