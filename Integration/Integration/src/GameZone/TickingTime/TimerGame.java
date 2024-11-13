package GameZone.TickingTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import GameZone.ConsoleChaos.ConsoleChaos;

public class TimerGame extends Thread {
    static Scanner sc = new Scanner(System.in);
    static int numb;
    public static String playerUsername;

    public void run() {
        ConsoleChaos.wait = true;
        try {
            File f = new File("timer_log.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(playerUsername + " playing");
            bw.newLine();
            bw.write(
                    "----------------------------------------------------------------------------------------------------------------------------------");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true) {
            System.out.print("Enter your number (between 1 - 100 )- ");
            try {
                numb = sc.nextInt();
                if(numb>100 || numb<1){
                    throw new MyOutOfBoundException("Enter number between 1 to 100");
                }
                break;
            } catch (InputMismatchException ie) {
                System.out.println("Please Enter a number");
                System.out.println();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            } catch (MyOutOfBoundException ex) {
                System.out.println(ex);
                System.out.println();
            }
        }
        rules r = new rules();
        r.start();
        try {
            r.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        while (true) {
            System.out.print("Press any key to start the timer - ");
            String s = sc.next();
            if (s != null) {
                break;
            }
        }
        Ticking t = new Ticking();
        t.start();
        System.out.println("*************************************************************************************");
        System.out.print("Press any key to stop the timer - ");
        myThread mt = new myThread();
        mt.start();
        try {
            mt.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        int num = numb;
        int i = Ticking.i;
        if (num == i) {
            System.out.println("Congratulations ! You stopped the timer at " + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            System.out.println("You get 300 coins");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            ConsoleChaos.coins += 50;
        } else if (i == num * 10) {
            try {
                System.out.println("WOW ! You stopped the timer at " + i);
                Thread.sleep(2000);
                System.out.println("You get 100 coins");
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            ConsoleChaos.coins += 100;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } else if (i >= num - 5 && i <= num + 5) {
            try {
                System.out.println("Great ! You stopped the timer at " + i);
                Thread.sleep(2000);
                System.out.println("You get 50 coins");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            ConsoleChaos.coins += 50;
        } else if (i % num == 0) {
            try {
                System.out.println("Nice ! You stopped the timer at " + i);
                Thread.sleep(2000);
                System.out.println("You get 10 coins");
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } else {
            System.out.println("You stopped the timer at " + i);
        }
        ConsoleChaos.wait = false;
    }
}

class rules extends Thread {
    public void run() {
        int num = TimerGame.numb;
        System.out.println("__________________________________________________________________________");
        System.out.println();
        System.out.println("---> Your number is " + num);
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("---> There's a timer that keeps running from 1 to 1000 until you press any key");
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("---> You get 300 coins if you stop at " + num);
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("---> You get 100 coins if you stop at " + (num * 10));
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("---> You get 50 coins if you stop in the range " + (num - 5) + " to " + (num + 5));
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("---> You get 10 coins if you stop at any other factor of " + num);
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        System.out.println("__________________________________________________________________________");
        System.out.println();
    }
}

class Ticking extends Thread {
    int num = TimerGame.numb;
    Scanner sc = new Scanner(System.in);
    static int i = 0;

    public void run() {
        while (true) {
            i++;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (i == 1000) {
                i = 1;
            }
        }
    }
}

class myThread extends Thread {
    Scanner sc = new Scanner(System.in);

    public void run() {
        if (sc.nextLine() != null) {
            synchronized (this) {
                notify();
            }
        }
    }
}

class MyOutOfBoundException extends Exception{
    MyOutOfBoundException(String s){
        super(s);
    }
}