import java.sql.SQLException;
import java.sql.Statement;

public class Help implements Command {

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
    }

    public void exec() {
        for (Command com : Library.getCommands()) {
            System.out.println(com.help());
        }

    }

    public String help() {
        return "help - помощь";
    }
}
