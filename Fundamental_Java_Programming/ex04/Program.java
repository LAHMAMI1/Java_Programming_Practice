import java.util.Scanner;

public class Program {
    static class CharList {
        char c;
        int num;
        CharList next;

        CharList(char c, int num) {
            this.c = c;
            this.num = num;
        }
    }

    static CharList fillNodes(int[] storedChar) {
        CharList head = null;
        CharList tail = null;

        for (int i = 0; i < storedChar.length; i++) {
            if (storedChar[i] == 0)
                continue;

            CharList node = new CharList((char) i, storedChar[i]);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    static CharList bubbleSort(CharList head) {
        CharList currentNode;
        boolean swapped;
        do {
            swapped = false;
            currentNode = head;
            while ((currentNode != null) && (currentNode.next != null)) {
                if ((currentNode.num < currentNode.next.num)
                        || (currentNode.num == currentNode.next.num && currentNode.c > currentNode.next.c)) {

                    int tempNum = currentNode.num;
                    char tempChar = currentNode.c;
                    currentNode.num = currentNode.next.num;
                    currentNode.c = currentNode.next.c;
                    currentNode.next.num = tempNum;
                    currentNode.next.c = tempChar;
                    swapped = true;

                }
                currentNode = currentNode.next;
            }
        } while (swapped);

        return head;
    }

    static void printResult(CharList currentNode) {
        double maxCount = currentNode.num;
        double oneHash = 10.0 / maxCount;

        for (int y = 10; y >= 0; y--) {
            CharList temp = currentNode;

            for (int x = 0; x < 10; x++) {
                if (temp == null) {
                    System.out.print(" ");
                }
                else {
                    double numHash = oneHash * (double) temp.num;
                    if ((int) numHash == y)
                        System.out.print(temp.num);
                    if ((int) numHash > y)
                        System.out.print("# ");
                    else
                        System.out.print(" ");
                    temp = temp.next;
                }
            }

            System.out.println();
        }

        for (int i = 0; i < 10 && currentNode != null; i++) {
            System.out.print(currentNode.c + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner cs = new Scanner(System.in);
        String input = cs.nextLine();
        cs.close();

        char[] inputArr = input.toCharArray();
        int[] storedChar = new int[65536];

        for (int i = 0; i < inputArr.length; i++) {
            char c = inputArr[i];
            storedChar[c]++;
        }

        CharList head = fillNodes(storedChar);

        CharList currentNode = bubbleSort(head);

        printResult(currentNode);

        System.exit(0);
    }
}