import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bank implements Serializable {
    private String name;

    private List<Credit> credits;

    public Bank(String name) {
        this.name = name;
        this.credits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", credits=" + credits +
                '}';
    }
}
