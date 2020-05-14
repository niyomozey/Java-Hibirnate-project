/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import Connection.HibernateUtil;
import Settings.ClientView;
import View.View;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.print.PrinterException;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Book;
import pojo.Bookcategory;
import pojo.Checkinout;
import pojo.Client;
import pojo.Clientcategory;

/**
 *
 * @author Niyonkuru Moise
 */
public class Operation extends javax.swing.JInternalFrame {

    public Session session() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        return session;
    }

    public void intoClientCategoryCombo() {
        Criteria cr = session().createCriteria(Clientcategory.class);
        List<Clientcategory> list = new ArrayList();
        list = cr.list();
        for (Clientcategory client : list) {
            client_combo.addItem(client.getCategoryName());
        }

        session().close();

    }

    public void intoBookCategoryCombo() {
        Criteria cr = session().createCriteria(Bookcategory.class);
        List<Bookcategory> list = new ArrayList();
        list = cr.list();
        for (Bookcategory book : list) {
            book_combo.addItem(book.getCategoryName());
        }

        session().close();

    }

    public Operation() {
        initComponents();
        intoClientCategoryCombo();
        intoBookCategoryCombo();
    }

    public void searchClient() {
        Criteria cr = session().createCriteria(Client.class);
        List<Client> list = new ArrayList();
        list = cr.list();
        if (client_combo.getSelectedItem().toString().equalsIgnoreCase("external")) {
            alert.setForeground(Color.BLACK);
            alert.setText("Your allowed to continue!!");
        } else {
            int count = 0;
            for (Client client : list) {
                String c = (String) client_combo.getSelectedItem();
                if (regno.getText().equalsIgnoreCase(client.getRegNo()) && c.equalsIgnoreCase(client.getClientcategory().getCategoryName())) {
                    ImageIcon image = new ImageIcon(client.getPhoto());
                    Image i = image.getImage().getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_AREA_AVERAGING);
                    picture.setIcon(new ImageIcon(i));
                    names.setText("Name : " + client.getFirstName() + " " + client.getLastName());
                    count = 1;
                }
            }
            if (count == 0) {
                alert.setForeground(Color.RED);
                alert.setText("Please try again, or check category match!!");
            } else {
                alert.setForeground(Color.BLACK);
                alert.setText("Your allowed to continue!!");
            }
        }
        session().close();
    }

    public void searchBook() {
        Criteria cr = session().createCriteria(Book.class);
        List<Book> list = new ArrayList();
        list = cr.list();

        int count = 0;
        for (Book book : list) {
            String c = (String) book_combo.getSelectedItem();
            if (search_book.getText().equalsIgnoreCase(book.getTitle()) && c.equalsIgnoreCase(book.getBookcategory().getCategoryName())) {
                id.setText("Book Id ");
                id1.setText(book.getBookId());
                title.setText("Title");
                title1.setText(book.getTitle());
                author.setText("author");
                author1.setText(book.getAuthor());
                long mil = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(mil);
                today.setText(date.toString());
                return_date.setText("Today ");
                ++count;
            }
        }
        if (count == 0) {
            message.setForeground(Color.RED);
            message.setText("Book not found for such Book category!!");
        } else {
            message.setForeground(Color.white);
            message.setText("Book Found!!");
        }

        session().close();
    }

    public void exportToPdf() {
        Document document = new Document();

        try {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Specify a file to save");
            int userSelect = fc.showSaveDialog(null);
            String path = null;
            if (userSelect == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                path = file.getAbsolutePath() + ".pdf";

            }
            try {
                if(path!=null){
                PdfWriter writer = PdfWriter.getInstance((com.lowagie.text.Document) document, new FileOutputStream(path));
                document.open();
                PdfContentByte cb = writer.getDirectContent();
                cb.saveState();
                Graphics2D g2 = cb.createGraphicsShapes(500, 500);
                Shape oldClip = g2.getClip();
                g2.clipRect(0, 0, 500, 500);
                checkin_table.print(g2);
                g2.setClip(oldClip);
                g2.dispose();
                cb.restoreState();
                }else{
                    JOptionPane.showMessageDialog(null, "No name provided!!");
                }
            } catch (FileNotFoundException ex) {
                System.err.print(ex.getMessage());
            }

        } catch (DocumentException ex) {
            System.err.print(ex.getMessage());
        }
        document.close();
    }

    public void exportToExcel() {
        int count = checkin_table.getColumnCount();

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Specify a file to save");
        int userSelect = fc.showSaveDialog(null);
        String path = null;
        if (userSelect == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            path = file.getAbsolutePath() + ".xlsx";
            System.out.println("path : " + path);
        }
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Excel Sheet");

        XSSFRow rowhead = sheet.createRow(0);
        for (int i = 0; i < count; i++) {
            rowhead.createCell(i).setCellValue(checkin_table.getColumnName(i));
        }
        checkin_table.getRowCount();
        int index = 1;
        if (checkin_table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Nothing in table!!!");
        } else {
            for (int i = 0; i < checkin_table.getRowCount(); i++) {
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue((String) checkin_table.getValueAt(index - 1, 0));
                row.createCell(1).setCellValue((String) checkin_table.getValueAt(index - 1, 1));
                row.createCell(2).setCellValue((String) checkin_table.getValueAt(index - 1, 2));
                row.createCell(3).setCellValue(checkin_table.getValueAt(index - 1, 3).toString());
                index++;
            }
        }
        try {
            FileOutputStream file = new FileOutputStream(path);
            try {
                book.write(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void intoCheckinTable() {
        Criteria cr = session().createCriteria(Checkinout.class);
        List<Checkinout> list = new ArrayList();
        list = cr.list();
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) checkin_table.getModel();
        model.getDataVector().removeAllElements();
        Object[] row = new Object[4];
        for (Checkinout ck : list) {

            row[0] = ck.getRegno();
            row[1] = ck.getBookTitle();
            row[2] = ck.getBookId();
            row[3] = ck.getDatetime();
            model.addRow(row);
        }
        session().close();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        picture = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        search_book = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        regno = new javax.swing.JTextField();
        client_combo = new javax.swing.JComboBox();
        alert = new javax.swing.JLabel();
        book_combo = new javax.swing.JComboBox();
        Checkout = new javax.swing.JButton();
        names = new javax.swing.JLabel();
        check = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        return_date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        id1 = new javax.swing.JLabel();
        author1 = new javax.swing.JLabel();
        today = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        author = new javax.swing.JLabel();
        message = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        checkin_table = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        regno_search = new javax.swing.JTextField();
        msg = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setText("Clent Category");

        jLabel2.setText("Book Category");

        picture.setBorder(javax.swing.BorderFactory.createTitledBorder("Picture"));

        jLabel5.setText("Search Book : ");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Regno", "Book Title", "Book id", "DateTime"
            }
        ));
        jScrollPane1.setViewportView(table);

        add.setText("+");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel10.setText("Enter Client Regno");

        regno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regnoActionPerformed(evt);
            }
        });

        alert.setForeground(new java.awt.Color(51, 153, 0));

        Checkout.setText("Check Out");
        Checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckoutActionPerformed(evt);
            }
        });

        check.setText("Check");
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        title.setText(" ");

        id.setText(" ");

        author.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                                .addGap(34, 34, 34)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(check, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(client_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(196, 196, 196)
                                                        .addComponent(Checkout)
                                                        .addGap(0, 0, Short.MAX_VALUE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(return_date, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(29, 29, 29)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                    .addComponent(title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                    .addComponent(search_book)
                                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addComponent(today, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(author1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addComponent(id1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                    .addComponent(book_combo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jButton5)))
                                                .addGap(18, 18, 18)
                                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(44, 44, 44))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)))
                                .addComponent(picture, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(alert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(148, 148, 148)
                                .addComponent(names, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(picture, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(client_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check))))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(names, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(book_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_book, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(id1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(author1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(id)
                        .addGap(18, 18, 18)
                        .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(today, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Checkout))
                    .addComponent(return_date, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jTabbedPane1.addTab("Check In", jPanel1);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel6.setText("Search By Regno");

        checkin_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Regno", "Book Title", "Book Id", "BorrowingDate"
            }
        ));
        jScrollPane2.setViewportView(checkin_table);

        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setText("Export pdf");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Export Excel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        exit.setText("Close");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setText("Show All");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        msg.setForeground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jButton4)
                        .addGap(32, 32, 32)
                        .addComponent(jButton6)
                        .addGap(35, 35, 35)
                        .addComponent(exit)))
                .addContainerGap(227, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(240, 240, 240))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(regno_search, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton2)
                        .addGap(58, 58, 58)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regno_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(28, 28, 28)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton6)
                    .addComponent(jButton4)
                    .addComponent(exit))
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Check Out", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void regnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regnoActionPerformed

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed
        searchClient();
    }//GEN-LAST:event_checkActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        searchBook();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //model.getDataVector().removeAllElements();
        Object[] row = new Object[4];
        int count = 0;
        for (int i = 0; i < table.getRowCount(); i++) {
            if (id1.getText().equalsIgnoreCase((String) table.getValueAt(i, 2))) {
                ++count;
            } else {

            }
        }
        if (count == 0) {
            row[0] = regno.getText();
            row[2] = id1.getText();
            row[1] = title1.getText();

            row[3] = Date.valueOf(today.getText());

            model.addRow(row);
        } else {
            message.setText("Book already Added!");
        }


    }//GEN-LAST:event_addActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            checkin_table.print();

        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        exportToPdf();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        exportToExcel();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void CheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckoutActionPerformed
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        int count = table.getRowCount();
        int index = 0;

        for (int i = 0; i < count; i++) {

            Checkinout ck = new Checkinout();
            ck.setRegno((String) table.getValueAt(i, 0));

            ck.setBookId((String) table.getValueAt(i, 2));
            ck.setBookTitle((String) table.getValueAt(i, 1));
            ck.setDatetime((java.sql.Date) table.getValueAt(i, 3));
            index++;
            session.save(ck);
        }
        trans.commit();
        session.close();
    }//GEN-LAST:event_CheckoutActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        intoCheckinTable();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Criteria cr = session().createCriteria(Checkinout.class);
        List<Checkinout> list = new ArrayList();
        list = cr.list();
        DefaultTableModel model = (DefaultTableModel) checkin_table.getModel();
        model.getDataVector().removeAllElements();
        int count = 0;
        Object[] row = new Object[4];
        for (Checkinout ck : list) {
            if (regno_search.getText().equalsIgnoreCase(ck.getRegno())) {
                row[0] = ck.getRegno();
                row[1] = ck.getBookTitle();
                row[2] = ck.getBookId();
                row[3] = ck.getDatetime();
                model.addRow(row);
                ++count;
            }

        }
        if (count == 0) {
            msg.setText("Client not Found!,Please try again");
        } else {
            msg.setForeground(Color.BLACK);
            msg.setText(" Found !!");
        }
        session().close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = checkin_table.getSelectedRow();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        //Query query=session.createQuery("from checkinout where bookid='"+checkin_table.getValueAt(row, 2)+"'");
        Checkinout ck = (Checkinout) session.load(Checkinout.class, checkin_table.getValueAt(row, 2).toString());
        //Checkinout ck=new Checkinout();
        //List<Checkinout> list=query.list();
        session.delete(ck);
        trans.commit();
        session.close();
        intoCheckinTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed

        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Checkout;
    private javax.swing.JButton add;
    private javax.swing.JLabel alert;
    private javax.swing.JLabel author;
    private javax.swing.JLabel author1;
    private javax.swing.JComboBox book_combo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton check;
    private javax.swing.JTable checkin_table;
    private javax.swing.JComboBox client_combo;
    private javax.swing.JButton exit;
    private javax.swing.JLabel id;
    private javax.swing.JLabel id1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel message;
    private javax.swing.JLabel msg;
    private javax.swing.JLabel names;
    private javax.swing.JLabel picture;
    private javax.swing.JTextField regno;
    private javax.swing.JTextField regno_search;
    private javax.swing.JLabel return_date;
    private javax.swing.JTextField search_book;
    private javax.swing.JTable table;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel today;
    // End of variables declaration//GEN-END:variables
}
