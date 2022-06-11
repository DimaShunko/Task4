import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Print implements Command {
    @Override
    public String getName() {
        return "print";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        ResultSet result = statement.executeQuery("SELECT t1.author, t2.book FROM tableA t1, tableB t2, tableC t3 WHERE t1.id = t3.idAuthor and t2.id = t3.idBook;");
        while (result.next()) {
            System.out.println("Author " + result.getString(1) + " BOOK " + result.getString(2));
        }

    }

    @Override
    public void exec() {

    }

    @Override
    public String help() {
        return "print - вывод базы данных";
    }
}
