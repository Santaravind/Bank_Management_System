import java.sql.*;

public class Transaction_Handling2 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://Localhost:3306/mysql";
        String username = "root";
        String password = "root";

        String withdroquery = "update accounts set balance = balance - ? where account_number= ?;";
        String depositQuery = "update accounts set balance = balance+ ? where account_number=?;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");

            try (Connection con = DriverManager.getConnection(url, username, password)) {
                con.setAutoCommit(false);

                try {
                    // Check balance of "account231" before withdrawal
                    double currentBalance = checkBalance(con, "account231");
                    if (currentBalance <= 500.00) {
                        throw new InsufficientFundsException("Insufficient funds in account231");
                    }

                    PreparedStatement withdrowStatement = con.prepareStatement(withdroquery);
                    PreparedStatement depositStatement = con.prepareStatement(depositQuery);

                    withdrowStatement.setDouble(1, 500.00);
                    withdrowStatement.setString(2, "account231");
                    depositStatement.setDouble(1, 500.00);
                    depositStatement.setString(2, "account421");

                    int affectedWithdraw = withdrowStatement.executeUpdate();
                    int affectedDeposit = depositStatement.executeUpdate();

                    if (affectedDeposit > 0 && affectedWithdraw > 0) {
                        con.commit();
                        System.out.println("Transaction successfully!!!.");
                    } else {
                        con.rollback();
                        System.out.println("Transaction failed (unexpected outcome).");
                    }
                } catch (SQLException | InsufficientFundsException e) {
                    con.rollback();
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Connection closed successfully!!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // Add a method to check balance
    private static double checkBalance(Connection con, String accountNumber) throws SQLException {
        String checkBalanceQuery = "SELECT balance FROM accounts WHERE account_number = ?";
        PreparedStatement checkStatement = con.prepareStatement(checkBalanceQuery);
        checkStatement.setString(1, accountNumber);
        ResultSet result = checkStatement.executeQuery();

        double balance = 0.0;
        if (result.next()) {
            balance = result.getDouble("balance");
        }
        result.close();
        checkStatement.close();
        return balance;
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

