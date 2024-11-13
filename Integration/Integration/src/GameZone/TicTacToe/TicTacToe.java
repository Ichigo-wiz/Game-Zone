package GameZone.TicTacToe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import GameZone.ConsoleChaos.ConsoleChaos;

public class TicTacToe {
    static String player1;
    static String player2;
    static Scanner sc;
    static int numbOfPlayer;

    public TicTacToe() {

    }

    public void play() {
        try {
            play(numbOfPlayer, player1);
        } catch (Exception e) {
            System.out.println("TicTacToe under Maintenance");
        }
        System.out.println("TicTacToe Ended");
    }

    synchronized public void play(int numbOfPlayer, String userName) throws Exception {
        sc = new Scanner(System.in);
        player1 = userName;
        System.out.println("Welcome to tic tac toe !");
        System.out.println();
        Thread.sleep(2000);
        System.out.println("I am PixelWiz, your gaming assistant :) ");
        System.out.println();
        Thread.sleep(2000);
        System.out.println(
                "_______________________________________________________________________________________________________________________");
        System.out.println();
        System.out.println("The rules are simple - ");
        System.out.println();
        Thread.sleep(2000);
        System.out.println("--> You need to make a sequence of crosses or O's to win based on whatever you choose");
        System.out.println();
        Thread.sleep(2000);
        if (numbOfPlayer == 1) {
            System.out.println("--> 500 coins for win , 200 for tie , 0 for defeat");
            System.out.println();
            Thread.sleep(2000);
        }
        System.out.println(
                "_______________________________________________________________________________________________________________________");
        System.out.println();
        gamePlay(numbOfPlayer);
    }

    synchronized void gamePlay(int player) throws Exception {
        File f = new File("TicTacToe_log.txt");
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        switch (player) {
            case 1: {
                System.out.println("Good luck " + TicTacToe.player1);
                System.out.println();
                gameSinglePlayer g = new gameSinglePlayer();
                g.start();
                g.join();
                bw.write(TicTacToe.player1 + " playing with Pixel-wiz");
                bw.newLine();
                bw.write(
                        "----------------------------------------------------------------------------------------------------------------------------------");
                bw.newLine();
                bw.flush();
            }
                break;
            case 2: {
                System.out.print("Enter player 2 name - ");
                TicTacToe.player2 = sc.next();
                System.out.println();
                bw.write(TicTacToe.player1 + " playing with " + TicTacToe.player2);
                bw.newLine();
                bw.write(
                        "----------------------------------------------------------------------------------------------------------------------------------");
                bw.newLine();
                bw.flush();
                System.out.println("Good luck " + TicTacToe.player1 + " and " + TicTacToe.player2);
                gameDoublePlayer gdp = new gameDoublePlayer(TicTacToe.player1, TicTacToe.player2);
                player1 p1 = new player1(gdp);
                player2 p2 = new player2(gdp);
                if (gdp.ch1 == 'X') {
                    p1.start();
                    p2.start();
                    p2.join();
                    return;
                } else {
                    p2.start();
                    p1.start();
                    p1.join();
                    return;
                }
            }
            default: {
                System.out.println("Invalid choice");
            }
                break;
        }
        bw.close();
    }
}

class gameSinglePlayer extends Thread {
    Scanner sc = new Scanner(System.in);
    char ar[][] = new char[3][3];
    char ch = 'X';
    char bot = 'X';

    public gameSinglePlayer() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ar[i][j] = '0';
            }
        }
    }

    @Override
    public void run() {
        onePlayer();
    }

    synchronized void onePlayer() {
        FileWriter fw = null;
        try {
            boolean b = true;
            while (b) {
                System.out.print("Enter your charachter (X or O) - ");
                ch = sc.next().toUpperCase().charAt(0);
                System.out.println();
                switch (ch) {
                    case 'X': {
                        bot = 'O';
                        b = false;
                    }
                        break;
                    case 'O': {
                        bot = 'X';
                        b = false;
                    }
                        break;
                    default: {
                        System.out.println("Invalid choice");
                        System.out.println("Choose again");
                    }
                        break;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println();
            System.out.println("       C1|       C2|       C3");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("R1_______|R1_______|R1_______");
            System.out.println("       C1|       C2|       C3");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("R2_______|R2_______|R2_______");
            System.out.println("       C1|       C2|       C3");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("R3       |R3       |R3       ");
            System.out.println();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            while (!fullArray(ar)) {
                if (getResult(ar, ch) || getResult(ar, bot)) {
                    break;
                }
                int y = 0;
                boolean x123 = false;
                try {
                    System.out.print("Enter row index - ");
                    sc.nextLine();
                    y = sc.nextInt();
                    System.out.println();
                } catch (InputMismatchException ie) {
                    System.out.println("Please enter a number");
                    System.out.println("Enter indices again");
                    System.out.println();
                    x123 = true;
                } finally {
                    if (x123) {
                        continue;
                    }
                }
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                if (y == 1 || y == 2 || y == 3) {
                    int x = 0;
                    boolean x1234 = false;
                    try {
                        System.out.print("Enter column index - ");
                        sc.nextLine();
                        x = sc.nextInt();
                        System.out.println();
                    } catch (InputMismatchException ie) {
                        System.out.println("Please enter a number");
                        System.out.println("Enter indices again");
                        System.out.println();
                        x123 = true;
                    } finally {
                        if (x123) {
                            continue;
                        }
                    }
                    if (x == 1 || x == 2 || x == 3) {
                        boolean insertion = insertIntoArray((x - 1), (y - 1), ch);
                        if (insertion) {
                            System.out.println("Index not empty");
                            System.out.println();
                        } else {
                            boolean b2 = true;
                            while (b2) {
                                int y1 = (int) (Math.random() * 3);
                                int x1 = (int) (Math.random() * 3);
                                boolean botInsertion = insertIntoArray((x1), (y1), bot);
                                if (botInsertion) {

                                } else {
                                    System.out.println();
                                    System.out.println("       C1|       C2|       C3");
                                    System.out.println("         |         |         ");
                                    System.out.println("         |         |         ");
                                    System.out.println(
                                            "    " + print(ar[0][0]) + "    |    " + print(ar[1][0]) + "    |   "
                                                    + print(ar[2][0]) + "     ");
                                    System.out.println("R1_______|R1_______|R1_______");
                                    System.out.println("       C1|       C2|       C3");
                                    System.out.println("         |         |         ");
                                    System.out.println("         |         |         ");
                                    System.out.println(
                                            "    " + print(ar[0][1]) + "    |    " + print(ar[1][1]) + "    |    "
                                                    + print(ar[2][1]) + "     ");
                                    System.out.println("R2_______|R2_______|R2_______");
                                    System.out.println("       C1|       C2|       C3");
                                    System.out.println("         |         |         ");
                                    System.out.println("         |         |         ");
                                    System.out.println(
                                            "    " + print(ar[0][2]) + "    |    " + print(ar[1][2]) + "    |    "
                                                    + print(ar[2][2]) + "     ");
                                    System.out.println("R3       |R3       |R3       ");
                                    System.out.println();
                                    b2 = false;
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid Index");
                        System.out.println("Enter indices again");
                    }
                } else {
                    System.out.println("Invalid Index");
                    System.out.println("Enter indices again");
                }
            }
            System.out.println();
            System.out.println("       C1|       C2|       C3");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("    " + print(ar[0][0]) + "    |    " + print(ar[1][0]) + "    |   "
                    + print(ar[2][0]) + "     ");
            System.out.println("R1_______|R1_______|R1_______");
            System.out.println("       C1|       C2|       C3");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("    " + print(ar[0][1]) + "    |    " + print(ar[1][1]) + "    |    "
                    + print(ar[2][1]) + "     ");
            System.out.println("R2_______|R2_______|R2_______");
            System.out.println("       C1|       C2|       C3");
            System.out.println("         |         |         ");
            System.out.println("         |         |         ");
            System.out.println("    " + print(ar[0][2]) + "    |    " + print(ar[1][2]) + "    |    "
                    + print(ar[2][2]) + "     ");
            System.out.println("R3       |R3       |R3       ");
            System.out.println();
            boolean playerResult = getResult(ar, ch);
            boolean botResult = getResult(ar, bot);
            File f = new File("TicTacToe_log.txt");
            fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (playerResult) {
                System.out.println("Congratulations ! you win ");
                bw.write("Pixel-Wiz lost");
                bw.newLine();
                bw.write(
                        "**********************************************************************************************************************************************************");
                bw.newLine();
                ConsoleChaos.coins += 500;
            } else if (botResult) {
                System.out.println("Looks like I got lucky ");
                System.out.println("Better luck next time");
                bw.write("Pixel-Wiz won");
                bw.newLine();
                bw.write(
                        "**********************************************************************************************************************************************************");
                bw.newLine();
            } else {
                System.out.println("Seems we are tied");
                System.out.println("You played quite well");
                bw.write("Both tied");
                bw.newLine();
                bw.write(
                        "**********************************************************************************************************************************************************");
                bw.newLine();
                ConsoleChaos.coins += 200;
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    boolean insertIntoArray(int x, int y, char ch) {
        if (ar[x][y] == '0') {
            ar[x][y] = ch;
            return false;
        } else {
            return true;
        }
    }

    char print(char ch) {
        if (ch == '0') {
            return ' ';
        } else {
            return ch;
        }
    }

    boolean fullArray(char[][] arr) {
        boolean flag = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == '0') {
                    flag=false;
                }
            }
        }
        return flag;
    }

    boolean getResult(char[][] ar, char ch) {
        if (ar[0][0] == ch && ar[0][1] == ch && ar[0][2] == ch) {
            return true;
        } else if (ar[1][0] == ch && ar[1][1] == ch && ar[1][2] == ch) {
            return true;
        } else if (ar[2][0] == ch && ar[2][1] == ch && ar[2][2] == ch) {
            return true;
        } else if (ar[0][0] == ch && ar[1][1] == ch && ar[2][2] == ch) {
            return true;
        } else if (ar[0][2] == ch && ar[1][1] == ch && ar[2][0] == ch) {
            return true;
        } else if (ar[0][0] == ch && ar[1][0] == ch && ar[2][0] == ch) {
            return true;
        } else if (ar[0][1] == ch && ar[1][1] == ch && ar[2][1] == ch) {
            return true;
        } else
            return ar[0][2] == ch && ar[1][2] == ch && ar[2][2] == ch;
    }
}

class gameDoublePlayer {
    String player1;
    String player2;
    char ch1;
    char ch2;
    boolean flag = true;
    Scanner sc = new Scanner(System.in);
    char ar[][] = new char[3][3];

    gameDoublePlayer(String player, String players) {
        this.player1 = player;
        this.player2 = players;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ar[i][j] = '0';
            }
        }
        System.out.println("Let's do the toss");
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("If it's a head then " + player1 + " wins");
        System.out.println("If it's a tail then " + player2 + " wins");
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Now tossing the coin");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        int toss = (int) (Math.random() * 3);
        if (toss == 1) {
            System.out.println("It's heads");
            System.out.println("Congratulations ! " + player1 + " wins the toss");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            boolean b = true;
            while (b) {
                System.out.print(player1 + " enter your choice (X or O) - ");
                ch1 = sc.next().toUpperCase().charAt(0);
                switch (ch1) {
                    case 'X': {
                        ch2 = 'O';
                        b = false;
                    }
                        break;
                    case 'O': {
                        ch2 = 'X';
                        b = false;
                    }
                        break;
                    default: {
                        System.out.println("Invalid choice ");
                        System.out.println();
                    }
                        break;
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(player1 + " plays " + ch1);
            System.out.println();
            System.out.println(player2 + " plays " + ch2);
            System.out.println();
            flag = true;
            System.out.println(player1 + "'s turn");
        } else {
            System.out.println("It's tails");
            System.out.println("Congratulations ! " + player2 + " wins the toss");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            boolean b = true;
            while (b) {
                System.out.print(player2 + " enter your choice (X or O) - ");
                ch2 = sc.next().toUpperCase().charAt(0);
                switch (ch2) {
                    case 'X': {
                        ch1 = 'O';
                        b = false;
                    }
                        break;
                    case 'O': {
                        ch1 = 'X';
                        b = false;
                    }
                        break;
                    default: {
                        System.out.println("Invalid choice ");
                        System.out.println();
                    }
                        break;
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(player1 + " plays " + ch1);
            System.out.println();
            System.out.println(player2 + " plays " + ch2);
            System.out.println();
            flag = false;
            System.out.println(player2 + "'s turn");
        }
        System.out.println();
        System.out.println("       C1|       C2|       C3");
        System.out.println("         |         |         ");
        System.out.println("         |         |         ");
        System.out.println("    " + print(ar[0][0]) + "    |    " + print(ar[1][0]) + "    |   "
                + print(ar[2][0]) + "     ");
        System.out.println("R1_______|R1_______|R1_______");
        System.out.println("       C1|       C2|       C3");
        System.out.println("         |         |         ");
        System.out.println("         |         |         ");
        System.out.println("    " + print(ar[0][1]) + "    |    " + print(ar[1][1]) + "    |    "
                + print(ar[2][1]) + "     ");
        System.out.println("R2_______|R2_______|R2_______");
        System.out.println("       C1|       C2|       C3");
        System.out.println("         |         |         ");
        System.out.println("         |         |         ");
        System.out.println("    " + print(ar[0][2]) + "    |    " + print(ar[1][2]) + "    |    "
                + print(ar[2][2]) + "     ");
        System.out.println("R3       |R3       |R3       ");
        System.out.println();
    }

    synchronized void playerOne() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        while (!fullArray(ar)) {
            if (getResult(ar, ch1) || getResult(ar, ch2)) {
                break;
            }
            System.out.println(TicTacToe.player1 + "'s turn");
            int y = 0;
            boolean x123 = false;
            try {
                System.out.print("Enter column index - ");
                sc.nextLine();
                y = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException ie) {
                System.out.println("Please enter a number");
                System.out.println("Enter indices again");
                System.out.println();
                x123 = true;
            } finally {
                if (x123) {
                    continue;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (y == 1 || y == 2 || y == 3) {
                int x = 0;
                boolean x1234 = false;
                try {
                    System.out.print("Enter row index - ");
                    sc.nextLine();
                    x = sc.nextInt();
                    System.out.println();
                } catch (InputMismatchException ie) {
                    System.out.println("Please enter a number");
                    System.out.println("Enter indices again");
                    System.out.println();
                    x123 = true;
                } finally {
                    if (x123) {
                        continue;
                    }
                }
                if (x == 1 || x == 2 || x == 3) {
                    boolean insertion = insertIntoArray((x - 1), (y - 1), ch1);
                    if (insertion) {
                        System.out.println("Index not empty");
                        System.out.println("Enter Indices again");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("       C1|       C2|       C3");
                        System.out.println("         |         |         ");
                        System.out.println("         |         |         ");
                        System.out.println("    " + print(ar[0][0]) + "    |    " + print(ar[1][0]) + "    |   "
                                + print(ar[2][0]) + "     ");
                        System.out.println("R1_______|R1_______|R1_______");
                        System.out.println("       C1|       C2|       C3");
                        System.out.println("         |         |         ");
                        System.out.println("         |         |         ");
                        System.out.println("    " + print(ar[0][1]) + "    |    " + print(ar[1][1]) + "    |    "
                                + print(ar[2][1]) + "     ");
                        System.out.println("R2_______|R2_______|R2_______");
                        System.out.println("       C1|       C2|       C3");
                        System.out.println("         |         |         ");
                        System.out.println("         |         |         ");
                        System.out.println("    " + print(ar[0][2]) + "    |    " + print(ar[1][2]) + "    |    "
                                + print(ar[2][2]) + "     ");
                        System.out.println("R3       |R3       |R3       ");
                        System.out.println();
                        flag = false;
                        notify();
                        break;
                    }
                } else {
                    System.out.println("Invalid index");
                    System.out.println("Enter Indices again");
                }
            } else {
                System.out.println("Invalid index");
                System.out.println("Enter Indices again");
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    synchronized void playerTwo() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        while (!fullArray(ar)) {
            if (getResult(ar, ch1) || getResult(ar, ch2)) {
                break;
            }
            System.out.println(TicTacToe.player2 + "'s turn");
            int y = 0;
            boolean x123 = false;

            try {
                System.out.print("Enter column index - ");
                sc.nextLine();
                y = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException ie) {
                System.out.println("Please enter a number");
                System.out.println("Enter indices again");
                System.out.println();
                x123 = true;
            } finally {
                if (x123) {
                    continue;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (y == 1 || y == 2 || y == 3) {
                int x = 0;
                boolean x1234 = false;
                try {
                    System.out.print("Enter row index - ");
                    sc.nextLine();
                    x = sc.nextInt();
                    System.out.println();
                } catch (InputMismatchException ie) {
                    System.out.println("Please enter a number");
                    System.out.println("Enter indices again");
                    System.out.println();
                    x123 = true;
                } finally {
                    if (x123) {
                        continue;
                    }
                }
                if (x == 1 || x == 2 || x == 3) {
                    boolean insertion = insertIntoArray((x - 1), (y - 1), ch2);
                    if (insertion) {
                        System.out.println("Index not empty");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("       C1|       C2|       C3");
                        System.out.println("         |         |         ");
                        System.out.println("         |         |         ");
                        System.out.println("    " + print(ar[0][0]) + "    |    " + print(ar[1][0]) + "    |   "
                                + print(ar[2][0]) + "     ");
                        System.out.println("R1_______|R1_______|R1_______");
                        System.out.println("       C1|       C2|       C3");
                        System.out.println("         |         |         ");
                        System.out.println("         |         |         ");
                        System.out.println("    " + print(ar[0][1]) + "    |    " + print(ar[1][1]) + "    |    "
                                + print(ar[2][1]) + "     ");
                        System.out.println("R2_______|R2_______|R2_______");
                        System.out.println("       C1|       C2|       C3");
                        System.out.println("         |         |         ");
                        System.out.println("         |         |         ");
                        System.out.println("    " + print(ar[0][2]) + "    |    " + print(ar[1][2]) + "    |    "
                                + print(ar[2][2]) + "     ");
                        System.out.println("R3       |R3       |R3       ");
                        System.out.println();
                        flag = true;
                        notify();
                        break;
                    }
                } else {
                    System.out.println("Invalid index");
                    System.out.println("Enter Indices again");
                }
            } else {
                System.out.println("Invalid index");
                System.out.println("Enter Indices again");
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    boolean insertIntoArray(int x, int y, char ch) {
        if (ar[x][y] == '0') {
            ar[x][y] = ch;
            return false;
        } else {
            return true;
        }
    }

    static char print(char ch) {
        if (ch == '0') {
            return ' ';
        } else {
            return ch;
        }
    }

    boolean fullArray(char[][] arr) {
        boolean flags = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == '0') {
                    return flags;
                }
            }
        }
        return flags;
    }

    boolean getResult(char[][] ar, char ch) {
        if (ar[0][0] == ch && ar[0][1] == ch && ar[0][2] == ch) {
            return true;
        } else if (ar[1][0] == ch && ar[1][1] == ch && ar[1][2] == ch) {
            return true;
        } else if (ar[2][0] == ch && ar[2][1] == ch && ar[2][2] == ch) {
            return true;
        } else if (ar[0][0] == ch && ar[1][1] == ch && ar[2][2] == ch) {
            return true;
        } else if (ar[0][2] == ch && ar[1][1] == ch && ar[2][0] == ch) {
            return true;
        } else if (ar[0][0] == ch && ar[1][0] == ch && ar[2][0] == ch) {
            return true;
        } else if (ar[0][1] == ch && ar[1][1] == ch && ar[2][1] == ch) {
            return true;
        } else
            return ar[0][2] == ch && ar[1][2] == ch && ar[2][2] == ch;
    }
}

class player1 extends Thread {
    gameDoublePlayer gdp;

    player1(gameDoublePlayer gdp) {
        this.gdp = gdp;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            gdp.playerOne();
            if (gdp.getResult(gdp.ar, gdp.ch1) || gdp.fullArray(gdp.ar)) {
                break;
            }
        }
    }
}

class player2 extends Thread {
    gameDoublePlayer gdp;

    player2(gameDoublePlayer gdp) {
        this.gdp = gdp;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                gdp.playerTwo();
                if (gdp.getResult(gdp.ar, gdp.ch2) || gdp.fullArray(gdp.ar)) {
                    break;
                }
            }
            File f = new File("TicTacToe_log.txt");
            FileWriter fw;
            fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (gdp.getResult(gdp.ar, gdp.ch1)) {
                System.out.println("Well played " + TicTacToe.player1);
                System.out.println("Congratulations on your victory");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                System.out.println("You played well too " + TicTacToe.player2);
                System.out.println("Better Luck next time");
                bw.write(TicTacToe.player1 + " wins");
                bw.newLine();
                bw.write(
                        "**********************************************************************************************************************************************************");
                bw.newLine();
                bw.close();
                ConsoleChaos.wait = false;
                return;
            } else if (gdp.getResult(gdp.ar, gdp.ch2)) {
                System.out.println("Congratulations on your victory " + TicTacToe.player2);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
                System.out.println("You played well too " + TicTacToe.player1);
                System.out.println("Better Luck next time");
                bw.write(TicTacToe.player2 + " wins");
                bw.newLine();
                bw.write(
                        "**********************************************************************************************************************************************************");
                bw.newLine();
                bw.close();
                ConsoleChaos.wait = false;
                return;
            } else {
                System.out.println("You both are equally skilled");
                System.out.println("It's a tie but both played well");
                bw.write("Both tied");
                bw.newLine();
                bw.write(
                        "**********************************************************************************************************************************************************");
                bw.newLine();
                bw.close();
                ConsoleChaos.wait = false;
                return;
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}