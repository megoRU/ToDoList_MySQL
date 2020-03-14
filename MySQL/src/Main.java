import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main
{

  public static void main(String[] args) throws Exception
  {
    {

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      TodoClass todoClass = new TodoClass();

      todoClass.getCreateTable();

      for (; ; )
      {
        String input = reader.readLine().trim();

        if (input.matches(todoClass.COMMAND_ADD_TO_INDEX))
        {
          todoClass.addToIndex(input);
        }
        else if (input.matches(todoClass.COMMAND_ADD))
        {
          todoClass.add(input);
        }
        else if (input.matches(todoClass.COMMAND_EDIT))
        {
          todoClass.edit(input);
        }
        else if (input.matches(todoClass.COMMAND_DELETE))
        {
          todoClass.delete(input);
        }
        else if (input.matches(todoClass.COMMAND_LIST))
        {
          todoClass.list(input);
        } else
        {
          System.out.println("Команда не распознана");
        }
      }
    }
  }
}
//    try {
//      Class.forName("com.mysql.jdbc.Driver");
//
//      Connection con = DriverManager.getConnection(CONN, USER, PASS);
//
//      Calendar calendar = Calendar.getInstance();
//      java.sql.Date startDate = new
//          java.sql.Date(calendar.getTime().getTime());
//
//      // the mysql insert statement
//      String query =
//          "INSERT INTO Todolist (first_name, last_name, date_created, is_admin, num_points)" + " values (?, ?, ?, ?, ?)";
//
//      // create the mysql insert preparedstatement
//      PreparedStatement preparedStmt = con.prepareStatement(query);
//      preparedStmt.setString(1, "Barney");
//      preparedStmt.setString(2, "Rubble");
//      preparedStmt.setDate(3, startDate);
//      preparedStmt.setBoolean(4, false);
//      preparedStmt.setInt(5, 5000);
//
//      // execute the preparedstatement
//      preparedStmt.execute();
//
//      con.close();
//    } catch (Exception e) {
//      System.err.println("Got an exception!");
//      System.err.println(e.getMessage());
//    }
//  }
//}



//    try {
//      Class.forName("com.mysql.jdbc.Driver");
//
//      Connection con = DriverManager.getConnection(CONN, USER, PASS);
//      Statement stmt = con.createStatement();
//      //INSERT INTO employee VALUES(100, 'Thomas', 'Sales', 5000);
//
//      PreparedStatement pstmt = null;
////~~~ Чтение таблицы БД ~~~
//      pstmt = con.prepareStatement("SELECT * FROM Todolist where Id");
//// Выполнение запроса
//
//      PreparedStatement preparedStatement;
//
//// Вывод результата запроса
//
////~~~ Запись в таблицу БД ~~~
//      pstmt = con.prepareStatement("INSERT INTO Todolist(Text) values(?)");
//      pstmt.setString(1, "Кофе");
//      pstmt.executeUpdate();
//
//
//      con.close();
//
//    } catch (Exception e) {
//      System.out.println(e);
//    }
//  }
//}