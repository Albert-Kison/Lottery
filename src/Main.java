import java.util.Random;
import java.util.Scanner;

/**
 * A lottery game in java
 * Firstly the program generates the winning numbers between 1 and 99
 * and prints out their sum
 *
 * As a bonus game the program generates four prize sums
 * which the user can win in addition to the lottery winning.
 * If the same sum appears more than once, the user wins that sum
 *
 * Then the user is asked to enter 5 numbers,
 * which are not supposed to be the same,
 * and try to guess the winning sum of all 10 numbers
 *
 * Then the program analyzes how much the user won
 *
 * First way
 * The program checks how many numbers
 * out of the winning 10 the user guessed correctly.
 * One is €1000, two is €6000, three is €25,000,
 * four is €100,000 and five is €800,000.
 * The order is not important.
 *
 * Second way
 * The program checks if the user guessed any sequences
 * A sequence must include two or more numbers and must not have gaps
 * A two-number sequence wins €70,000
 * A three-number sequence is €400,000
 * A four-number sequence is €1,000,000
 * A five-number sequence is €5,000,000
 *
 * Third way
 * If the user correctly guessed the sum of all winning numbers,
 * they win €300,000
 */

public class Main {
    public static void main(String[] args) {

        //Task 1
        //finished

        Random random = new Random();

        int[] winningNumbers = new int[10];

        //the sum
        int sum = 0;

        //the range
        int maxBound = 99;
        int minBound = 1;

        //generates the winning numbers
        int i = 0;
        while (i < winningNumbers.length) {
            boolean isRepeat = false;

            winningNumbers[i] = random.nextInt(maxBound) + minBound;

            //checks if the number is the same
            for (int j = 0; j < i; j++) {
                if (winningNumbers[j] == winningNumbers[i]) {
                    isRepeat = true;
                    break;
                }
            }

            if (!isRepeat) {
                //output
                if (winningNumbers[i] < 10) {
                    System.out.print("0" + winningNumbers[i] + "  ");
                } else {
                    System.out.print(winningNumbers[i] + "  ");
                }

                sum += winningNumbers[i];
                i++;
            }
        }

        System.out.println();       //new line
        System.out.println("The sum = " + sum);    //prints the sum



        //-----------------------------------------------------------------------------------------------------------------------------
        //Task 2
        //finished

        //four prize sums
        int prizesumsNumber = 4;
        int[] prizeSums = new int[prizesumsNumber];

        //generates random sums
        for (i = 0; i < prizeSums.length; i++) {
            int chance = random.nextInt(100) + 1;

            //2 percents is 1000 euros
            //6 percents is 200 euros
            //15 percents is 50 euros
            //27 percents is 20 euros
            //50 percents is 10 euros
            if (chance <= 2) {
                prizeSums[i] = 1000;
            } else if (chance <= 8) {
                prizeSums[i] = 200;
            } else if (chance <= 23) {
                prizeSums[i] = 50;
            } else if (chance <= 50) {
                prizeSums[i] = 200;
            } else prizeSums[i] = 10;

            System.out.print(prizeSums[i] + "   ");   //output

        }
        System.out.println();   //new line

        int winSum = 0;
        int[] usedIndexes = new int[prizesumsNumber];   //stores a used index

        //looks for the same numbers
        for (i = 0; i < prizeSums.length - 1; i++) {
            //checks if the index has already been used
            if (usedIndexes[i] == 0) {
                for (int j = i + 1; j < prizeSums.length; j++) {
                    if (prizeSums[i] == prizeSums[j]) {
                        usedIndexes[j] = j;
                        winSum += prizeSums[j];
                        break;
                    }
                }
            }
        }



        //-----------------------------------------------------------------------------------------------------------------------------
        //Task 3
        //finished

        //The user is asked to enter 5 numbers

        Scanner in = new Scanner(System.in);
        int[] numbersUser = new int[5];

        i = 0;
        while (i < numbersUser.length) {
            boolean isRepeat = false;

            System.out.println("Please enter the number " + (i + 1) + ": ");
            numbersUser[i] = in.nextInt();

            //checks if the number is the same
            for (int j = 0; j < i; j++) {
                if (numbersUser[j] == numbersUser[i]) {
                    isRepeat = true;
                    break;
                }
            }

            if (!isRepeat) {
                i++;
            } else {
                System.out.println("It already exists, try again...");
            }
        }

        //The user is asked to guess the sum
        System.out.println("Try to guess the sum of all 10 winning numbers: ");
        int sumUser = in.nextInt();



        //-----------------------------------------------------------------------------------------------------------------------------
        //Task 4
        //finished

        //checks how many numbers out of the winning 10 the user guessed correctly
        int k = 0;  //counts the same numbers
        int[] guessedNumbers = new int[5];     //stores the guessed numbers
        for (i = 0; i < numbersUser.length; i++) {
            for (int j = 0; j < winningNumbers.length; j++) {
                if (numbersUser[i] == winningNumbers[j]) {
                    guessedNumbers[k] = winningNumbers[j];
                    k++;
                }
            }
        }

        int win1 = 0;    //the result
        switch (k) {
            case 1:
                win1 = 1000;
                break;
            case 2:
                win1 = 6000;
                break;
            case 3:
                win1 = 25000;
                break;
            case 4:
                win1 = 100000;
                break;
            case 5:
                win1 = 800000;
                break;
        }



        //-----------------------------------------------------------------------------------------------------------------------------
        //Task 5
        //finished

        k = 0;
        int numberSequence = 0;   //stores the number of elements in a sequence
        int numberSequenceMax = 0;
        int lastIndex = 0;

        //compares the elements of the 2 arrays
        for (i = 0; i < numbersUser.length; i++) {
            for (int j = k; j < winningNumbers.length; j++) {

                if (numbersUser[i] == winningNumbers[j]) {
                    numberSequence++;
                    k = j + 1;

                    //a sequence must include 2 or more numbers
                    if (numberSequence >= 2) {
                        if (numberSequence > numberSequenceMax) {
                            numberSequenceMax = numberSequence;
                            lastIndex = j;
                        }
                    }
                    break;
                } else {
                    k = 0;
                    numberSequence = 0;
                }
            }
        }


        int win2 = 0;
        switch (numberSequenceMax) {
            case 2:
                win2 = 70000;
                break;
            case 3:
                win2 = 400000;
                break;
            case 4:
                win2 = 1000000;
                break;
            case 5:
                win2 = 5000000;
                break;
        }



        //-----------------------------------------------------------------------------------------------------------------------------
        //Task 6
        //finished

        int win3 = 0;
        if (sum == sumUser) {
            win3 = 300000;
        }



        //-----------------------------------------------------------------------------------------------------------------------------
        //Task 7
        //finished

        int winTotal = Math.max(Math.max(win1, win2), win3);

        //output
        if (winTotal == win1) {
            System.out.println("Your guessed numbers are:");
            for (int guessedNumber : guessedNumbers) {
                if (guessedNumber == 0) {
                    break;
                } else {
                    System.out.print(guessedNumber + "   ");
                }
            }
            System.out.println();

            System.out.println("Your winning prize is " + win1);

        } else if (winTotal == win2) {
            System.out.println("Your winning sequence is:");
            for (int ij = lastIndex - numberSequenceMax + 1; ij <= lastIndex; ij++) {
                System.out.print(numbersUser[ij] + "   ");
            }
            System.out.println();

            System.out.println("Your winning prize is " + win2);

        } else {
            System.out.println("Your guessed sum is " + sumUser);
            System.out.println("Your winning prize is " + win3);
        }

        System.out.println("The winning prize in the bonus game is " + winSum);
        System.out.println("Overall you won " + winTotal + " + " + winSum + " = " + (winTotal + winSum));
    }
}
