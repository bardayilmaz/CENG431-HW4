package util;

import java.util.InputMismatchException;
import java.util.Scanner;

import valuable.Dollar;
import valuable.Euro;
import valuable.Gold;
import valuable.TurkishLira;
import valuable.Valuable;

public class InputUtil {

	public static int getOption() {
		int option = -1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type option: ");
		try {
			option = scanner.nextInt();
		} catch(InputMismatchException exception) {
			System.out.println("Invalid input!");
			return -1;
		}
		return option;
	}
	
	public static Valuable getValuable(String input) {
		if(input.equalsIgnoreCase("usd")) {
			return Dollar.getInstance();
		} else if(input.equalsIgnoreCase("eur")) {
			return Euro.getInstance();
		} else if(input.equalsIgnoreCase("gold")) {
			return Gold.getInstance();
		} else if(input.equalsIgnoreCase("try")) {
			return TurkishLira.getInstance();
		} else {
			System.out.println("Invalid valuable!");
			return null;
		}
	}
}
