import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Add implements Command{
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите книгу и автора");
        System.out.print("Книга: ");
        String book = scanner.nextLine();
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        statement.execute("INSERT INTO library(book, author) VALUES( '" + book + "', '" + author + "');");
    }



    @Override
    public String help() {
        return "add - добавление элемента в базу данных";
    }
}
