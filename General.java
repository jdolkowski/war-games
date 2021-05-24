import java.io.Serializable;
import java.util.*;

public class General implements Serializable, Subject {
    private ArrayList<Soldier> soldiers;
    private MoneySack moneySack;
    private ArrayList<Observer> observerList = new ArrayList<>();

    public General() {
        this.soldiers = new ArrayList<>();
        this.moneySack = new MoneySack();
        register(new Secretary());
    }
    public ArrayList<Observer> getObserverList(){
        return observerList;
    }

    public ArrayList<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(ArrayList<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    public void addSoldier(Soldier soldier){
        getSoldiers().add(soldier);
    }

    public void removeSoldier(Soldier soldier){
        getSoldiers().removeIf(tmpSoldier -> tmpSoldier == soldier);
    }

    public final MoneySack getMoneySack() {
        return moneySack;
    }

    public void setMoneySack(MoneySack moneySack) {
        this.moneySack = moneySack;
    }

    public int getBalance(){
        return moneySack.getBalance();
    }

    public void addSoldiersExperience(Class soldierType, int howMany){
        incorrectSoldiersQuantity(soldierType, howMany);
        balanceError(howMany);
        int count = 0;
        for(int i = 0; i < getSoldiers().size(); i++){
            if(getSoldiers().get(i).getClass().equals(soldierType) && count++ < howMany){
                getSoldiers().get(i).addExperience();
            }
        }
        notifyObserver("Dodano " + Integer.toString(howMany) + " punkty doświadczenia żołenierzom typu: " + soldierType.toString());
        getMoneySack().removeFromBalance(howMany);

    }

    public void addArmyExperience(){
        int armySize = getSoldiers().size();
        balanceError(armySize);
        getMoneySack().removeFromBalance(armySize);
        for(Soldier i : getSoldiers()){
            i.addExperience();
        }
        notifyObserver("Dodano punkty doświadczenia wszystkim żołnierzom");
    }
    public void addArmyExperienceNoMoney(General general){
        for(Soldier i : general.getSoldiers()){
            i.addExperience();
        }
    }
    public void removeArmyExperienceNoMoney(General general){
        for(Soldier i : general.getSoldiers()){
            if(i.getExperience()-1 >= 0) {
                i.setExperience(i.getExperience() - 1);
            }
        }
    }


    protected void incorrectSoldiersQuantity(Class soldier, int quantity){
        int count = 0;
        for(Soldier tmpSoldier : getSoldiers()){
            if(soldier.equals(tmpSoldier.getClass())){
                count++;
            }
        }
        if(quantity > count) throw new IllegalArgumentException();
    }

    protected void balanceError(int money){
        if(money > getBalance()) throw new IllegalArgumentException();
    }

    public void attackGeneral(General otherGeneral){
        String note;
        int thisStrength = calculateArmyStrength(this);
        int otherStrength = calculateArmyStrength(otherGeneral);

        int thisBalance = this.getBalance();
        int otherBalance = otherGeneral.getBalance();

        if(thisStrength > otherStrength){
            otherGeneral.getMoneySack().removeFromBalance((int)(otherBalance * 0.10));
            this.getMoneySack().addToBalance((int)(otherBalance * 0.10));
            addArmyExperienceNoMoney(this);
            removeArmyExperienceNoMoney(otherGeneral);
            note = "Atakujący generał wygrał bitwe";
        } else if(thisBalance <  otherBalance){
            this.getMoneySack().removeFromBalance((int)(thisBalance * 0.10));
            otherGeneral.getMoneySack().addToBalance((int)(thisBalance * 0.10));
            addArmyExperienceNoMoney(otherGeneral);
            removeArmyExperienceNoMoney(this);
            note = "Atakujący generał przegrał bitwę";
        } else {
            int thisUpperBound = this.getSoldiers().size();
            int otherUpperBound = otherGeneral.getSoldiers().size();
            Random rand = new Random();
            int thisRandomNumber = rand.nextInt(thisUpperBound);
            int otherRandomNumber = rand.nextInt(otherUpperBound);

            this.getSoldiers().remove(thisRandomNumber);
            otherGeneral.getSoldiers().remove(otherRandomNumber);
            note = "Bitwa zakończyła się remisem";
        }
        notifyObserver(note);
    }

    protected int calculateArmyStrength(General general){
        int totalStrength = 0;
        for(Soldier i : general.getSoldiers()){
            totalStrength += i.getStrength();
        }
        return totalStrength;
    }

    public void buySoldier(String type, int quantity){
        switch(type){
            case "private":
                balanceError(10 * quantity);
                this.getMoneySack().removeFromBalance(10 * quantity);
                while(quantity != 0){
                    new PrivateSoldier(this);
                    quantity--;
                }
            case "corporal":
                balanceError(10 * quantity * 2);
                this.getMoneySack().removeFromBalance(10 * quantity * 2);
                while(quantity != 0){
                    new CorporalSoldier(this);
                    quantity--;
                }
            case "capitan":
                balanceError(10 * quantity * 3);
                this.getMoneySack().removeFromBalance(10 * quantity * 3);
                while(quantity != 0){
                    new CapitanSoldier(this);
                    quantity--;
                }
            case "major":
                balanceError(10 * quantity * 4);
                this.getMoneySack().removeFromBalance(10 * quantity * 4);
                while(quantity != 0){
                    new MajorSoldier(this);
                    quantity--;
                }
        }
        notifyObserver("zakupionych zostało " + Integer.toString(quantity) + " " + type);
    }
    @Override
    public void register(Observer o) {
        observerList.add(o);
    }


    @Override
    public void notifyObserver(String note) {
        for(Observer o : observerList){
            o.update(note);
        }
    }
}
