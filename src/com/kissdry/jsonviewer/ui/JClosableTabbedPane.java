/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kissdry.jsonviewer.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A JTabbedPane which has a close ('X') icon on each tab. To add a tab, use the
 * method addTab(String, Component) To have an extra icon on each tab (e.g. like
 * in JBuilder, showing the file type) use the method addTab(String, Component,
 * Icon). Only clicking the 'X' closes the tab.
 */
public class JClosableTabbedPane extends JTabbedPane implements MouseListener {

    private double scaleRatio = 0.3;
    private final HashMap<String, Component> maps = new HashMap<>();

    public JClosableTabbedPane() {
        super();

        addMouseListener(this);
    }

    private void initTabComponent(int i) {
        setTabComponentAt(i,
                new ButtonTabComponent(this));
    }

    private void initTabComponent() {
        int i = getTabCount();
        i = i - 1;//index 从0 开始
        System.out.println("initTabComponent i:" + i);
        initTabComponent(i);
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);
        initTabComponent();
    }

    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        tip = "tab" + component.hashCode();
        maps.put(tip, component);
        super.insertTab(title, icon, component, tip, index);
    }

    @Override
    public void removeTabAt(int index) {
        Component component = getComponentAt(index);
        maps.remove("tab" + component.hashCode());
        super.removeTabAt(index);
    }

    @Override
    public JToolTip createToolTip() {
        ImageToolTip tooltip = new ImageToolTip();
        tooltip.setComponent(this);
        return tooltip;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    class ImageToolTip extends JToolTip {

        @Override
        public Dimension getPreferredSize() {
            String tip = getTipText();
            Component component = maps.get(tip);
            if (component != null) {
                return new Dimension((int) (getScaleRatio() * component.getWidth()), (int) (getScaleRatio() * component.getHeight()));
            } else {
                return super.getPreferredSize();
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            String tip = getTipText();
            Component component = maps.get(tip);
            if (component instanceof JComponent) {
                JComponent jcomponent = (JComponent) component;
                Graphics2D g2d = (Graphics2D) g;
                AffineTransform at = g2d.getTransform();
                g2d.transform(AffineTransform.getScaleInstance(getScaleRatio(), getScaleRatio()));
                ArrayList<JComponent> dbcomponents = new ArrayList<>();
                updateDoubleBuffered(jcomponent, dbcomponents);
                jcomponent.paint(g);
                resetDoubleBuffered(dbcomponents);
                g2d.setTransform(at);
            }
        }

        private void updateDoubleBuffered(JComponent component, ArrayList<JComponent> dbcomponents) {
            if (component.isDoubleBuffered()) {
                dbcomponents.add(component);
                component.setDoubleBuffered(false);
            }
            for (int i = 0; i < component.getComponentCount(); i++) {
                Component c = component.getComponent(i);
                if (c instanceof JComponent) {
                    updateDoubleBuffered((JComponent) c, dbcomponents);
                }
            }
        }

        private void resetDoubleBuffered(ArrayList<JComponent> dbcomponents) {
            dbcomponents.stream().forEach((component) -> {
                component.setDoubleBuffered(true);
            });
        }
    }

    public double getScaleRatio() {
        return scaleRatio;
    }

    public void setScaleRatio(double scaleRatio) {
        this.scaleRatio = scaleRatio;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}

class CloseTabIcon implements Icon {

    private int x_pos;
    private int y_pos;
    private final int width;
    private final int height;
    private final Icon fileIcon;

    public CloseTabIcon(Icon fileIcon) {
        this.fileIcon = fileIcon;
        width = 16;
        height = 16;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        if (fileIcon == null) {
            this.x_pos = x;
            this.y_pos = y;
            Color col = g.getColor();
            g.setColor(Color.black);
            int y_p = y + width;
//            g.drawLine(x + 1, y_p, x + 12, y_p);
//            g.drawLine(x + 1, y_p + 13, x + 12, y_p + 13);
//            g.drawLine(x, y_p + 1, x, y_p + 12);
//            g.drawLine(x + 13, y_p + 1, x + 13, y_p + 12);
//            g.drawLine(x + 3, y_p + 3, x + 10, y_p + 10);
//            g.drawLine(x + 3, y_p + 4, x + 9, y_p + 10);
//            g.drawLine(x + 4, y_p + 3, x + 10, y_p + 9);
//            g.drawLine(x + 10, y_p + 3, x + 3, y_p + 10);
//            g.drawLine(x + 10, y_p + 4, x + 4, y_p + 10);
//            g.drawLine(x + 9, y_p + 3, x + 3, y_p + 9);
//            g.setColor(col);
        } else {
            this.x_pos = x;
            this.y_pos = y;
            fileIcon.paintIcon(c, g, x, y);
        }
    }

    @Override
    public int getIconWidth() {
        return width + (fileIcon != null ? fileIcon.getIconWidth() : 0);
    }

    @Override
    public int getIconHeight() {
        return height;
    }

    public Rectangle getBounds() {
        return new Rectangle(x_pos, y_pos, width, height);
    }
    /*public static void main(String args[]) { 
    try { 
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
    } 
    catch (Exception e) { 
    e.printStackTrace(); 
    } 
    JClosableTabbedPane pane = new JClosableTabbedPane(); 
    ImageIcon icon = new ImageIcon("images/middle.jpg"); 
    pane.addTab("tab1",new JButton("first Button"),icon); 
    pane.addTab("tab2",new JButton("sec Button"),icon); 
    pane.addTab("tab3",new JButton("third Button"),icon); 
    pane.addTab("tab4",new JButton("fourth Button"),icon); 
    JFrame frame = new JFrame("Demo"); 
    frame.getContentPane().add(pane,BorderLayout.CENTER); 
    frame.setSize(500,300); 
    frame.setLocation(300,200); 
    frame.show(); 
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }*/
}
