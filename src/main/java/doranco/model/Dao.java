package doranco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Dao {

    protected void closeDataFlow(Object... objects) throws Exception {
        for (Object object : objects) {
            if (object == null) continue;

            if (object instanceof Connection) {
                ((Connection) object).close();
            } else if (object instanceof PreparedStatement) {
                ((PreparedStatement) object).close();
            } else if (object instanceof ResultSet) {
                ((ResultSet) object).close();
            } else {
                throw new IllegalArgumentException("Can not close unknown data flow object.");
            }
        }
    }

}
