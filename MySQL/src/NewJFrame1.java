import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;


class NewJFrame extends JFrame {

  private static final String CONN = "jdbc:mysql://176.96.239.141:3306/u2864_skillbox2?useSSL=false";
  private static final String USER = "u2864_skillbox2";
  private static final String PASS = "9S7j1D0b";

//  JTextField input = new JTextField("", 5);
//  JTextField outPut = new JTextField("", 5);
  // JButton button = new JButton("Отправить");
  // private String Null;

  public TodoClass todoClass = new TodoClass();

  public NewJFrame() {
    initComponents();
    list();
    super.setTitle("Список дел на Java");
  }


  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    jButton1 = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    JScrollPane jScrollPane2 = new JScrollPane();
    jTextPane1 = new javax.swing.JTextPane();

    JTextField jTextField1 = new JTextField();
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jTextArea1.setEditable(false);
    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    jButton1.setText("Отправить");
    // jButton1.addActionListener(new ButtonEventListener());

    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        //  jButton3ActionPerformed(evt);
        //jTextArea1.add(input);
        if (jTextField1.getText().equals("")) {
          System.err.println("Should not be NULL");
        }
        String input = jTextField1.getText();
        if (!jTextField1.getText().equals("")) {
          try {
            if (input.matches(todoClass.COMMAND_ADD_TO_INDEX)) {
              todoClass.addToIndex(input);
              jTextField1.setText("");
            }
            if (input.matches(todoClass.ALL_LETTERS_AND_NUMBERS)) {
              todoClass.addText(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_ADD_TO_INDEX_RU)) {
              todoClass.addToIndex(input);
            } else if (input.matches(todoClass.COMMAND_ADD)) {
              todoClass.add(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_ADD_RU)) {
              todoClass.add(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_ALL_RU)) {
              todoClass.deleteAll(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_ALL)) {
              todoClass.deleteAll(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_EDIT)) {
              todoClass.editByIndex(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE)) {
              todoClass.deleteByIndex(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DEL)) {
              todoClass.deleteByIndex(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_EDIT_RU)) {
              todoClass.editByIndex(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_RU)) {
              todoClass.deleteByIndex(input);
              jTextField1.setText("");
            } else if (input.matches(todoClass.COMMAND_DELETE_BD)) {
              todoClass.getDropTable();
              jTextField1.setText("");
            }
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    });

    jButton2.setText("Список всех дел");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    jButton3.setText("Очистка");
    jButton3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jTextArea1.setText("");
        //    jButton1ActionPerformed(e);
        //textfield.setText(null); //or use this
      }
    });

    // jTextPane1.setToolTipText("");
    jScrollPane2.setViewportView(jTextPane1);

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel1.setText("Вывод всех заметок:");

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel2.setText("Поле ввода:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton2)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton3)))
                        .addGap(0, 344, Short.MAX_VALUE)))
                .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(47, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    // отправить

  }

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    //список дел
    list();
  }

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    // очистка

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
      System.err.println("Заметок нет!");
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
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
          .getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(NewJFrame.class.getName())
          .log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new NewJFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JTextPane jTextPane1;

  // End of variables declaration
}