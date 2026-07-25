import java.util.Scanner;

class PiCalculator {

    private int terms;
    public double pi;
    protected double calculatePi() {
        double sum = 0.0;

        for (int i = 0; i < terms; i++) {
            if (i % 2 == 0)
                sum += 1.0 / (2 * i + 1);
            else
                sum -= 1.0 / (2 * i + 1);
        }

        pi = 4 * sum;
        return pi;
    }

    public PiCalculator(int terms) {
        this.terms = terms;
    }

    public double getPiValue() {
        return calculatePi();
    }

    protected void showPrecision() {
        System.out.println("\nProtected Method - Displaying Precision Info:");
        System.out.println("Precision used: " + terms + " terms");
        System.out.println("Series used: Leibniz Series (4/1 - 4/3 + 4/5 - 4/7 + ...)");
    }

    private void showPrivateData() {
        System.out.println("\nPrivate Data - Accessed only within class:");
        System.out.println("Raw computed value (private): " + pi);
    }

    public void displayPrivateData() {
        showPrivateData();
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Pi Calculator using Access Specifiers ===");
        System.out.print("Enter the number of terms for Pi approximation: ");
        int n = sc.nextInt();

        PiCalculator obj = new PiCalculator(n);

        System.out.println("\nCalculating Pi using Leibniz Series...");

        System.out.println("\nPublic Method - Displaying Result:");
        System.out.println("Approximated value of Pi: " + obj.getPiValue());

        obj.showPrecision();

        obj.displayPrivateData();

        sc.close();
    }
}
