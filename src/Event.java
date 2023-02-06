import java.io.*;
import java.util.*;

public class Event implements Serializable {
	String name;
	float ratio = 1;
	int id;
	private boolean isTrue = true;
	private boolean isFinish = false;
	private ArrayList<Bet> bets = new ArrayList<Bet> ();
	
	public Event(String name, float ratio) {
		this.name = name;
		this.ratio = ratio;
	}
	public void endEvent(boolean isTrue) {
		this.isTrue = isTrue;
		this.isFinish = true;
		for (int i = 0; i < this.bets.size(); ++ i) {
			Bet bet = this.bets.get(i);
			//System.out.println(bet.getUser().getName());
			//System.out.println(bet.numberOfEvents());
			bet.finishedEvent(isTrue);
		}
		Data.saveData();
	}
	public void addBet(Bet bet) {
		this.bets.add(bet);
		Data.saveData();
	}
	public void setRatio(float ratio) {
		this.ratio = ratio;
		Data.saveData();
	}
}