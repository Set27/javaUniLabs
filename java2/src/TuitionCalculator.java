import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TuitionCalculator {
    private static final int FULL_TIME_COST = 1000;
    private static final int REMOTE_COST = 500;
    private static final int MORNING_COST = 0;
    private static final int AFTERNOON_COST = 100;
    private static final int EVENING_COST = 200;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the date of the event (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Please choose the time of the event (morning, afternoon, or evening): ");
        String timeChoice = scanner.nextLine().toLowerCase();
        int timeCost;
        switch (timeChoice) {
            case "morning":
                timeCost = MORNING_COST;
                break;
            case "afternoon":
                timeCost = AFTERNOON_COST;
                break;
            case "evening":
                timeCost = EVENING_COST;
                break;
            default:
                System.err.println("Invalid time choice.");
                return;
        }

        System.out.println("Please choose the form of training (full-time or remote): ");
        String formChoice = scanner.nextLine().toLowerCase();
        int formCost;
        switch (formChoice) {
            case "full-time":
                formCost = FULL_TIME_COST;
                break;
            case "remote":
                formCost = REMOTE_COST;
                break;
            default:
                System.err.println("Invalid form choice.");
                return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        int[] costs = new int[2];
        executorService.submit(() -> costs[0] = formCost);
        executorService.submit(() -> costs[1] = timeCost);
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error calculating tuition cost.");
            return;
        }

        // Sort costs in ascending order
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> Arrays.sort(costs));
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error sorting costs.");
            return;
        }
        System.out.println("Costs in ascending order: " + Arrays.toString(costs));

        // Sort costs in descending order
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Arrays.sort(costs);
            for (int i = 0; i < costs.length / 2; i++) {
                int temp = costs[i];
                costs[i] = costs[costs.length - 1 - i];
                costs[costs.length - 1 - i] = temp;
            }
        });
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error sorting costs.");
            return;
        }
        System.out.println("Costs in descending order: " + Arrays.toString(costs));
    }
}
