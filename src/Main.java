import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

class Main extends JFrame {

  private static final String CONN = "jdbc:mysql://176.96.239.141:3306/u2864_skillbox2?useSSL=false";
  private static final String USER = "u2864_skillbox2";
  private static final String PASS = "9S7j1D0b";

  public TodoClass todoClass = new TodoClass();

  public Main() {
    todoClass.getCreateTable();
    initComponents();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    JFrame frame = new JFrame("Demo");
    frame.setSize(550, 600);
    //Calculate the frame location
    int x = (screenSize.width - frame.getWidth()) / 2;
    int y = (screenSize.height - frame.getHeight()) / 2;
    //Set the new frame location
    frame.setLocation(x, y);
    setLocation(x, y);
    list();
    super.setTitle("Список дел на Java");
    setIconImage(getImage());
  }

  private Image getImage() {
    String fileName = "img/" + "icon" + ".png";
    ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
    return icon.getImage();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();
    jTextField1 = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(0, 128, 128));

    jPanel1.setBackground(new java.awt.Color(0, 128, 128));

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setText("Вывод всех заметок:");
    jLabel1.setToolTipText("");

    jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton2.setText("Список всех дел");
    jButton2.setFocusable(false);
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed();
      }
    });

    jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton3.setText("Очистка");
    jButton3.setFocusable(false);
    jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTextArea1.setText("");
        //    jButton1ActionPerformed(e);
        //textfield.setText(null); //or use this
      }
    });

    jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jButton4.setText("Список команд");
    jButton4.setFocusable(false);
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton4ActionPerformed();
      }
    });

    jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jButton1.setText("Отправить");
    jButton1.setFocusable(false);

    Action action = new AbstractAction()
    {
      @Override
      public void actionPerformed(ActionEvent e) {
        jTextField1.getText();
        try {
          if (jTextField1.getText().equals("")) {
            System.err.println("Should not be NULL");
            jTextArea1.setText("Зачем пустая строка в базе данных?");
          }
          String inputLine = jTextField1.getText();
          if (!jTextField1.getText().equals("")) {

            if (inputLine.matches(todoClass.COMMAND_ADD_TO_INDEX)) {
              todoClass.addToIndex(inputLine);
              list();
              jTextField1.setText("");
            }
            if (inputLine.matches(todoClass.ALL_LETTERS_AND_NUMBERS)) {
              todoClass.addText(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_ADD_TO_INDEX_RU)) {
              todoClass.addToIndex(inputLine);
              list();
            } else if (inputLine.matches(todoClass.COMMAND_ADD)) {
              todoClass.add(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_ADD_RU)) {
              todoClass.add(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_DELETE_ALL_RU)) {
              todoClass.deleteAll(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_DELETE_ALL)) {
              todoClass.deleteAll(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_EDIT)) {
              todoClass.editByIndex(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_DELETE)) {
              todoClass.deleteByIndex(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_DEL)) {
              todoClass.deleteByIndex(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_EDIT_RU)) {
              todoClass.editByIndex(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_DELETE_RU)) {
              todoClass.deleteByIndex(inputLine);
              list();
              jTextField1.setText("");
            } else if (inputLine.matches(todoClass.COMMAND_DELETE_BD)) {
              todoClass.getDropTable();
              list();
              jTextField1.setText("");
            }
            else {
              jTextArea1.setText("Произошла ошибка." + "\n" + "Вы написали первое слово с маленькой буквы либо команда нераспознанна");
            }
          }
        } catch (Exception ee) {
          ee.printStackTrace();
        }
      }
    };
    jTextField1.addActionListener(action);

    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        //  jButton3ActionPerformed(evt);
        //jTextArea1.add(input);
        try {
          if (jTextField1.getText().equals("")) {
            System.err.println("Should not be NULL");
            jTextArea1.setText("Зачем пустая строка в базе данных?");
          }
          String input = jTextField1.getText();
          if (!jTextField1.getText().equals("")) {
            if (input.matches(todoClass.COMMAND_ADD_TO_INDEX)) {
              todoClass.addToIndex(input);
              list();
              jTextField1.setText("");
            }
            if (input.matches(todoClass.ALL_LETTERS_AND_NUMBERS)) {
              todoClass.addText(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_ADD_TO_INDEX_RU)) {
              todoClass.addToIndex(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_ADD)) {
              todoClass.add(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_ADD_RU)) {
              todoClass.add(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_ALL_RU)) {
              todoClass.deleteAll(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_ALL)) {
              todoClass.deleteAll(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_EDIT)) {
              todoClass.editByIndex(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE)) {
              todoClass.deleteByIndex(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DEL)) {
              todoClass.deleteByIndex(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_EDIT_RU)) {
              todoClass.editByIndex(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_RU)) {
              todoClass.deleteByIndex(input);
              list();
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_BD)) {
              todoClass.getDropTable();
              list();
              jTextField1.setText("");
            } else {
              jTextArea1.setText("Произошла ошибка." + "\n" + "Вы написали первое слово с маленькой буквы либо команда нераспознанна");
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed();
      }
    });

    jLabel2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Поле ввода:");

    jTextArea1.setEditable(false);
    jTextArea1.setColumns(20);
    jTextArea1.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 217, Short.MAX_VALUE)))
                .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(36, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>

  private void jTextField1ActionPerformed() {
  }

  private void jButton2ActionPerformed() {
    //список дел
    list();
  }

  private void jButton4ActionPerformed() {
    //список всех команд
    availableCommands();
  }

  public void availableCommands() {
    jTextArea1.setText("");
    jTextArea1.setText("Доступные команды:"
        + "\nadd, "
        + "add Index, "
        + "edit Index, "
        + "delete index, "
        + "del index, "
        + "delete all, "
        + "list"
        + "\nадд, "
        + "адд индекс, "
        + "изменить индекс, "
        + "удалить индекс, "
        + "удалить все, "
        + "лист"
        + "\nP. S. Index/индекс = цифра"
        + "\nP. S.2 Если слово начинается с большой буквы, то комманду писать не нужно!");
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

  public void list() {
    jTextArea1.setText("");
    if (num() == 0) {
      jTextArea1.setText("Заметок нет!");
    } else {
      try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
        //Получаем данные и выводим
        Statement statement = conn.createStatement();
        String sql = "SELECT id, text, time FROM Todolist ORDER BY id ASC";
        ResultSet rs = statement.executeQuery(sql);
        //  jTextArea1.append("Список всех заметок: ");
        while (rs.next()) {
          String id = rs.getString("id");
          String text = rs.getString("text");
          String time = rs.getString("time");
          //Вывод данных
          // todoList.put(id, text);
          //jTextArea1.append("\n" + id + ". " + text + " | Дата создания: " + time);
          jTextArea1.append(id + ". " + text + " | Дата создания: " + time + "\n");
        }
        System.out.println();
        rs.close();
        conn.close();
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Main().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextField jTextField1;
  // End of variables declaration
}
