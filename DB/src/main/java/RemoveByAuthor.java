import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveByAuthor implements Command {

    @Override
    public String getName() {
        return "removeByAuthor";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        ResultSet result;
        Scanner scanner = new Scanner(System.in);
        boolean b = false;
        List<Integer> idBookDelete = new ArrayList<>();

        System.out.println("Введите автора");
        System.out.print("Автор: ");
        String author = scanner.nextLine();

        result = statement.executeQuery("SELECT COUNT(idBook) num,idBook FROM tableC WHERE idBook IN (SELECT idBook FROM tableC WHERE (idAuthor = (SELECT id FROM tableA WHERE author = '" + author + "'))) GROUP BY idBook;");

        while (result.next()) {
            if (result.getInt(1) == 1) {
                idBookDelete.add(result.getInt(2));
            }
        }

        for (Integer idBook : idBookDelete) {
            statement.execute("DELETE FROM tableB WHERE id = '" + idBook + "';");
        }


        result = statement.executeQuery("SELECT author FROM tableA WHERE author = '" + author + "';");

        while (result.next()) {
            b = true;
        }

        if (b) {
            statement.execute("DELETE FROM tableA WHERE author = '" + author + "';");
        } else {
            System.out.println("Такого автора нет");
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