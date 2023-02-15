import java.io.*;
import java.util.*;

//creating own exception that is a subclass of Exception
class NotPossibleException extends Exception {
    public String toString() {
        return "The value entered cannot be accepted";
    }
}

class DisplaySuper {
    static int att[] = new int[4];
    static int marks[][][] = new int[3][6][4];
    static String name[] = { "Hemashri", "Meghana", "Sanjana", "Shreya" };
    static int avg[][] = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, }, { 0, 0, 0, 0, 0, 0 } };

    // method to calculate average of 3 internals
    void average() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++)
                    avg[i][j] += marks[k][j][i];
                avg[i][j] = avg[i][j] / 3;
            }
        }
    }

    // method overriding
    // this method display is hidden by subclass's method
    public void display() {
        System.out.println("Average IA marks:");
        for (int i = 0; i < 4; i++) {
            System.out.print("Student name : ");
            System.out.println(name[i]);

            for (int j = 0; j < 6; j++) {
                System.out.print("18CS4" + (j + 1) + "\t");
                System.out.print(avg[i][j]);
                System.out.println();
            }
            System.out.println();
        }
    }
}

// sub class that inherits superclass DisplaySuper
class project extends DisplaySuper {
    static int choice = 0;

    // method to display the values
    public void display() {
        if (choice == 2) {
            super.display();
            return;
        }
        for (int i = 0; i < 4; i++) {
            System.out.print("Student name : ");
            System.out.println(name[i]);
            System.out.println("\n\t" + "IA 1\tIA 2\tIA 3");
            for (int j = 0; j < 6; j++) {
                System.out.print("18CS4" + (j + 1) + "\t");
                for (int k = 0; k < 3; k++)
                    System.out.print(marks[k][j][i] + "\t");
                System.out.println();
            }
            System.out.println();
        }
    }

    // method to initialize the values in the array
    public void initialize() {
        Random r = new Random();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 6; j++)
                for (int k = 0; k < 3; k++)
                    marks[k][j][i] = r.nextInt(30);
        for (int i = 0; i < 4; i++)
            att[i] = r.nextInt(100);
    }

    public void update(int marks[][][], int n, int mark, int sub, int id) {
        marks[n - 1][sub - 1][id - 1] = mark;

    }

    public static void main(String args[]) {
        project obj = new project();
        Scanner input;
        input = new Scanner(System.in);
        try {
            // marks of students are initialized using random class
            obj.initialize();

            while (true) {
                System.out.println(
                        "What would you like to do:\n1.Update the student marks for IA\n2.Calculate the average\n3.Update attendance\n4.Check for eligibility\n5.Exit");
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter the ID of the student:");
                        int id = input.nextInt();
                        System.out.println("Enter IA you would like to update [1st,2nd,3rd] and the marks:");
                        int n = input.nextInt();

                        int mark = input.nextInt();
                        if (mark > 30)
                            throw new NotPossibleException();
                        System.out
                                .println(
                                        "Choose the subject\n1.18CS41\t2.18CS42\t3.18CS43\t4.18CS44\t5.18CS45\t6.18CS46");
                        int sub = input.nextInt();
                        obj.update(marks, n, mark, sub, id);
                        System.out.println("The updated marks are:");
                        obj.display();
                        break;
                    case 2:
                        obj.average();
                        obj.display();
                        break;
                    case 3:
                        try {
                            System.out.println("Attendance is :");
                            for (int i = 0; i < 4; i++)
                                System.out.println(name[i] + " " + att[i]);
                            System.out.println("Enter the ID to update attendance");
                            int attID = input.nextInt();
                            System.out.println("Enter the updated Attendance : ");
                            int attendance = input.nextInt();
                            if (attendance > 100)
                                throw new NotPossibleException();
                            att[attID - 1] = attendance;
                            System.out.println("Updated Attendace is :");
                            for (int i = 0; i < 4; i++)
                                System.out.println(name[i] + " " + att[i]);
                        } catch (NotPossibleException e) {
                            System.out.println(e);
                        }
                        break;
                    case 4:
                        boolean val = false;
                        System.out.println("Checking for eligibility :");
                        for (int i = 0; i < 4; i++) {
                            if (att[i] >= 85)
                                for (int j = 0; j < 6; j++)
                                    if (avg[i][j] >= 18)
                                        val = true;
                        }
                        for (int i = 0; i < 4; i++)
                            if (val == true)
                                System.out.println(name[i]  + " Eligible");
                            else
                                System.out.println(name[i] + "\t" + " Not Eligible");
                        break;
                    case 5:
                        System.exit(1);
                    default:
                        System.out.println("Wrong Choice!");

                }
            }
        }
        // multiple catch clauses
        catch (NotPossibleException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } finally { // finally block deallocates resource input
            input.close();
        }
    }

}