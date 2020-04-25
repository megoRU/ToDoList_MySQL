import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Window extends JFrame{
  private JTextArea myConsole = new JTextArea("myConsole@myApp $: ");
  private final String PS = "myConsole@myApp $: ";

  public Window() throws IOException {
    super.setTitle("Собственная консоль через Swing");
    this.setSize(800, 500);

    myConsole.setFont(new Font("Consolas", Font.PLAIN, 15));
    myConsole.setBounds(0, 0, 800, 500);
    myConsole.setBackground(Color.BLACK);
    myConsole.setForeground(Color.GREEN);
    myConsole.setCaretPosition(PS.length());
    myConsole.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 0x0A) {
          try {
            setPS();
          } catch (IOException ioException) {
            ioException.printStackTrace();
          }
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
      }
    });

    this.getContentPane().setLayout(new GridLayout());
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().add(myConsole);

    this.setLocationRelativeTo(null);
    this.setVisible(true);
  }

  private void setPS() throws IOException {

      myConsole.setText(myConsole.getText() + PS);
      myConsole.moveCaretPosition(myConsole.getText().length());
  }

  public static void ToClass() throws IOException {
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

  public static void main(String[] args) throws IOException {
    new Window();
  }
}