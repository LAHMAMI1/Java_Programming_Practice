import java.util.Scanner;

public class Program {
    static class StudentList {
        String name;
        int index;
        StudentList next;

        StudentList(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }

    static class CLassList {
        String hour;
        String day;
        CLassList next;

        CLassList(String hour, String day) {
            this.hour = hour;
            this.day = day;
        }
    }

    static class AttendanceList {
        String studentName;
        String hour;
        String numDay;
        String presence;
        AttendanceList next;

        AttendanceList(String studentName, String hour, String numDay, String presence) {
            this.studentName = studentName;
            this.hour = hour;
            this.numDay = numDay;
            this.presence = presence;
        }
    }

    static StudentList getStudents(Scanner cs) {
        StudentList head = null;
        StudentList tail = null;
        int i = 0;

        while (i < 10) {
            String input = cs.next();

            if (input.equals("."))
                break;
            if (input.length() > 10) {
                System.err.println("Name length must be max 10 charactere");
                continue;
            }
            
            StudentList node = new StudentList(input, i);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            
            i++;
        }
        cs.nextLine();

        return head;
    }

    static CLassList getSchedules(Scanner cs) {
        CLassList head = null;
        CLassList tail = null;
        String[] days = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        int i = 0;

        while (i < 10) {
            String input = cs.nextLine();

            if (input.equals("."))
                break;
            if (input.length() > 4) {
                System.err.println("Bad entry Schedule format");
                continue;
            }

            char[] inputChar = input.toCharArray();
            if (!(inputChar[0] >= '1' && inputChar[0] <= '6') || (inputChar[1] != ' ')) {
                System.err.println("Bad entry Schedule format");
                continue;
            }

            String dayInput = "";
            for (int j = 2; j < 4; j++)
                dayInput += inputChar[j];

            boolean validDay = false;
            for (int k = 0; k < days.length; k++) {
                if (days[k].equals(dayInput)) {
                    validDay = true;
                    break;
                }
            }

            if (validDay == false) {
                System.err.println("Bad entry Schedule format");
                continue;
            }

            String hourInput = "";
            hourInput += inputChar[0]; 
            CLassList node = new CLassList(hourInput, dayInput);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            i++;
        }

        return head;
    }

    static String getDayFromNumber(String day) {
        char[] dayChar = day.toCharArray();
        int d = 0;

        String[] days = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        for (int i = 0; i < dayChar.length; i++) {
            if (dayChar[i] >= '0' && dayChar[i] <= '9')
                d = d * 10 + (dayChar[i] - '0');
            else {
                System.err.println("Bad entry Attendance format");
                break;
            }
        }
        
        if (!(d >= 1 && d <= 30))
            return "Bad entry Attendance format";
            
        // September 1, 2020 was a Tuesday (index 1 in our array)
        int dayOfWeek = d % 7;

        return days[dayOfWeek];
    }

    static AttendanceList getAttendances(Scanner cs, StudentList students, CLassList classes) {
        String input = cs.nextLine();
        AttendanceList head = null;
        AttendanceList tail = null;

        while (!input.equals(".")) {
            char[] inputChar = input.toCharArray();
            String[] collectedInput = new String[4];

            for (int j = 0; j < 4; j++)
                collectedInput[j] = "";
            int count = 0;
            for (int i = 0; i < inputChar.length; i++) {
                if (count >= 4)
                    break;
                if (inputChar[i] != ' ')
                    collectedInput[count] += inputChar[i];
                else
                    count++;
            }
            if (count >= 4) {
                System.err.println("Bad entry Attendance format");
                input = cs.nextLine();
                continue;
            }

            boolean exist = false;
            StudentList currentStudent = students;
            while (currentStudent != null) {
                if (currentStudent.name.equals(collectedInput[0])) {
                    exist = true;
                    break;
                }
                currentStudent = currentStudent.next;
            }
            if (exist == false) {
                System.err.println("Bad entry Attendance format");
                input = cs.nextLine();
                continue;
            }

            exist = false;
            String dayStr = getDayFromNumber(collectedInput[2]);
            CLassList currentClass = classes;
            while (currentClass != null) {
                if (currentClass.hour.equals(collectedInput[1]) && currentClass.day.equals(dayStr)) {
                    exist = true;
                    break;
                }
                currentClass = currentClass.next;
            }
            if (exist == false 
                || (!(collectedInput[3].equals("HERE") || collectedInput[3].equals("NOT_HERE")))) {
                System.err.println("Bad entry Attendance format");
                input = cs.nextLine();
                continue;
            }

            AttendanceList node = new AttendanceList(collectedInput[0], collectedInput[1], collectedInput[2], collectedInput[3]);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }

            input = cs.nextLine();
        }

        return head;
    }

    public static void main(String[] args) {
        Scanner cs = new Scanner(System.in);

        StudentList students = getStudents(cs);
        CLassList classes = getSchedules(cs);
        AttendanceList Attendances = getAttendances(cs, students, classes);
        cs.close();

        CLassList currentClass = classes;
        while (currentClass != null) {
            for (int day = 1; day <= 30; day++) {
                String dayOfWeek = getDayFromNumber(String.valueOf(day));
                if (dayOfWeek.equals(currentClass.day))
                    System.out.print(currentClass.hour + ":00 " + currentClass.day + " " + day + "|");
            }
            currentClass = currentClass.next;
        }
        System.out.println();

        StudentList currentStudent = students;
        while (currentStudent != null) {
            System.out.print(String.format("%-10s", currentStudent.name));

            CLassList classNode = classes;
            while (classNode != null) {
                for (int day = 1; day <= 30; day++) {
                    String dayOfWeek = getDayFromNumber(String.valueOf(day));

                    if (dayOfWeek.equals(classNode.day)) {
                        AttendanceList attendanceNode = Attendances;
                        boolean found = false;

                        while (attendanceNode != null) {

                            if (attendanceNode.studentName.equals(currentStudent.name) &&
                                attendanceNode.hour.equals(classNode.hour) &&
                                attendanceNode.numDay.equals(String.valueOf(day))) {
                                
                                if (attendanceNode.presence.equals("HERE"))
                                    System.out.print(String.format("%-10s"," 1|"));
                                else if (attendanceNode.presence.equals("NOT_HERE"))
                                    System.out.print(String.format("%-10s"," -1|"));

                                found = true;
                                break;
                            }
                            attendanceNode = attendanceNode.next;
                        }
                        if (!found)
                            System.out.print(String.format("%-10s"," |"));
                    }
                }
                classNode = classNode.next;
            }
            System.out.println();
            currentStudent = currentStudent.next;
        }

        System.exit(0);
    }
}