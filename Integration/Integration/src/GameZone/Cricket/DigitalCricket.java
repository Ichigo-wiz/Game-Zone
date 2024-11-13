package GameZone.Cricket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import GameZone.ConsoleChaos.ConsoleChaos;

public class DigitalCricket {
    synchronized public void play(String userName) throws Exception {
        Scanner sc = new Scanner(System.in);
        File f = new File("cricket_log.txt");
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(userName + " playing with Pixel-wiz");
        bw.newLine();
        bw.write(
                "----------------------------------------------------------------------------------------------------------------------------------");
        bw.newLine();
        bw.close();
        System.out.println("Hello ! Welcome to digicricket");
        Thread.sleep(3000);
        System.out.println();
        System.out.println("I am your gaming assistant Pixel-Wiz :)");
        Thread.sleep(2000);
        System.out.println();
        System.out.println("Let me quickly explain you the rules :-");
        System.out.println();
        Thread.sleep(3000);
        myThread mt = new myThread();
        mt.run();
        System.out.println("Good luck ! I won't go easy on you .");
        Thread.sleep(2000);
        DigiCricket dc = new DigiCricket();
        dc.run();
        System.out.println("It was nice playing with you !");
        Thread.sleep(2000);
        System.out.println("Hope you had fun .");
    }
}

class DigiCricket{
    Scanner sc = new Scanner(System.in);
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
                int t = (int) (Math.random() * 2);
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
                    int ran = (int) (Math.random() * 2);
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
                normalCricket();
            }
        }
    }

    synchronized void normalCricket() {
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
                    File f = new File("cricket_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Pixel-Wiz lost");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                    ConsoleChaos.coins += 300;
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
                    File f = new File("cricket_log.txt");
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
                    File f = new File("cricket_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Both tied");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                    ConsoleChaos.wait = false;
                    ConsoleChaos.coins += 100;
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        } else {
            botRuns = ballOpen();
            System.out.println();
            System.out.println("I gave you a target of " + (botRuns + 1) + " runs");
            System.out.println();
            System.out.println("Your turn to bat");
            System.out.println();
            playerRuns = batTarget(botRuns + 1);
            if (playerRuns > botRuns) {
                try {
                    System.out.println("Congratulations ! You win .");
                    System.out.println();
                    System.out.println(
                            "**********************************************************************************************************************************************************");
                    System.out.println();
                    File f = new File("cricket_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Pixel-Wiz lost");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                    ConsoleChaos.coins += 300;
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
                    File f = new File("cricket_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Pixel-Wiz won");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                    ConsoleChaos.wait = false;
                    return;
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
                    File f = new File("cricket_log.txt");
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("Both tied");
                    bw.newLine();
                    bw.write(
                            "**********************************************************************************************************************************************************");
                    bw.newLine();
                    bw.close();
                    ConsoleChaos.coins += 100;
                    ConsoleChaos.wait = false;
                    return;
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
        return;
    }

    int batOpen() {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyStack bat = new MyStack(6);
        Queue ba = new Queue(5);
        int ballcount = 0;
        int overcount = 0;
        boolean noBall = false;
        int extraRuns = 0;
        while (b2) {
            System.out.println("****************************************************************");
            System.out.println();
            if (overcount == 5) {
                System.out.println("Damn You survived all my balls");
                System.out.println();
                System.out.println("You are quite skilled");
                while (!bat.isEmpty()) {
                    runs += bat.pop();
                }
                ba.Enqueue(runs);
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
            int ball = (int) (Math.random() * 8);
            System.out.println("My number is " + ball);
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println();
            if (n > 6 || n < 1) {
                System.out.println("Invalid number");
                ballcount--;
                continue;
            } else if (noBall) {
                if (ball != n) {
                    System.out.println("You scored " + n + " runs in the free hit");
                    System.out.println();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    extraRuns += n;
                } else {
                    System.out.println("You are safe");
                }
                noBall = false;
            } else if (ball == n) {
                System.out.println("Yahoo! You are out");
                System.out.println();
                System.out.println("You played well but better luck next time");
                while (!bat.isEmpty()) {
                    runs += bat.pop();
                }
                ba.Enqueue(runs);
                b2 = false;
                continue;
            } else if (ball == 0) {
                System.out.println("Oops it's a wide");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You get an extra run");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                ballcount--;
                extraRuns += 1;
            } else if (ball == 7) {
                System.out.println("Ah! it's a no ball ");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You get a free hit");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                extraRuns += 1;
                ballcount--;
                noBall = true;
                continue;
            } else {
                System.out.println("You scored " + n + " runs");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("Now play the next ball");
                System.out.println();
                bat.push(n);
            }
            if (ballcount == 6) {
                overcount++;
                while (!bat.isEmpty()) {
                    runs += bat.pop();
                }
                System.out.println("You scored " + runs + " runs in over " + overcount);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                ba.Enqueue(runs);
                runs = 0;
                ballcount = 0;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!ba.isEmpty()) {
            totalRuns += ba.Dequeue();
        }
        totalRuns += extraRuns;
        return totalRuns;
    }

    int ballTarget(int target) {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyStack ball = new MyStack(6);
        Queue ba = new Queue(5);
        int ballcount = 0;
        int overcount = 0;
        while (b2) {
            System.out.println("****************************************************************");
            System.out.println();
            if (overcount == 5) {
                System.out.println("Look I survived all your balls");
                while (!ball.isEmpty()) {
                    runs += ball.pop();
                }
                ba.Enqueue(runs);
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
            int bat = (int) (Math.random() * 6);
            bat += 1;
            if (n > 6 || n < 1) {
                System.out.println("Invalid number");
                ballcount--;
                continue;
            } else if (bat == n) {
                System.out.println("Oops! I am out");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You played well .");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                while (!ball.isEmpty()) {
                    runs += ball.pop();
                }
                ba.Enqueue(runs);
                b2 = false;
                continue;
            } else {
                System.out.println("I scored " + bat + " runs");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("Now the next ball");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                target -= bat;
                ball.push(bat);
            }
            if (ballcount == 6) {
                overcount++;
                while (!ball.isEmpty()) {
                    runs += ball.pop();
                }
                System.out.println("I scored " + runs + " runs in " + overcount + " overs");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                ba.Enqueue(runs);
                runs = 0;
                ballcount = 0;
            }
            if (target <= 0) {
                System.out.println("Let's gooo ! I win ");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                while (!ball.isEmpty()) {
                    runs += ball.pop();
                }
                ba.Enqueue(runs);
                b2 = false;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!ba.isEmpty()) {
            totalRuns += ba.Dequeue();
        }
        return totalRuns;
    }

    int ballOpen() {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyStack ball = new MyStack(6);
        Queue ba = new Queue(5);
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
                while (!ball.isEmpty()) {
                    runs += ball.pop();
                }
                ba.Enqueue(runs);
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
            int bat = (int) (Math.random() * 6);
            bat += 1;
            if (n > 6 || n < 1) {
                System.out.println("Invalid number");
                ballcount--;
                continue;
            } else if (bat == n) {
                System.out.println("Oops! I am out");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You played well .");
                while (!ball.isEmpty()) {
                    runs += ball.pop();
                }
                ba.Enqueue(runs);
                b2 = false;
                continue;
            } else {
                System.out.println("I scored " + bat + " runs");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("Now the next ball");
                System.out.println();
                ball.push(bat);
            }
            if (ballcount == 6) {
                overcount++;
                while (!ball.isEmpty()) {
                    runs += ball.pop();
                }
                System.out.println("I scored " + runs + " runs in over " + overcount);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                ba.Enqueue(runs);
                runs = 0;
                ballcount = 0;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!ba.isEmpty()) {
            totalRuns += ba.Dequeue();
        }
        return totalRuns;
    }

    int batTarget(int target) {
        int runs = 0;
        int totalRuns = 0;
        boolean b2 = true;
        MyStack bat = new MyStack(6);
        Queue ba = new Queue(5);
        int ballcount = 0;
        int overcount = 0;
        boolean noBall = false;
        int extraRuns = 0;
        while (b2) {
            System.out.println("****************************************************************");
            System.out.println();
            if (overcount == 5) {
                System.out.println("Damn You survived all my balls");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You are quite skilled");
                while (!bat.isEmpty()) {
                    runs += bat.pop();
                }
                ba.Enqueue(runs);
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
            int ball = (int) (Math.random() * 8);
            System.out.println("My number is " + ball);
            if (n > 6 || n < 1) {
                System.out.println("Invalid number");
                ballcount--;
                continue;
            } else if (noBall) {
                if (ball != n) {
                    System.out.println("You scored " + n + " runs in the free hit");
                    System.out.println();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    extraRuns += n;
                } else {
                    System.out.println("You are safe");
                    System.out.println();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    System.out.println();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
                noBall = false;
            } else if (ball == n) {
                System.out.println("Yahoo! You are out");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You played well but better luck next time");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                while (!bat.isEmpty()) {
                    runs += bat.pop();
                }
                ba.Enqueue(runs);
                b2 = false;
                continue;
            } else if (ball == 0) {
                System.out.println("Oops it's a wide");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You get an extra run");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                ballcount--;
                extraRuns += 1;
            } else if (ball == 7) {
                System.out.println("Ah! it's a no ball ");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("You get a free hit");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                extraRuns += 1;
                ballcount--;
                noBall = true;
                continue;
            } else {
                System.out.println("You scored " + n + " runs");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println("Now play the next ball");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                target -= n;
                System.out.println();
                bat.push(n);
            }
            if (ballcount == 6) {
                overcount++;
                while (!bat.isEmpty()) {
                    runs += bat.pop();
                }
                System.out.println("You scored " + runs + " runs in over " + overcount);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                ba.Enqueue(runs);
                runs = 0;
                ballcount = 0;
            }
            if (target <= 0) {
                System.out.println("You defeated me");
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                while (!bat.isEmpty()) {
                    runs += bat.pop();
                }
                ba.Enqueue(runs);
                b2 = false;
            }
            System.out.println("****************************************************************");
            System.out.println();
        }
        while (!ba.isEmpty()) {
            totalRuns += ba.Dequeue();
        }
        totalRuns += extraRuns;
        return totalRuns;
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

class myThread {
    Scanner sc = new Scanner(System.in);

    public void run() {
        rules();
    }

    synchronized void rules() {
        try {
            System.out.println("__________________________________________________________________________");
            System.out.println();
            System.out.println("--> You are supposed to enter a number between 1 and 6 .");
            System.out.println();
            Thread.sleep(2000);
            System.out.println(
                    "--> When you are batting , the number you choose becomes the run you scored in that ball .");
            System.out.println("--> You get out if the number you entered is same as the number I choose .");
            Thread.sleep(8000);
            System.out.println();
            System.out.println("--> When you are balling , you are supposed to guess the number I will choose .");
            System.out.println("--> If we choose the same number , I am out .");
            System.out.println();
            Thread.sleep(7000);
            System.out.println("--> Whoever scores more runs in 5 overs wins the match .");
            System.out.println();
            Thread.sleep(2000);
            System.out.println("--> You get 300 coins if you win,100 if it's a tie and 0 if you lose");
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