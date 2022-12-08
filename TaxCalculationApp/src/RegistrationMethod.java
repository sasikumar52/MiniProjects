import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public interface RegistrationMethod {
	 public default void RegistrationMethod() {
	        try {
	            //Connection in the database
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tax", "root",
	                    "Sasi@kumar97");
	            Scanner in = new Scanner(System.in);
	            System.out.println("Registering your details");
	            System.out.print(" Enter customer id: ");
	            int id = in.nextInt();
	            System.out.print(" Enter Username: ");
	            String name = in.next();
	            boolean s=Pattern.matches("^[a-z]",name);
	            System.out.print(" Enter password: ");
	            String pass = in.next();
	            String qry= "SELECT * from customer where UserName='" + name + "' AND Password='" + pass + "'";
	            PreparedStatement pstmt=connection.prepareStatement(qry);
	            ResultSet rs=pstmt.executeQuery();
	            if (rs.next())
	            {
	                System.out.println("Enterted details already exists in the database");
	            }else{
	                qry = "INSERT into customer (CustId,UserName,Password) values(?,?,?)";
	                pstmt = connection.prepareStatement(qry);
	                pstmt.setInt(1, id);
	                pstmt.setString(2, name);
	                pstmt.setString(3, pass);
	                pstmt.executeUpdate();
	                System.out.println("Congrats " + name + " your registration is successful");
	            }
	            //Tax Calculation

	            double tax;
	            System.out.print("Enter the amount to tax calculation ");
	            double it = in.nextDouble();
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
	            //Update in the database
	            qry = "UPDATE customer SET TaxAmount=? WHERE CustId=?";
	            pstmt = connection.prepareStatement(qry);
	            pstmt.setDouble(1, tax);
	            pstmt.setInt(2, id);
	            pstmt.executeUpdate();
	            System.out.println("Dear " + name + " your tax amount is Updated in the table successfully");

	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	    }


}
