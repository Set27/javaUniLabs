import java.io.Serializable;
import java.util.Objects;

public class Credit implements Serializable {

    private Bank bank;
    private double amount;
    private double interestRate;
    private boolean earlyRepaymentAllowed;
    private boolean creditLineIncreaseAllowed;

    public Credit(Bank bank, double amount, double interestRate, boolean earlyRepaymentAllowed, boolean creditLineIncreaseAllowed) {
        this.bank = bank;
        this.amount = amount;
        this.interestRate = interestRate;
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
        this.creditLineIncreaseAllowed = creditLineIncreaseAllowed;
        bank.getCredits().add(this);
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public boolean isEarlyRepaymentAllowed() {
        return earlyRepaymentAllowed;
    }

    public void setEarlyRepaymentAllowed(boolean earlyRepaymentAllowed) {
        this.earlyRepaymentAllowed = earlyRepaymentAllowed;
    }

    public boolean isCreditLineIncreaseAllowed() {
        return creditLineIncreaseAllowed;
    }

    public void setCreditLineIncreaseAllowed(boolean creditLineIncreaseAllowed) {
        this.creditLineIncreaseAllowed = creditLineIncreaseAllowed;
    }

    public void calculateIncreseForDays(int days){
        try{
            if(this.isCreditLineIncreaseAllowed() == false){
                throw new IllegalAccessException("cannot calucalte for this credit");
            }
            int finalAmount = (int) ((this.getAmount() * this.getInterestRate()) * days);
            System.out.println("The final amount after " + days + " days is: " + finalAmount);
        } catch (IllegalAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Double.compare(credit.amount, amount) == 0 && Double.compare(credit.interestRate, interestRate) == 0 && earlyRepaymentAllowed == credit.earlyRepaymentAllowed && creditLineIncreaseAllowed == credit.creditLineIncreaseAllowed && Objects.equals(bank, credit.bank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, amount, interestRate, earlyRepaymentAllowed, creditLineIncreaseAllowed);
    }
}
