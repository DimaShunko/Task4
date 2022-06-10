import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindBookByAuthor implements  Command{
    @Override
    public String getName() {
        return "findBookByAuthor";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        boolean b = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите книгу");
        System.out.print("Книгу: ");
        String book = scanner.nextLine();
        ResultSet result = statement.executeQuery("SELECT author FROM library WHERE author = '" + book + "'");
        while (result.next()){
            System.out.println("AUTHOR " + result.getString(1));
            b =true;
        }
        if(!b){
            System.out.println("Такой книги нет");
        }

    }

    @Override
    public String help() {
        return "findBookByAuthor - поиск книги по автору";
    }
}
