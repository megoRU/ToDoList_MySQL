import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws Exception {
    {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      TodoClass todoClass = new TodoClass();
      todoClass.availableCommands();
      todoClass.getCreateTable();

      for (; ; ) {
        System.out.print("> ");
        String input = reader.readLine().trim();
        if (input.matches(todoClass.COMMAND_ADD_TO_INDEX))
        {
          todoClass.addToIndex(input);
        }
        if (input.matches(todoClass.ALL_LETTERS_AND_NUMBERS))
        {
          todoClass.addText(input);
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
        else if (input.matches(todoClass.COMMAND_DEL))
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
          todoClass.list();
        }
        else if (input.matches(todoClass.COMMAND_DELETE_BD))
        {
          todoClass.getDropTable();
        }
        else if (input.matches(todoClass.COMMAND_LIST))
        {
          todoClass.list();
        } else {
          todoClass.availableCommands();
        }
      }
    }
  }
}
