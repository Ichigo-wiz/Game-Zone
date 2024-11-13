package GameZone.Game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuizGame {
  static public int coins=0;
  public void play(String playerUsername) throws Exception {
    Scanner sc = new Scanner(System.in);
    String CompTag = null;
    try {
      File f = new File("Quiz_log.txt");
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
    System.out.println(
        "_________________________________________________________________");
    System.out.println();
    System.out.println("---> This a quiz game with each question having 4 options .");
    Thread.sleep(2000);
    System.out.println();
    System.out.println("---> You have 60 seconds to answer each question");
    Thread.sleep(2000);
    System.out.println();
    System.out.println("---> Each correct answer gets you 50 coins for easy question and 100 coins for hard questions");
    Thread.sleep(2000);
    System.out.println();
    System.out.println("---> You have lifelines for help if you get stuck (press L to activate)");
    Thread.sleep(2000);
    System.out.println();
    System.out.println(
        "_________________________________________________________________");
    System.out.println();
    System.out.println("Press any key to continue");
    String del = sc.next();
    boolean b = true;
    int flag = 0;
    int f = 0;
    while (b) {
      System.out.println("Select Difficulty level");
      System.out.println("::::::::::::::::::::::::");
      System.out.println("Press E for Easy level ");
      System.out.println("Press H for Hard level");
      char ch = sc.next().charAt(0);
      if (ch == 'E' || ch == 'e') {
        b = false;
        new questions(CompTag).easyQuestions();
      } else if (ch == 'H' || ch == 'h') {

        b = false;
        new hard(CompTag).hardquestions();

      }
    }
  }
}

class node {
  int qno;
  int score;
  boolean answer;
  node next;

  node(int qno, int score, boolean answer) {
    this.qno = qno;
    this.answer = answer;
    this.score = score;
  }
}

class valueSet {
  node head;

  void addValue(int q, int s, boolean v) {
    node n = new node(q, s, v);
    if (head == null) {
      head = n;
    } else {
      node temp = head;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = n;
    }
  }

  void display(String TempTag, int flag) throws Exception {
    node temp = head;
    int sum = 0;
    while (temp != null) {
      System.out.println("Question : " + temp.qno + ")" + " Points: " + temp.score + " Status: " + temp.answer);
      sum = sum + temp.score;
      temp = temp.next;
    }
    System.out.println("Total score : " + sum);
    return;
  }
}

class lifeLine {
  Scanner sc = new Scanner(System.in);
  boolean flag1 = true;
  boolean flag2 = true;
  boolean flag3 = true;
  // boolean flag4=true;
  char c[] = { 0, 'b', 'a', 'c', 'b', 'd', 'c', 'b', 'c', 'd', 'a', 'c', 'd', 'c', 'a', 'c', 'd', 'b', 'a', 'c', 'a' };

  boolean chooseLifeline(int qno) {
    if (flag1) {
      System.out.println("Press F to flip the question");
    }
    if (flag2) {
      System.out.println("Press E to ask the expert ");
    }
    if (flag3) {
      System.out.println("Press T for double dip");
    }
    if (flag1 == false && flag2 == false && flag3 == false) {
      System.out.println("You have no LifeLines Remaining");
      System.out.println("Enter your answer");
      int ch = sc.next().charAt(0);
      if (ch == c[qno] || ch == c[qno] - 32) {
        return true;
      } else {
        return false;
      }
    }
    int ch = sc.next().charAt(0);
    if ((ch == 'F' || ch == 'f') && flag1) {
      boolean ans = flip();
      flag1 = false;
      return ans;
    } else if ((ch == 'E' || ch == 'e') && flag2) {
      boolean ans = askExpert(qno);
      flag2 = false;
      return ans;
    } else if ((ch == 'T' || ch == 't') && flag3) {
      boolean ans = doubleDip(qno);
      flag3 = false;
      return ans;
    } else {
      return false;
    }

  }

  boolean flip() {
    int ch;
    boolean b = false;
    while (true) {

      System.out.println("Press 1 for Entertainment and movies");
      System.out.println("Press 2 for History and heritage");
      System.out.println("Press 3 for Nature and Wildlife");
      System.out.println("Press 4 for Science and Technology");
      System.out.println("Press 5 for Sports and games");
      ch = sc.nextInt();
      if (ch == 1 || ch == 2 || ch == 3 || ch == 4 || ch == 5) {
        break;
      }
    }
    int r = (int) (Math.random() * 2) + 1;
    switch (ch) {
      case 1: {
        if (r == 1) {
          System.out.println("Who directed the movie Gangs of Wassepur?");
          System.out.println("A)Karan Johar        B)Anurag Kashyap");
          System.out.println("C)Rohit Shetty       D)Abhishek Pathak");
          char ans = sc.next().charAt(0);
          if (ans == 'B' || ans == 'b') {
            b = true;
          } else {
            b = false;
          }
        } else {
          System.out.println("Which Indian actor-turned politician won the 2017 Filmfare Lifetime award");
          System.out.println("A)Paresh Rawal        B)Vinod Khanna");
          System.out.println("C)Dharmendra          D)Shatrughan Sinha");
          char ans = sc.next().charAt(0);
          if (ans == 'D' || ans == 'd') {
            b = true;
          } else {
            b = false;
          }
        }
        break;
      }
      case 2: {
        if (r == 1) {
          System.out.println("Who was the mongol conqueror who reigned most of asia?");
          System.out.println("A)Gengis Khan        B)Napolean Bonaparte");
          System.out.println("C)Kublai Khan      D)Alexander the great");
          char ans = sc.next().charAt(0);
          if (ans == 'A' || ans == 'a') {
            b = true;
          } else {
            b = false;
          }
        } else {
          System.out.println("What is the heigth of qutub minar?");
          System.out.println("A)75 m            B)8o m");
          System.out.println("C)72.5 m          D)83.4 m");
          char ans = sc.next().charAt(0);
          if (ans == 'C' || ans == 'c') {
            b = true;
          } else {
            b = false;
          }
        }
        break;
      }
      case 3: {
        if (r == 1) {
          System.out.println("Which is the fastest animal in the world in terms of all domain?");
          System.out.println("A)Outback Turtle        B)Peregrine Falcon");
          System.out.println("C)Ostrich               D)Cheetah");
          char ans = sc.next().charAt(0);
          if (ans == 'B' || ans == 'b') {
            b = true;
          } else {
            b = false;
          }
        } else {
          System.out.println("Which is the tallest tree in the world");
          System.out.println("A)Coast Redwood        B)Eastern Sandalwood");
          System.out.println("C)Himalayan Cypress       D)Norther Oakwood");
          char ans = sc.next().charAt(0);
          if (ans == 'A' || ans == 'a') {
            b = true;
          } else {
            b = false;
          }
        }
        break;
      }
      case 4: {
        if (r == 1) {
          System.out.println("What is the name of first Indian Missile ");
          System.out.println("A)Asurastra        B)Prithvi");
          System.out.println("C)Dhanush             D)Agni");
          char ans = sc.next().charAt(0);
          if (ans == 'B' || ans == 'b') {
            b = true;
          } else {
            b = false;
          }
        } else {
          System.out.println("What is the scientific name for humans");
          System.out.println("A)Homo Habilis        B)Homo Sapiens Linnaeus");
          System.out.println("C)Homo Erectus       D)Homo Sapiens Sapiens");
          char ans = sc.next().charAt(0);
          if (ans == 'd' || ans == 'D') {
            b = true;
          } else {
            b = false;
          }
        }
        break;
      }
      case 5: {
        if (r == 1) {
          System.out.println("What position is beside the wicket keeper in cricket?");
          System.out.println("A)Slip               B)Mid off");
          System.out.println("C)Square Leg         D)Cover");
          char ans = sc.next().charAt(0);
          if (ans == 'A' || ans == 'a') {
            b = true;
          } else {
            b = false;
          }
        } else {
          System.out.println("Who is the highest goal scorer of football?");
          System.out.println("A)Zlatan Imbhrimovic        B)Cristiano Ronaldo");
          System.out.println("C)Romario             D)Lionel Messi");
          char ans = sc.next().charAt(0);
          if (ans == 'b' || ans == 'B') {
            b = true;
          } else {
            b = false;
          }
        }
        break;
      }
    }
    return b;
  }

  boolean askExpert(int qno) {
    System.out.println("Please see that the expert answer being correct ");
    System.out.println("has a probability of 8/10 ");
    System.out.println(":::::::::::::");
    int r = (int) (Math.random() * 10) + 1;
    if (r < 2 || qno == 1) {
      System.out.println("The correct answer might be : " + c[qno]);
    } else {
      System.out.println("The correct answer might be : " + c[qno - 1]);
    }
    System.out.println("Enter your choice");
    int ch = sc.next().charAt(0);
    if (ch == c[qno] || ch == c[qno] - 32) {
      return true;
    } else {
      return false;
    }
  }

  boolean doubleDip(int qno) {
    boolean ans = false;
    System.out.println("Enter Your choice");
    int i = 0;
    while (i != 2) {
      int ch = sc.next().charAt(0);
      if (ch == c[qno] || ch == c[qno] - 32) {
        ans = true;
        break;
      }
      if (i == 0) {
        System.out.println("Wrong Answer!");
      }
      i++;
    }
    return ans;
  }
}

class questions extends Thread {
  Scanner sc = new Scanner(System.in);
  String TempTag;

  questions() {
  }

  questions(String TempTag) {
    this.TempTag = TempTag;
  }

  public valueSet vs = new valueSet();
  lifeLine l1 = new lifeLine();

  void easyQuestions() throws Exception {
    questions q = new questions();
    Thread t1 = new Thread(q);
    System.out.println("Good luck! Your time starts now ");
    System.out.println("###############################");
    System.out.println("Question 1");
    System.out.println("::::::::::::::::::::");
    System.out.println("Which city is decided to be common time zone of India?");
    System.out.println("A)Bihar            B)Mirzapur");
    System.out.println("C)Gorakhpur        D)Allahbad");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t1.start();
    char q1 = sc.next().charAt(0);
    if (q1 == 'b' || q1 == 'B') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(1, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(1)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(1, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(1, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(1, 0, false);
    }
    try {
      t1.interrupt();
    } catch (Exception e) {
    }
    Thread t2 = new Thread(q);
    System.out.println("Question 2");
    System.out.println("::::::::::::::::::::");
    System.out.println("Who is the current prime minister of Italy?");
    System.out.println("A)Georgia Meloni     B)Saddam Hussein");
    System.out.println("C)Ivanka Trump       D)Rishi Sunak");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t2.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'a' || q1 == 'A') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(2, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(2)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(2, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(2, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(2, 0, false);
    }
    try {
      t2.interrupt();
    } catch (Exception e) {
    }
    Thread t3 = new Thread(q);
    System.out.println("Question 3");
    System.out.println("::::::::::::::::::::");
    System.out.println("How many bones does a human have?");
    System.out.println("A)208     B)207");
    System.out.println("C)206     D)300");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t3.start();
    q1 = sc.next().charAt(0);
    try {
      t3.interrupt();
    } catch (Exception e) {
    }
    if (q1 == 'c' || q1 == 'C') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(3, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(3)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(3, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(3, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(3, 0, false);
    }
    Thread t4 = new Thread(q);
    System.out.println("Question 4");
    System.out.println("::::::::::::::::::::");
    System.out.println("What does not grow on tree in regard to the HINDI saying?");
    System.out.println("A)Money     B)Paise");
    System.out.println("C)Leaves   D)Roots");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t4.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'B' || q1 == 'b') {
      System.out.println("Correct Answer!");
      QuizGame.coins += 50;
      System.out.println("::::::::::::::::::::");
      vs.addValue(4, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(4)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(4, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(4, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(4, 0, false);
    }
    try {
      t4.interrupt();
    } catch (Exception e) {
    }
    Thread t5 = new Thread(q);
    System.out.println("Question 5");
    System.out.println("::::::::::::::::::::");
    System.out.println("Who wrote India's National anthem?");
    System.out.println("A)Chetan Bhagat         B)Udit Narayan");
    System.out.println("C)Lal Bahadur Shastri   D) Rabindranath Tagore");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t5.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'D' || q1 == 'd') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(5, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(5)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(5, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is D");
        System.out.println("::::::::::::::::::::");
        vs.addValue(5, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is D");
      System.out.println("::::::::::::::::::::");
      vs.addValue(5, 0, false);
    }
    try {
      t5.interrupt();
    } catch (Exception e) {
    }
    Thread t6 = new Thread(q);
    System.out.println("Question 6");
    System.out.println("::::::::::::::::::::");
    System.out.println("What is the longest bone on the human body?");
    System.out.println("A)Clavicle        B)Patella");
    System.out.println("C)Femur           D)Cranium");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t6.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'C' || q1 == 'c') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(6, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(6)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(6, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(6, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(6, 0, false);
    }
    try {
      t6.interrupt();
    } catch (Exception e) {
    }
    Thread t7 = new Thread(q);
    System.out.println("Question 7");
    System.out.println("::::::::::::::::::::");
    System.out.println("Who was the first woman to win a nobel prize?");
    System.out.println("A)Shakira                     B)Marie Curie");
    System.out.println("C)Dr Annie Fitzgerald         D)Estora Grubusky");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t7.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'B' || q1 == 'b') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(7, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(7)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(7, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(7, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(7, 0, false);
    }
    try {
      t7.interrupt();
    } catch (Exception e) {
    }
    Thread t8 = new Thread(q);
    System.out.println("Question 8");
    System.out.println("::::::::::::::::::::");
    System.out.println("Which country had Chris Columbus searching for?");
    System.out.println("A)America        B)Brazil");
    System.out.println("C)India           D)Myanmar");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t8.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'C' || q1 == 'c') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(8, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(8)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(8, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(8, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(8, 0, false);
    }
    try {
      t8.interrupt();
    } catch (Exception e) {
    }
    Thread t9 = new Thread(q);
    System.out.println("Question 9");
    System.out.println("::::::::::::::::::::");
    System.out.println("What is the national bird of the USA?");
    System.out.println("A)Sparrow       B)Falcon");
    System.out.println("C)Eagle         D)The bald eagle");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t9.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'D' || q1 == 'd') {
      System.out.println("Correct Answer!");
      vs.addValue(9, 50, true);
      QuizGame.coins += 50;
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(9)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(9, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is D");
        System.out.println("::::::::::::::::::::");
        vs.addValue(9, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is D");
      System.out.println("::::::::::::::::::::");
      vs.addValue(9, 0, false);
    }
    try {
      t9.interrupt();
    } catch (Exception e) {
    }
    Thread t10 = new Thread(q);
    System.out.println("Question 10");
    System.out.println("::::::::::::::::::::");
    System.out.println("What is the capital of the country where Eiffel Tower is located?");
    System.out.println("A)Paris       B)Hungary");
    System.out.println("C)Budapest    D)Beijing");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    t10.start();
    q1 = sc.next().charAt(0);
    if (q1 == 'A' || q1 == 'a') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(10, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(10)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(10, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(10, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(10, 0, false);
    }
    try {
      t10.interrupt();
    } catch (Exception e) {
    }
    System.out.println("Question 11");
    System.out.println("::::::::::::::::::::");
    System.out.println("What is the largest ocean on Earth?");
    System.out.println("A)Atlantic Ocean       B)Indian Ocean");
    System.out.println("C)Pacific Ocean        D)Arctic Ocean");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'C' || q1 == 'c') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(11, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(11)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(11, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(11, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(11, 0, false);
    }
    System.out.println("Question 12");
    System.out.println("::::::::::::::::::::");
    System.out.println("What is the hardest natural substance on Earth");
    System.out.println("A)Sillicon Carbide      B)Tungsten");
    System.out.println("C)Graphene              D)Diamond");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'D' || q1 == 'd') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(12, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(12)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(12, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is D");
        System.out.println("::::::::::::::::::::");
        vs.addValue(12, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is D");
      System.out.println("::::::::::::::::::::");
      vs.addValue(12, 0, false);
    }
    System.out.println("Question 13");
    System.out.println("::::::::::::::::::::");
    System.out.println("What is the speed of light?");
    System.out.println("A)30,000 km/s      B)30,00,000 m/s");
    System.out.println("C)300,000 km/s     D)300,000 m/s");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'C' || q1 == 'c') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(13, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(13)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(13, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(13, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(13, 0, false);
    }
    System.out.println("Question 14");
    System.out.println("::::::::::::::::::::");
    System.out.println("Which city is know as Pink City of India? ");
    System.out.println("A)Jaipur       B)Kochi");
    System.out.println("C)Udaipur      D)Kolkata ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'A' || q1 == 'a') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      vs.addValue(14, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(14)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(14, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(14, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(14, 0, false);
    }
    try {

      File f = new File("image.png");
      int length = (int) f.length();
      byte b[] = new byte[length];
      FileInputStream fis = new FileInputStream(f);
      File f2 = new File("Source.png");
      f2.createNewFile();
      FileOutputStream fos = new FileOutputStream(f2);
      fis.read(b);
      fos.write(b);
    } catch (Exception e) {
    }
    System.out.println("Question 15");
    System.out.println("::::::::::::::::::::");
    System.out.println("IMAGE BASED");
    System.out.println("Who is the actor in the picture who played this Legendary role in The Dark Knight?");
    System.out.println("A)Joaquin Phoenix   B)Dhanush");
    System.out.println("C)Heath Ledger      D)Christian Bale ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'C' || q1 == 'c') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(15, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(15)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(15, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is c");
        System.out.println("::::::::::::::::::::");
        vs.addValue(15, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(15, 0, false);
    }
    System.out.println("Question 16");
    System.out.println("::::::::::::::::::::");
    System.out.println("Which country gave USA the Statue Of Liberty?");
    System.out.println("A)Italy           B)Germany");
    System.out.println("C)Russia          D)France ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'D' || q1 == 'd') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(16, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(16)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(16, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is D");
        System.out.println("::::::::::::::::::::");
        vs.addValue(16, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is D");
      System.out.println("::::::::::::::::::::");
      vs.addValue(16, 0, false);
    }
    System.out.println("Question 17");
    System.out.println("::::::::::::::::::::");
    System.out.println("Which wall is visible from space?");
    System.out.println("A)Great Wall of Mongol       B)Great Wall of China");
    System.out.println("C)Mexico-US Border Wall      D)Trojan Wall ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'B' || q1 == 'b') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(17, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(17)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(17, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(17, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(17, 0, false);
    }
    System.out.println("Question 18");
    System.out.println("::::::::::::::::::::");
    System.out.println("Which country won the First Cricket World cup in 1975?");
    System.out.println("A)West Indies       B)Australia");
    System.out.println("C)India             D)South Africa ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'A' || q1 == 'a') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(18, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(18)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(18, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(18, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(18, 0, false);
    }
    System.out.println("Question 19");
    System.out.println("::::::::::::::::::::");
    System.out.println("Who is known as the King of Pop?");
    System.out.println("A)Elvis Presly         B)Queen");
    System.out.println("C)Michael Jackson      D)Freddie Mercury ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'C' || q1 == 'c') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      vs.addValue(19, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(19)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(19, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(19, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(19, 0, false);
    }
    System.out.println("Question 20");
    System.out.println("::::::::::::::::::::");
    System.out.println("In which sport would you perform a 3-pointer?");
    System.out.println("A)Basketball       B)Rugby");
    System.out.println("C)Football         D)Ice Hockey ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'A' || q1 == 'a') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 50;
      vs.addValue(20, 50, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (l1.chooseLifeline(20)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 50;
        vs.addValue(20, 50, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(20, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(20, 0, false);
    }
    vs.display(TempTag, 1);
  }

  public void run() {
    try {
      sleep(30000);
      System.out.println("30 seconds remaining");
      sleep(30000);
      System.out.println("You lost the game!");
      System.exit(0);
    } catch (Exception e) {
    }
  }
}

class hard {
  Scanner sc = new Scanner(System.in);
  String temptag;
  valueSet vs = new valueSet();
  hardlines h1 = new hardlines();

  hard(String temptag) {
    this.temptag = temptag;
  }

  void hardquestions() throws Exception {
    System.out.println("Good luck!");
    System.out.println("###############################");
    System.out.println("Question 1");
    System.out.println("::::::::::::::::::::");
    System.out.println("Announced in Sept 2021,the AUKUS is a trilateral Security pact among which countries?");
    System.out.println("A)Austria,Uganda,Kenya      B)UK,USA,Australia");
    System.out.println("C)Australia,UK,Japan        D)Austria,Ukraine,USA");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    char q1 = sc.next().charAt(0);
    if (q1 == 'b' || q1 == 'B') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(1, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(1)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(1, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(1, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(1, 0, false);
    }
    System.out.println("Question 2");
    System.out.println("::::::::::::::::::::");
    System.out.println("In 2021, Anita Anand took charge as the Minister of national defence of which country?");
    System.out.println("A)Canada      B)Norway");
    System.out.println("C)UK          D)USA ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'A' || q1 == 'a') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(2, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(2)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(2, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(2, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(2, 0, false);
    }
    System.out.println("Question 3");
    System.out.println("::::::::::::::::::::");
    System.out.println("Where are the WHO(World Health Organization) headquarters located?");
    System.out.println("A)Paris,France        B)Brussels,Belgium");
    System.out.println("C)Geneva,Switzerland  D)Austin,Texas ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'C' || q1 == 'c') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(3, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(3)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(3, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(3, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(3, 0, false);
    }
    System.out.println("Question 4");
    System.out.println("::::::::::::::::::::");
    System.out.println("Who set the fastest lap record in the Monaco Grand Prix in 2024?");
    System.out.println("A)Max Verstappen        B)Fernando Alonso");
    System.out.println("C)George Russell        D)Lewis Hamilton ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'D' || q1 == 'd') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(4, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(4)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(4, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is D");
        System.out.println("::::::::::::::::::::");
        vs.addValue(4, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is D");
      System.out.println("::::::::::::::::::::");
      vs.addValue(4, 0, false);
    }
    System.out.println("Question 5");
    System.out.println("::::::::::::::::::::");
    System.out
        .println("What is unique to the nameing of two elements in periodic table with atomic numbers 96 and 109?");
    System.out.println("A)Named after Nobel Laureattes           B)Named after women Scientists");
    System.out.println("C)Named after Indian Scientists          D)They are unnamed ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'B' || q1 == 'b') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(5, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(5)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(5, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(5, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(5, 0, false);
    }
    System.out.println("Question 6");
    System.out.println("::::::::::::::::::::");
    System.out.println("From which country that has a well known football team did Brazil gain its Independence?");
    System.out.println("A)Spain                        B)Portugal");
    System.out.println("C)Argentina                    D)Italy ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'B' || q1 == 'b') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(6, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(6)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(6, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(6, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(6, 0, false);
    }
    System.out.println("Question 7");
    System.out.println("::::::::::::::::::::");
    System.out.println("Which country recieved a nobel prize in Peace category in 2022?");
    System.out.println("A)Ukraine                     B)Iceland");
    System.out.println("C)USA                         D)Switzerland ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'A' || q1 == 'a') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(7, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(7)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(7, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(7, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(7, 0, false);
    }
    System.out.println("Question 8");
    System.out.println("::::::::::::::::::::");
    System.out.println("Who patented RDX in 1898 and was first used in World war II?");
    System.out.println("A)Steve Wozniak                              B)Arthur J Morgan");
    System.out.println("C)George Friedrich Henning                   D)Albrecht Schneider ");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'c' || q1 == 'C') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(8, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(8)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(8, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is C");
        System.out.println("::::::::::::::::::::");
        vs.addValue(8, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is C");
      System.out.println("::::::::::::::::::::");
      vs.addValue(8, 0, false);
    }
    System.out.println("Question 9");
    System.out.println("::::::::::::::::::::");
    System.out.println("Who found the planet Uranus?");
    System.out.println("A)Galileo Galilei               B)William Herschel");
    System.out.println("C)Augean Heracle                D)Kramer Kravitz");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'B' || q1 == 'b') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(9, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(9)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(9, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is B");
        System.out.println("::::::::::::::::::::");
        vs.addValue(9, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is B");
      System.out.println("::::::::::::::::::::");
      vs.addValue(9, 0, false);
    }
    System.out.println("Question 10");
    System.out.println("::::::::::::::::::::");
    System.out.println(
        "The news of which of these was announced in papers in London the day of corantion of queen Elizabeth II?");
    System.out.println("A)Summiting of Mount Everest                   B)Moon Landing");
    System.out.println("C)Avalanche in Alps killing People             D)Launch of Sputnik");
    System.out.println("***********************");
    System.out.println("Press L to see availaible lifelines ");
    q1 = sc.next().charAt(0);
    if (q1 == 'A' || q1 == 'a') {
      System.out.println("Correct Answer!");
      System.out.println("::::::::::::::::::::");
      QuizGame.coins += 100;
      vs.addValue(10, 200, true);
    } else if (q1 == 'L' || q1 == 'l') {
      if (h1.choose(10)) {
        System.out.println("Correct Answer!");
        System.out.println("::::::::::::::::::::");
        QuizGame.coins += 100;
        vs.addValue(10, 200, true);
      } else {
        System.out.println("Wrong Answer!");
        System.out.println("The correct answer is A");
        System.out.println("::::::::::::::::::::");
        vs.addValue(10, 0, false);
      }
    } else {
      System.out.println("Wrong Answer!");
      System.out.println("The correct answer is A");
      System.out.println("::::::::::::::::::::");
      vs.addValue(10, 0, false);
    }
    vs.display(temptag, 2);
  }
}

class hardlines {
  Scanner sc = new Scanner(System.in);
  boolean flag1 = true;
  boolean flag2 = true;
  // boolean flag3=true;
  char c[] = { 0, 'b', 'a', 'c', 'd', 'b', 'b', 'a', 'c', 'b', 'a' };

  boolean choose(int qno) {
    if (flag1) {
      System.out.println("Press H to get Hints");
    }
    if (flag2) {
      System.out.println("Press T for double dip");
    }
    if (flag1 == false && flag2 == false) {
      System.out.println("You have no LifeLines Remaining");
      System.out.println("Enter your answer");
      int ch = sc.next().charAt(0);
      if (ch == c[qno] || ch == c[qno] - 32) {
        return true;
      } else {
        return false;
      }
    }
    int ch = sc.next().charAt(0);
    if ((ch == 'H' || ch == 'h') && flag1) {
      boolean hints = displayHints(qno);
      // flag1=false;
      return hints;
    } else if ((ch == 'T' || ch == 't') && flag2) {
      boolean ans = hDip(qno);
      // flag2=false;
      return ans;
    } else {
      return false;
    }
  }

  boolean displayHints(int qno) {
    ArrayList<String> a1 = new ArrayList<>();
    a1.add("Hints");
    a1.add("One of these countries is also a continent");
    a1.add("A stereotype is that people of this country are very polite and a lot of Indians are immigrating here");
    a1.add("The chocolate Toblerone Originated from the country in question");
    a1.add("This racer drives for Mercedes");
    a1.add("Atomic No 96-Curium, Atomic No 109-Meitnerium");
    a1.add("Ronaldo plays for this country");
    a1.add("This country is/was at a war with its neighbouring country");
    a1.add("This person is german");
    a1.add("This person founded Uranus in 1781 and he made his own telescopes");
    a1.add("Something to do with mountains");
    System.out.println(a1.get(qno));
    System.out.println(":::::::::::::::::");
    System.out.println("Enter your choice");
    char ch = sc.next().charAt(0);
    if (ch == c[qno] || ch == c[qno] - 32) {
      return true;
    } else {
      return false;
    }
  }

  boolean hDip(int qno) {
    boolean ans = false;
    System.out.println("Enter Your choice");
    int i = 0;
    while (i != 2) {
      int ch = sc.next().charAt(0);
      if (ch == c[qno] || ch == c[qno] - 32) {
        ans = true;
        break;
      }
      if (i == 0) {
        System.out.println("Wrong Answer!");
      }
      i++;
    }
    return ans;
  }
}