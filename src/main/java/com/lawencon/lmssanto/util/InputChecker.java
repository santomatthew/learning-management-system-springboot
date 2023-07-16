package com.lawencon.lmssanto.util;

public class InputChecker {

	Boolean isString(String value) {
		Boolean result = false;

		if (value.equals("")) {
			System.out.println("Invalid Input");
			result = true;
		}

		return result;
	}

	Boolean isOutOfRange(int value, int min, int max) {
		Boolean result = false;
		if (value < min) {
			System.out.println("Inputan tidak bisa lebih kecil dari 1");
			result = true;
		} else if (value > max) {
			System.out.println("Inputan melebihi batas");
			result = true;
		}
		return result;
	}
	
	Boolean isLessThanMinimum(int value, int min) {
		Boolean result = false;
		if (value < min) {
			result = true;
		}

		return result;
	}
	
	Boolean isOutOfRangeFloat(float value, float min, float max) {
		Boolean result = false;
		if (value < min) {
			System.out.println("Inputan tidak bisa lebih kecil dari 1");
			result = true;
		} else if (value > max) {
			System.out.println("Inputan melebihi batas");
			result = true;
		}
		return result;
	}
	
}
