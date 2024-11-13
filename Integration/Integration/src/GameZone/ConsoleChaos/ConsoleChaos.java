package GameZone.ConsoleChaos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import GameZone.CrazyCricket.CrazeCricket;
import GameZone.Cricket.DigitalCricket;
import GameZone.Game.QuizGame;
import GameZone.Hangman.HangmanGame;
import GameZone.TicTacToe.TicTacToe;
import GameZone.TickingTime.TimerGame;

public class ConsoleChaos {
    static Connection con;
    static Scanner sc = new Scanner(System.in);
    static String playerType;
    static int creditPoints = 2500;
    public static boolean wait = true;
    public static int coins = 0;

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/console_chaos", "root", "");
        if (con == null) {
            System.out.println("Sorry Game zone is under Maintenance");
        } else {
            System.out.println("Welcome to Console Chaos , a digital game Zone .");
            System.out.println();
            Thread.sleep(3000);
            System.out.println("I am Pixel-Wiz , your gaming assitant and guide through this Fun zone .");
            System.out.println();
            boolean b = true;
            Thread.sleep(3000);
            do {
                System.out.println();
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println("Press 1 if you are an admin");
                System.out.println("Press 2 if you are a player");
                System.out.println("Press 3 to exit");
                System.out.print("Enter your choice - ");
                int choice;
                try {
                    choice = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println();
                    sc.nextLine();
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("I did not understand your input");
                    System.out.println("Please try again");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println();
                    Thread.sleep(3000);
                    continue;
                }
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.println();
                switch (choice) {
                    case 1: {
                        playerType = "admin";
                        b = false;
                    }
                        break;
                    case 2: {
                        playerType = "player";
                        b = false;
                    }
                        break;
                    case 3: {
                        System.exit(0);
                    }
                        break;
                    default: {
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Oops! I did not understand your response");
                        System.out.println("Please Enter again");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        Thread.sleep(2000);
                    }
                        break;
                }
            } while (b);
            login l1 = new login();
            l1.start();
            l1.join();
            System.out.println();
            do {
                String q = "SELECT captcha_code FROM captcha_codes WHERE id=?";
                PreparedStatement pst = con.prepareStatement(q);
                int ran = (int) (Math.random() * 100);
                ran++;
                pst.setInt(1, ran);
                ResultSet rs = pst.executeQuery();
                String captcha = "";
                while (rs.next()) {
                    captcha = rs.getString("captcha_code");
                }
                String input;
                System.out.println(captcha);
                System.out.println();
                System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                System.out.print("Enter captcha as shown (case sensitive) - ");
                input = sc.next();
                System.out.println();
                if (input.equals(captcha)) {
                    System.out.println("Let's start !");
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                    break;
                } else {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Invalid Captcha");
                    System.out.println("Try again");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    Thread.sleep(3000);
                }
            } while (true);
            System.out.println();
            System.out.println("Please note that you will be able to convert your reward coins only in the next visit");
            File f = new File("login log.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String q1 = "{CALL currentTime()}";
            CallableStatement cst = con.prepareCall(q1);
            Timestamp ts = null;
            ResultSet rs1 = cst.executeQuery();
            while (rs1.next()) {
                ts = rs1.getTimestamp(1);
            }
            if (playerType.equalsIgnoreCase("admin")) {
                String q = "SELECT * FROM login_details  WHERE mail='" + l1.adminMail + "'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(q);
                while (rs.next()) {
                    bw.write(
                            rs.getString("name") + " aka " + rs.getString("username") + " logged in as admin at " + ts);
                    bw.flush();
                }
            }
            bw.close();
            if (playerType.equals("player")) {
                Player p1 = new Player(l1.playerMail);
                p1.playing();
            } else {
                creditPoints = 999999999;
                Player p1 = new Player(l1.adminMail);
                p1.playing();
            }
        }
    }
}

class login extends Thread {
    Scanner sc = new Scanner(System.in);
    String adminMail;
    String playerMail;

    @Override
    public void run() {
        try {
            if (ConsoleChaos.playerType.equalsIgnoreCase("admin")) {
                loginAdmin();
            } else {
                loginPlayer();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("login failed");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.exit(0);
        }
    }

    synchronized void loginAdmin() throws Exception {
        boolean b = true;
        while (b) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Enter your email ID - ");
            try {
                adminMail = sc.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Invalid email");
                System.out.println("Try again");
                System.out.println();
                Thread.sleep(2000);
                continue;
            }
            String password;
            System.out.print("Enter your password - ");
            try {
                password = sc.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Inalid password");
                System.out.println("Try again");
                System.out.println();
                Thread.sleep(2000);
                continue;
            }
            String q = "SELECT * FROM login_details WHERE personType='admin'";
            boolean found = false;
            Statement st = ConsoleChaos.con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                if (adminMail.equalsIgnoreCase(rs.getString("mail"))) {
                    if (password.equalsIgnoreCase(rs.getString("password"))) {
                        found = true;
                        System.out.println("Welcome " + rs.getString("name") + " sir");
                    }
                }
            }
            if (!found) {
                System.out.println("The entered ID and/or Password are incorret");
                System.out.println("Try again");
                System.out.println();
                Thread.sleep(3000);
            } else {
                b = false;
            }
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    synchronized void loginPlayer() throws Exception {
        boolean b = true;
        while (b) {
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("Press 1 if you are a new player");
            System.out.println("Press 2 if you are a registered player");
            System.out.println("Enter 3 to exit");
            System.out.print("Enter your choice - ");
            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println();
                sc.nextLine();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("I did not understand your input");
                System.out.println("Please try again");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                Thread.sleep(3000);
                continue;
            }
            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println();
            switch (choice) {
                case 1: {
                    newPlayerRegistration();
                }
                    break;
                case 2: {
                    oldPlayerLogin();
                    b = false;
                }
                    break;
                case 3: {
                    b = false;
                    System.exit(0);
                }
                    break;
                default: {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Oops I did not understand your input");
                    System.out.println("Please Choose again");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println();
                }
                    break;
            }
        }
    }

    synchronized void newPlayerRegistration() throws Exception {
        boolean b = true;
        while (b) {
            String q = "INSERT INTO login_details VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = ConsoleChaos.con.prepareStatement(q);
            String mail;
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Enter emailID - ");
            try {
                mail = sc.next();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Invalid mail ID");
                System.out.println("Try again");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                Thread.sleep(2000);
                continue;
            }
            String q1 = "SELECT mail FROM login_details";
            CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
            ResultSet rs = cst.executeQuery();
            boolean found = false;
            while (rs.next()) {
                if (rs.getString("mail").equalsIgnoreCase(mail)) {
                    found = true;
                }
            }
            if (found) {
                System.out.println();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Email already exists");
                System.out.println("Choose different email");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                Thread.sleep(2000);
                continue;
            }
            long number;
            System.out.print("Enter mobile number - ");
            try {
                number = sc.nextLong();
                System.out.println();
                long temp = number;
                int count = 0;
                while (temp > 0) {
                    temp = temp / 10;
                    count++;
                }
                if (count != 10) {
                    throw new NumberTooLongException();
                }
            } catch (InputMismatchException | NumberTooLongException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Invalid Phone number");
                System.out.println("Try again");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                sc.nextLine();
                Thread.sleep(2000);
                continue;
            }
            String name;
            System.out.print("Enter name - ");
            try {
                sc.nextLine();
                name = sc.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Please enter a valid Name");
                System.out.println("Try again");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                sc.nextLine();
                Thread.sleep(2000);
                continue;
            }
            boolean b1 = true;
            String username = "";
            while (b1) {
                System.out.print("Enter username - ");
                try {
                    username = sc.nextLine();
                    System.out.println();
                } catch (InputMismatchException e) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("Invalid User Name");
                    System.out.println("Try again");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println();
                    Thread.sleep(2000);
                    continue;
                }
                String q2 = "SELECT Username FROM login_details";
                PreparedStatement cst1 = ConsoleChaos.con.prepareStatement(q2);
                ResultSet rs1 = cst1.executeQuery();
                boolean found1 = false;
                while (rs1.next()) {
                    if (rs1.getString("Username").equalsIgnoreCase(username)) {
                        found1 = true;
                    }
                }
                if (found1) {
                    System.out.println();
                    System.out.println("Username already exists");
                    System.out.println("Choose different username");
                    System.out.println();
                    Thread.sleep(2000);
                } else {
                    break;
                }
            }
            String password;
            System.out.print("Enter password - ");
            try {
                password = sc.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Invalid password");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                continue;
            }
            int points = ConsoleChaos.creditPoints;
            int coins = 0;
            String personType = "player";
            pst.setString(1, name);
            pst.setString(2, username);
            pst.setString(3, mail);
            pst.setLong(4, number);
            pst.setString(5, password);
            pst.setInt(6, points);
            pst.setString(7, personType);
            pst.setInt(8, coins);
            int r = pst.executeUpdate();
            if (r > 0) {
                System.out.println("Registration successfull");
                b = false;
            } else {
                System.out.println("Registration failed");
                Thread.sleep(2000);
                System.out.println("Please Try again");
                System.out.println();
                continue;
            }
            System.out.println("--------------------------------------------------------------------------------");
            File f = new File("player registration.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String q1234 = "{CALL currentTime()}";
            CallableStatement cst1234 = ConsoleChaos.con.prepareCall(q1234);
            Timestamp ts1234 = null;
            ResultSet rs1234 = cst1234.executeQuery();
            while (rs1234.next()) {
                ts1234 = rs1234.getTimestamp(1);
            }
            bw.write("New Player details - ");
            bw.newLine();
            bw.write("Name : " + name);
            bw.newLine();
            bw.write("Username : " + username);
            bw.newLine();
            bw.write("Mail : " + mail);
            bw.newLine();
            bw.write("Number : " + number);
            bw.newLine();
            bw.write("Date : " + ts1234);
            bw.newLine();
            bw.write(
                    "-----------------------------------------------------------------------------------------------------------");
            bw.newLine();
            bw.flush();
            bw.close();
        }
    }

    synchronized void oldPlayerLogin() throws Exception {
        boolean b = true;
        while (b) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.print("Enter your email ID - ");
            try {
                playerMail = sc.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Invalid email");
                System.out.println("Try again");
                System.out.println();
                Thread.sleep(2000);
                continue;
            }
            String password;
            System.out.print("Enter your password - ");
            try {
                password = sc.nextLine();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Invalid email");
                System.out.println("Try again");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                Thread.sleep(2000);
                continue;
            }
            String q = "SELECT * FROM login_details WHERE personType='player'";
            boolean found = false;
            Statement st = ConsoleChaos.con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                if (playerMail.equalsIgnoreCase(rs.getString("mail"))) {
                    if (password.equalsIgnoreCase(rs.getString("password"))) {
                        found = true;
                        System.out.println("Welcome " + rs.getString("name") + " aka " + rs.getString("username"));
                        System.out.println();
                        System.out.println("You have " + (rs.getInt("creditPoints") + rs.getInt("coinsEarned"))
                                + " credit points remaining");
                    }
                }
            }
            if (!found) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("The entered ID and/or Password are incorret");
                System.out.println("Try again");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println();
                Thread.sleep(2000);
            } else {
                b = false;
            }
            System.out.println("--------------------------------------------------------------------------------");
        }
    }
}

class NumberTooLongException extends Exception {
    NumberTooLongException() {
        super();
    }
}

class Player {
    String playerName;
    String playerUsername;
    String playerMail;
    long playerNumber;
    Scanner sc = new Scanner(System.in);
    static int creditPoints = ConsoleChaos.creditPoints;
    ArrayList<games> game = new ArrayList<>();
    static int coins;

    Player() {
        coins = 0;
    }

    Player(String mail) {
        try {
            String q1 = "SELECT name,username,mail,phoneNumber FROM login_details WHERE mail='" + mail + "'";
            Statement st = ConsoleChaos.con.createStatement();
            ResultSet rs = st.executeQuery(q1);
            while (rs.next()) {
                playerName = rs.getString("name");
                playerUsername = rs.getString("username");
                playerMail = rs.getString("mail");
                playerNumber = rs.getLong("phoneNumber");
            }
            File f = new File("login log.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String q = "{CALL currentTime()}";
            CallableStatement cst = ConsoleChaos.con.prepareCall(q);
            Timestamp ts = null;
            ResultSet rs1 = cst.executeQuery();
            while (rs1.next()) {
                ts = rs1.getTimestamp(1);
            }
            bw.write(playerName + " aka " + playerUsername + " logged in as player at " + ts + " with mail "
                    + playerMail + " and number " + playerNumber);
            bw.newLine();
            bw.write(
                    "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            bw.newLine();
            bw.flush();
            bw.close();
        } catch (SQLException | IOException ex) {
            System.out.println(ex);
        }
    }

    public synchronized void playing() {
        int choice = 0;
        ConsoleChaos.creditPoints = creditPoints;
        try {
            boolean b = true;
            String q = "SELECT * FROM available_games";
            Statement st = ConsoleChaos.con.createStatement();
            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                games g = new games(rs.getInt("ID"), rs.getString("name"), rs.getInt("MaxPlayers"),
                        rs.getInt("credit_points"));
                game.add(g);

            }
            System.out.println(
                    "##############################################################################################");
            System.out.println(
                    "Game Number            Game Name            Maximum Players            Credits Required(PP)");
            System.out.println();
            try {
                @SuppressWarnings("rawtypes")
                Iterator itr = game.iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next());
                }
            } catch (NoSuchElementException e) {
                System.out.println("Hi");
            }
            System.out.println(
                    "##############################################################################################");
            System.out.println();
            System.out.print("Enter game number you want to play (0 to exit) - ");
            try {
                choice = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Please Enter a number ");
                System.out.println();
                sc.nextLine();
                return;
            } catch (NoSuchElementException e) {
                System.out.println("Please Enter a number ");
                System.out.println();
                choice = sc.nextInt();
                sc.nextLine();
                return;
            }
            switch (choice) {
                case 0: {
                    System.out.println("Points remaining : " + creditPoints);
                    System.out.println("reward coins earned : " + coins);
                    System.out.println();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println("Thanks for being my guest " + playerUsername);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println();
                    System.out.println("Do visit us again");
                    String query = "UPDATE login_details SET coinsEarned=?,SET creditPoints=? WHERE Username=?";
                    PreparedStatement pst = ConsoleChaos.con.prepareStatement(query);
                    pst.setInt(1, ConsoleChaos.coins);
                    pst.setInt(2, ConsoleChaos.creditPoints);
                    pst.setString(3, playerUsername);
                    pst.executeUpdate();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.exit(0);
                }
                    break;
                case 1: {
                    if (creditPoints >= game.get(0).getCreds()) {
                        try {
                            String q1 = "{CALL currentTime()}";
                            CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
                            Timestamp ts = null;
                            ResultSet rs1 = cst.executeQuery();
                            while (rs1.next()) {
                                ts = rs1.getTimestamp(1);
                            }
                            String query = "INSERT INTO digicricketlogs VALUES ('" + playerUsername
                                    + "','" + ts + "'," + game.get(0).getCreds() + ")";
                            Statement state = ConsoleChaos.con.createStatement();
                            state.execute(query);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        creditPoints -= game.get(0).getCreds();
                        System.out.println("Starting Digi Criciket");
                        DigitalCricket dc = new DigitalCricket();
                        try {
                            dc.play(playerUsername);
                        } catch (Exception e) {
                            System.out.println("Digital Cricket under Maintencnace");
                        }
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println(creditPoints + " points remaining ");
                        System.out.println();
                        System.out.println("reward coins = " + ConsoleChaos.coins);
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println();
                        sc.nextLine();
                    } else {
                        System.out.println("Not enough points");
                        System.out.println();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                }
                    break;
                case 2: {
                    if (creditPoints >= game.get(1).getCreds()) {
                        try {
                            String q1 = "{CALL currentTime()}";
                            CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
                            Timestamp ts = null;
                            ResultSet rs1 = cst.executeQuery();
                            while (rs1.next()) {
                                ts = rs1.getTimestamp(1);
                            }
                            String query = "INSERT INTO crazycricketlogs VALUES ('" + playerUsername
                                    + "','" + ts + "'," + game.get(1).getCreds() + ")";
                            Statement state = ConsoleChaos.con.createStatement();
                            state.execute(query);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        creditPoints -= game.get(1).getCreds();
                        System.out.println("Starting Crazy Criciket");
                        CrazeCricket cc = new CrazeCricket();
                        try {
                            cc.play(playerUsername);
                        } catch (Exception e) {
                            System.out.println("Crazy Cricket under Maintencnace");
                        }
                        System.out.println();
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println(creditPoints + " points remaining ");
                        System.out.println();
                        System.out.println("reward coins = " + ConsoleChaos.coins);
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println();
                        sc.nextLine();
                    } else {
                        System.out.println("Not enough points");
                        System.out.println();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                }
                    break;
                case 3: {
                    boolean b1 = true;
                    while (b1) {
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("Press 1 for single player(500 points)");
                        System.out.println("Press 2 for double player(1000 points)");
                        System.out.print("Your input - ");
                        int numbOfPlayers;
                        try {
                            numbOfPlayers = sc.nextInt();
                            System.out.println();
                        } catch (InputMismatchException e) {
                            System.out.println("I did not understand your input");
                            System.out.println();
                            System.out.println("Try again");
                            System.out.println();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                            continue;
                        }
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        switch (numbOfPlayers) {
                            case 1: {
                                if (creditPoints >= game.get(2).getCreds()) {
                                    try {
                                        String q1 = "{CALL currentTime()}";
                                        CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
                                        Timestamp ts = null;
                                        ResultSet rs1 = cst.executeQuery();
                                        while (rs1.next()) {
                                            ts = rs1.getTimestamp(1);
                                        }
                                        String query = "INSERT INTO tictactoelogs VALUES ('" + playerUsername
                                                + "','" + ts + "'," + game.get(2).getCreds() + ")";
                                        Statement state = ConsoleChaos.con.createStatement();
                                        state.execute(query);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                    creditPoints -= game.get(2).getCreds();
                                    TicTacToe tt = new TicTacToe();
                                    try {
                                        tt.play(1, playerUsername);
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                } else {
                                    System.out.println("Not enough points");
                                    System.out.println();
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        System.out.println(e);
                                    }
                                }
                                b1 = false;
                            }
                                break;
                            case 2: {
                                if (creditPoints >= (2 * game.get(2).getCreds())) {
                                    try {
                                        String q1 = "{CALL currentTime()}";
                                        CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
                                        Timestamp ts = null;
                                        ResultSet rs1 = cst.executeQuery();
                                        while (rs1.next()) {
                                            ts = rs1.getTimestamp(1);
                                        }
                                        String query = "INSERT INTO tictactoelogs VALUES ('" + playerUsername
                                                + "','" + ts + "'," + (2 * game.get(2).getCreds()) + ")";
                                        Statement state = ConsoleChaos.con.createStatement();
                                        state.execute(query);
                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                    creditPoints -= 2 * game.get(2).getCreds();
                                    TicTacToe tt = new TicTacToe();
                                    try {
                                        tt.play(2, playerUsername);
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                } else {
                                    System.out.println("Not enough points");
                                    System.out.println();
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        System.out.println(e);
                                    }
                                }
                                b1 = false;
                            }
                                break;
                            default: {
                                System.out.println("No such option available , Sorry !");
                                System.out.println();
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    System.out.println(e);
                                }
                            }
                                break;
                        }
                    }
                    System.out.println();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(creditPoints + " points remaining");
                    System.out.println();
                    System.out.println("reward coins = " + ConsoleChaos.coins);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println();
                }
                    break;
                case 4: {
                    boolean b1 = true;
                    while (b1) {
                        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println("Press 1 for single player(600 points)");
                        System.out.println("Press 2 for double player(1200 points)");
                        System.out.print("Your input - ");
                        int numbOfPlayers;
                        try {
                            numbOfPlayers = sc.nextInt();
                            System.out.println();
                        } catch (InputMismatchException e) {
                            System.out.println("I did not understand your input");
                            System.out.println();
                            System.out.println("Try again");
                            System.out.println();
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                System.out.println(ex);
                            }
                            System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
                            continue;
                        }
                        switch (numbOfPlayers) {
                            case 1: {
                                try {
                                    String q1 = "{CALL currentTime()}";
                                    CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
                                    Timestamp ts = null;
                                    ResultSet rs1 = cst.executeQuery();
                                    while (rs1.next()) {
                                        ts = rs1.getTimestamp(1);
                                    }
                                    String query = "INSERT INTO hangmanlogs VALUES ('" + playerUsername
                                            + "','" + ts + "'," + game.get(3).getCreds() + ")";
                                    Statement state = ConsoleChaos.con.createStatement();
                                    state.execute(query);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                if (creditPoints > game.get(3).getCreds()) {
                                    creditPoints -= game.get(3).getCreds();
                                    HangmanGame hg = new HangmanGame();
                                    try {
                                        hg.play(1, playerUsername);
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                } else {
                                    System.out.println("Not enough points");
                                    System.out.println();
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        System.out.println(e);
                                    }
                                }
                                b1 = false;
                            }
                                break;
                            case 2: {
                                if (creditPoints > (2 * game.get(3).getCreds())) {
                                    creditPoints -= 2 * game.get(3).getCreds();
                                    HangmanGame hg = new HangmanGame();
                                    try {
                                        hg.play(2, playerUsername);
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                } else {
                                    System.out.println("Not enough points");
                                    System.out.println();
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        System.out.println(e);
                                    }
                                }
                                b1 = false;
                            }
                                break;
                            default: {
                                System.out.println("No such option available , Sorry !");
                                System.out.println();
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    System.out.println(e);
                                }
                            }
                                break;
                        }
                    }
                    System.out.println();
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println(creditPoints + " points remaining");
                    System.out.println();
                    System.out.println("reward coins = " + ConsoleChaos.coins);
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println();
                }
                    break;
                case 5: {
                    if (creditPoints > game.get(4).getCreds()) {
                        try {
                            String q1 = "{CALL currentTime()}";
                            CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
                            Timestamp ts = null;
                            ResultSet rs1 = cst.executeQuery();
                            while (rs1.next()) {
                                ts = rs1.getTimestamp(1);
                            }
                            String query = "INSERT INTO tickingtimelogs VALUES ('" + playerUsername
                                    + "','" + ts + "'," + game.get(4).getCreds() + ")";
                            Statement state = ConsoleChaos.con.createStatement();
                            state.execute(query);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        creditPoints -= game.get(4).getCreds();
                        TimerGame.playerUsername = playerUsername;
                        TimerGame tg = new TimerGame();
                        try {
                            tg.start();
                            tg.join();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        while (ConsoleChaos.wait) {
                            synchronized (this) {
                                try {
                                    wait();
                                } catch (InterruptedException ex) {
                                    System.out.println(ex);
                                }
                            }
                        }
                        ConsoleChaos.wait = true;
                        System.out.println();
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println(creditPoints + " points remaining");
                        System.out.println();
                        System.out.println("reward coins = " + ConsoleChaos.coins);
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println();
                    } else {
                        System.out.println("Not enough points");
                        System.out.println();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                }
                    break;
                case 6: {
                    if (creditPoints > game.get(5).getCreds()) {
                        try {
                            String q1 = "{CALL currentTime()}";
                            CallableStatement cst = ConsoleChaos.con.prepareCall(q1);
                            Timestamp ts = null;
                            ResultSet rs1 = cst.executeQuery();
                            while (rs1.next()) {
                                ts = rs1.getTimestamp(1);
                            }
                            String query = "INSERT INTO quizgamelogs VALUES ('" + playerUsername
                                    + "','" + ts + "'," + game.get(5).getCreds() + ")";
                            Statement state = ConsoleChaos.con.createStatement();
                            state.execute(query);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        creditPoints -= game.get(5).getCreds();
                        QuizGame qg = new QuizGame();
                        try {
                            qg.play(playerUsername);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                        ConsoleChaos.coins += QuizGame.coins;
                        System.out.println();
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println(creditPoints + " points remaining");
                        System.out.println();
                        System.out.println("reward coins = " + ConsoleChaos.coins);
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println();
                    } else {
                        System.out.println("Not enough points");
                        System.out.println();
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                }
                    break;
                default: {
                    System.out.println("No such game available");
                    System.out.println("Choose again");
                    System.out.println();
                }
                    break;
            }
        } catch (SQLException ex) {
            System.out.println("Games under maintenance");
        }
        select s = new select(playerMail);
        ConsoleChaos.creditPoints = creditPoints;
    }
}

class games {
    int id;
    String name;
    int MaxPlayers;
    int creds;

    games(int id, String name, int MaxPlayers, int creds) {
        this.id = id;
        this.name = name;
        this.MaxPlayers = MaxPlayers;
        this.creds = creds;
    }

    @Override
    public String toString() {
        return "Game Number = " + id + " , Game Name = " + name + ", Maximum Players = " + MaxPlayers + ", Credits = "
                + creds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxPlayers() {
        return MaxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        MaxPlayers = maxPlayers;
    }

    public int getCreds() {
        return creds;
    }

    public synchronized void setCreds(int creds) {
        this.creds = creds;
    }
}

class select {
    Scanner input = new Scanner(System.in);

    select(String mail) {
        try {
            selectagain(mail);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void selectagain(String mail) throws Exception {
        Player pl = new Player(mail);
        String cont;
        try {
            System.out.print("Enter yes to continue playing and anything else to exit ");
            // input.nextLine();
            cont = input.next();
            System.out.println();
            System.out.println();
        } catch (NoSuchElementException e) {
            cont = input.next();
        }
        if (cont.equalsIgnoreCase("yes")) {
            if (ConsoleChaos.creditPoints < 300) {
                System.out.println("You do not have enough points to play any game");
            } else {
                pl.playing();
            }
        }
        System.out.println("Points remaining : " + Player.creditPoints);
        System.out.println("reward coins earned : " + ConsoleChaos.coins);
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Thanks for being my guest " + pl.playerUsername);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println();
        System.out.println("Do visit us again");
        String query = "UPDATE login_details SET coinsEarned=?,creditPoints=? WHERE userName=?";
        PreparedStatement pst = ConsoleChaos.con.prepareStatement(query);
        pst.setInt(1, ConsoleChaos.coins);
        pst.setInt(2, Player.creditPoints);
        pst.setString(3, pl.playerUsername);
        pst.executeUpdate();
        try {
            String q123="{CALL insert_gaming_logs(?,?,?,?,?,?)}";
            CallableStatement cst1=ConsoleChaos.con.prepareCall(q123);
            cst1.registerOutParameter(1, java.sql.Types.VARCHAR);
            cst1.registerOutParameter(2, java.sql.Types.VARCHAR);
            cst1.registerOutParameter(3, java.sql.Types.VARCHAR);
            cst1.registerOutParameter(4, java.sql.Types.VARCHAR);
            cst1.registerOutParameter(5, java.sql.Types.VARCHAR);
            cst1.registerOutParameter(6, java.sql.Types.VARCHAR);
            cst1.execute();
        } catch (Exception e) {
            
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.exit(0);
    }
}