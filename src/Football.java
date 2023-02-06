import java.io.*;
import java.util.*;

public class Football extends Game implements Serializable {
    public Team homeTeam;
    public Team awayTeam;
    public int homeTeamScore;
    public int awayTeamScore;
    //public String time;
    public Event homeWin;
    public Event awayWin;
    public Event draw;
    public boolean finish = false;
    public static ArrayList<Football> games = new ArrayList<Football> ();
    static String fileName = "games.ser";

    public Football(Team homeTeam, Team awayTeam, String time, float homeTeamRatio, float drawRatio, float awayTeamRatio) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.time = time;
        this.homeWin = new Event(homeTeam.name + " win " + awayTeam.name, homeTeamRatio);
        this.draw = new Event(homeTeam.name + " draw " + awayTeam.name, drawRatio);
        this.awayWin = new Event(awayTeam.name + " win " + homeTeam.name, awayTeamRatio);
        games.add(this);
        saveGames();
    }
    /**
    * in method natijeye baazi raa vaared mikonad.
    */
    public void endGame(int homeTeamScore, int awayTeamScore) {
        this.finish = true;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        if (awayTeamScore < homeTeamScore) {
            homeWin.endEvent(true);
            draw.endEvent(false);
            awayWin.endEvent(false);
        }
        else if (awayTeamScore == homeTeamScore) {
            homeWin.endEvent(false);
            draw.endEvent(true);
            awayWin.endEvent(false);
        }
        else {
            homeWin.endEvent(false);
            draw.endEvent(false);
            awayWin.endEvent(true);
        }
        Data.saveData();
    }
    public void showGame() {
        System.out.println(homeTeam.name + " vs " + awayTeam.name);
        return;
    }
    /**
    * method haaye zir marbut be database hastand
    */
    static public void getGames() {
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			Football.games = ( ArrayList<Football> )in.readObject();
			in.close();
			file.close();
		}
		catch(IOException ex) {
            //System.out.println(ex.getMessage());
            //System.out.println("IOException is caught Football");
        }
		catch(ClassNotFoundException ex) {
            //System.out.println("ClassNotFoundException is caught");
        }
	}
	static public void saveGames() {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(games);
			out.close();
			file.close();
		}
		catch(IOException ex) {
            //System.out.println("IOException is caught");
        }
	}
	static public void resetGames() {
		games.clear();
		saveGames();
	}
}