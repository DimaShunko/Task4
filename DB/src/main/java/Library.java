import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    private static List<Command> commands;

    Library(){
        commands = new ArrayList<>();
        this.commands.add(new Add());
        this.commands.add(new Print());
        this.commands.add(new Clean());
        this.commands.add(new Exit());
        this.commands.add(new RemoveByBook());
        this.commands.add(new RemoveByAuthor());
        //this.commands.add(new Help());
        this.commands.add(new FindAuthorByBook());
        this.commands.add(new FindBookByAuthor());
    }

    public static List<Command> getCommands() {
        return commands;
    }


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Help help = new Help();
        boolean b;
        String name;

        Library library = new Library();

        String url = "jdbc:h2:~/library";
        Connection connection = DriverManager.getConnection(url, "sa", "");
        Statement statement = connection.createStatement();
        //statement.execute("CREATE TABLE library (id INT NOT NULL AUTO_INCREMENT, book VARCHAR(50) NOT NULL, author VARCHAR(20) NOT NULL, PRIMARY KEY(id));");

        while(true){
            System.out.println("Введите команду");
            name = scanner.nextLine();
            b = false;
            for(Command command: library.getCommands()){
                if(command.getName().contentEquals(name)){
                    command.exec(statement);
                    b = true;
                    break;
                }

            }
            if(!b){
                System.out.println("Такой команды нет");
                help.exec();
            }

        }
    }
}
