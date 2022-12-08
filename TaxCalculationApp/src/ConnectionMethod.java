import java.sql.*;
import java.util.Scanner;

public interface ConnectionMethod {
    public default void ConnectionMethod()
    {
        try {
            Scanner s = new Scanner(System.in);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tax", "root",
                    "Sasi@kumar97");
            Statement stmt = connection.createStatement();
            PreparedStatement pstmt;
            System.out.print("Enter username : ");
            String name = s.next();
            System.out.print("Enter Password : ");
            String pass = s.next();

            String qry = "SELECT * from customer where UserName='" + name + "' AND Password='" + pass + "'";


            ResultSet rs = stmt.executeQuery(qry);
            int CustId = 0;
            if (rs.next()) {
                CustId = rs.getInt("CustId");
                String Taxamount = rs.getString("TaxAmount");
                System.out.println("Dear " + name + " your login is successful and your customer id is " + CustId + " and previous TaxAmount is: " + Taxamount);
            } else {
                System.out.println("Check your entered correct details:");
            }
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
            qry = "UPDATE customer SET TaxAmount=? WHERE CustId=?";
            pstmt = connection.prepareStatement(qry);
            pstmt.setDouble(1, tax);
            pstmt.setInt(2, CustId);
            pstmt.executeUpdate();
            System.out.println("Dear " + name + " your tax amount is Updated in the table successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }


