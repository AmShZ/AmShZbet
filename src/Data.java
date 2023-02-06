import java.io.*;
import java.util.*;

/**
* in class method haaye class haaye digar raa ba yek mothod sedaa mikonad
*/

public class Data implements Serializable {
	static public void saveData() {
		Team.saveTeams();
		User.saveUsers();
		Football.saveGames();
	}
	static public void recoverData() {
		Team.getTeams();
		User.getUsers();
		Football.getGames();
	}
	static public void resetData() {
		Team.resetTeams();
		User.resetUsers();
		Football.resetGames();
	}
}