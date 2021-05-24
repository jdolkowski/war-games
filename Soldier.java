import java.io.Serializable;

public abstract class Soldier implements Serializable {
    private final int value;
    private int experience;
    private int strength;
    private final General general;
    private final String type;

    public Soldier(int value, General general, String type) {
        this.value = value;
        this.experience = 1;
        this.strength = value;
        this.general = general;
        this.type = type;
        general.addSoldier(this);
    }

    public final int getValue() {
        return value;
    }

    public final int getExperience() {
        return experience;
    }

    public void setStrength(int strength) {
        setStrengthException(strength);
        this.strength = strength;
    }

    private void setStrengthException(int strength){ if(strength < getExperience() * getValue()) throw new IllegalArgumentException();}

    public final int getStrength() {
        return strength;
    }

    public General getGeneral() {
        return general;
    }

    public final String getType() {return type; }

    public void setExperience(int experience) {
        setExperienceError(experience);
        this.experience = experience;
    }
    private void setExperienceError(int experience){
        if(experience < 0) throw new IllegalArgumentException();
    }

    public void addExperience(){
        setExperience(getExperience()+1);
    }

    public Boolean canAdvance(){
        return getExperience() >= getValue() * 5;
    }

    public final Boolean isDead(){
        return this.getExperience() == 0;
    }

    public void executeDeath(Boolean isDead){
        if(isDead){
            getGeneral().removeSoldier(this);
        }
    }

    protected abstract void executeAdvance(Boolean canAdvance);
}
