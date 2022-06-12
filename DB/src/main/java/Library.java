import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    private static List<Command> commands;
    private static List<String> commands2;


    Library() {
        commands = new ArrayList<>();
        this.commands.add(new Add());
        this.commands.add(new Print());
        this.commands.add(new Clean());
        this.commands.add(new Exit());
        this.commands.add(new RemoveByBook());
        this.commands.add(new RemoveByAuthor());
        this.commands.add(new Help());
        this.commands.add(new findBookByAuthor());
        this.commands.add(new findAuthorByBook());

        commands2 = new ArrayList<>();
        this.commands2.add("help");
        this.commands2.add("exit");

    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static List<String> getCommands2() {
        return commands2;
    }


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean b;
        String name;

        Library library = new Library();

        String url = "jdbc:h2:~/library";
        Connection connection = DriverManager.getConnection(url, "sa", "");
        Statement statement = connection.createStatement();
//        statement.execute("CREATE TABLE tableA (id INT NOT NULL AUTO_INCREMENT, author VARCHAR(20) NOT NULL, PRIMARY KEY(id), UNIQUE (author));");
//        statement.execute("CREATE TABLE tableB (id INT NOT NULL AUTO_INCREMENT, book VARCHAR(20) NOT NULL, PRIMARY KEY(id), UNIQUE (book));");
//        statement.execute("CREATE TABLE tableC (idAuthor INT NOT NULL, idBook INT NOT NULL, FOREIGN KEY (idAuthor) REFERENCES tableA(id) ON DELETE CASCADE, FOREIGN KEY (idBook) REFERENCES tableB(id) ON DELETE CASCADE);");
//        statement.execute("DROP TABLE tableA ;");
//        statement.execute("DROP TABLE tableB ;");
//        statement.execute("DROP TABLE tableC ;");

        while (true) {
            System.out.println("Введите команду");
            name = scanner.nextLine();
            b = false;
            for (Command command : library.getCommands()) {
                if (command.getName().contentEquals(name)) {
                    if (library.getCommands2().contains(name)) {
                        command.exec();
                    } else {
                        command.exec(statement);
                    }
                    b = true;
                    break;
                }
            }

            if (!b) {
                System.out.println("Такой команды нет");
            }
        }
    }
}
