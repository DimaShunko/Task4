import java.sql.SQLException;
import java.sql.Statement;

public class Clean implements Command {
    @Override
    public String getName() {
        return "clean";
    }

    @Override
    public void exec(Statement statement) throws SQLException {
        statement.execute("DELETE FROM tableA ;");
        statement.execute("DELETE FROM tableB ;");
        statement.execute("DELETE FROM tableC ;");

    }

    @Override
    public void exec() {
    }

    @Override
    public String help() {
        return "clean - чистка базы данных";
    }
}
