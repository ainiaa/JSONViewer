package com.kissdry.jsonviewer.ui;

import com.kissdry.jsonviewer.util.PropertiesUtil;
import com.kissdry.jsonviewer.util.ParseJson;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.commons.lang.StringEscapeUtils;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrator
 */
public class JSONViewerJFrame extends javax.swing.JFrame {

    /**
     * Creates new form JsonViewerJFrame
     */
    public JSONViewerJFrame() {
        initUI();
        initComponents();
        initTreeAndIcon();

        addTabNew();
        getTextArea().paste();
    }

    public static void initUI() {
        PropertiesUtil.buildConf("zh_cn");
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JSONViewerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private void initTreeAndIcon() {
        DefaultMutableTreeNode root = Kit.objNode("JSON");
        DefaultTreeModel model = (DefaultTreeModel) jsonTree.getModel();
        model.setRoot(root);
        JSONViewerUIUtil.setNodeIcon(jsonTree);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getClassLoader().getResource("resources/images/json.png")));//这个不能以 '/'开头]
        setTitle(JSONViewerUIUtil.getI18nById("title"));
    }

    private void codeChangeAction() {
        javax.swing.JDialog dlg = new javax.swing.JDialog(this);
        dlg.setTitle(JSONViewerUIUtil.getI18nById("stringUnescape"));
        dlg.setSize(500, 350);
        dlg.setMinimumSize(new Dimension(500, 350));
        JSplitPane spiltPane2 = new JSplitPane();
        spiltPane2.setDividerLocation(150);
        spiltPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);

        final JTextArea textAreaSrc = new JTextArea();
        final JTextArea textAreaDest = new JTextArea();
        textAreaSrc.setLineWrap(true);
        textAreaDest.setLineWrap(true);

        spiltPane2.setTopComponent(new JScrollPane(textAreaSrc));
        spiltPane2.setBottomComponent(new JScrollPane(textAreaDest));

        JButton btnOK = new JButton("转换");
        btnOK.setSize(50, 25);
        java.awt.Container pane = dlg.getContentPane();
        BorderLayout layout = new BorderLayout();
        //layout.addLayoutComponent(spiltPane, BorderLayout.CENTER);
        // layout.addLayoutComponent(btnOK, BorderLayout.SOUTH);
        pane.setLayout(layout);
        pane.add(spiltPane2, BorderLayout.CENTER);
        pane.add(btnOK, BorderLayout.SOUTH);

        btnOK.addActionListener((ActionEvent e) -> {
            String str = textAreaSrc.getText();
            str = StringEscapeUtils.unescapeJava(str);
            textAreaDest.setText(str);
        });
        dlg.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topjToolBar = new javax.swing.JToolBar();
        parseAndPretty = new javax.swing.JButton();
        parseAndPress = new javax.swing.JButton();
        cleanText = new javax.swing.JButton();
        escapeString = new javax.swing.JButton();
        unescapeString = new javax.swing.JButton();
        cleanNewLine = new javax.swing.JButton();
        pasteAndPretty = new javax.swing.JButton();
        pasteAndPress = new javax.swing.JButton();
        addNewTab = new javax.swing.JButton();
        copyContent = new javax.swing.JButton();
        urlDecode = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        containerjTabbedPane = new com.kissdry.jsonviewer.ui.JClosableTabbedPane();
        jsonTreejScrollPane = new javax.swing.JScrollPane();
        jsonTree = new javax.swing.JTree();
        jMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openFile = new javax.swing.JMenuItem();
        saveFile = new javax.swing.JMenuItem();
        close = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cleanContent = new javax.swing.JMenuItem();
        formatContent = new javax.swing.JMenuItem();
        formatAndPettry = new javax.swing.JMenuItem();
        toolMenu = new javax.swing.JMenu();
        createTab = new javax.swing.JMenuItem();
        convertChinese = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        topjToolBar.setRollover(true);

        parseAndPretty.setBackground(new java.awt.Color(51, 51, 255));
        parseAndPretty.setForeground(new java.awt.Color(240, 240, 240));
        parseAndPretty.setText(JSONViewerUIUtil.getI18nById("parseAndPretty"));
        parseAndPretty.setFocusable(false);
        parseAndPretty.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        parseAndPretty.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        parseAndPretty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parseAndPrettyActionPerformed(evt);
            }
        });
        topjToolBar.add(parseAndPretty);

        parseAndPress.setBackground(new java.awt.Color(0, 204, 204));
        parseAndPress.setForeground(new java.awt.Color(255, 51, 255));
        parseAndPress.setText(JSONViewerUIUtil.getI18nById("parseAndPress"));
        parseAndPress.setFocusable(false);
        parseAndPress.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        parseAndPress.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        parseAndPress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parseAndPressActionPerformed(evt);
            }
        });
        topjToolBar.add(parseAndPress);

        cleanText.setText(JSONViewerUIUtil.getI18nById("cleanText"));
        cleanText.setFocusable(false);
        cleanText.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanText.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cleanText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanTextActionPerformed(evt);
            }
        });
        topjToolBar.add(cleanText);

        escapeString.setText(JSONViewerUIUtil.getI18nById("escape"));
        escapeString.setFocusable(false);
        escapeString.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        escapeString.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        escapeString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                escapeStringActionPerformed(evt);
            }
        });
        topjToolBar.add(escapeString);

        unescapeString.setText(JSONViewerUIUtil.getI18nById("unescape"));
        unescapeString.setFocusable(false);
        unescapeString.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        unescapeString.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        unescapeString.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unescapeStringActionPerformed(evt);
            }
        });
        topjToolBar.add(unescapeString);

        cleanNewLine.setText(JSONViewerUIUtil.getI18nById("cleanNewLine"));
        cleanNewLine.setFocusable(false);
        cleanNewLine.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanNewLine.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cleanNewLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanNewLineActionPerformed(evt);
            }
        });
        topjToolBar.add(cleanNewLine);

        pasteAndPretty.setText(JSONViewerUIUtil.getI18nById("pasteAndPretty"));
        pasteAndPretty.setFocusable(false);
        pasteAndPretty.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteAndPretty.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pasteAndPretty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteAndPrettyActionPerformed(evt);
            }
        });
        topjToolBar.add(pasteAndPretty);

        pasteAndPress.setText(JSONViewerUIUtil.getI18nById("pasteAndPress"));
        pasteAndPress.setFocusable(false);
        pasteAndPress.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pasteAndPress.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pasteAndPress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteAndPressActionPerformed(evt);
            }
        });
        topjToolBar.add(pasteAndPress);

        addNewTab.setText(JSONViewerUIUtil.getI18nById("addNewTab"));
        addNewTab.setFocusable(false);
        addNewTab.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addNewTab.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addNewTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewTabActionPerformed(evt);
            }
        });
        topjToolBar.add(addNewTab);

        copyContent.setText(JSONViewerUIUtil.getI18nById("copyContent"));
        copyContent.setFocusable(false);
        copyContent.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        copyContent.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        copyContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyContentActionPerformed(evt);
            }
        });
        topjToolBar.add(copyContent);

        urlDecode.setText(JSONViewerUIUtil.getI18nById("urlDecode"));
        urlDecode.setFocusable(false);
        urlDecode.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        urlDecode.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        urlDecode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlDecodeActionPerformed(evt);
            }
        });
        topjToolBar.add(urlDecode);

        jSplitPane1.setDividerLocation(630);
        jSplitPane1.setDividerSize(8);

        containerjTabbedPane.setAutoscrolls(true);
        jSplitPane1.setLeftComponent(containerjTabbedPane);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jsonTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jsonTreejScrollPane.setViewportView(jsonTree);

        jSplitPane1.setRightComponent(jsonTreejScrollPane);

        fileMenu.setText(JSONViewerUIUtil.getI18nById("fileMenu"));

        openFile.setText(JSONViewerUIUtil.getI18nById("openFile"));
        fileMenu.add(openFile);

        saveFile.setText(JSONViewerUIUtil.getI18nById("saveFile"));
        fileMenu.add(saveFile);

        close.setText(JSONViewerUIUtil.getI18nById("close"));
        fileMenu.add(close);

        jMenuBar.add(fileMenu);

        editMenu.setText(JSONViewerUIUtil.getI18nById("editMenu"));

        cleanContent.setText(JSONViewerUIUtil.getI18nById("cleanContent"));
        cleanContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanContentActionPerformed(evt);
            }
        });
        editMenu.add(cleanContent);

        formatContent.setText(JSONViewerUIUtil.getI18nById("formatContent"));
        formatContent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatContentActionPerformed(evt);
            }
        });
        editMenu.add(formatContent);

        formatAndPettry.setText(JSONViewerUIUtil.getI18nById("formatAndPettry"));
        formatAndPettry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatAndPettryActionPerformed(evt);
            }
        });
        editMenu.add(formatAndPettry);

        jMenuBar.add(editMenu);

        toolMenu.setText(JSONViewerUIUtil.getI18nById("toolMenu"));

        createTab.setText(JSONViewerUIUtil.getI18nById("createTab"));
        createTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTabActionPerformed(evt);
            }
        });
        toolMenu.add(createTab);

        convertChinese.setText(JSONViewerUIUtil.getI18nById("convertChinese"));
        convertChinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertChineseActionPerformed(evt);
            }
        });
        toolMenu.add(convertChinese);

        jMenuBar.add(toolMenu);

        helpMenu.setText(JSONViewerUIUtil.getI18nById("helpMenu"));

        aboutMe.setText(JSONViewerUIUtil.getI18nById("aboutMe"));
        aboutMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMeActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMe);

        jMenuBar.add(helpMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topjToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topjToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void parseAndPrettyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parseAndPrettyActionPerformed
        parseJson(true);
    }//GEN-LAST:event_parseAndPrettyActionPerformed

    private void addTabNew() {
        RSyntaxTextArea rTextArea = new org.fife.ui.rsyntaxtextarea.RSyntaxTextArea();
        rTextArea.setColumns(20);
        rTextArea.setRows(5);
        rTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        javax.swing.JScrollPane jScrollPanex = new javax.swing.JScrollPane();
        jScrollPanex.setViewportView(rTextArea);
        int i = containerjTabbedPane.getTabCount();
        containerjTabbedPane.addTab("JSON source #" + i, jScrollPanex);
    }

    private int getPreferredWidthForColumn(JTable table, TableColumn col) {
        int hw = columnHeaderWidth(table, col);  // hw = header width
        int cw = widestCellInColumn(table, col);  // cw = column width
        return hw > cw ? hw : cw;

    }

    private int columnHeaderWidth(JTable table, TableColumn col) {
        TableCellRenderer renderer = table.getTableHeader().getDefaultRenderer();
        Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
        return comp.getPreferredSize().width;
    }

    private int widestCellInColumn(JTable table, TableColumn col) {
        int c = col.getModelIndex();
        int width, maxw = 0;
        for (int r = 0; r < table.getRowCount(); r++) {
            TableCellRenderer renderer = table.getCellRenderer(r, c);
            Component comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, c), false, false, r, c);
            width = comp.getPreferredSize().width;
            maxw = width > maxw ? width : maxw;
        }
        if (maxw < 90) {
            maxw = 90;
        }
        return maxw + 10;
    }

    private void treeSelection(JTree tree, JTable table) {
        DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (selNode == null) {
            //System.out.println("jTree1ValueChanged:selNode is null");
            return;
        }
        String col[] = {"key", "value"};
        DefaultTableModel tm = (DefaultTableModel) table.getModel();
        tm.setColumnCount(2);
        tm.setColumnIdentifiers(col);
        if (selNode.isLeaf()) {
            tm.setRowCount(1);
            String arr[] = Kit.pstr(selNode.toString());
            tm.setValueAt(arr[1], 0, 0);
            tm.setValueAt(arr[2], 0, 1);
        } else {
            int childCount = selNode.getChildCount();
            tm.setRowCount(childCount);
            for (int i = 0; i < childCount; i++) {
                String arr[] = Kit.pstr(selNode.getChildAt(i).toString());
                tm.setValueAt(arr[1], i, 0);
                tm.setValueAt(arr[2], i, 1);
            }
        }
        table.setModel(tm);
        TableColumn column0 = table.getColumnModel().getColumn(0);
        column0.setPreferredWidth(getPreferredWidthForColumn(table, column0));
        TableColumn column1 = table.getColumnModel().getColumn(1);
        column1.setPreferredWidth(getPreferredWidthForColumn(table, column1));
        table.updateUI();
    }

    private JTable newTable() {
        String col[] = {"key", "value"};
        DefaultTableModel tm = new DefaultTableModel();
        tm.setColumnCount(2);
        tm.setColumnIdentifiers(col);
        JTable table = new JTable(tm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setAutoscrolls(true);
        table.setMinimumSize(new Dimension(160, 100));
        return table;
    }

    /**
     * 解析json 并尝试生成Jtree
     *
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param prettyFormat
     */
    private void parseJson(boolean prettyFormat) {
        String jsonStr = getTextArea().getText();
        if (jsonStr.isEmpty()) {
            MessageUtil.showInfoMessageDialog(JSONViewerUIUtil.getI18nById("emptyJSON"));
        } else {
            DefaultMutableTreeNode root = Kit.objNode("JSON");
            DefaultTreeModel model = (DefaultTreeModel) jsonTree.getModel();
            try {
                Object jsonObj = ParseJson.parseObject(jsonStr);
                jsonStr = ParseJson.parseJsonStr(jsonObj, prettyFormat);
                getTextArea().setText(jsonStr);

                JSONViewerUIUtil.createJsonTree(jsonObj, root);
                model.setRoot(root);
                JSONViewerUIUtil.setNodeIcon(jsonTree);
            } catch (Exception ex) {
                root.removeAllChildren();
                model.setRoot(root);
                MessageUtil.showMessageDialogMessage(ex.getMessage(), "解析或者创建tree失败");
            }
        }

    }

    private RSyntaxTextArea getTextArea() {
        javax.swing.JScrollPane pane = (javax.swing.JScrollPane) containerjTabbedPane.getSelectedComponent();
        RSyntaxTextArea r = (RSyntaxTextArea) pane.getViewport().getView();
        return r;
    }

    private void parseAndPressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parseAndPressActionPerformed
        parseJson(false);
    }//GEN-LAST:event_parseAndPressActionPerformed

    private void cleanTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanTextActionPerformed
        getTextArea().setText("");
    }//GEN-LAST:event_cleanTextActionPerformed

    private void escapeStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_escapeStringActionPerformed
        String jsonStr = getTextArea().getText();
        if (!jsonStr.isEmpty()) {
            StringEscapeUtils.escapeJava(jsonStr);
        }
    }//GEN-LAST:event_escapeStringActionPerformed

    private void unescapeStringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unescapeStringActionPerformed
        String jsonStr = getTextArea().getText();
        if (!jsonStr.isEmpty()) {
            StringEscapeUtils.unescapeJava(jsonStr);
        }
    }//GEN-LAST:event_unescapeStringActionPerformed

    private void cleanNewLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanNewLineActionPerformed
        String jsonStr = getTextArea().getText();
        if (!jsonStr.isEmpty()) {
            getTextArea().setText(jsonStr.replaceAll("\n", ""));
        }
    }//GEN-LAST:event_cleanNewLineActionPerformed

    private void aboutMeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMeActionPerformed
        ControllerJFrame.showAboutBoxFrame();
    }//GEN-LAST:event_aboutMeActionPerformed

    private void pasteAndPrettyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteAndPrettyActionPerformed
        getTextArea().paste();
        parseJson(true);
    }//GEN-LAST:event_pasteAndPrettyActionPerformed

    private void pasteAndPressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteAndPressActionPerformed
        getTextArea().paste();
        parseJson(false);
    }//GEN-LAST:event_pasteAndPressActionPerformed

    private void addNewTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewTabActionPerformed
        addTabNew();
    }//GEN-LAST:event_addNewTabActionPerformed

    private void copyContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyContentActionPerformed
        getTextArea().copy();
    }//GEN-LAST:event_copyContentActionPerformed

    private void createTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTabActionPerformed
        addTabNew();
    }//GEN-LAST:event_createTabActionPerformed

    private void cleanContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanContentActionPerformed
        getTextArea().setText("");
    }//GEN-LAST:event_cleanContentActionPerformed

    private void formatContentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatContentActionPerformed
        parseJson(false);
    }//GEN-LAST:event_formatContentActionPerformed

    private void formatAndPettryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatAndPettryActionPerformed
        parseJson(true);
    }//GEN-LAST:event_formatAndPettryActionPerformed

    private void convertChineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertChineseActionPerformed
        codeChangeAction();
    }//GEN-LAST:event_convertChineseActionPerformed

    private void urlDecodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlDecodeActionPerformed
        String origin = getTextArea().getText();
        try {
            origin = URLDecoder.decode(origin, "UTF-8");
            getTextArea().setText(origin);
        } catch (UnsupportedEncodingException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_urlDecodeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMe;
    private javax.swing.JButton addNewTab;
    private javax.swing.JMenuItem cleanContent;
    private javax.swing.JButton cleanNewLine;
    private javax.swing.JButton cleanText;
    private javax.swing.JMenuItem close;
    private com.kissdry.jsonviewer.ui.JClosableTabbedPane containerjTabbedPane;
    private javax.swing.JMenuItem convertChinese;
    private javax.swing.JButton copyContent;
    private javax.swing.JMenuItem createTab;
    private javax.swing.JMenu editMenu;
    private javax.swing.JButton escapeString;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem formatAndPettry;
    private javax.swing.JMenuItem formatContent;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTree jsonTree;
    private javax.swing.JScrollPane jsonTreejScrollPane;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JButton parseAndPress;
    private javax.swing.JButton parseAndPretty;
    private javax.swing.JButton pasteAndPress;
    private javax.swing.JButton pasteAndPretty;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JMenu toolMenu;
    private javax.swing.JToolBar topjToolBar;
    private javax.swing.JButton unescapeString;
    private javax.swing.JButton urlDecode;
    // End of variables declaration//GEN-END:variables
}
