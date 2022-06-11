import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindBookByAuthor implements Command {

    @Override
    public String getName() {
        return "findBookByAuthor";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        boolean b = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите автора");
        System.out.print("Автор: ");
        String author = scanner.nextLine();

        ResultSet result = statement.executeQuery("SELECT t2.book FROM tableA t1, tableB t2, tableC t3 WHERE t1.author = '" + author + "' AND t1.id = t3.idAuthor AND t3.idBook = t2.id;");
        while (result.next()) {
            System.out.println("BOOK " + result.getString(1));
            b = true;
        }

        if (!b) {
            System.out.println("Такого автора нет");
        }

    }

    @Override
    public void exec() {
    }

    @Override
    public String help() {
        return "findAuthorByBook - поиск автора по книге";
    }
}
