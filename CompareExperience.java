import java.util.Comparator;

public class CompareExperience implements Comparator<Soldier>{
    @Override
    public int compare(Soldier o1, Soldier o2){
        return Integer.compare(o1.getExperience(), o2.getExperience());
    }
}
