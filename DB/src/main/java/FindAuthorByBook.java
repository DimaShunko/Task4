import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FindAuthorByBook implements Command{
    @Override
    public String getName() {
        return "findAuthorByBook";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        boolean b = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите автора");
        System.out.print("Автор: ");
        String author = scanner.nextLine();
        ResultSet result = statement.executeQuery("SELECT book FROM library WHERE author = '" + author + "'");
        while (result.next()){
            System.out.println("BOOK " + result.getString(1));
            b = true;
        }
        if(!b){
            System.out.println("Такого автора нет");
        }

    }

    @Override
    public String help() {
        return "findAuthorByBook - поиск автора по книге";
    }
}
