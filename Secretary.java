import java.util.ArrayList;

public class Secretary implements Observer{
    private ArrayList<String> logs = new ArrayList<>();

    public Secretary(){
    }

    @Override
    public void update(String note) {
        getLogs().add(note);
        System.out.println(note);
    }

    public ArrayList<String> getLogs() {
        return logs;
    }
}
