package bytereader;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Xuri Li
 */
public class frmPresent extends javax.swing.JFrame {

    Console console = new Console("");
    Vector colNames = new Vector();
    char[] colParams = new char[]{'d', 'b', 'h', 'c', 'i'};
    String lastCommand = null;
    String thisCommand = null;

    /**
     * Creates new form frmPresent
     */
    public frmPresent() {
        initComponents();
        colNames.add("Row");
        colNames.add("Byte(Decimal)");
        colNames.add("Binary");
        colNames.add("Hexidecimal");
        colNames.add("Char");
        colNames.add("Char to Int");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelDisplay = new javax.swing.JPanel();
        jScrollPaneDisplay = new javax.swing.JScrollPane();
        jTableDisplay = new javax.swing.JTable();
        jPanelConsoleDisplay = new javax.swing.JPanel();
        jScrollPaneConsole = new javax.swing.JScrollPane();
        jTextAreaConsole = new javax.swing.JTextArea();
        jPanelConsoleCommand = new javax.swing.JPanel();
        jTextFieldCommand = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        fMenuItemOpen = new javax.swing.JMenuItem();
        jMenuSave = new javax.swing.JMenu();
        fMenuItemSaveBytes = new javax.swing.JMenuItem();
        jMenuSavePlain = new javax.swing.JMenu();
        fMenuItemSavePlainDec = new javax.swing.JMenuItem();
        fMenuItemSavePlainBin = new javax.swing.JMenuItem();
        fMenuItemSavePlainHex = new javax.swing.JMenuItem();
        fMenuItemSavePlainChar = new javax.swing.JMenuItem();
        fMenuItemSavePlainChar2Int = new javax.swing.JMenuItem();
        fMenuItemExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItemEditSearchFront = new javax.swing.JMenuItem();
        jMenuItemEditSearchBack = new javax.swing.JMenuItem();
        jMenuItemEditRow = new javax.swing.JMenuItem();
        jMenuItemAddRow = new javax.swing.JMenuItem();
        jMenuItemRemoveRow = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemGetHelp = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ByteReader");
        setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().setLayout(new java.awt.GridLayout(3, 1));

        jPanelDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelDisplay.setLayout(new java.awt.GridLayout(1, 0));

        jTableDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Row", "Byte(Decimal)", "Binary", "Hexidecimal", "Char", "Char to Int"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.Byte.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDisplay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableDisplayFocusLost(evt);
            }
        });
        jTableDisplay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableDisplayPropertyChange(evt);
            }
        });
        jTableDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTableDisplayKeyTyped(evt);
            }
        });
        jTableDisplay.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                jTableDisplayVetoableChange(evt);
            }
        });
        jScrollPaneDisplay.setViewportView(jTableDisplay);

        jPanelDisplay.add(jScrollPaneDisplay);

        getContentPane().add(jPanelDisplay);

        jPanelConsoleDisplay.setLayout(new java.awt.GridLayout(1, 0));

        jTextAreaConsole.setEditable(false);
        jTextAreaConsole.setColumns(20);
        jTextAreaConsole.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jTextAreaConsole.setRows(5);
        jTextAreaConsole.setWrapStyleWord(true);
        jScrollPaneConsole.setViewportView(jTextAreaConsole);

        jPanelConsoleDisplay.add(jScrollPaneConsole);

        getContentPane().add(jPanelConsoleDisplay);

        jPanelConsoleCommand.setLayout(new java.awt.BorderLayout());

        jTextFieldCommand.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jTextFieldCommand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCommandActionPerformed(evt);
            }
        });
        jTextFieldCommand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCommandKeyPressed(evt);
            }
        });
        jPanelConsoleCommand.add(jTextFieldCommand, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanelConsoleCommand);

        jMenuFile.setText("File");

        fMenuItemOpen.setText("Open");
        fMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(fMenuItemOpen);

        jMenuSave.setText("Save");
        jMenuSave.setEnabled(false);

        fMenuItemSaveBytes.setText("SaveBytes");
        fMenuItemSaveBytes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemSaveBytesActionPerformed(evt);
            }
        });
        jMenuSave.add(fMenuItemSaveBytes);

        jMenuSavePlain.setText("SavePlainTexts");

        fMenuItemSavePlainDec.setText("Save Decimal Bytes Text");
        fMenuItemSavePlainDec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemSavePlainDecActionPerformed(evt);
            }
        });
        jMenuSavePlain.add(fMenuItemSavePlainDec);

        fMenuItemSavePlainBin.setText("Save Binary Text");
        fMenuItemSavePlainBin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemSavePlainBinActionPerformed(evt);
            }
        });
        jMenuSavePlain.add(fMenuItemSavePlainBin);

        fMenuItemSavePlainHex.setText("Save Hexidecimal Text");
        fMenuItemSavePlainHex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemSavePlainHexActionPerformed(evt);
            }
        });
        jMenuSavePlain.add(fMenuItemSavePlainHex);

        fMenuItemSavePlainChar.setText("Save Char Text");
        fMenuItemSavePlainChar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemSavePlainCharActionPerformed(evt);
            }
        });
        jMenuSavePlain.add(fMenuItemSavePlainChar);

        fMenuItemSavePlainChar2Int.setText("Save Char to Integer Text");
        fMenuItemSavePlainChar2Int.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemSavePlainChar2IntActionPerformed(evt);
            }
        });
        jMenuSavePlain.add(fMenuItemSavePlainChar2Int);

        jMenuSave.add(jMenuSavePlain);

        jMenuFile.add(jMenuSave);

        fMenuItemExit.setMnemonic('x');
        fMenuItemExit.setText("Exit");
        fMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fMenuItemExitActionPerformed(evt);
            }
        });
        jMenuFile.add(fMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setText("Edit");
        jMenuEdit.setEnabled(false);

        jMenuItemEditSearchFront.setMnemonic('b');
        jMenuItemEditSearchFront.setText("Search from beginning");
        jMenuItemEditSearchFront.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditSearchFrontActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemEditSearchFront);

        jMenuItemEditSearchBack.setMnemonic('n');
        jMenuItemEditSearchBack.setText("Search from end");
        jMenuItemEditSearchBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditSearchBackActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemEditSearchBack);

        jMenuItemEditRow.setMnemonic('e');
        jMenuItemEditRow.setText("Edit Row");
        jMenuItemEditRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEditRowActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemEditRow);

        jMenuItemAddRow.setMnemonic('i');
        jMenuItemAddRow.setText("Insert Row");
        jMenuItemAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAddRowActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemAddRow);

        jMenuItemRemoveRow.setMnemonic('r');
        jMenuItemRemoveRow.setText("Remove Row");
        jMenuItemRemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRemoveRowActionPerformed(evt);
            }
        });
        jMenuEdit.add(jMenuItemRemoveRow);

        jMenuBar1.add(jMenuEdit);

        jMenuHelp.setText("Help");

        jMenuItemGetHelp.setText("Get Help");
        jMenuItemGetHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGetHelpActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemGetHelp);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_fMenuItemExitActionPerformed

    private void jTextFieldCommandKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCommandKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            thisCommand = jTextFieldCommand.getText();
            jTextFieldCommand.setText(lastCommand == null ? "" : lastCommand);            
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            jTextFieldCommand.setText(thisCommand == null ? "" : thisCommand);
        }
    }//GEN-LAST:event_jTextFieldCommandKeyPressed

    private void jTextFieldCommandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCommandActionPerformed
        String cmd = jTextFieldCommand.getText();
        switch (cmd) {
            case "exit":
                System.exit(0);
            case "cls":
                jTextAreaConsole.setText("");
                break;
            default:
                if (console != null) {
                    log(console.executeCommand(cmd));
                } else {
                    log("please choose a file first to execute commands.");
                }
        }
        lastCommand = jTextFieldCommand.getText();
        jTextFieldCommand.setText("");
    }//GEN-LAST:event_jTextFieldCommandActionPerformed

    private void fMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemOpenActionPerformed
        chooseFile(true, false, null);
        displayFileInfo();
    }//GEN-LAST:event_fMenuItemOpenActionPerformed

    private void fMenuItemSaveBytesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemSaveBytesActionPerformed
        chooseFile(false, false, null);
    }//GEN-LAST:event_fMenuItemSaveBytesActionPerformed

    private void fMenuItemSavePlainDecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemSavePlainDecActionPerformed
        chooseFile(false, true, "d");
    }//GEN-LAST:event_fMenuItemSavePlainDecActionPerformed

    private void fMenuItemSavePlainBinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemSavePlainBinActionPerformed
        chooseFile(false, true, "b");
    }//GEN-LAST:event_fMenuItemSavePlainBinActionPerformed

    private void fMenuItemSavePlainHexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemSavePlainHexActionPerformed
        chooseFile(false, true, "h");
    }//GEN-LAST:event_fMenuItemSavePlainHexActionPerformed

    private void fMenuItemSavePlainCharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemSavePlainCharActionPerformed
        chooseFile(false, true, "c");
    }//GEN-LAST:event_fMenuItemSavePlainCharActionPerformed

    private void fMenuItemSavePlainChar2IntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMenuItemSavePlainChar2IntActionPerformed
        chooseFile(false, true, "ci");
    }//GEN-LAST:event_fMenuItemSavePlainChar2IntActionPerformed

    private void jMenuItemAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddRowActionPerformed
        int count = jTableDisplay.getSelectedColumnCount();
        if (count > 1) {
            JOptionPane.showMessageDialog(null, "Please select one row at a time.", "Error", JOptionPane.OK_OPTION);
            return;
        }
        if (count == 0) {
            DefaultTableModel dtm = (DefaultTableModel) jTableDisplay.getModel();
            dtm.addRow(console.addEmptyRow());
            jTableDisplay.setModel(dtm);
        } else if (count == 1) {
            console.insertEmptyRow(jTableDisplay.getSelectedRow());
            updateTable();
        }
    }//GEN-LAST:event_jMenuItemAddRowActionPerformed

    private void jTableDisplayVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_jTableDisplayVetoableChange

    }//GEN-LAST:event_jTableDisplayVetoableChange

    private void jTableDisplayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableDisplayPropertyChange

    }//GEN-LAST:event_jTableDisplayPropertyChange

    private void jTableDisplayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableDisplayFocusLost

    }//GEN-LAST:event_jTableDisplayFocusLost

    private void jTableDisplayKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDisplayKeyTyped

    }//GEN-LAST:event_jTableDisplayKeyTyped

    private void jMenuItemRemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRemoveRowActionPerformed
        int[] selectedRows = jTableDisplay.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(null, "Please select at least one row.", "No row selected", JOptionPane.ERROR);
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove these " + selectedRows.length + " rows?", "Warning", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                //remove
                console.removeRows(selectedRows);
                updateTable();
            } else {
                return;
            }
        }
    }//GEN-LAST:event_jMenuItemRemoveRowActionPerformed

    private void jMenuItemEditRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditRowActionPerformed
        int count = jTableDisplay.getSelectedRowCount();
        String result = "";
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "Please select one row to edit.", "Error", JOptionPane.OK_OPTION);
            return;
        } else if (count > 1) {
            JOptionPane.showMessageDialog(null, "Please select at most one row.", "Error", JOptionPane.OK_OPTION);
        } else if (count == 1) {
            if (jTableDisplay.getSelectedColumnCount() == 1) {
                //located specific element
                String args = JOptionPane.showInputDialog(null, "Input a desired value.");
                if (args == null) {
                    return;
                }
                result = console.executeCommand("edit " + jTableDisplay.getSelectedRow() + " " + colParams[jTableDisplay.getSelectedColumn() - 1] + " " + args);
                log(result);
            } else {
                //show a dialog with a combo box and an input box
                //pass col and val
                //depending on the return val, log it
                String args = JOptionPane.showInputDialog(null, "Input an indicator(d,b,h,c, or ci) and a desired value.");
                if (args == null) {
                    return;
                }
                log(console.executeCommand("edit " + jTableDisplay.getSelectedRow() + " " + args));
            }
        }
        updateTable();
    }//GEN-LAST:event_jMenuItemEditRowActionPerformed

    private void jMenuItemEditSearchFrontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditSearchFrontActionPerformed
        String args = JOptionPane.showInputDialog(null, "Input an indicator(d, b, h, c, or i) and a target value.");
        if (args == null) {
            return;
        }
        String result = console.executeCommand("searchf " + args);
        if (result.charAt(0) == '-' || result.charAt(0) == 'I' || result.charAt(0) == 'E') {
            log(result);
        } else {
            //select that target row
            int row = Integer.valueOf(result);
            log("Target is at row:" + (1 + row));
            jTableDisplay.changeSelection(row, 1, false, false);
        }
    }//GEN-LAST:event_jMenuItemEditSearchFrontActionPerformed

    private void jMenuItemEditSearchBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEditSearchBackActionPerformed
        String args = JOptionPane.showInputDialog(null, "Input an indicator(d, b, h, c, or i) and a target value.");
        if (args == null) {
            return;
        }
        String result = console.executeCommand("searchb " + args);
        if (result.charAt(0) == '-' || result.charAt(0) == 'I' || result.charAt(0) == 'E') {
            log(result);
        } else {
            //select that target row
            int row = Integer.valueOf(result);
            log("Target is at row:" + (1 + row));
            jTableDisplay.changeSelection(row, 1, false, false);
        }
    }//GEN-LAST:event_jMenuItemEditSearchBackActionPerformed

    private void jMenuItemGetHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGetHelpActionPerformed
        log(console.executeCommand("help"));
    }//GEN-LAST:event_jMenuItemGetHelpActionPerformed

    private void enableAfterOpenMenus() {
        jMenuSave.setEnabled(true);
        jMenuEdit.setEnabled(true);
    }

    private void disableAfterOpenMenus() {
        jMenuSave.setEnabled(false);
        jMenuEdit.setEnabled(false);
    }

    private void chooseFile(boolean open, boolean p, String parg) {
        if (p && (parg == null || parg.isEmpty())) {
            log("Invalid saving argument.");
            return;
        }
        JFileChooser jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.showDialog(new JLabel(), "Choose");
        File file = jfc.getSelectedFile();
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            if (open) {
                log("Please choose a file to read.");
                chooseFile(open, p, parg);
            } else {
                //saving
                log("Please give a name to the file.");
                chooseFile(open, p, parg);
            }
        } else if (file.isFile()) {
            if (open) {
                log("reading the file...");
                console = new Console(file.getAbsolutePath());
            } else {
                Toolkit.getDefaultToolkit().beep();
                //“是”：0，“否”：1，“取消”：2
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to replace " + file.getName(), "Warning", JOptionPane.ERROR_MESSAGE);
                switch (confirm) {
                    case 0:
                        //write bytes or plain
                        if (p) {
                            if (!console.save(parg, file.getAbsolutePath())) {
                                log("Unable to save the file.");
                            } else {
                                log("Save successd!");
                            }
                        } else if (!console.writeBytes(file.getAbsolutePath(), console.getDec())) {
                            log("save insuccess.");
                        } else {
                            log("save successed!");
                        }
                        break;
                    case 1:
                        //choose another one
                        chooseFile(open, p, parg);
                        break;
                    case 2:
                }
            }
        } else if (open) {
            log("File does not exist.");
        } else //write bytes or plain
        if (p) {
            if (!console.save(parg, file.getAbsolutePath())) {
                log("Unable to save the file.");
            } else {
                log("Save successd!");
            }
        } else if (!console.writeBytes(file.getAbsolutePath(), console.getDec())) {
            log("save insuccess.");
        } else {
            log("save successed!");
        }
    }

    private void updateTable() {
        Vector data = console.getRows();
        DefaultTableModel dtm = new DefaultTableModel(data, colNames);
        jTableDisplay.setModel(dtm);
    }

    private void displayFileInfo() {
        if (console != null && console.isAvaliable()) {
            log(console.getFileInfo());
            updateTable();
            enableAfterOpenMenus();
        } else {
            log("Invalid file, please choose a valid file to open.");
            disableAfterOpenMenus();
        }
    }

    public static void startGui() {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmPresent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPresent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPresent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPresent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPresent().setVisible(true);
            }
        });
    }

    public void getHelp() {
        log(console.getHelp());
    }

    public void log(String log) {
        jTextAreaConsole.setText(jTextAreaConsole.getText() + "\n" + log);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem fMenuItemExit;
    private javax.swing.JMenuItem fMenuItemOpen;
    private javax.swing.JMenuItem fMenuItemSaveBytes;
    private javax.swing.JMenuItem fMenuItemSavePlainBin;
    private javax.swing.JMenuItem fMenuItemSavePlainChar;
    private javax.swing.JMenuItem fMenuItemSavePlainChar2Int;
    private javax.swing.JMenuItem fMenuItemSavePlainDec;
    private javax.swing.JMenuItem fMenuItemSavePlainHex;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAddRow;
    private javax.swing.JMenuItem jMenuItemEditRow;
    private javax.swing.JMenuItem jMenuItemEditSearchBack;
    private javax.swing.JMenuItem jMenuItemEditSearchFront;
    private javax.swing.JMenuItem jMenuItemGetHelp;
    private javax.swing.JMenuItem jMenuItemRemoveRow;
    private javax.swing.JMenu jMenuSave;
    private javax.swing.JMenu jMenuSavePlain;
    private javax.swing.JPanel jPanelConsoleCommand;
    private javax.swing.JPanel jPanelConsoleDisplay;
    private javax.swing.JPanel jPanelDisplay;
    private javax.swing.JScrollPane jScrollPaneConsole;
    private javax.swing.JScrollPane jScrollPaneDisplay;
    private javax.swing.JTable jTableDisplay;
    private javax.swing.JTextArea jTextAreaConsole;
    private javax.swing.JTextField jTextFieldCommand;
    // End of variables declaration//GEN-END:variables
}
