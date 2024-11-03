package qwerty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarFile;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pinter
 */
public class QwertyGUI2 {

  private JFrame frame;
  private JPanel displayPanel;
  private JPanel textPanel;
  private JTextField display;

  public QwertyGUI2() {
    frame = new JFrame("Keyboard");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    textPanel = new JPanel();    
    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS)); 


    displayPanel = new JPanel();
    displayPanel.setLayout(new GridLayout(2,4));

    JButton letterA = new JButton("Q");
    JButton letterB = new JButton("W");
    JButton letterC = new JButton("E");
    JButton letterD = new JButton("<x");

    displayPanel.add(letterA);
    displayPanel.add(letterB);
    displayPanel.add(letterC);
    displayPanel.add(letterD);

    JButton letterF = new JButton("R");
    JButton letterG = new JButton("T");
    JButton letterH = new JButton("Y");
    JButton letterL = new JButton("CLR");

    displayPanel.add(letterF);
    displayPanel.add(letterG);
    displayPanel.add(letterH);
    displayPanel.add(letterL);


    frame.getContentPane().add(BorderLayout.SOUTH, displayPanel);
    // frame.getContentPane().add(BorderLayout.SOUTH, displayPanel);

    frame.pack();
    frame.setVisible(true);

  }

  class QwertyListener implements ActionListener {

    private String button;

    public QwertyListener(String button) {
      this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String text = display.getText();
      display.setText(text + button);
    }
  }

  class BackspaceActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      String text = display.getText();
      if (!text.isEmpty()) {
        display.setText(text.substring(0, text.length() - 1));
      }
    }
  }
}
