import java.io.*;
import java.util.*;

/**
* UI baraye kaarbar
*/

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static User user;

	public static int toInt(String S) {
		try {
			int s = Integer.parseInt(S);
			return s;
		}
		catch(NumberFormatException nfe) {
			return -1;
		}
	}
	public static void showGame(Football game) {
		System.out.println("");
		game.showGame();
		System.out.println("start time: " + game.time);
		System.out.println("");
		System.out.printf("Press 1 if you want to bet on %s win - %.2f%n", game.homeTeam.name, game.homeWin.ratio);
		System.out.printf("Press 2 if you want to bet on draw - %.2f%n", game.draw.ratio);
		System.out.printf("Press 3 if you want to bet on %s win - %.2f%n", game.awayTeam.name, game.awayWin.ratio);
		System.out.println("Press Q if you want to back to game manu");
		System.out.println("");
		System.out.print("Enter your input: ");
		String inp = scanner.nextLine();
		if (inp.equals("1")) {
			user.addEventToCurrentBet(game.homeWin);
			home();
			return;
		}
		else if (inp.equals("2")) {
			user.addEventToCurrentBet(game.draw);
			home();
			return;
		}
		else if (inp.equals("3")) {
			user.addEventToCurrentBet(game.awayWin);
			home();
			return;
		}
		else if (inp.equals("Q")) {
			gameMenu();
			return;
		}
		else {
			System.out.println("Invalid input!");
			showGame(game);
			return;
		}
	}
	public static void gameMenu() {
		ArrayList<Football> games = new ArrayList<Football> ();
		System.out.println("");
		System.out.println("You can input the number of game that you want to bet");
		System.out.println("");
		for (int i = 0; i < Football.games.size(); ++ i) {
			Football game = Football.games.get(i);
			if (game.finish == false) {
				games.add(game);
			}
		}
		for (int i = 0; i < games.size(); ++ i) {
			Football game = games.get(i);
			System.out.print(i + ": ");
			game.showGame();
		}
		System.out.println("");
		System.out.println("Press Q if you want to back to home");
		System.out.println("");
		System.out.print("Enter your input: ");
		String inp = scanner.nextLine();
		int ind = toInt(inp);
		if (0 <= ind && ind < games.size()) {
			Football game = games.get(ind);
			showGame(game);
			return;
		}
		else if (inp.equals("Q")) {
			home();
			return;
		}
		else {
			System.out.println("Invalid input!");
			gameMenu();
			return;
		}
	}
	public static void payBet() {
		System.out.println("");
		System.out.print("Please enter the value you want to bet: ");
		String inp = scanner.nextLine();
		int val = toInt(inp);
		if (val < 0) {
			System.out.println("Invalid input!");
			payBet();
			return;
		}
		if (user.addMoney(-val) == false) {
			System.out.println("You don't have enough money to pay!");
			payBet();
			return;
		}
		user.setCurBet(val);
		home();
		return;
	}
	public static void resetBet() {
		user.setCurBet(0);
		home();
		return;
	}
	public static void logOut() {
		start();
		return;
	}
	public static void chargeAccount() {
		System.out.println("");
		System.out.print("Please Enter the value you want to charge: ");
		String inp = scanner.nextLine();
		int val = toInt(inp);
		if (val < 0) {
			System.out.println("Invalid input!");
			chargeAccount();
			return;
		}
		user.addMoney(val);
		System.out.println("done!");
		home();
		return;
	}
	public static void home() {
		System.out.println("");
		user.showCurrentBet();
		System.out.println("");
		System.out.println("Your balance: " + user.getMoney());
		System.out.println("");
		System.out.println("Press 1 if you want to go to game manu");
		System.out.println("Press 2 if you want to pay your current bet");
		System.out.println("Press 3 if you want to reset your current bet");
		System.out.println("Press 4 if you want to charge your account");
		System.out.println("Press Q if you want to log out");
		System.out.println("");
		System.out.print("Enter your input: ");
		String inp = scanner.nextLine();
		if (inp.equals("1")) {
			gameMenu();
			return;
		}
		else if (inp.equals("2")) {
			payBet();
			return;
		}
		else if (inp.equals("3")) {
			resetBet();
			return;
		}
		else if (inp.equals("4")) {
			chargeAccount();
			return;
		}
		else if (inp.equals("Q")){
			logOut();
			return;
		}
		else {
			System.out.println("Invalid input!");
			home();
			return;
		}
	}
	public static void login() {
		System.out.println("");
		System.out.print("Enter your email: ");
		String email = scanner.nextLine();
		if (User.isNew(email)) {
			System.out.println("Please register before login!");
			start();
			return;
		}
		user = User.getUserByEmail(email);
		System.out.print("Enter your password : ");
		String password = scanner.nextLine();
		if (user.isTruePassword(password)) {
			System.out.println("");
			System.out.println("Welcome " + user.getName() + " !");
			home();
			return;
		}
		else {
			System.out.println("Incorrect password");
			start();
		}
		return;
	}
	public static void register() {
		System.out.println("");
		System.out.print("Please enter your email: ");
		String email = scanner.nextLine();
		if (User.isNew(email) == false) {
			System.out.println("You have already register!");
			register();
			return;
		}
		System.out.print("Please enter your name: ");
		String name = scanner.nextLine();
		System.out.print("Please enter your password: ");
		String password = scanner.nextLine();
		System.out.print("Please enter your password again: ");
		String password2 = scanner.nextLine();
		if (password.equals(password2) == false) {
			System.out.println("Passwords aren't same!");
			register();
			return;
		}
		user = new User(name, email, password);
		System.out.println("Your account created successfully!");
		start();
		return;
	}
	public static void start() {
		System.out.println("");
		System.out.println("Press 1 to login");
		System.out.println("Press 2 to register");
		System.out.println("Press Q to exit");
		System.out.println("");
		System.out.print("Enter your input: ");
		String inp = scanner.nextLine();
		if (inp.equals("1")) {
			login();
			return;
		}
		else if (inp.equals("2")) {
			register();
			return;
		}
		else if (inp.equals("Q")) {
			return;
		}
		else {
			System.out.println("Invalid input!");
			start();
			return;
		}
	}
	/*
	public static void main(String[] args) {
		//Data.resetData();
		Data.recoverData();
		
		Team barca = new Team("FC Barcelona");
		Team real = new Team("Real Madrid");
		Team ajax = new Team("Ajax");
		Team colombia = new Team("Colombia");
		Team senegal = new Team("Senegal");
		User amshz = new User("AmShZ", "amshz", "1383");
		User keshi = new User("keshi", "keshi@gmail.com", "42");
		amshz.addMoney(10);
		Football elClassico = new Football(barca, real, "2:00", 2, 3, 2);
		Football cose = new Football(colombia, senegal, "Farda saat 6", 2.33f, 3.4f, 4.2f);
		amshz.addEventToCurrentBet(elClassico.homeWin);
		amshz.setCurBet(4);
		elClassico.endGame(3, 1);
		
		
		start();
	}
	*/
}