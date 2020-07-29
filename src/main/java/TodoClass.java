import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import oshi.SystemInfo;

public class TodoClass extends JFrame {

  public final String COMMAND_ADD = "add\\s+.+";
  public final String COMMAND_ADD_RU = "адд\\s+.+";
  public final String COMMAND_ADD_TO_INDEX = "add\\s+\\d+\\s+.+";
  public final String COMMAND_ADD_TO_INDEX_RU = "адд\\s+\\d+\\s+.+";
  public final String COMMAND_EDIT = "edit\\s+\\d+\\s+.+";
  public final String COMMAND_EDIT_RU = "изменить\\s+\\d+\\s+.+";
  public final String COMMAND_DELETE = "delete\\s+\\d+";
  public final String COMMAND_DEL = "del\\s+\\d+";
  public final String COMMAND_DELETE_RU = "удалить\\s+\\d+";
  public final String COMMAND_DELETE_ALL = "delete all";
  public final String COMMAND_DELETE_ALL_RU = "удалить все";
  //public final String COMMAND_LIST = "list";
  //public final String COMMAND_LIST_RU = "лист";
  public final String COMMAND_DELETE_BD = "удалить бд";
  public final String ALL_LETTERS_AND_NUMBERS = "^[A-ZА-Я]+[А-Яа-яA-Za-z0-9\\s+]+";
  private final Connection conn = DriverManager.getConnection(CONN, USER, PASS);

  public Connection getConn() {
    return conn;
  }
//private final TreeMap<String, String> todoList = new TreeMap<>();

  /**
   * Заменить данные на свои; CONN = "jdbc:mysql://ip_adress_or_domain:3306/BD_name";
   * <p>
   * USER = "BD_name";
   * <p>
   * PASS = "Password";
   * <p>
   * SQL База данных которую можно просто импортировать:
   **/

  private static final String CONN = "jdbc:mysql://95.181.157.159:3306/admin_todolist?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
  private static final String USER = "admin_todolist";
  private static final String PASS = "B0*cg1k0";
  private static final String SQL_DROP_TABLE = "DROP TABLE Todolist_" + CPUid();

  private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS Todolist_" + CPUid()
      + " ("
      + " id int(6) NOT NULL AUTO_INCREMENT,"
      + " text varchar(255) NOT NULL,"
      + " time TIMESTAMP NOT NULL,"
      + " PRIMARY KEY (id),"
      + " UNIQUE KEY text (text)"
      + ")";

  public TodoClass() throws SQLException {
  }

  private void dropTable() {
    try {
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_DROP_TABLE);
      preparedStatement.executeUpdate();
      createTable(); //создаем
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  private void createTable() {
    try {
      PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }

  public void getCreateTable() {
    createTable();
  }

  public void getDropTable() {
    dropTable();
  }


  private static String CPUid() {
    SystemInfo si = new SystemInfo();
    String processorId = si.getHardware().getProcessor().toString();
    String[] cpuId = processorId.split("\\s+"); //cpuId[26]
    return cpuId[26];
  }

  public void addText(String command) {
    String todoText = command.trim();
    try {
      Date dateNow = new Date();
      SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
      String dateADD = formatForDateNow.format(dateNow);
      String query = "INSERT INTO Todolist_" + CPUid() + " (id, text, time)" + " values (?, ?, ?)";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(1, num() + 1); // Получаем "длину" таблицы и прибавляем 1
      preparedStmt.setString(2, todoText);
      preparedStmt.setString(3, dateADD);
      preparedStmt.execute(); //Записываем данные в БД
      System.err.println("Заметка сохранена!");
      //conn.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void add(String command) {
    String[] todoText = command.split("\\s+", 2);
    try {
      Date dateNow = new Date();
      //Нужно проверить + дописать для секунд
      SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
      String dateADD = formatForDateNow.format(dateNow);
      String query = "INSERT INTO Todolist_" + CPUid() + " (id, text, time)" + " values (?, ?, ?)";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(1, num() + 1); // Получаем "длину" таблицы и прибавляем 1
      preparedStmt.setString(2, todoText[1]);
      preparedStmt.setString(3, dateADD);
      preparedStmt.execute(); //Записываем данные в БД
      System.err.println("Заметка сохранена!");
   //   conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Подсчитываем колличество записей в таблице
   **/
  public int num() {
    try {
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) AS id FROM Todolist_" + CPUid());
      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
   //   conn.close();
    } catch (Exception e) {
      e.printStackTrace();
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

        //Получаем данные
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        String dateADD = formatForDateNow.format(dateNow);
        String query = "INSERT INTO Todolist_" + CPUid() + " (id, text, time)" + " values (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, addToIndex2);
        preparedStmt.setString(2, todoTextAddToIndex);
        preparedStmt.setString(3, dateADD);

        // Записываем все данные в Базу Данных
        preparedStmt.execute();
        System.err.println("Заметка сохранена!");
    //    conn.close();
      } catch (Exception e) {
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
      String query = "update Todolist_" + CPUid() + " SET text = ? WHERE id = ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setInt(2, addToIndex);
      preparedStmt.setString(1, todoTextEdit);
      preparedStmt.executeUpdate();
      System.err.println("Заметка изменена!");
   //   conn.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void deleteAll(String command) {
    if (num() == 0) {
      System.err.println("Заметок нет! Зачем их удалять :)");
    } else if (command.equals(COMMAND_DELETE_ALL) || command.equals(COMMAND_DELETE_ALL_RU)) {
      try {
        String query3 = "DELETE FROM Todolist_" + CPUid() + " WHERE id";
        PreparedStatement preparedStmt = conn.prepareStatement(query3);
        preparedStmt.executeUpdate(query3);
        System.err.println("Заметки удалены!");
   //     conn.close();
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
  }

  public void deleteByIndex(String command) {
    String todoTextDelete = command.replaceAll("[^0-9]", "").trim();
    int indexRemove = Integer.parseInt(todoTextDelete);
    int indexReSetId = Integer.parseInt(todoTextDelete);
    try {
      String query3 = "DELETE FROM Todolist_" + CPUid() + " WHERE id = ?";
      String query4 = "UPDATE Todolist_" + CPUid() +  " SET id = id - 1 WHERE id >= ?";
      PreparedStatement preparedStmt = conn.prepareStatement(query3);
      PreparedStatement preparedStmt2 = conn.prepareStatement(query4);
      preparedStmt.setInt(1, indexRemove);
      preparedStmt2.setInt(1, indexReSetId);
      preparedStmt.executeUpdate();
      preparedStmt2.executeUpdate();
      System.err.println("Заметка удалена!");
 //     conn.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
}
