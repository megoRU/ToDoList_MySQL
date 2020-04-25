import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SimpleGUI extends JFrame {

  private static final String CONN = "jdbc:mysql://176.96.239.141:3306/u2864_skillbox2?useSSL=false";
  private static final String USER = "u2864_skillbox2";
  private static final String PASS = "9S7j1D0b";

  private JButton button = new JButton("Отправить");
  private JTextField input = new JTextField("", 5);
  private JTextField outPut = new JTextField("", 5);

  private JLabel label = new JLabel("Поле ввода:");
  private JLabel label2 = new JLabel("Поле вывода:");

//  private JRadioButton radio1 = new JRadioButton("Select this");
//  private JRadioButton radio2 = new JRadioButton("Select that");
//  private JCheckBox check = new JCheckBox("Check", false);

  public SimpleGUI() {
    super("Список дел на Java");
    this.setBounds(800,400,500,200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container container = this.getContentPane();
    container.setLayout(new GridLayout(6,2,2,2));
    container.add(label);
    container.add(input);



    ButtonGroup group = new ButtonGroup();
//    group.add(radio1);
//    group.add(radio2);
//    container.add(radio1);

  //  radio1.setSelected(true);
  //  container.add(radio2);
//    container.add(check);
    button.addActionListener(new ButtonEventListener());
    container.add(button);
  }

  public int num() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
      Statement statement = conn.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) AS id FROM Todolist");
      if (resultSet.next()) {
        return resultSet.getInt(1);
      }
    } catch (Exception ignored) {
    }
    return 0;
  }

  class ButtonEventListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {

      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy '|' HH:mm:ss");
        String dateADD = formatForDateNow.format(dateNow);
        String query = "INSERT INTO Todolist (id, text, time)" + " values (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, num() + 1); // Получаем "длину" таблицы и прибавляем 1
        preparedStmt.setString(2, input.getText());
        preparedStmt.setString(3, dateADD);

        preparedStmt.execute(); //Записываем данные в БД
        System.err.println("Заметка сохранена!");
        input.setText("");
        conn.close();
      } catch (Exception ex) {
        System.err.println(ex.getMessage());
      }


//      String message = "";
//      message += "Button was pressed\n";
//      message += "Text is " + input.getText() + "\n";
//        input.setText("");
    //  message += (radio1.isSelected()?"Radio #1":"Radio #2")
   //       + " is selected\n";
  ////    message += "CheckBox is " + ((check.isSelected())
  //        ?"checked":"unchecked");
//      JOptionPane.showMessageDialog(null,
//          message,
//          "Output",
//          JOptionPane.PLAIN_MESSAGE);
    }
  }

  public static void main(String[] args) {
    SimpleGUI app = new SimpleGUI();
    app.setVisible(true);
  }
}