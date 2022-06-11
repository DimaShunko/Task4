import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RemoveByBook implements Command {

    @Override
    public String getName() {
        return "removeByBook";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        System.out.println("Введите книгу");
        System.out.print("Книга: ");
        String book = scanner.nextLine();

        try {
            statement.execute("SELECT EXISTS(SELECT book FROM tableB WHERE book = '" + book + "';");
        } catch (SQLException e) {
            System.out.println("Такой книги нет");
            b = false;
        }

        if (!b) {
            statement.execute("DELETE FROM tableC WHERE idBook = (SELECT t2.idBook FROM tableB t1, tableC t2 WHERE t1.book = '" + book + "' AND t1.id = t2.idBook);");
            statement.execute("DELETE FROM tableB WHERE id = ALL (SELECT t2.idBook FROM tableB t1, tableC t2 WHERE t1.book = '" + book + "' AND t1.id = t2.idBook);");
        }
    }

    @Override
    public void exec() {
    }

    @Override
    public String help() {
        return "removeByBook - удаление элемента по книге из базы данных";
    }
}
