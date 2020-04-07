import java.sql.*;
import java.sql.PreparedStatement;
import java.util.TreeMap;

public class TodoClass {

  public final String COMMAND_ADD = "ADD\\s+.+";
  public final String COMMAND_ADD_RU = "адд\\s+.+";
  public final String COMMAND_ADD_TO_INDEX = "ADD\\s+\\d+\\s+.+";
  public final String COMMAND_ADD_TO_INDEX_RU = "адд\\s+\\d+\\s+.+";
  public final String COMMAND_EDIT = "EDIT\\s+\\d+\\s+.+";
  public final String COMMAND_EDIT_RU = "изменить\\s+\\d+\\s+.+";
  public final String COMMAND_DELETE = "DELETE\\s+\\d+";
  public final String COMMAND_DELETE_RU = "удалить\\s+\\d+";
  public final String COMMAND_DELETE_ALL = "DELETE ALL";
  public final String COMMAND_DELETE_ALL_RU = "удалить все";
  public final String COMMAND_LIST = "LIST";
  public final String COMMAND_LIST_RU = "лист";
  public final String COMMAND_DELETE_BD = "удалить бд";

  private TreeMap<String, String> todoList = new TreeMap<>();

  /**
   * Заменить данные на свои; CONN = "jdbc:mysql://ip_adress_or_domain:3306/BD_name";
   * <p>
   * USER = "BD_name";
   * <p>
   * PASS = "Password";
   * <p>
   * SQL База данных которую можно просто импортировать:
   **/

  private static final String CONN = "jdbc:mysql://176.96.239.141:3306/u2864_skillbox2?useSSL=false";
  private static final String USER = "u2864_skillbox2";
  private static final String PASS = "9S7j1D0b";
  private static final String SQL_DROP_TABLE = "DROP TABLE Todolist";

  private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS Todolist"
      + "("
      + " id int(6) NOT NULL AUTO_INCREMENT,"
      + " text varchar(128) NOT NULL,"
      + " PRIMARY KEY (id),"
      + " UNIQUE KEY text (text)"
      + ")";

  public void dropTable(String command) {
    try {
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_DROP_TABLE);
      preparedStatement.executeUpdate();
      createTable(); //создаем
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void createTable() {
    try {
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  //Создаем таблицу
  public void getCreateTable() {
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
      System.err.println("Заметка сохранена!");
      conn.close();
    } catch (Exception e) {
      System.err.println("Получена ошибка!");
      System.err.println(e.getMessage());
    }
  }

  /**
   * Подсчитываем колличество записей в таблице
   **/
  public int num() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) AS id FROM Todolist");
      while (resultSet.next()) {
        return resultSet.getInt(1);
      }
    } catch (Exception ignored) {
    }
    return 0;
  }

  public void addToIndex(String command) {
    String[] commandArray = command.split("\\s+", 3);
    String todoTextAddToIndex = commandArray[2];
    String indexText = commandArray[1];

    int addToIndex = Integer.parseInt(indexText);
    if (addToIndex == 0) {
      System.out.println("Индекс не может быть нулём!");
    }
    if (addToIndex <= num() || addToIndex >= num()) {
      int addToIndex2 = num() + 1;
      System.err.println("Индекс изменен на: " + addToIndex2);
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
        //Получаем данные
        String query = "INSERT INTO Todolist (id, text)" + " values (?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, addToIndex2);
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

  public void editByIndex(String command) {
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

  public void deleteAll(String command) {
    if (num() == 0) {
      System.err.println("Заметок нет! Зачем их удалять :)");
    } else if (command.equals(COMMAND_DELETE_ALL) || command.equals(COMMAND_DELETE_ALL_RU)) {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
        String query3 = "DELETE FROM Todolist WHERE id";
        PreparedStatement preparedStmt = conn.prepareStatement(query3);
        preparedStmt.executeUpdate(query3);
        System.err.println("Заметки удалены!");
        conn.close();
      } catch (Exception e) {
        System.err.println("Получена ошибка!");
        System.err.println(e.getMessage());
      }
    }
  }

  public void deleteByIndex(String command) {
    String todoTextDelete = command.replaceAll("[^0-9]", "").trim();
    int indexRemove = Integer.parseInt(todoTextDelete);
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      String query3 = "DELETE FROM Todolist WHERE id = ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query3);
      preparedStmt.setInt(1, indexRemove);
      preparedStmt.executeUpdate();
      System.err.println("Заметка удалена!\n>");
      conn.close();
    } catch (Exception e) {
      System.err.println("Получена ошибка!");
      System.err.println(e.getMessage());
    }
  }

  public void list(String input) {
    if (num() == 0) {
      System.err.println("Заметок нет!");
    } else {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
        //Получаем данные и выводим
        Statement statement = conn.createStatement();
        String sql = "SELECT id, text FROM Todolist ORDER BY id ASC";
        ResultSet rs = statement.executeQuery(sql);
        System.err.println("Список всех заметок: ");
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
