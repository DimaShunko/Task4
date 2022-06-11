import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class findAuthorByBook implements Command {

    @Override
    public String getName() {
        return "findAuthorByBook";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        boolean b = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите книгу");
        System.out.print("Книгу: ");

        String book = scanner.nextLine();
        ResultSet result = statement.executeQuery("SELECT t1.author FROM tableA t1, tableB t2, tableC t3 WHERE t2.book = '" + book + "' AND t2.id = t3.idBook AND t3.idAuthor = t1.id;");
        while (result.next()) {
            System.out.println("AUTHOR " + result.getString(1));
            b = true;

        }
        if (!b) {
            System.out.println("Такой книги нет");
        }

    }

    @Override
    public void exec() {

    }

    @Override
    public String help() {
        return "findBookByAuthor - поиск книги по автору";
    }
}
