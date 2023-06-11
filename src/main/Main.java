package main;

import java.util.Scanner;

import bank.ConcreteBank;
import bank.IBank;
import factory.AccountFactory;
import factory.InterestAccountFactory;
import product.IValuableAccount;
import user.BankUser;
import user.ClientUser;
import user.IUser;
import util.InputUtil;
import valuable.Dollar;
import valuable.Euro;
import valuable.Gold;
import valuable.TurkishLira;
import valuable.Valuable;

public class Main {

	public static void main(String[] args) {
		IBank bank = new ConcreteBank();
		IUser bankUser = new BankUser(bank, "BerkeUdunman");
		bank.addUser(bankUser);
		initFirstInterestRatesAndValues();
		int input = -1;
		boolean terminate = false;
		System.out.println("Welcome to Bank 413! How can we help you? - Please use names 'without' whitespaces. Thanks for your understanding ^^.");
		while(input != 0) {
			listAppOptions();
			input = InputUtil.getOption();
			evaluateAppOptions(input, bank);
		}
	}
	
	private static void initFirstInterestRatesAndValues() {
		Euro.getInstance().setInterestRate(0.1);
		Euro.getInstance().setValue(25.16);
		Dollar.getInstance().setInterestRate(0.1);
		Dollar.getInstance().setValue(23.4);
		Gold.getInstance().setInterestRate(0.05);
		Gold.getInstance().setValue(1500);
		TurkishLira.getInstance().setInterestRate(0.2);
		TurkishLira.getInstance().setValue(1);
	}
	
	private static void listAppOptions() {
		System.out.println("1. Switch to user\n2. List Users\n0. Exit");
	}
	
	private static void evaluateAppOptions(int option, IBank bank) {
		Scanner scanner = new Scanner(System.in);
		switch (option) {
		case 1: {
			String userName;
			System.out.println("Type user name: ");
			userName = scanner.next();
			IUser user = bank.getUserByName(userName);
			if(user != null) {
				int userOption = -1;
				while(userOption != 0) {
					System.out.println(user.toString());
					user.displayOptions();
					userOption = InputUtil.getOption();
					user.evaluateOptionInput(userOption);
				}
			}
			break;
		} case 2: {
			bank.listUsers();
			break;
		} case 0: {
			System.out.println("Exit");
			break;
		}
		default:
			break;
		}
	}
}
