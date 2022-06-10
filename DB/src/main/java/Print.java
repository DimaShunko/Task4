import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Print implements Command{
    @Override
    public String getName() {
        return "print";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT * FROM library;");
        while (result.next()){
            System.out.println("ID " + result.getInt(1) + " BOOK " + result.getString(2) + " AUTHOR " + result.getString(3));
        }

    }

    @Override
    public String help() {
        return "print - вывод базы данных";
    }
}
