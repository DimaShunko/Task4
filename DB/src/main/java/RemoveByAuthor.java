import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RemoveByAuthor implements Command {
    @Override
    public String getName() {
        return "removeByAuthor";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите автора");
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        statement.execute("DELETE FROM library WHERE author = '" + author + "'");

    }

    @Override
    public String help() {
        return "removeByAuthor - удаление элемента по автору из базы данных";
    }
}
