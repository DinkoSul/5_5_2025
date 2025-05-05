import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class PreparedStatementPrimjer {
    public static void main(String[] args) {
        ArrayList<Polaznik> polaznici = new ArrayList<>();
        polaznici.add(new Polaznik("Ana", "Ivić"));
        polaznici.add(new Polaznik("Marko", "Anić"));
        polaznici.add(new Polaznik("Ivica", "Doležal"));
        polaznici.add(new Polaznik("Ivana", "Benakić"));

        DataSource dataSource = createDataSource();
try (Connection connection = dataSource.getConnection()) {
    System.out.println("Uspješno ste spojeni na bazu podataka");

    PreparedStatement stmt;
    stmt = connection.prepareStatement("INSERT INTO Polaznik (Ime, Prezime) VALUES (?, ?)");
for (Polaznik p : polaznici) {
    stmt.setString(1, p.getIme());
    stmt.setString(2, p.getPrezime());
    stmt.executeUpdate();
}
}
catch (SQLException e) {
System.err.println("Greška prilikom spajanja");
e.printStackTrace();

}

    }


    private static DataSource createDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        //ds.setPortNumber(1433);
        ds.setDatabaseName("AdventureWorksOBP");
        ds.setUser("sa");
        ds.setPassword("SQL");
        ds.setEncrypt(false);

        return ds;
    }
}