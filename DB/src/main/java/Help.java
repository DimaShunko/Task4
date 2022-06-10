import java.sql.Statement;

public class Help{

    public void exec() {
        for (Command com: Library.getCommands()){
            System.out.println(com.help());
        }

    }

    public String help() {
        return "help - помощь";
    }
}
