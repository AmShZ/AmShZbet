import java.io.*;
import java.util.*;

/**
* UI baraye admin
*/

public class Admin {
    static Scanner scanner = new Scanner(System.in);
    private static String loginPassword = "this is AP";

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
		System.out.println("Enter home team score: ");
        int homeTeamScore = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter away team score: ");
        int awayTeamScore = Integer.parseInt(scanner.nextLine());
        game.endGame(homeTeamScore, awayTeamScore);
        home();
        return;
	}
    public static void gameMenu() {
        ArrayList<Football> games = new ArrayList<Football> ();
        System.out.println("");
		System.out.println("You can input the number of game you want");
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
    public static void addGame() {
        System.out.println("");
        System.out.println("Enter the name of home team: ");
        String homeTeamName = scanner.nextLine();
        System.out.println("Enter the name of away team: ");
        String awayTeamName = scanner.nextLine();
        System.out.println("Enter the time of game: ");
        String time = scanner.nextLine();
        System.out.println("Enter the home team win ratio: ");
        float homeTeamRatio = Float.parseFloat(scanner.nextLine());
        System.out.println("Enter the draw ratio: ");
        float drawRatio = Float.parseFloat(scanner.nextLine());
        System.out.println("Enter the away team win ratio: ");
        float awayTeamRatio = Float.parseFloat(scanner.nextLine());
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        Football game = new Football(homeTeam, awayTeam, time, homeTeamRatio, drawRatio, awayTeamRatio);
        home();
        return;
    }
    public static void home() {
        System.out.println("");
        System.out.println("Press 1 to add a game");
		System.out.println("Press 2 to end a game");
		System.out.println("Press Q if you want to log out");
		System.out.println("");
		System.out.print("Enter your input: ");
		String inp = scanner.nextLine();
		if (inp.equals("1")) {
			addGame();
			return;
		}
		else if (inp.equals("2")) {
			gameMenu();
			return;
		}
		else if (inp.equals("Q")){
			login();
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
		System.out.println("Press Q if you want to exit");
        System.out.println("");
		System.out.print("Enter the password: ");
		String password = scanner.nextLine();
		if (password.equals("Q")) {
			return;
		}
        if (password.equals(loginPassword)) {
            home();
            return;
        }
        else {
            System.out.println("Password was incorrect!");
            login();
            return;
        }
    }
	public static void start() {
		System.out.println("");
		System.out.println("Press 1 to login");
		System.out.println("Press Q to exit");
		System.out.println("");
		System.out.print("Enter your input: ");
		String inp = scanner.nextLine();
		if (inp.equals("1")) {
			login();
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
		Data.recoverData();
		start();
    }
	*/
}