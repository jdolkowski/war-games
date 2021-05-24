public class MoneySack {
    private int balance;

    public MoneySack(int balance) {
        this.balance = balance;
    }

    public MoneySack() {
        this.balance = 20;
    }

    public final int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void removeFromBalance(int money) {this.balance -= money; }

    public void addToBalance(int money) {this.balance += money; }
}
