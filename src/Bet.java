import java.io.*;
import java.util.*;

/**
* yek bet shaamele shand event mibaashad
* true budane event be true budane tamaame event haaye aan bastegi darad
*/

public class Bet implements Serializable {
    //static ArrayList<Bet> bets = new ArrayList<Bet> ();
    private User user;
    private ArrayList<Event> events = new ArrayList<Event> ();
    private int price = 0;
    private float ratio = 1;
    private boolean isTrue = true;
    private boolean isFinish = false;
    private int numberOfFinishedEvents = 0;

    public Bet(User user) {
        this.user = user;
        //bets.add(this);
        Data.saveData();
    }
    public User getUser() {
        return this.user;
    }
    /**
    * in method yek event raa be bet ezafe mikonad
    */
    public void addEvent(Event event) {
        this.events.add(event);
        //System.out.println("add shod!");
        event.addBet(this);
        this.ratio *= event.ratio;
        Data.saveData();
    }
    public void setPrice(int price) {
        this.price = price;
        Data.saveData();
    }
    public int numberOfEvents() {
        return this.events.size();
    }
    /**
    * in method natijeye yek event raa vaared mikonad
    */
    public void finishedEvent(boolean isTrue) {
        this.user = User.replaceUser(this.user);
        //System.out.println(user.getName());
        if (isTrue == false) {
            this.isTrue = false;
        }
        this.numberOfFinishedEvents++;
        //System.out.println("finished events: " + this.numberOfFinishedEvents);
        //System.out.println("events: " + this.events.size());
        if (this.numberOfFinishedEvents == this.events.size()) {
            //System.out.println("residam!");
            this.isFinish = true;
            if (this.isTrue) {
                float money = (float) this.price;
                money *= this.ratio;
                //System.out.println(money);
                this.user.addMoney((int) money);
            }
        }
        Data.saveData();
        return;
    }
    public void showBet() {
		System.out.println("Your current bet: ");
		for (int i = 0; i < this.events.size(); ++ i) {
			Event event = this.events.get(i);
			System.out.println(event.name + " - " + event.ratio);
		}
        System.out.println("");
		System.out.printf("Total ratio: %.2f%n", this.ratio);
	}
    public void resetBet() {
        this.ratio = 1;
        events.clear();
        Data.saveData();
    }
}