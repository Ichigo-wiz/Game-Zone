package GameZone.CrazyCricket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import GameZone.ConsoleChaos.ConsoleChaos;

public class CrazeCricket {
    synchronized public void play(String userName) throws Exception {
        Scanner sc = new Scanner(System.in);
        File f = new File("crazy_log.txt");
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(userName + " playing with Pixel-wiz");
        bw.newLine();
        bw.write(
                "----------------------------------------------------------------------------------------------------------------------------------");
        bw.newLine();
        bw.close();
        System.out.println("Hello ! Welcome to Crazy Cricket");
        Thread.sleep(3000);
        System.out.println();
        System.out.println("I am your gaming assistant Pixel-Wiz :) ");
        Thread.sleep(2000);
        System.out.println("Let me quickly explain you the rules :-");
        Thread.sleep(3000);
        myThread1 mt = new myThread1();
        mt.run();
        System.out.println("Good luck ! I won't go easy on you .");
        Thread.sleep(2000);
        CraCricket cc = new CraCricket();
        cc.run();
        System.out.println("It was nice playing with you !");
        Thread.sleep(2000);
        System.out.println("Hope you had fun .");
    }
}

class CraCricket {
    Scanner sc = new Scanner(System.in);
    static boolean playerWin = false;
    String play;

    public void run() {
        boolean b = true;
        while (b) {
            char choice;
            System.out.println();
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println("Let's do the toss");
            System.out.println();
            System.out.println("Press 1 for Heads");
            System.out.println("Press 2 for Tails");
            System.out.print("Enter your choice - ");
            choice = sc.next().charAt(0);
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            System.out.println();
            if (choice != '1' && choice != '2') {
                System.out.println();
                System.out.println("Please enter 1 or 2");
            } else {
                int t = (int) (Math.random() * 3);
                char toss;
                if (t == 1) {
                    toss = '1';
                } else {
                    toss = '2';
                }
                if (toss == choice) {
                    System.out.println("Congratulations ! You won the toss");
                    boolean b1 = true;
                    while (b1) {
                        System.out.println("Choose (bat or ball) - ");
                        this.play = sc.next();
                        if (play.equalsIgnoreCase("bat") || play.equalsIgnoreCase("ball")) {
                            b1 = false;
                            b = false;
                        } else {
                            System.out.println("Oops! Invalid choice ");
                        }
                    }
                } else {
                    System.out.println("Looks like I just got lucky");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    int ran = (int) (Math.random() * 3);
                    if (ran == 1) {
                        System.out.println("I choose to bat first");
                        play = "ball";
                        b = false;
                    } else {
                        System.out.println("I choose to ball first");
                        play = "bat";
                        b = false;
                    }
                }
                crazyCricket();
            }
        }
    }

    synchronized void crazyCricket() {
        int botRuns;
        int playerRuns;
        if (play.equalsIgnoreCase("bat")) {
            playerRuns = batOpen();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("You gave me a target of " + (playerRuns + 1) + " runs");
            System.out.println("Good luck with balling");
            botRuns = ballTarget(playerRuns + 1);
            if (playerRuns > botRuns) {
                try {
                    System.out.println("Congratulations ! You win .");
                    System.out.println();
                    System.out.println(
                            "**********************************************************************************************************************************************************");
                    System.out.println();
                    File f = new File("crazy_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Pixel-Wiz lost");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                    ConsoleChaos.coins += 200;
                    ConsoleChaos.wait = false;
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            } else if (botRuns > playerRuns) {
                System.out.println("Seems like you are having a bad day , I win but you gave me a nice challenge");
                System.out.println();
                System.out.println(
                        "**********************************************************************************************************************************************************");
                System.out.println();
                try {
                    System.out.println();
                    System.out.println(
                            "**********************************************************************************************************************************************************");
                    System.out.println();
                    File f = new File("crazy_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Pixel-Wiz won");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                    ConsoleChaos.wait = false;
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            } else {
                System.out.println("Seems like we are tied , it was nice playing with you");
                System.out.println();
                System.out.println(
                        "**********************************************************************************************************************************************************");
                System.out.println();
                try {
                    System.out.println();
                    System.out.println(
                            "**********************************************************************************************************************************************************");
                    System.out.println();
                    File f = new File("crazy_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Both tied");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                ConsoleChaos.coins += 50;
                ConsoleChaos.wait = false;
            }
        } else {
            botRuns = ballOpen();
            System.out.println("I gave you a target of " + (botRuns + 1) + " runs");
            System.out.println("Your turn to bat");
            playerRuns = batTarget(botRuns + 1);
            if (playerWin) {
                try {
                    System.out.println("Congratulations ! You win .");
                    System.out.println();
                    System.out.println(
                            "**********************************************************************************************************************************************************");
                    System.out.println();
                    File f = new File("crazy_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Pixel-Wiz lost");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                ConsoleChaos.coins += 200;
                ConsoleChaos.wait = false;
                return;
            } else if (botRuns > playerRuns) {
                System.out.println("Seems like you are having a bad day , I win but you gave me a nice challenge");
                System.out.println();
                System.out.println(
                        "**********************************************************************************************************************************************************");
                System.out.println();
                try {
                    System.out.println();
                    System.out.println(
                            "**********************************************************************************************************************************************************");
                    System.out.println();
                    File f = new File("crazy_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Pixel-Wiz won");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                ConsoleChaos.wait = false;
                return;
            } else {
                System.out.println("Seems like we are tied , it was nice playing with you");
                System.out.println();
                System.out.println(
                        "**********************************************************************************************************************************************************");
                System.out.println();
                try {
                    System.out.println();
                    System.out.println(
                            "**********************************************************************************************************************************************************");
                    System.out.println();
                    File f = new File("crazy_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Both tied");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
                ConsoleChaos.coins += 50;
                ConsoleChaos.wait = false;
                return;
            }
        }
    }

    int batOpen() {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyLinkedList bat = new MyLinkedList();
        Queue ba = new Queue(6);
        int ballcount = 0;
        int overcount = 0;
        while (b2) {
            System.out.println("****************************************************************");
            System.out.println();
            if (overcount == 5) {
                System.out.println("Damn You survived all my balls");
                System.out.println("You are quite skilled");
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                bat.insert(runs);
                b2 = false;
                continue;
            }
            ballcount++;
            System.out.print("Your number - ");
            int n;
            try {
                n = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                System.out.println();
                sc.nextLine();
                continue;
            }
            int ball = (int) (Math.random() * 10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println();
            System.out.println("My number is " + ball);
            if (n > 9 || n < 0) {
                System.out.println("Invalid number");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ballcount--;
                continue;
            } else if ((ball - 1) == n || (ball + 1) == n) {
                System.out.println("Yahoo! You are out");
                System.out.println("You played well but better luck next time");
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                bat.insert(runs);
                b2 = false;
                continue;
            } else if (ball == n) {
                System.out.println("You scored " + (n * n) + " runs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now play the next ball");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ba.Enqueue(n * n);
            } else {
                System.out.println("You scored " + n + " runs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now play the next ball");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ba.Enqueue(n);
            }
            if (ballcount == 6) {
                overcount++;
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                System.out.println("You scored " + runs + " runs in over " + overcount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                bat.insert(runs);
                runs = 0;
                ballcount = 0;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!bat.isEmpty()) {
            totalRuns += bat.delete();
        }
        return totalRuns;
    }

    int ballTarget(int target) {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyLinkedList ball = new MyLinkedList();
        Queue ba = new Queue(6);
        int ballcount = 0;
        int overcount = 0;
        while (b2) {
            System.out.println("****************************************************************");
            System.out.println();
            if (overcount == 5) {
                System.out.println("Look I survived all your balls");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                ball.insert(runs);
                b2 = false;
                continue;
            }
            ballcount++;
            System.out.print("Your number - ");
            int n;
            try {
                n = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                System.out.println();
                sc.nextLine();
                continue;
            }
            int bat = (int) (Math.random() * 10);
            if (n > 9 || n < 0) {
                System.out.println("Invalid number");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ballcount--;
                continue;
            } else if ((bat - 1) == n || (bat + 1) == n) {
                System.out.println("Oops! I am out");
                System.out.println("You played well .");
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                ball.insert(runs);
                b2 = false;
                continue;
            } else if (bat == n) {
                System.out.println("I scored " + (bat * bat) + " runs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now play the next ball");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                target -= bat * bat;
                ba.Enqueue(bat * bat);
            } else {
                System.out.println("I scored " + bat + " runs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now the next ball");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                target -= bat;
                ba.Enqueue(bat);
            }
            if (ballcount == 6) {
                overcount++;
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                System.out.println("I scored " + runs + " runs in over " + overcount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ball.insert(runs);
                runs = 0;
                ballcount = 0;
            }
            if (target <= 0) {
                System.out.println("Let's gooo ! I win ");
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                ball.insert(runs);
                b2 = false;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!ball.isEmpty()) {
            totalRuns += ball.delete();
        }
        return totalRuns;
    }

    int ballOpen() {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyLinkedList ball = new MyLinkedList();
        Queue ba = new Queue(6);
        int ballcount = 0;
        int overcount = 0;
        while (b2) {
            System.out.println("****************************************************************");
            System.out.println();
            if (overcount == 5) {
                System.out.println("Look I survived all your balls");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                ball.insert(runs);
                b2 = false;
                continue;
            }
            ballcount++;
            System.out.print("Your number - ");
            int n;
            try {
                n = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                System.out.println();
                sc.nextLine();
                continue;
            }
            int bat = (int) (Math.random() * 10);
            if (n > 9 || n < 0) {
                System.out.println("Invalid number");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ballcount--;
                continue;
            } else if ((bat - 1) == n || (bat + 1) == n) {
                System.out.println("Oops! I am out");
                System.out.println("You played well .");
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                ball.insert(runs);
                b2 = false;
                continue;
            } else if (bat == n) {
                System.out.println("I scored " + (n * n) + " runs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now the next ball");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ba.Enqueue(n * n);
            } else {
                System.out.println("I scored " + bat + " runs");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now the next ball");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ba.Enqueue(bat);
            }
            if (ballcount == 6) {
                overcount++;
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                System.out.println("I scored " + runs + " runs in over " + overcount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ball.insert(runs);
                runs = 0;
                ballcount = 0;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!ball.isEmpty()) {
            totalRuns += ball.delete();
        }
        return totalRuns;
    }

    int batTarget(int target) {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyLinkedList bat = new MyLinkedList();
        Queue ba = new Queue(6);
        int ballcount = 0;
        int overcount = 0;
        while (b2) {
            System.out.println("****************************************************************");
            System.out.println();
            if (overcount == 5) {
                System.out.println("Damn You survived all my balls");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("You are quite skilled");
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                bat.insert(runs);
                b2 = false;
                continue;
            }
            ballcount++;
            System.out.print("Your number - ");
            int n;
            try {
                n = sc.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number");
                System.out.println();
                sc.nextLine();
                continue;
            }
            int ball = (int) (Math.random() * 10);
            System.out.println("My number is " + ball);
            if (n > 9 || n < 0) {
                System.out.println("Invalid number");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                ballcount--;
                continue;
            } else if ((ball - 1) == n || (ball + 1) == n) {
                System.out.println("Yahoo! You are out");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("You played well but better luck next time");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                bat.insert(runs);
                b2 = false;
                continue;
            } else if (ball == n) {
                System.out.println("You scored " + (n * n) + " runs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now play the next ball");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                target -= n * n;
                ba.Enqueue(n * n);
            } else {
                System.out.println("You scored " + n + " runs");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                System.out.println("Now play the next ball");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                ba.Enqueue(n);
                System.out.println();
                target -= n;
                System.out.println();
            }
            if (ballcount == 6) {
                overcount++;
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                System.out.println("You scored " + runs + " runs in over " + overcount);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                bat.insert(runs);
                runs = 0;
                ballcount = 0;
            }
            if (target <= 0) {
                System.out.println("You defeated me");
                CraCricket.playerWin = true;
                while (!ba.isEmpty()) {
                    runs += ba.Dequeue();
                }
                b2 = false;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!bat.isEmpty()) {
            totalRuns += bat.delete();
        }
        return totalRuns;
    }
}

class MyLinkedList {
    class Node {
        Node next;
        int data;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    synchronized void insert(int data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
        } else {
            n.next = head;
            head = n;
        }
    }

    boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
    }

    int delete() {
        if (head == null) {
            return -1;
        } else {
            int data = head.data;
            head = head.next;
            return data;
        }
    }
}

class myThread1 {
    Scanner sc = new Scanner(System.in);

    public void run() {
        rules();
    }

    synchronized void rules() {
        try {
            System.out.println("__________________________________________________________________________");
            System.out.println();
            System.out.println("--> You are supposed to enter a number between 0 and 9 .");
            System.out.println();
            Thread.sleep(2000);
            System.out.println(
                    "--> When you are batting , the number you choose becomes the run you scored in that ball .");
            System.out
                    .println("--> You get out if I choose a number which preceeds or suceeds the number you chose .");
            System.out.println("--> If we choose the same number , that number's square is added to your total runs .");
            Thread.sleep(10000);
            System.out.println();
            System.out.println("--> When you are balling , you are supposed to guess the number I will choose .");
            System.out.println("--> If you choose a successor or predecessor of my number , I am out .");
            System.out.println();
            Thread.sleep(7000);
            System.out.println("For Example - ");
            Thread.sleep(1000);
            System.out.println("Suppose you are batting , and you play 7");
            System.out.println("--> You will be out if my number is either 6 or 8 .");
            System.out.println();
            System.out.println("--> If I choose 7 at the same time , then you get 49 runs (7*7).");
            System.out.println();
            System.out.println("--> In all other cases , you get 7 runs .");
            System.out.println();
            System.out.println("--> And when you are balling , the same set of rules apply to me .");
            Thread.sleep(10000);
            System.out.println();
            System.out.println("--> Whoever scores more runs in 5 overs wins the match .");
            System.out.println();
            Thread.sleep(2000);
            System.out.println("--> You get 200 coins if you win,50 if it's a tie and 0 if you lose");
            System.out.println("__________________________________________________________________________");
            System.out.println();
            Thread.sleep(2000);
            boolean b = true;
            while (b) {
                System.out.print("Are you ready to play(Enter yes) ? ");
                String s = sc.next();
                System.out.println();
                if (s.equalsIgnoreCase("yes")) {
                    b = false;
                } else {
                    System.out.println("Sorry I did not understand your response");
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class MyStack {
    int ar[];
    int top = -1;
    int size;

    public MyStack(int size) {
        this.size = size;
        this.ar = new int[this.size];
    }

    synchronized void push(int n) {
        if (!isFull()) {
            top++;
            ar[top] = n;
        }

    }

    boolean isFull() {
        return top >= size - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    int pop() {
        if (!isEmpty()) {
            if (top < size) {
                int r = ar[top];
                top--;
                return r;
            }
            return -1;
        }
        return -1;
    }
}

class Queue {
    int front;
    int rear;
    int ar[];
    int size;

    public Queue(int size) {
        this.size = size;
        this.ar = new int[size];
        this.front = -1;
        this.rear = -1;
    }

    synchronized void Enqueue(int n) {
        rear++;
        ar[rear] = n;
        if (front == -1) {
            front++;
        }
    }

    boolean isFull() {
        return rear >= size - 1;
    }

    boolean isEmpty() {
        return rear == -1;
    }

    int Dequeue() {
        if (!isEmpty()) {
            int r = ar[front];
            front++;
            if (front == rear + 1) {
                front = rear = -1;
            }
            return r;
        }
        return -1;
    }
}