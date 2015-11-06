package com.kissdry.ui;

import javax.swing.JOptionPane;

/**
 *
 * @author Jeff Liu<jeff.liu.guo@gmail.com>
 */
public class MessageUtil {

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param ex
     */
    public static void showMessageDialogMessage(Exception ex) {
        String exMsg = ex.toString();
        JOptionPane.showMessageDialog(null, exMsg + new Throwable().getStackTrace()[1].toString(), "错误信息提示", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param msg
     * @param title
     */
    public static void showMessageDialogMessage(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param msg
     * @param title
     * @param messageType
     */
    public static void showMessageDialogMessage(String msg, String title, int messageType) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param msg
     */
    public static void showMessageDialogMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg, "错误信息提示", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param msg
     */
    public static void showInfoMessageDialog(String msg) {
        JOptionPane.showMessageDialog(null, msg, "信息提示", JOptionPane.INFORMATION_MESSAGE);
    }
}
