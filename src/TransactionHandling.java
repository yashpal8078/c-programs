import java.sql.*;

public class TransactionHandling {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/test_tb";
        String user = "root";
        String password = "YASH@123p";
        String withDrowQuery = "UPDATE accounts SET balance  = balance - ? WHERE  account_number =  ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try {
            Class.forName("com.cj.mysql.jdbc.Driver");
            System.out.println("Driver loaded successfully ");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("connection established successfully !!");

            connection.setAutoCommit(false);
            try {
                PreparedStatement withDrowStatement = connection.prepareStatement(withDrowQuery);
                PreparedStatement depositStatement = connection.prepareStatement(depositQuery);
                withDrowStatement.setDouble(1, 500.00);
                withDrowStatement.setString(2, "account123");

                depositStatement.setDouble(1, 500.00);
                depositStatement.setString(2, "account456");

              int rowsAffectedWithdrawl = withDrowStatement.executeUpdate();
              int rowsAAffectedDeposited =   depositStatement.executeUpdate();
               if(rowsAffectedWithdrawl > 0 && rowsAAffectedDeposited >0){
                   connection.commit();
                   System.out.println("Transaction successful");
               }else{
                   connection.rollback();
                   System.out.println("Transaction failed");
               }

            } catch (SQLException e) {
               e.printStackTrace();

            }


        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

}
