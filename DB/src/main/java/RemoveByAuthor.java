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
        boolean b = true;
        System.out.println("Введите автора");
        System.out.print("Автор: ");
        String author = scanner.nextLine();

        try {
            statement.execute("SELECT EXISTS(SELECT author FROM tableA WHERE author = '" + author + "';");
        } catch (SQLException e) {
            System.out.println("Такого автора нет");
            b = false;
        }

        if (!b) {
            statement.execute("DELETE FROM tableC WHERE idAuthor = (SELECT t2.idAuthor FROM tableA t1, tableC t2 WHERE t1.author = '" + author + "' AND t1.id = t2.idAuthor);");
            statement.execute("DELETE FROM tableA WHERE id = ALL (SELECT t2.idAuthor FROM tableA t1, tableC t2 WHERE t1.author = '" + author + "' AND t1.id = t2.idAuthor);");
        }

    }

    @Override
    public void exec() {
    }

    @Override
    public String help() {
        return "removeByAuthor - удаление элемента по автору из базы данных";
    }
}
