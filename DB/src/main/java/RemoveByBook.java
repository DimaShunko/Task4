import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RemoveByBook implements Command{

    @Override
    public String getName() {
        return "removeByBook";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите книгу");
        System.out.print("Книга: ");
        String book = scanner.nextLine();
        statement.execute("DELETE FROM library WHERE book = '" + book + "'");
    }

    @Override
    public String help() {
        return "removeByBook - удаление элемента по книге из базы данных";
    }
}
