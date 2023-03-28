import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Variables define
        Bank firstBank = null;
        Bank belAgro = null;
        Bank belInvest = null;

        //Serialize Fist Nation Bank
        ArrayList<Bank> banks = new ArrayList<>();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("FirstBank.dat"))) {
            Bank bank1 = new Bank("First Nation Bank");
            Credit credit1 = new Credit(bank1, 5000.0, 2.0, true, true);
            Credit credit2 = new Credit(bank1, 6000.0, 2.5, false, false);
            Credit credit3 = new Credit(bank1, 7000.0, 3.0, true, false);
            ArrayList<Credit> credits = new ArrayList<>();
            credits.add(credit1);
            credits.add(credit2);
            credits.add(credit3);
            bank1.setCredits(credits);
            oos.writeObject(bank1);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("InputOutput error");
        }

        //Serialize BelAgro
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BelAgro.dat"))) {
            Bank bank2 = new Bank("BelAgro");
            Credit credit4 = new Credit(bank2, 8000.0, 3.5, false, true);
            Credit credit5 = new Credit(bank2, 9000.0, 4.0, true, false);
            Credit credit6 = new Credit(bank2, 10000.0, 4.5, false, true);
            ArrayList<Credit> credits = new ArrayList<>();
            credits.add(credit4);
            credits.add(credit5);
            credits.add(credit6);
            bank2.setCredits(credits);
            oos.writeObject(bank2);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("InputOutput error");
        }

        //Serialize Belinvest
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Belinvest.dat"))) {
            Bank bank3 = new Bank("BelInvest");
            Credit credit7 = new Credit(bank3, 11000.0, 5.0, true, false);
            Credit credit8 = new Credit(bank3, 12000.0, 5.5, false, true);
            Credit credit9 = new Credit(bank3, 13000.0, 6.0, true, false);
            ArrayList<Credit> credits = new ArrayList<>();
            credits.add(credit7);
            credits.add(credit8);
            credits.add(credit9);
            bank3.setCredits(credits);
            oos.writeObject(bank3);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("InputOutput error");
        }

        //Deserialize Fist Nation Bank
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("FirstBank.dat"))) {
            firstBank = (Bank) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //Deserialize Belagro
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BelAgro.dat"))) {
            belAgro = (Bank) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //Deserialize Belinvest
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BelInvest.dat"))) {
            belInvest = (Bank) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

//       for (Credit credit : firstBank.getCredits()) {
//            System.out.println("Credit amount: " + credit.getAmount());
//            System.out.println("Interest rate: " + credit.getInterestRate());
//            System.out.println("Allow early payment: " + credit.isEarlyRepaymentAllowed());
//            System.out.println("Is line incresed allow: " + credit.isCreditLineIncreaseAllowed());
//            System.out.println("Belongs to bank: " + credit.getBank().getName());
//            System.out.println("--------------------------");
//        }

       //Console
       Scanner scanner = new Scanner(System.in);
       boolean console = true;
       Bank selectedBank = null;
       String result = "";

       while(console) {
           System.out.println("Choose bank name: ");
           System.out.println("1. First Nation Bank\n2. Belagro\n3. Belinvest\n4.Any other input to leave");
           int bankName = scanner.nextInt();

           switch (bankName) {
               case (1):
                   selectedBank = firstBank;
                   break;
               case (2):
                   selectedBank = belAgro;
                   break;
               case (3):
                   selectedBank = belInvest;
                   break;
               default:
                   System.out.println("Invalid bank name");
                   console = false;
                   break;
           }

           System.out.println("Enter the amount: ");
           double amount = 0.0;
           try {
               amount = scanner.nextDouble();
           } catch (InputMismatchException e ){
               System.out.println("Invalid input. Please enter a valid amount using comma.");
               scanner.nextLine(); // To discard the invalid input from the input stream
           }

           try{
               if (amount <= 0) {
                   throw new MyException("Amount can not be 0 or lower");
//                   eSystem.out.println("Amount can not be 0 or lower");
               }
           } catch(MyException m){
               System.out.println(m.getMessage());
               System.out.println("Попробуйте еще раз!");
               break;
           }


           System.out.println("Enter the interest rate: ");
           double interestRate = 0.0;
           try{
               interestRate = scanner.nextDouble();
           } catch (InputMismatchException e ){
               System.out.println("Invalid input. Please enter a interest rate using comma.");
               scanner.nextLine(); // To discard the invalid input from the input stream
           }

           if (interestRate <= 0) {
               System.out.println("interestRate can not be 0 or lower");
               console = false;
           }

           for (Credit credit : selectedBank.getCredits()) {
               if (credit.getAmount() == amount && credit.getInterestRate() == interestRate) {
                   result = "Find somethin for u\n" +
                            "Credit amount: " + credit.getAmount() +
                            "\nInterest rate: " + credit.getInterestRate() +
                            "\nAllow early payment: " + credit.isEarlyRepaymentAllowed() +
                            "\nIs line incresed allow: " + credit.isCreditLineIncreaseAllowed() +
                            "\nBelongs to bank: " + credit.getBank().getName() + "\n\n";
//                   System.out.println("Find somethin for u");
//                   System.out.println("Credit amount: " + credit.getAmount());
//                   System.out.println("Interest rate: " + credit.getInterestRate());
//                   System.out.println("Allow early payment: " + credit.isEarlyRepaymentAllowed());
//                   System.out.println("Is line incresed allow: " + credit.isCreditLineIncreaseAllowed());
//                   System.out.println("Belongs to bank: " + credit.getBank().getName());
//                   System.out.println("-------------");
                   break;
               } else {
                   result = "Credit not found, change params";
               }
           }

           System.out.println(result);
       }
    }
}
