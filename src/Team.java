import java.io.*;
import java.util.*;

public class Team implements Serializable {
	public String name;
	public int id;
	static public ArrayList<Team> teams = new ArrayList<Team> ();
	static String fileName = "teams.ser";

	public Team(String name) {
		this.name = name;
		teams.add(this);
		Data.saveData();
	}
	/**
	* method haaye zir marbut be database hastand
	*/
	static public void getTeams() {
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			Team.teams = ( ArrayList<Team> )in.readObject();
			in.close();
			file.close();
		}
		catch(IOException ex) {
            //System.out.println("IOException is caught Team");
        }
		catch(ClassNotFoundException ex) {
            //System.out.println("ClassNotFoundException is caught");
        }
	}
	static public void saveTeams() {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(teams);
			out.close();
			file.close();
		}
		catch(IOException ex) {
            //System.out.println("IOException is caught");
        }
	}
	static public void resetTeams() {
		teams.clear();
		saveTeams();
	}
}
