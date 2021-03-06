// Example found on page 507 of Head First Java:
// Uses synchronizaiton to lock a methods call 
// to a specific thread till it has finished what
// it started

import java.util.*;

// The Runnable interface requires the run() method
// to implemented which will hold jobs which will be
// executed by the thread.
public class RyanAndMonicaJob implements Runnable{
    
    private BankAccount account = new BankAccount();

    public static void main (String[] args){
        RyanAndMonicaJob theJob = new RyanAndMonicaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName("Ryan");
        two.setName("Monica");
        one.start();
        two.start();
    }

    public void run(){
        for (int x = 0; x < 10; x++){
          makeWithdrawal(10);
          if (account.getBalance() < 0){
            System.out.println("Overdrawn!");
          }
          System.out.println("Account Balance = " + String.valueOf( account.getBalance()));
        }
    }

    private synchronized void makeWithdrawal(int amount){
        if(account.getBalance() >= amount){
            System.out.println(Thread.currentThread().getName() + " is about to withdraw");
              try {
                System.out.println(Thread.currentThread().getName() + " is going to sleep");
                Thread.sleep(500);
              }catch(InterruptedException ex) {ex.printStackTrace();}
              System.out.println(Thread.currentThread().getName() + " woke up.");
              account.withdraw(amount);
              System.out.println(Thread.currentThread().getName() + " completes the withdrawal");
        }else{
            System.out.println("Sorry, not enough for " + Thread.currentThread().getName());
        }
    }
}
