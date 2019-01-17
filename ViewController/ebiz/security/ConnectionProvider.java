package nhs.esr.ebiz.security;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class ConnectionProvider {

    private ConnectionProvider() {
    }

    private static DataSource myDS = null;
    static {
        try {
            Context ctx = new InitialContext();
            myDS = (DataSource)ctx.lookup("jdbc/ADF_EBS");
            // your datasource jndi name as defined during configuration
            if (ctx != null)
                ctx.close();
        } catch (NamingException ne) {
            //ne.printStackTrace();//ideally you should log it
            throw new RuntimeException(ne);
            // means jndi setup is not correct or doesn't exist
        }
    }

    public static Connection getConnection() throws SQLException {

        if (myDS == null)
            throw new IllegalStateException("AppsDatasource is not properly initialized or available");
        return myDS.getConnection();
    }

}
