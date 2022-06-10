import java.sql.Statement;

public class Exit implements Command{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void exec(Statement statement) {
        System.exit(0);

    }

    @Override
    public String help() {
        return "exit - выход";
    }
}
