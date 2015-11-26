/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kissdry.jsonviewer.ui;


/**
 *
 * @author Administrator
 */
public class ControllerJFrame {

    private static JSONViewerJFrame mainFrame;
    private static JSONViewerAboutBoxJFrame aboutBoxFrame;

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     * @param args 
     */
    public static void main(String[] args) {
        mainFrame = new JSONViewerJFrame();
        aboutBoxFrame = new JSONViewerAboutBoxJFrame();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);  
    }

    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     */
    public static void showAboutBoxFrame() {
        if (aboutBoxFrame == null) {
            aboutBoxFrame = new JSONViewerAboutBoxJFrame();
        }
        if (mainFrame == null) {
            mainFrame = new JSONViewerJFrame();
        }
        aboutBoxFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(false);
        aboutBoxFrame.setVisible(true);
        
    }
    
    /**
     * @author Jeff Liu<jeff.liu.guo@gmail.com>
     */
    public static void showMainJFrame() {
        if (aboutBoxFrame == null) {
            aboutBoxFrame = new JSONViewerAboutBoxJFrame();
        }
        if (mainFrame == null) {
            mainFrame = new JSONViewerJFrame();
        }
        mainFrame.setLocationRelativeTo(null);
        aboutBoxFrame.setVisible(false);
        mainFrame.setVisible(true);
    }
}
