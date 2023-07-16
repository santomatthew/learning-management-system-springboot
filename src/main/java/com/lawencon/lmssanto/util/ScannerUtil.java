package com.lawencon.lmssanto.util;

import java.util.Scanner;


public class ScannerUtil {

	public static int scannerInt(String question, int min, int max) {
		System.out.print(question);
		final Scanner scan = new Scanner(System.in);
		try {
			final String input = scan.nextLine().trim();
			final InputChecker inputChecker = new InputChecker();
			final Boolean isInputString = inputChecker.isString(input);
			if (isInputString) {
				return scannerInt(question, min, max);
			} else {
				int inputStrToInt = Integer.parseInt(input);
				final Boolean isOutOfRange = inputChecker.isOutOfRange(inputStrToInt, min, max);
				if (isOutOfRange) {
					return scannerInt(question, min, max);
				} else {
					return inputStrToInt;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return scannerInt(question, min, max);
		}

	}

	public static int scannerNoMaximum(String question, int min) {
		System.out.print(question);
		final Scanner scan = new Scanner(System.in);

		try {
			final String input = scan.nextLine().trim();
			final InputChecker inputChecker = new InputChecker();
			final Boolean isInputString = inputChecker.isString(input);
			if (isInputString) {
				return scannerNoMaximum(question, min);
			} else {
				int inputStrToInt = Integer.parseInt(input);
				final Boolean isLessThaMin = inputChecker.isLessThanMinimum(inputStrToInt, min);
				if (isLessThaMin) {
					return scannerNoMaximum(question, min);
				} else {
					return inputStrToInt;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return scannerNoMaximum(question, min);
		}
	}

	public static String scannerStr(String question) {
		System.out.print(question);
		final Scanner scan = new Scanner(System.in);
		final String input = scan.nextLine().trim();
		if (input.equals("")) {
			System.out.println("Invalid Input");
			return scannerStr(question);
		} else {
			return input;
		}
	}

	public static boolean scannerBool(String question) {
		System.out.print(question);
		Boolean option = true;
		final Scanner scan = new Scanner(System.in);
		final int input = scan.nextInt();
		if (input == 1) {
			option = true;

		} else if (input == 2) {
			option = false;
		}
		else {
			System.out.println("Inputan salah silahkan coba lagi");
			return scannerBool(question);
		}

		return option;
	}
	
	public static float scannerFloat(String question, float min,float max) {
		System.out.print(question);
		final Scanner scan = new Scanner(System.in);
		
		try {
			final String input = scan.nextLine().trim();
			final InputChecker inputChecker = new InputChecker();
			final Boolean isInputString = inputChecker.isString(input);
			if (isInputString) {
				return scannerFloat(question, min,max);
			} else {
				float inputStrToInt = Float.parseFloat(input);
				final Boolean isLessThaMin = inputChecker.isOutOfRangeFloat(inputStrToInt, min,max);
				if (isLessThaMin) {
					return scannerFloat(question, min,max);
				} else {
					return inputStrToInt;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return scannerFloat(question, min,max);
		}
	}

}
