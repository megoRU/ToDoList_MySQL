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