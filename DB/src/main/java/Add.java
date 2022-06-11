import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Add implements Command {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        int n = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите книгу и автора");
        System.out.print("Книга: ");
        String book = scanner.nextLine();

        try {
            statement.execute("INSERT INTO tableB(book) VALUES( '" + book + "');");
        } catch (SQLException e) {
            System.out.println("Книга уже добавлена в базу данных");
        }

        System.out.println("Сколько авторов у книги?");
        while (scanner.hasNextLine()) {
            try {
                n = Integer.parseInt(scanner.next("[0-9]+"));
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ожидалось число");
                scanner.nextLine();
            }
        }

        scanner.nextLine();
        for (int i = 1; i <= n; i++) {
            System.out.print("Автор " + i + ": ");
            String author = scanner.nextLine();

            try {
                statement.execute("INSERT INTO tableA(author) VALUES( '" + author + "');");
            } catch (SQLException e) {
                System.out.println("Автор уже добавлен в базу данных");
            }

            statement.execute("INSERT INTO tableC(idAuthor, idBook) VALUES( (SELECT tableA.id FROM tableA WHERE tableA.author ='" + author + "'), (SELECT tableB.id FROM tableB WHERE tableB.book ='" + book + "'));");
        }
    }

    @Override
    public void exec() {
    }

    @Override
    public String help() {
        return "add - добавление элемента в базу данных";
    }
}
