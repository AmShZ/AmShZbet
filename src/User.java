import java.io.*;
import java.util.*;

public class User implements Serializable {
	private String name;
	private String email;
	private String password;
	private int money = 0;
	static private ArrayList<User> users = new ArrayList<User> ();
	private ArrayList<Bet> betHistory = new ArrayList<Bet> ();
	private Bet currentBet;
	static String fileName = "users.ser";
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		users.add(this);
		this.currentBet = new Bet(this);
		Data.saveData();
	}
	static public boolean isNew(String email) {
		for (int i = 0; i < users.size(); ++ i) {
			User user = users.get(i);
			if (user.email.equals(email)) {
				return false;
			}
		}
		return true;
	}
	public boolean isTruePassword(String password) {
		return this.password.equals(password);
	}
	static public User getUserByEmail(String email) {
		for (int i = 0; i < users.size(); ++ i) {
			User user = users.get(i);
			if (user.email.equals(email)) {
				return user;
			}
		}
		return null;
	}
	static public User replaceUser(User user) {
		for (int i = 0; i < users.size(); ++ i) {
			User useri = users.get(i);
			if (useri.email.equals(user.email)) {
				//System.out.println("Yes!");
				return useri;
			}
		}
		return user;
	}
	public void setName(String name) {
		this.name = name;
		Data.saveData();
	}
	public String getName() {
		return this.name;
	}
	public void setPassword(String password) {
		this.password = password;
		Data.saveData();
	}
	public String getPassword() {
		return this.password;
	}
	public String getEmail() {
		return this.email;
	}
	public int getMoney() {
		return this.money;
	}
	public void showCurrentBet() {
		this.currentBet.showBet();
		return;
	}
	/**
	* in method yek pish binie digar raa be sabade kharide user ezafe mikonad
	*/
	public void addEventToCurrentBet(Event event) {
		//System.out.println("add event to cur");
		this.currentBet.addEvent(event);
		//System.out.println(currentBet.numberOfEvents());
		Data.saveData();
	}
	public boolean addMoney(int money) {
		if (this.money + money < 0) {
			return false;
		}
		this.money += money;
		Data.saveData();
		return true;
	}
	public void setCurBet(int money) {
		this.currentBet.setPrice(money);
		this.betHistory.add(this.currentBet);
		//this.currentBet.resetBet();
		this.currentBet = new Bet(this);
		Data.saveData();
		return;
	}
	/**
	* method haaye zir marbut be database hastand
	*/
	static public void getUsers() {
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(file);
			User.users = ( ArrayList<User> )in.readObject();
			in.close();
			file.close();
		}
		catch(IOException ex) {
			//System.out.println(ex.getMessage());
            //System.out.println("IOException is caught User");
        }
		catch(ClassNotFoundException ex) {
            //System.out.println("ClassNotFoundException is caught");
        }
	}
	static public void saveUsers() {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(users);
			out.close();
			file.close();
		}
		catch(IOException ex) {
            //System.out.println("IOException is caught(INPUT)");
        }
	}
	static public void resetUsers() {
		users.clear();
		saveUsers();
	}
}
