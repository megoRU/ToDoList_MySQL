import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws Exception {
    {
      System.err.print("Доступные команды:");
      System.err.print(""
          + "\nADD, "
          + "ADD Index, "
          + "EDIT Index, "
          + "DELETE index, "
          + "DELETEALL, "
          + "LIST");
      System.err.print(""
          + "\nадд, "
          + "адд индекс, "
          + "изменить индекс, "
          + "удалить индекс, "
          + "удалить все, "
          + "лист");
      System.err.println(""
          + "\nP. S. Index/индекс = цифра");

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      TodoClass todoClass = new TodoClass();
      todoClass.getCreateTable();

      for (; ; ) {
        System.out.print("> ");
        String input = reader.readLine().trim();
        if (input.matches(todoClass.COMMAND_ADD_TO_INDEX))
        {
          todoClass.addToIndex(input);
        }
        else if (input.matches(todoClass.COMMAND_ADD_TO_INDEX_RU))
        {
          todoClass.addToIndex(input);
        }
        else if (input.matches(todoClass.COMMAND_ADD))
        {
          todoClass.add(input);
        }
        else if (input.matches(todoClass.COMMAND_ADD_RU))
        {
          todoClass.add(input);
        }
        else if (input.matches(todoClass.COMMAND_DELETE_ALL_RU))
        {
          todoClass.deleteAll(input);
        }
        else if (input.matches(todoClass.COMMAND_DELETE_ALL))
        {
          todoClass.deleteAll(input);
        }
        else if (input.matches(todoClass.COMMAND_EDIT))
        {
          todoClass.editByIndex(input);
        }
        else if (input.matches(todoClass.COMMAND_DELETE))
        {
          todoClass.deleteByIndex(input);
        }
        else if (input.matches(todoClass.COMMAND_EDIT_RU))
        {
          todoClass.editByIndex(input);
        }
        else if (input.matches(todoClass.COMMAND_DELETE_RU))
        {
          todoClass.deleteByIndex(input);
        }
        else if (input.matches(todoClass.COMMAND_LIST_RU))
        {
          todoClass.list(input);
        }
        else if (input.matches(todoClass.COMMAND_DELETE_BD))
        {
          todoClass.dropTable(input);
        }

        else if (input.matches(todoClass.COMMAND_LIST))
        {
          todoClass.list(input);
        } else {
          System.out.println("Команда не распознана");
        }
      }
    }
  }
}