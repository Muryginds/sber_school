import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.SneakyThrows;

public class H2DbEmbedded
{
  private static final String URL_MEM = "jdbc:h2:mem:h2db";
  private static final String USER = "sa";
  private static final String PASSWD = "sa";
  private static Connection connection;
  @SneakyThrows
  public static void main(String[] args) {
    try (Statement stmt = getConnection().createStatement()) {
      stmt.executeUpdate("CREATE TABLE species ( "
          + "id INTEGER PRIMARY KEY, "
          + "name VARCHAR(255), "
          + "num_acres DECIMAL)");
      stmt.executeUpdate("CREATE TABLE animal ( "
          + "id INTEGER PRIMARY KEY, "
          + "species_id integer, "
          + "name VARCHAR(255), "
          + "date_born TIMESTAMP)");
      stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
      stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");
      stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001-05-06T02:15')");
      stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002-08-15T09:12')");
      stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002-09-09T10:36')");
      stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010-06-08T01:24')");
      stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005-11-12T03:44')");
    }

/*    String sql = "SELECT * FROM species WHERE id = ?";
    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, "2");
    ResultSet resultSet = statement.executeQuery();
    System.out.println(resultSet.absolute(-1));*/
    //System.out.println(resultSet.absolute(-3));
    //printResultSet(resultSet);

/*    statement.setString(1, "1");
    resultSet = statement.executeQuery();
    printResultSet(resultSet);*/

    String sql1 = "SELECT * FROM species, animal WHERE species.id = animal.species_id";
    PreparedStatement statement1 = connection.prepareStatement(sql1);
    ResultSet resultSet1 = statement1.executeQuery();
    printResultSet1(resultSet1);

    connection.close();
  }

  private static void printResultSet1(ResultSet resultSet) throws SQLException {
    StringBuilder sb = new StringBuilder();
    while (resultSet.next()) {


      for (int i = 1; i <= 7; i++) {
        sb.append(checkInstanceOf(resultSet.getObject(i)));
        if (i != 7) {
          sb.append(" ");
        }
      }
      sb.append("\n============================\n");
    }
    System.out.println(sb.toString());
  }

  private static String checkInstanceOf(Object object) {
     if (object instanceof String) {
       return object.toString();
     } else if (object instanceof Integer) {
       return object.toString();
     } else {
       return object.toString();
     }
  }

  private static void printResultSet(ResultSet resultSet) throws SQLException {
    while (resultSet.next()) {
      int id = resultSet.getInt("id");
      String name = resultSet.getString("name");
      double numAcres = resultSet.getDouble("num_acres");

      System.out.println("id: " + id);
      System.out.println("Name: " + name);
      System.out.println("Num_acres: " + numAcres);
      System.out.println("\n============================\n");
    }
  }

  @SneakyThrows
  static Connection getConnection() {
    connection = connection ==null ? DriverManager.getConnection(URL_MEM, USER, PASSWD) :
        connection;
    return connection;
  }
}