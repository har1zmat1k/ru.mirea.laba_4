package ru.mirea.laba_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Match extends JFrame {
    JPanel[] pnl = new JPanel[9];
    final JMenuBar menuBar = new JMenuBar();
    private int firstGroup = 0;
    private int secondGroup = 0;
    private final JLabel headerLabel;
    private final JLabel statusLabel;
    private final JLabel scoreLabel;
    private final JLabel itemsLabel;
    private final JLabel lbl;

    public Match(){
        setLayout(new GridLayout(3,3));
        for(int i = 0 ; i < pnl.length ; i++) {
            pnl[i] = new JPanel();
            add(pnl[i]);
        }
        headerLabel = new JLabel("Winner: DRAW",JLabel.CENTER );
        statusLabel = new JLabel("Result: 0 X 0",JLabel.CENTER);
        statusLabel.setSize(350,200);
        scoreLabel = new JLabel("Last Scorer: N/A",JLabel.CENTER);
        itemsLabel = new JLabel("", JLabel.CENTER);
        lbl = new JLabel("");

        ButtonListener nl = new ButtonListener();
        JButton firstButton = new JButton("AC Milan");
        firstButton.addActionListener(nl);
        JButton secondButton = new JButton("Real Madrid");
        secondButton.addActionListener(nl);

        pnl[3].setLayout(new BorderLayout());
        pnl[3].add(firstButton, BorderLayout.CENTER);
        pnl[5].setLayout(new BorderLayout());
        pnl[5].add(secondButton, BorderLayout.CENTER);
        setSize(800,500);
        pnl[1].setLayout(new BorderLayout());
        pnl[1].add(headerLabel);
        pnl[4].setLayout(new BorderLayout());
        pnl[4].add(statusLabel);
        pnl[8].setLayout(new BorderLayout());
        pnl[8].add(scoreLabel);
        pnl[6].setLayout(new BorderLayout());
        pnl[6].add(itemsLabel);
        pnl[0].setLayout(new BorderLayout());
        pnl[0].add(lbl, BorderLayout.NORTH);

        JMenu matchMenu = new JMenu("Match");
        JMenu textFieldMenu = new JMenu("Text field");
        final JMenu mouseListenerMenu = new JMenu("Mouse listener");
        final JMenu jTextAreaMenu = new JMenu("JText area");

        //////Создание "подменю"
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.setActionCommand("New");

        JMenuItem openMenuItem = new JMenuItem("Open");
        openMenuItem.setActionCommand("Open");

        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setActionCommand("Save");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setActionCommand("Exit");

        JMenuItem cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setActionCommand("Cut");

        JMenuItem copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");

        JMenuItem pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setActionCommand("Paste");

        MenuItemListener menuItemListener = new MenuItemListener();

        newMenuItem.addActionListener(menuItemListener);
        openMenuItem.addActionListener(menuItemListener);
        saveMenuItem.addActionListener(menuItemListener);
        exitMenuItem.addActionListener(menuItemListener);
        cutMenuItem.addActionListener(menuItemListener);
        copyMenuItem.addActionListener(menuItemListener);
        pasteMenuItem.addActionListener(menuItemListener);
        ///////////

        /// Добавления "подменю" в меню
        matchMenu.add(newMenuItem);
        matchMenu.add(openMenuItem);
        matchMenu.add(saveMenuItem);
        matchMenu.add(exitMenuItem);

        textFieldMenu.add(cutMenuItem);
        textFieldMenu.add(copyMenuItem);
        textFieldMenu.add(pasteMenuItem);

        ///// Добавление меню в меню бар
        menuBar.add(matchMenu);
        menuBar.add(textFieldMenu);
        menuBar.add(mouseListenerMenu);
        menuBar.add(jTextAreaMenu);
        this.setJMenuBar(menuBar);

        //////// Прослушка мыши
        this.addMouseListener(new MouseListener()
        {
            public void mouseExited(MouseEvent a){}
            public void mouseClicked(MouseEvent a)
            {lbl.setText("X="+a.getX()+" Y="+a.getY());}
            public void mouseEntered(MouseEvent a) {}
            public void mouseReleased(MouseEvent a) {}
            public void mousePressed(MouseEvent a) {}
        });
        //////////////////////////

        /////Для отключения процессов в случе прерывания работы программы
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

    }

    void newMatch(){
        firstGroup = 0;
        secondGroup = 0;
        statusLabel.setText("Result: " + firstGroup + " X " + secondGroup);
        scoreLabel.setText("Last Scorer: N/A");
        headerLabel.setText("DRAW");
        itemsLabel.setText("");
        lbl.setText("");
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("AC Milan")){
                firstGroup += 1;
            }
            else {secondGroup += 1;}
            statusLabel.setText("Result: " + firstGroup + " X " + secondGroup);
            scoreLabel.setText("Last Scorer: " + e.getActionCommand());
            if(firstGroup == secondGroup){
                headerLabel.setText("DRAW");
            }
            else if(firstGroup > secondGroup){
                headerLabel.setText("Winner: AC Milan");
            }
            else{
                headerLabel.setText("Winner: Real Madrid");
            }
        }
    }

    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("New")){
                newMatch();
            }
            else {
                itemsLabel.setText("Clicked: " + e.getActionCommand());
            }

        }
    }
}
