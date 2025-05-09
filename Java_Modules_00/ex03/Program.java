import java.util.Scanner;

public class Program {

    static class WeekNode {
        int weekNumber;
        int minGrade;
        WeekNode next;

        WeekNode(int weekNumber, int minGrade) {
            this.weekNumber = weekNumber;
            this.minGrade = minGrade;
        }
    }

    static void printResult(WeekNode head) {
        WeekNode currentNode = head;
        while (currentNode != null) {
            System.out.print("Week " + currentNode.weekNumber + " ");
            for (int i = 0; i < currentNode.minGrade; i++)
                System.out.print("=");
            System.out.println(">");
            currentNode = currentNode.next;
        }
    }

    public static void main(String[] args) {
        Scanner scWeek = new Scanner(System.in);
        Scanner scGrades = new Scanner(System.in);
        WeekNode head = null;
        WeekNode tail = null;
  
        for (int i = 1; i <= 18; i++) {
            String week = scWeek.nextLine();

            if (week.equals("42"))
                break;

            if (!week.equals("Week " + i)) {
                System.err.println("Unordered Week number or entry");
                System.exit(-1);
            }
           
            int min = 9;
            for (int j = 0; j < 5; j++) {
                int grade = scGrades.nextInt();
                if (grade < min)
                    min = grade;
            }
            
            WeekNode node = new WeekNode(i, min);
            if (head == null) {
                head = node;
                tail = node;
            }
            else {
                tail.next = node;
                tail = node;
            }
        }
        scGrades.close();
        scWeek.close();

        printResult(head);

        System.exit(0);
    }
}