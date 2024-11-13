package GameZone.Hangman;

import GameZone.ConsoleChaos.ConsoleChaos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HangmanGame {
    static Connection con;
    static String player1;

    public void play(int player, String username) throws Exception {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/console_chaos", "root", "");
        if (con == null) {
            System.out.println("Game under maintenance");
        } else {
            player1 = username;
            System.out.println("Hello ! Welcome to Hangman");
            Thread.sleep(3000);
            System.out.println();
            System.out.println("I am your assistant Pixel-wiz ");
            System.out.println();
            Thread.sleep(3000);
            System.out.println("Here's a quick tour to the rules - ");
            Thread.sleep(3000);
            Rules r1 = new Rules();
            r1.start();
            r1.join();
            System.out.println("Let's Start");
            System.out.println();
            Thread.sleep(1000);
            File f = new File("Hangman_log.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            switch (player) {
                case 1: {
                    bw.write(HangmanGame.player1 + " playing");
                    bw.newLine();
                    bw.write(
                            "----------------------------------------------------------------------------------------------------------------------------------");
                    bw.newLine();
                    bw.flush();
                    SinglePlayer sp = new SinglePlayer();
                    sp.start();
                    sp.join();
                }
                    break;
                case 2: {
                    DoublePlayer dp = new DoublePlayer();
                    dp.start();
                    dp.join();
                    bw.write(HangmanGame.player1 + " playing with " + DoublePlayer.p2);
                    bw.newLine();
                    bw.write(
                            "----------------------------------------------------------------------------------------------------------------------------------");
                    bw.newLine();
                    bw.flush();
                }
                    break;
                default: {
                    System.out.println("Oops ! I did not understand your input ");
                    System.out.println("Try again");
                    System.out.println();
                }
                    break;
            }
            bw.close();
        }
    }
}

class SinglePlayer extends Thread {
    Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        try {
            String q = "SELECT * FROM hangman_words where id=? ";
            PreparedStatement pst = HangmanGame.con.prepareCall(q);
            System.out.println();
            System.out.println("Here are 5 easy words");
            System.out.println();
            for (int i = 0; i < 5; i++) {
                int ran = (int) (Math.random() * 100);
                ran++;
                pst.setInt(1, ran);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    String word = rs.getString("word");
                    String hint = rs.getString("Hint");
                    boolean win = singlePlayer(word, hint);
                    if (win) {
                        ConsoleChaos.coins += 200;
                    }
                }
            }
            System.out.println();
            System.out.println("Here are 3 medium words");
            System.out.println();
            for (int i = 0; i < 3; i++) {
                int ran = (int) (Math.random() * 100);
                ran += 101;
                pst.setInt(1, ran);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    String word = rs.getString("word");
                    String hint = rs.getString("hint");
                    boolean win = singlePlayer(word, hint);
                    if (win) {
                        ConsoleChaos.coins += 200;
                    }
                }
            }
            System.out.println();
            System.out.println("Here's one hard word");
            System.out.println();
            int ran = (int) (Math.random() * 100);
            ran += 201;
            pst.setInt(1, ran);
            ResultSet rs = pst.executeQuery();
            String hint = "";
            String word = "";
            while (rs.next()) {
                word = rs.getString("word");
                hint = rs.getString("hint");
            }
            boolean win = singlePlayer(word, hint);
            if (win) {
                ConsoleChaos.coins += 500;
            }

        } catch (SQLException ex) {
            System.out.println("Game under repairing");
        }

    }

    boolean singlePlayer(String word, String hint) {
        boolean correct = false;
        String wordString = word.toLowerCase();
        char[] wordArray = wordString.toCharArray();
        char[] answer = new char[wordArray.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = '_';
        }
        boolean b = true;
        int x = 7;
        // int y = answer.length;
        while (b) {
            System.out.println("Hint : " + hint);
            System.out.println(
                    "***********************************************************************************************");
            switch (x) {
                case 7: {
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                }
                    break;
                case 6: {
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|                       O           ");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                }
                    break;
                case 5: {
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|                       O           ");
                    System.out.println("|                       |           ");
                    System.out.println("|                       |           ");
                    System.out.println("|");
                }
                    break;
                case 4: {
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|                       O           ");
                    System.out.println("|                       |           ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      | |          ");
                }
                    break;
                case 3: {
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|                       O           ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      | |          ");
                }
                    break;
                case 2: {
                    System.out.println("|");
                    System.out.println("|");
                    System.out.println("|                        _            ");
                    System.out.println("|                       |            ");
                    System.out.println("|                       O            ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      | |          ");
                }
                    break;
                case 1: {
                    System.out.println("|");
                    System.out.println("|                          |          ");
                    System.out.println("|                        __|          ");
                    System.out.println("|                       |            ");
                    System.out.println("|                       O            ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      | |          ");
                }
                    break;
                case 0: {
                    System.out.println("|                         |          ");
                    System.out.println("|                         |          ");
                    System.out.println("|                        _|          ");
                    System.out.println("|                       |            ");
                    System.out.println("|                       O            ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      -|-           ");
                    System.out.println("|                      | |          ");
                    System.out.println("|");
                    System.out.println("|                      " + wordString);
                    b = false;
                    correct = false;
                    System.out.println("|");
                    System.out.println("|                    Game   Over     ");
                    System.out.println();
                    System.out.println(
                            "-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------");
                    System.out.println();
                    System.out.println();
                    System.out.println(
                            "-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------");
                    System.out.println();
                }
                    break;
            }
            System.out.println();
            System.out.print("           ");
            for (char i : answer) {
                System.out.print(i + " ");
            }
            System.out.println();
            int j;
            for (j = 0; j < answer.length; j++) {
                if (answer[j] != wordArray[j]) {
                    break;
                }
            }
            if (j == answer.length) {
                System.out.println();
                System.out.println("Congratulations you guessed the word");
                System.out.println();
                System.out.println(
                        "-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------");
                System.out.println();
                System.out.println();
                System.out.println(
                        "-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------#-------");
                System.out.println();
                b = false;
                correct = true;
            }
            if (b) {
                System.out.println();
                System.out.println(" " + x + " lives remaining");
                System.out.println();
                char ans;
                try {
                    ans = sc.next().toLowerCase().charAt(0);
                } catch (InputMismatchException ex) {
                    System.out.println("Please Enter a valid character");
                    System.out.println();
                    sc.nextLine();
                    continue;
                }
                boolean flag = false;
                for (int i = 0; i < answer.length; i++) {
                    if (ans == wordArray[i]) {
                        flag = true;
                        answer[i] = ans;
                    }
                }
                if (!flag) {
                    System.out.println("Character not found");
                    System.out.println();
                    x--;
                }
            }
        }
        return correct;
    }
}

class DoublePlayer extends Thread {
    Scanner sc = new Scanner(System.in);
    static String p2;

    @Override
    public void run() {
        System.out.println();
        String p1 = HangmanGame.player1;
        System.out.print("Enter name of Player 2 - ");
        p2 = sc.nextLine();
        System.out.println();
        int point1 = 0, point2 = 0;
        boolean x = true;
        while (x) {
            System.out.println();
            System.out.print(p1 + " Enter word for " + p2 + " to guess - ");
            String word1 = sc.next().toLowerCase();
            System.out.println();
            sc.nextLine();
            System.out.print(p1 + " Enter hint for " + p2 + " - ");
            String hint1 = sc.nextLine();
            System.out.println();
            boolean chance1 = new SinglePlayer().singlePlayer(word1, hint1);
            if (chance1 == true) {
                point2 += 1;
                point1 -= 1;
            } else {
                point2 -= 1;
                point1 += 1;
            }
            System.out.println();
            System.out.print(p2 + " Enter word for " + p1 + " to guess - ");
            String word2 = sc.next();
            System.out.println();
            sc.nextLine();
            System.out.print(p2 + " Enter hint for " + p1 + " - ");
            String hint2 = sc.nextLine();
            System.out.println();
            boolean chance2 = new SinglePlayer().singlePlayer(word2, hint2);
            if (chance2 == true) {
                point1 += 1;
                point2 -= 1;
            } else {
                point1 -= 1;
                point2 += 1;
            }
            System.out.println(p1 + " you are playing with " + point1 + " points");
            System.out.println(p2 + " you are playing with " + point2 + " points");
            if (point1 > point2) {
                System.out.println(p1 + " Wins");
            } else if (point1 < point2) {
                System.out.println(p2 + " Wins");
            } else {
                System.out.println("There is a tie between both players");
            }
            System.out.println("Thank You !");
            x = false;
        }
    }
}

class Rules extends Thread {
    @Override
    public void run() {
        System.out.println("__________________________________________________________________________");
        System.out.println();
        System.out.println("---> All you need to do is guess the word letter by letter using the hints provided.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println();
        System.out.println("---> Be sure to do it before you hang the man");
        System.out.println();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("---> You've got 7 lives .");
        System.out.println();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println(
                "---> You get 100 points for each correctly guessed easy word,200 for each medium word and 500 for hard word");
        System.out.println("__________________________________________________________________________");
        System.out.println();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("Good luck !");
        System.out.println();
    }
}