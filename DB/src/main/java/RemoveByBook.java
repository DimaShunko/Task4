import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveByBook implements Command {

    @Override
    public String getName() {
        return "removeByBook";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        ResultSet result;
        Scanner scanner = new Scanner(System.in);
        boolean b = false;
        List<Integer> idAuthorDelete = new ArrayList<>();

        System.out.println("Введите книгу");
        System.out.print("Книга: ");
        String book = scanner.nextLine();

        result = statement.executeQuery("SELECT book FROM tableB WHERE book = '" + book + "';");

        while (result.next()) {
            b = true;
        }

        if (b) {
            result = statement.executeQuery("SELECT COUNT(idAuthor) num,idAuthor FROM tableC WHERE idAuthor IN (SELECT idAuthor FROM tableC WHERE (idBook = (SELECT id FROM tableB WHERE book = '" + book + "'))) GROUP BY idAuthor;");

            while (result.next()) {
                if (result.getInt(1) == 1) {
                    idAuthorDelete.add(result.getInt(2));
                }
            }

            for (Integer idAuthor : idAuthorDelete) {
                statement.execute("DELETE FROM tableA WHERE id = '" + idAuthor + "';");
            }

            statement.execute("DELETE FROM tableB WHERE book = '" + book + "';");
        } else {
            System.out.println("Такой книги нет");
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
