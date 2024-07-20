
//imports

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


class Main {

    static JProgressBar progress = new JProgressBar(0, 5);
    //buttons erzeugen
    static JButton button1 = new JButton();
    static JButton button2 = new JButton();
    static JButton button3 = new JButton();
    static JButton button4 = new JButton();
    static JButton button5 = new JButton();
    static JButton button6 = new JButton();
    static JButton button7 = new JButton();
    static JButton button8 = new JButton();
    static JButton button9 = new JButton();


    //main methode
    public static void main(String[] args) {

        //aufruf der Methode GameLaden, welche das fenster usw. erstellt
        GameLaden(null);


    }

    public static void ButtonImage(Game game, JButton button, String text) {


        if (text.equals("o")) {
            button.setIcon(new ImageIcon("D:\\Download\\JLüdemann\\Downloads\\images.png"));

        }
        if (text.equals("x")) {
            button.setIcon(new ImageIcon("D:\\Download\\JLüdemann\\Downloads\\Cross-draw-image.svg.png"));

        }
    }

    private static void GameLaden(JFrame altesfenster) {

        //schließen des alten Fensters, falls vorhanden
        if (altesfenster != null) {
            altesfenster.dispose();
        }
        Game game = new Game();
        switchPlayers(game);

        JFrame frame = new JFrame("Tic Tac Toe");
        JPanel buttonPanel = new JPanel();
        JPanel progressPanel = new JPanel();
        JPanel hauptPanel = new JPanel();
        JTextField Text = new JTextField();


        Text.setSize(80, 20);
        Text.setVisible(true);
        Text.setEditable(false);

        frame.add(hauptPanel);

        hauptPanel.add(buttonPanel);
        hauptPanel.add(progressPanel);
        progressPanel.add(progress);

        GridLayout grid = new GridLayout(3, 0);

        buttonPanel.setPreferredSize(new Dimension(300, 300));

        frame.setVisible(true);
        frame.setBounds(500, 0, 400, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buttonPanel.setLayout(grid);
        frame.setResizable(false);


        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);


        //action listener auf buttons
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 0, 0, frame);

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 1, 0, frame);

            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 2, 0, frame);

            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 0, 1, frame);

            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 1, 1, frame);

            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 2, 1, frame);

            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 0, 2, frame);

            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 1, 2, frame);

            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked(e, game, 2, 2, frame);

            }
        });

    }

    //Methode zum spieler wechsel
    private static void switchPlayers(Game game) {

        if (game.draw() || game.won() != null) {


        } else {
            if (Objects.equals(game.playerAktuell.player, "o")) {
                int botZug = game.botZug();
                int[] a = game.feldCord(botZug);
                int x = a[0];
                int y = a[1];
                System.out.println("Dein virtueller Gegner ist dran");


                // Hier gleich den bot xy aussuchen lassen
                // ButtonVerändern disabeld den geklickten button
                ButtonVerändern(getButton(x, y), game.playerAktuell.player, game);
                game.marked(x, y);


            }


        }

    }

    private static JButton getButton(int x, int y) {
        if (x == 0 && y == 0) {
            return button1;
        }
        if (x == 1 && y == 0) {
            return button2;
        }
        if (x == 2 && y == 0) {
            return button3;
        }
        if (x == 0 && y == 1) {
            return button4;
        }
        if (x == 1 && y == 1) {
            return button5;
        }
        if (x == 2 && y == 1) {
            return button6;
        }
        if (x == 0 && y == 2) {
            return button7;
        }
        if (x == 1 && y == 2) {
            return button8;
        }
        if (x == 2 && y == 2) {
            return button9;
        }


        return null;
    }

    private static void buttonClicked(ActionEvent e, Game game, int x, int y, JFrame altesfenster) {

        String text = game.currentPlayer().player;

        game.marked(x, y);
        ButtonVerändern((JButton) e.getSource(), text, game);
        switchPlayers(game);


        if (game.won() != null) {


            boolean streakLost = game.gameend();
            if (streakLost) {
                JOptionPane.showMessageDialog(null, "Streak Verloren!");
            }

            JOptionPane.showMessageDialog(null, "Your Winstreak = " + game.winStreak);

            JOptionPane.showMessageDialog(null, "Gewonnen! " + game.won().player);


            winstrprogbar(game);

            GameZurücksetzen(game);

        }
        if (game.draw()) {
            JOptionPane.showMessageDialog(null, "Draw!");
            boolean streakLost = game.gameend();
            if (streakLost) {
                JOptionPane.showMessageDialog(null, "Streak Verloren!");
            }
            GameZurücksetzen(game);
        }


    }

    private static void winstrprogbar(Game game) {
        progress.setValue(game.winStreak);
        int m = progress.getValue();
        if (m == 5) {
            progress.setMinimum(5);
            JOptionPane.showMessageDialog(null, "Du hast eine winstreak von 5. 10 Wins für das nächste Ziel! ");
            progress.setMaximum(10);
            progress.setValue(game.winStreak);
        }
        if (m == 10) {
            progress.setValue(0);
            JOptionPane.showMessageDialog(null, "Du hast eine win streak von 10. 20 Wins für das nächste Ziel!");
            progress.setMaximum(20);
        }
        if (m >= 20) {
            progress.setValue(0);
            progress.setMaximum(5);
            progress.setMinimum(0);
            game.winStreak = 0;
        }
    }

    private static void ButtonVerändern(JButton button, String text, Game game) {
        button.setText(text);
        button.setEnabled(false);
        ButtonImage(game, button, text);
    }

    public static boolean GameZurücksetzen(Game game) {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        button5.setEnabled(true);
        button6.setEnabled(true);
        button7.setEnabled(true);
        button8.setEnabled(true);
        button9.setEnabled(true);

        button1.setText("");
        button2.setText("");
        button3.setText("");
        button4.setText("");
        button5.setText("");
        button6.setText("");
        button7.setText("");
        button8.setText("");
        button9.setText("");
        game.reset();
        button1.setIcon(null);
        button2.setIcon(null);
        button3.setIcon(null);
        button4.setIcon(null);
        button5.setIcon(null);
        button6.setIcon(null);
        button7.setIcon(null);
        button8.setIcon(null);
        button9.setIcon(null);


        return false;
    }


}





