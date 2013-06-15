package utilities;

import java.util.ArrayList;

public class Util {
	public static String listToString(@SuppressWarnings("rawtypes") ArrayList list) {
		String returnString = "";
		for(int index = 0; index < list.size(); index++) {
			Object element = list.get(index);
			if(index != 0) {
				returnString += ", ";
			}
			returnString += element;
		}
		return returnString;
	}
	
	public static String listToString(Object[] list) {
		String returnString = "";
		for(int index = 0; index < list.length; index++) {
			Object element = list[index];
			if(index != 0) {
				returnString += ", ";
			}
			returnString += element;
		}
		return returnString;
	}
	
	public static double mod(double numberToBeModified, double base) {
		double result = numberToBeModified % base;
		return (result + base) % base;
	}

	public static double bringTowardsValue(double originalValue, double step, double targetValue) {
		if(Math.abs(originalValue - targetValue) <= step) {
			return targetValue;
		}
		if(originalValue > targetValue) {
			return originalValue - step;
		} else {
			return originalValue + step;
		}
	}
}
