import java.util.Scanner;

public interface CalculationMethod {
    public default void CalculationMethod()
    {
    	//Tax Calculation
        Scanner s = new Scanner(System.in);
        double tax;
        System.out.print("Enter the amount to tax calculation ");
        double it = s.nextDouble();
        {
            if (it <= 20000)
                tax = 10000;
            else if (it <= 30000)
                tax = 0.1 * (it - 20000);
            else if (it <= 50000)
                tax = (0.2 * (it - 30000)) + (0.1 * 10000);
            else if (it <= 100000)
                tax = (0.3 * (it - 50000)) + (0.2 * 20000) + (0.1 * 10000);
            else
                tax = (0.4 * (it - 100000)) + (0.3 * 50000) + (0.2 * 20000) + (0.1 * 10000);
            System.out.println("Your tax calculation is completed and the value is " + tax);
        }
    }
}

