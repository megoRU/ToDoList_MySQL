import java.sql.*;
import java.sql.PreparedStatement;
import java.util.TreeMap;

public class TodoClass {

  public final String COMMAND_ADD = "ADD\\s+.+";
  public final String COMMAND_ADD_TO_INDEX = "ADD\\s+\\d+\\s+.+";
  public final String COMMAND_EDIT = "EDIT\\s+\\d+\\s+.+";
  public final String COMMAND_DELETE = "DELETE\\s+\\d+";
  public final String COMMAND_LIST = "LIST";

  private TreeMap<String, String> todoList = new TreeMap<>();

  /**
   * Заменить данные на свои; CONN = "jdbc:mysql://ip_adress_or_domain:3306/BD_name";
   * 
   * USER = "BD_name";
   *
   * PASS = "Password";
   *
   * SQL База данных которую можно просто импортировать:
   **/

  private static final String CONN = "";
  private static final String USER = "";
  private static final String PASS = "";

  private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS Todolist"
      + "("
      + " id int(6) NOT NULL AUTO_INCREMENT,"
      + " text varchar(128) NOT NULL,"
      + " PRIMARY KEY (id),"
      + " UNIQUE KEY text (text)"
      + ")";

  private void createTable() throws Exception {
    try {
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void getCreateTable() throws Exception {
    createTable();
  }

  public void add(String command) {
    String todoText = command.split("\\s+", 2)[1];
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      String query = "INSERT INTO Todolist (id, text)" + " values (?, ?)";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(1, num() + 1); // Получаем "длину" таблицы и прибавляем 1
      preparedStmt.setString(2, todoText);
      preparedStmt.execute(); //Записываем данные в БД
      System.err.println("Заметка сохранена");
      conn.close();
    } catch (Exception e) {
      System.err.println("Получена ошибка!");
      System.err.println(e.getMessage());
    }
  }

  /**
   * Подсчитываем колличество записей в таблице
   **/
  public int num() throws Exception {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) AS id FROM Todolist");
      while (resultSet.next()) {
        return resultSet.getInt(1);
      }
    } catch (Exception e) {
    }
    return 0;
  }

  public void addToIndex(String command) throws Exception {
    String[] commandArray = command.split("\\s+", 3);
    String todoTextAddToIndex = commandArray[2];
    String indexText = commandArray[1];
    int addToIndex = Integer.parseInt(indexText);

    if (addToIndex == 0) {
      System.out.println("Индекс не может быть нулём!");
    } else {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
        //Получаем данные
        String query = "INSERT INTO Todolist (id, text)" + " values (?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, addToIndex);
        preparedStmt.setString(2, todoTextAddToIndex);
        // Записываем все данные в Базу Данных
        preparedStmt.execute();
        System.err.println("Заметка сохранена!");
        conn.close();
      } catch (Exception e) {
        System.err.println("Получена ошибка!");
        System.err.println(e.getMessage());
      }
    }
  }

  public void edit(String command) {

    String[] commandArray = command.split("\\s+", 3);
    String todoTextEdit = commandArray[2];
    String indexText = commandArray[1];
    int addToIndex = Integer.parseInt(indexText);
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      String query = "update Todolist set text = ? where id = ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(2, addToIndex);
      preparedStmt.setString(1, todoTextEdit);
      preparedStmt.executeUpdate();
      System.err.println("Заметка изменена!");
      conn.close();
    } catch (Exception e) {
      System.err.println("Получена ошибка!");
      System.err.println(e.getMessage());
    }
  }

  public void delete(String command) {
    String todoTextDelete = command.replaceAll("[^0-9]", "").trim();
    int indexRemove = Integer.parseInt(todoTextDelete);
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      String query3 = "DELETE FROM Todolist WHERE id = ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query3);
      preparedStmt.setInt(1, indexRemove);
      preparedStmt.executeUpdate();
      System.err.println("Заметка удалена");
      conn.close();
    } catch (Exception e) {
      System.err.println("Получена ошибка!");
      System.err.println(e.getMessage());
    }
  }

  public void list(String command) throws Exception {
    if (num() == 0) {
      System.err.println("Заметок нет!");
    } else {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
        //Получаем данные и выводим
        Statement statement = conn.createStatement();
        String sql = "SELECT id, text FROM Todolist";
        ResultSet rs = statement.executeQuery(sql);
        System.err.print("Список всех заметок: ");
        while (rs.next()) {
          String id = rs.getString("id");
          String text = rs.getString("text");
          //Вывод данных
          todoList.put(id, text);
          System.out.print("\n" + id + ". " + text);
        }
        System.out.println();
        rs.close();
        conn.close();
      } catch (Exception e) {
        System.err.println("Получена ошибка!");
        System.err.println(e.getMessage());
      }
    }
  }
}
