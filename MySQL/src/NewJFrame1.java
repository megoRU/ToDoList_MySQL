import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;


class NewJFrame extends JFrame {

  private static final String CONN = "jdbc:mysql://176.96.239.141:3306/u2864_skillbox2?useSSL=false";
  private static final String USER = "u2864_skillbox2";
  private static final String PASS = "9S7j1D0b";


  JTextField input = new JTextField("", 5);
  JTextField outPut = new JTextField("", 5);
  JButton button = new JButton("Отправить");


  public NewJFrame() {
    initComponents();
    super.setTitle("Список дел на Java");
  }

  /**
   *
   */

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

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    jButton1.setText("Отправить");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3ActionPerformed(evt);
       // jScrollPane1.add(input);
        try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection(CONN, USER, PASS);
          Date dateNow = new Date();
          SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy '|' HH:mm:ss");
          String dateADD = formatForDateNow.format(dateNow);
          String query = "INSERT INTO Todolist (id, text, time)" + " values (?, ?, ?)";
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setInt(1, num() + 1); // Получаем "длину" таблицы и прибавляем 1
          preparedStmt.setString(2, String.valueOf(jTextPane1));
          preparedStmt.setString(3, dateADD);

          preparedStmt.execute(); //Записываем данные в БД
          System.err.println("Заметка сохранена!");
          conn.close();
        } catch (Exception e) {
          System.err.println(e.getMessage());
        }
       // String input = evt;
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
      public void actionPerformed(ActionEvent e){
        jTextArea1.setText("");
        jButton1ActionPerformed(e);
        //textfield.setText(null); //or use this
      }
    });

    jTextPane1.setToolTipText("");
  jScrollPane2.setViewportView(jTextPane1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(0, 358, Short.MAX_VALUE)))
                .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(84, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
      // удалить

    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
      //список дел
      list();
    }

   private void jButton3ActionPerformed(ActionEvent evt) {
     // Отправить

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

  //class ButtonEventListener implements ActionListener {
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
        jTextArea1.append("Список всех заметок: ");
        while (rs.next()) {
          String id = rs.getString("id");
          String text = rs.getString("text");
          String time = rs.getString("time");
          //Вывод данных
          // todoList.put(id, text);
          //jTextArea1.append("\n" + id + ". " + text + " | Дата создания: " + time);
          jTextArea1.append("\n" + id + ". " + text + " | Дата создания: " + time);
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
    public static void main(String args[]) {
      /* Set the Nimbus look and feel */
      //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
      /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
       * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
       */
      try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
          if ("Nimbus".equals(info.getName())) {
            javax.swing.UIManager.setLookAndFeel(info.getClassName());
            break;
          }
        }
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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


//  @SuppressWarnings("unchecked")
//  // <editor-fold defaultstate="collapsed" desc="Generated Code">
//  private void initComponents() {
//
//    jButton1 = new javax.swing.JButton();
//    jScrollPane1 = new javax.swing.JScrollPane();
//    jTextArea1 = new javax.swing.JTextArea();
//    jButton2 = new javax.swing.JButton();
//    jButton3 = new javax.swing.JButton();
//    jTextPane1 = new javax.swing.JTextPane();
//
//    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//    jButton1.setText("Отправить");
//    jButton1.addActionListener(new java.awt.event.ActionListener() {
//      public void actionPerformed(java.awt.event.ActionEvent evt) {
//        jButton1ActionPerformed(evt);
//      }
//    });
//
//    jTextArea1.setColumns(20);
//    jTextArea1.setRows(5);
//    jScrollPane1.setViewportView(jTextArea1);
//
//    jButton2.setText("Список всех дел");
//    jButton2.addActionListener(new java.awt.event.ActionListener() {
//      public void actionPerformed(java.awt.event.ActionEvent evt) {
//        jButton2ActionPerformed(evt);
//      }
//    });
//
//    jButton3.setText("Очистка");
//    jButton3.addActionListener(new ActionListener() {
//      public void actionPerformed(ActionEvent e){
//        jTextArea1.setText("");
//        //textfield.setText(null); //or use this
//      }
//    });
//
//
//
//    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//    getContentPane().setLayout(layout);
//    layout.setHorizontalGroup(
//        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(jScrollPane1)
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jButton2)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
//                .addComponent(jButton3)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
//                .addComponent(jButton1)
//                .addGap(20, 20, 20))
//    );
//    layout.setVerticalGroup(
//        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
//                    .addComponent(jButton2)
//                    .addComponent(jButton1)
//                    .addComponent(jButton3))
//                .addContainerGap(131, Short.MAX_VALUE))
//    );
//
//    pack();
//  }// </editor-fold>



//  /**
//   * Creates new form NewJFrame
//   */
//  public NewJFrame() {
//    initComponents();
//  }
//
//  /**
//   * This method is called from within the constructor to initialize the form.
//   * WARNING: Do NOT modify this code. The content of this method is always
//   * regenerated by the Form Editor.
//   */
//  @SuppressWarnings("unchecked")
//  // <editor-fold defaultstate="collapsed" desc="Generated Code">
//  private void initComponents() {
//
//    // Variables declaration - do not modify
//    javax.swing.JButton jButton1 = new javax.swing.JButton();
//    javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
//    jTextArea1 = new javax.swing.JTextArea();
//
//    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//
//    jButton1.setText("jButton1");
//    jButton1.addActionListener(new java.awt.event.ActionListener() {
//      public void actionPerformed(java.awt.event.ActionEvent evt) {
//        jButton1ActionPerformed(evt);
//      }
//    });
//
//    jTextArea1.setColumns(25);
//    jTextArea1.setRows(5);
//    jScrollPane1.setViewportView(jTextArea1);
//
//    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
//    getContentPane().setLayout(layout);
//    layout.setHorizontalGroup(
//        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(50, 50, 50)
//                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(jButton1)
//                .addContainerGap(411, Short.MAX_VALUE))
//    );
//    layout.setVerticalGroup(
//        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(347, 347, 347)
//                .addComponent(jButton1)
//                .addContainerGap(99, Short.MAX_VALUE))
//            .addGroup(layout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(jScrollPane1)
//                .addContainerGap())
//    );
//
//    pack();
//  }// </editor-fold>
//  public void dbselect() {
//    if (num() == 0) {
//      System.err.println("Заметок нет!");
//    } else {
//      try {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection conn = DriverManager.getConnection(CONN, USER, PASS);
//        //Получаем данные и выводим
//        Statement statement = conn.createStatement();
//        String sql = "SELECT id, text, time FROM Todolist ORDER BY id ASC";
//        ResultSet rs = statement.executeQuery(sql);
//        System.err.println("Список всех заметок: ");
//        while (rs.next()) {
//          String id = rs.getString("id");
//          String text = rs.getString("text");
//          String time = rs.getString("time");
//          //Вывод данных
//          // todoList.put(id, text);
//          jTextArea1.setText("\n" + id + ". " + text + " | Дата создания: " + time);
//        }
//
//        System.out.println();
//        rs.close();
//        conn.close();
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
//    // TODO add your handling code here:
//    dbselect();
//  }
//
//  public int num() {
//    try {
//      Class.forName("com.mysql.jdbc.Driver");
//      Connection conn = DriverManager.getConnection(CONN, USER, PASS);
//      Statement statement = conn.createStatement();
//      ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) AS id FROM Todolist");
//      if (resultSet.next()) {
//        return resultSet.getInt(1);
//      }
//    } catch (Exception ignored) {
//    }
//    return 0;
//  }
//
//  /**
//   * @param args the command line arguments
//   */
//  public static void main(String[] args) {
//    /* Set the Nimbus look and feel */
//    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//     * For details see [url]http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html[/url]
//     */
//    try {
//      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//        if ("Nimbus".equals(info.getName())) {
//          javax.swing.UIManager.setLookAndFeel(info.getClassName());
//          break;
//        }
//      }
//    } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
//      java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//    }
//    //</editor-fold>
//
//    /* Create and display the form */
//    java.awt.EventQueue.invokeLater(new Runnable() {
//      public void run() {
//        new NewJFrame().setVisible(true);
//
//      }
//    });
//  }
//
//  private javax.swing.JTextArea jTextArea1;
//  // End of variables declaration
//}