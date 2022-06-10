import java.sql.SQLException;
import java.sql.Statement;

public interface Command {
    String getName();
    void exec(Statement statement) throws SQLException;
    String help();
}
