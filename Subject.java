public interface Subject {
    void register(Observer o);
    void notifyObserver(String note);
}
