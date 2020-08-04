import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import junit.framework.TestCase;
import oshi.SystemInfo;

public class TodoClassTest extends TestCase {

  private static final String CONN = "jdbc:mysql://95.181.157.159:3306/admin_todolist?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
  private static final String USER = "admin_todolist";
  private static final String PASS = "B0*cg1k0";
  private final Connection conn = DriverManager.getConnection(CONN, USER, PASS);
  private final Statement statement = conn.createStatement();
  private final Base64Class base64Class = new Base64Class();
  private final TodoClass todoClass = new TodoClass();
  public TodoClassTest() throws SQLException {

  }

  @Override
  protected void setUp() throws SQLException {
    todoClass.deleteAll("удалить все");
  }

  public int num() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      ResultSet rs = statement.executeQuery("SELECT COUNT(id) AS id FROM Todolist_BFEBFBFF000306C3");

      if (rs.next()) {
        return rs.getInt(1);
      }
      rs.close();
    } catch (Exception ignored) {
    }
    return 0;
  }

  public String getCPUid() {
    SystemInfo si = new SystemInfo();
    String processorId = si.getHardware().getProcessor().toString();
    String[] prcessorIdMassive = processorId.split("\\s+"); //prcessorIdMassive[26]
    return prcessorIdMassive[26];
  }

  public void testaddText() throws Exception {
    String query3 = "DELETE FROM Todolist_" + getCPUid() + " WHERE id = 1";
    PreparedStatement preparedStmts = conn.prepareStatement(query3);
    preparedStmts.executeUpdate(query3);
    String command = "Привет  ";
    String todoText = command.trim();
    Date dateNow = new Date();
    String encodedString = base64Class.encrypt(todoText);
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    String dateADD = formatForDateNow.format(dateNow);
    String query = "INSERT INTO Todolist_BFEBFBFF000306C3" + " (id, text, time)" + " values (?, ?, ?)";
    PreparedStatement preparedStmt = conn.prepareStatement(query);
    preparedStmt.setInt(1, num() + 1); // Получаем "длину" таблицы и прибавляем 1
    preparedStmt.setString(2, encodedString);
    preparedStmt.setString(3, dateADD);
    preparedStmt.execute(); //Записываем данные в БД
    //conn.close();

    String sql = "SELECT text FROM Todolist_" + getCPUid() + " ORDER BY id ASC";
    ResultSet rs = statement.executeQuery(sql);


    while (rs.next()) {
      String text = rs.getString("text");
      String decodedString = base64Class.decrypt(text);
      assertEquals(decodedString, todoText);
    }

    //todoClass.deleteAll("удалить все");


  }



}
