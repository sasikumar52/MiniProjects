import java.util.Scanner;

public class Main implements ConnectionMethod,RegistrationMethod,CalculationMethod {
    public static void main(String[] args) {
        Main m = new Main();
        Scanner in = new Scanner(System.in);
        System.out.print("Do you have already account with us?(Y/N): ");
        char account = in.next().charAt(0);
        if (account == 'Y' || account == 'y') {
            m.ConnectionMethod();
        } else if (account == 'N' || account == 'n') {
            System.out.print("whether you wants to signup and proceed with the calculation ? (Y/N): ");
            char answer = in.next().charAt(0);
            if (answer == 'Y' || answer == 'y') {
                m.RegistrationMethod();
            }
            else if(answer=='N'||answer=='n')
            {
                m.CalculationMethod();
            }
        }
    }
}