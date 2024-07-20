import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    // Konstruktor
    public Game() {
        playerx = new Player("x");
        playero = new Player("o");
        //akuteller spieler = spieler x
        playerAktuell = playerx;
    }

    // erstellen der verschiedenen Objekete
    int winStreak;
    private Player playerx;
    private Player playero;
    public Player playerAktuell;


    private Player feld1;
    private Player feld2;
    private Player feld3;
    private Player feld4;
    private Player feld5;
    private Player feld6;
    private Player feld7;
    private Player feld8;
    private Player feld9;


    public void reset() {
        feld1 = null;
        feld2 = null;
        feld3 = null;
        feld4 = null;
        feld5 = null;
        feld6 = null;
        feld7 = null;
        feld8 = null;
        feld9 = null;

        playerAktuell = playerx;
    }


    // funktion welche das aktuell geklickte feld erkennt und es mit dem aktuellem spieler belegt
    public void marked(int x, int y) {
        if (x == 0 && y == 0) {
            feld1 = playerAktuell;
        }
        if (x == 1 && y == 0) {
            feld2 = playerAktuell;
        }
        if (x == 2 && y == 0) {
            feld3 = playerAktuell;
        }
        if (x == 0 && y == 1) {
            feld4 = playerAktuell;
        }
        if (x == 1 && y == 1) {
            feld5 = playerAktuell;
        }
        if (x == 2 && y == 1) {
            feld6 = playerAktuell;
        }
        if (x == 0 && y == 2) {
            feld7 = playerAktuell;
        }
        if (x == 1 && y == 2) {
            feld8 = playerAktuell;
        }
        if (x == 2 && y == 2) {
            feld9 = playerAktuell;
        }
        if (playerAktuell == playerx) {
            playerAktuell = playero;
        } else {
            playerAktuell = playerx;
        }
    }

    // Funktion welche alle fälle eines sieges durchgeht und erkennt
    public Player won() {
        if (feld1 != null && feld2 != null && feld3 != null) {
            if (feld1 == feld2 && feld2 == feld3) {
                return feld1;
            }
        }
        if (feld4 != null && feld5 != null && feld6 != null) {
            if (feld4 == feld5 && feld5 == feld6) {
                return feld4;
            }
        }
        if (feld7 != null && feld8 != null && feld9 != null) {
            if (feld7 == feld8 && feld8 == feld9) {
                return feld7;
            }
        }
        if (feld1 != null && feld4 != null && feld7 != null) {
            if (feld1 == feld4 && feld4 == feld7) {
                return feld1;
            }
        }
        if (feld2 != null && feld5 != null && feld8 != null) {
            if (feld2 == feld5 && feld5 == feld8) {
                return feld2;
            }
        }
        if (feld3 != null && feld6 != null && feld9 != null) {
            if (feld3 == feld6 && feld6 == feld9) {
                return feld3;
            }
        }
        if (feld1 != null && feld5 != null && feld9 != null) {
            if (feld1 == feld5 && feld5 == feld9) {
                return feld1;
            }
        }
        if (feld7 != null && feld5 != null && feld3 != null) {
            if (feld7 == feld5 && feld5 == feld3) {
                return feld7;
            }
        }
        return null;
    }

    public Player currentPlayer() {
        return playerAktuell;
    }

    // funktion um zu erkennen wann das spiel unentschieden ist
    public boolean draw() {
        return feld1 != null && feld2 != null && feld3 != null && feld4 != null && feld5 != null && feld6 != null && feld7 != null && feld8 != null && feld9 != null;
    }

    // Funktion um einen möglichen Sieg zu erkennen und die winstreak hoch zu setzen(winstreak wird in seperater datei gespeichert)
    public boolean gameend() {

        boolean streakLost = false;
        try {

            BufferedReader in = new BufferedReader(new FileReader("text.txt"));

            String zeile1 = in.readLine();
            System.out.println(zeile1);
            in.close();

            winStreak = Integer.parseInt(zeile1);
            if (won() != null) {
                winStreak = winStreak + 1;
            }
            if (won() != playerx) {
                winStreak = 0;
                streakLost = true;

            }


            BufferedWriter out = new BufferedWriter(new FileWriter("text.txt"));

            String s = Integer.toString(winStreak);
            out.write(s);
            out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return streakLost;
    }

    public int botZug() {
        //return simpleNächstesFeld();
        return getRandomFeld();
    }

    private int[] simpleNächstesFeld() {
        int x;
        int y;

        if (feld1 == null) {
            x = 0;
            y = 0;
        } else if (feld2 == null) {
            x = 1;
            y = 0;
        } else if (feld3 == null) {
            x = 2;
            y = 0;
        } else if (feld4 == null) {
            x = 0;
            y = 1;
        } else if (feld5 == null) {
            x = 1;
            y = 1;
        } else if (feld6 == null) {
            x = 2;
            y = 1;
        } else if (feld7 == null) {
            x = 0;
            y = 2;
        } else if (feld8 == null) {
            x = 1;
            y = 2;
        } else {
            y = 2;
            x = 2;
        }

        return new int[]{x, y};
    }

    // festelegung welches feld zu welcher koordinate gehört
    private Player findFeld(int x, int y) {
        if (x == 0 && y == 0) {
            return feld1;
        }
        if (x == 1 && y == 0) {
            return feld2;
        }
        if (x == 2 && y == 0) {
            return feld3;
        }
        if (x == 0 && y == 1) {
            return feld4;
        }
        if (x == 1 && y == 1) {
            return feld5;
        }
        if (x == 2 && y == 1) {
            return feld6;
        }
        if (x == 0 && y == 2) {
            return feld7;
        }
        if (x == 1 && y == 2) {
            return feld8;
        }
        if (x == 2 && y == 2) {
            return feld9;
        }
        return null;
    }

    public int getRandomFeld() {

        Player[] Felder = {feld1, feld2, feld3, feld4, feld5, feld6, feld7, feld8, feld9};
        ArrayList<Integer> leereFelder = new ArrayList<>();
        for (int i = 0; i < Felder.length; i = i + 1) {
            Player Feld = Felder[i];
            if (Feld == null) {
                leereFelder.add(i);

            }
        }

        int a;

        Random randomZahl = new Random();
        a = randomZahl.nextInt(leereFelder.size());


        return leereFelder.get(a);

    }

    public int[] feldCord(int feldZahl) {
        switch (feldZahl) {
            case 0:
                return new int[]{0, 0};
            case 1:
                return new int[]{1, 0};
            case 2:
                return new int[]{2, 0};
            case 3:
                return new int[]{0, 1};
            case 4:
                return new int[]{1, 1};
            case 5:
                return new int[]{2, 1};
            case 6:
                return new int[]{0, 2};
            case 7:
                return new int[]{1, 2};
            case 8:
                return new int[]{2, 2};
            default:
                return null;
        }

    }
}
