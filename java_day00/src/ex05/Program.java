import java.util.Scanner;

public class Program {
    static Scanner scanner = new Scanner(System.in);
    static int countStudents = 0;
    static Student[] students;
    static int countLessons = 0;
    static Lesson[] lessons = new Lesson[50];
    static int countVisits = 0;
    static Visit[] visits = new Visit[50];

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            visits[i] = new Visit();
        }
        students = readStudentsFromTerminal();
        lessons = readLessonsFromTerminal();
        setVisitLessonsStatus();
        sortLessons();
        printTable();
        System.out.println();
    }

    public static void setVisitLessonsStatus() {
        String name = scanner.next();
        Student tempStudent;

        while (!name.equals(".")){
             tempStudent = findStudent(name);
             tempStudent.visits[tempStudent.countVisitLesson].hour = scanner.nextInt();
             tempStudent.visits[tempStudent.countVisitLesson].day = scanner.nextInt();
             tempStudent.visits[tempStudent.countVisitLesson].status = scanner.next();
             setNewVisit(tempStudent.visits[tempStudent.countVisitLesson]);
             tempStudent.countVisitLesson++;
             name = scanner.next();
        }
        sortVisits();
    }

    public static void setNewVisit(Visit visit) {
        boolean isNewVisit = true;
        for (int i = 0; i < countVisits; i++) {
            if (visits[i].day == visit.day && visits[i].hour == visit.hour){
                isNewVisit = false;
                break;
            }
        }
        if (isNewVisit)
            visits[countVisits++] = visit;
    }

    public static void sortVisits() {
        for (int i = 0; i < countVisits - 1; i++){
            for (int j = i; j < countVisits - 1; j++){
                if (visits[i].day < visits[j].day ||
                        (visits[i].day == visits[j].day && visits[i].hour < visits[j].hour)){
                    Visit tmp = visits[i];
                    visits[i] = visits[j];
                    visits[j] = tmp;
                }
            }
        }
    }

    public static Student findStudent(String name) {
        for (int i = 0; i < countStudents; i++) {
            if (students[i].name.equals(name))
                return students[i];
        }
        return null;
    }
    public static void printTable() {
        System.out.print("          ");
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < countLessons; j++) {
                if (lessons[j].dayWeek.equals(getDayWeek(i))) {
                    System.out.printf("%d:00%3s%3d|", lessons[j].hour, lessons[j].dayWeek, i);
                }
            }
        }
        for (int stud = 0; stud < countStudents; stud++) {
            System.out.println();
            System.out.printf("%10s", students[stud].name);
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < countLessons; j++) {
                        boolean flag = false;
                    if (lessons[j].dayWeek.equals(getDayWeek(i))) {
                        for (int o = 0; o < students[stud].countVisitLesson; o++) {
                            if (students[stud].visits[o].day == i && students[stud].visits[o].hour == lessons[j].hour) {
                                int print = 1;
                                if (students[stud].visits[o].status.equals("NOT_HERE"))
                                    print *= -1;
                                System.out.printf("%10d|", print);
                                flag = true;
                                break;
                            }
                        }
                        if (!flag)
                            System.out.print("          |");
                    }
                }
            }
        }
    }

    public static Student[] readStudentsFromTerminal() {
        Student[] students = new Student[10];
        String name = scanner.nextLine();
        while (!name.equals(".")) {
            students[countStudents++] = new Student(name);
            name = scanner.nextLine();
        }
        return students;
    }

    public static Lesson[] readLessonsFromTerminal() {
        for (int i = 0; i < 50; i++)
            lessons[i] = new Lesson(0,"");
        char[] line = scanner.nextLine().toCharArray();
        while (line[0] != '.') {
            lessons[countLessons++] = new Lesson(line[0] - '0', "" + line[2] + line[3]);
            line = scanner.nextLine().toCharArray();
        }
        return lessons;
    }

    public static String getDayWeek(int day) {
        if (day == 7 || day == 14 || day == 21 || day == 28)
            return "MO";
        if (day == 1 || day == 8 || day == 15 || day == 22 || day == 29)
            return "TU";
        if (day == 2 || day == 9 || day == 16 || day == 23 || day == 30)
            return "WE";
        if (day == 3 || day == 10 || day == 17 || day == 24)
            return "TH";
        if (day == 4 || day == 11 || day == 18 || day == 25)
            return "FR";
        if (day == 5 || day == 12 || day == 19 || day == 26)
            return "SA";
       return "SU";
    }

    public static void sortLessons() {
        for (int o = 0; o < countLessons; o++) {
            for (int i = 0; i < countLessons - 1; i++) {
                for (int j = i; j < countLessons - 1; j++) {
                    if (lessons[j + 1].compare(lessons[j]) > 0) {
                        Lesson tmp = lessons[j];
                        lessons[j] = lessons[j + 1];
                        lessons[j + 1] = tmp;
                        break;
                    }
                }
            }
        }
    }

}

class Student {
    String  name;
    int countVisitLesson;
    Visit[] visits;
    public Student(String name) {
        this.name = name;
        countVisitLesson = 0;
        visits = new Visit[50];
        for (int i = 0; i < 50; i++) {
            visits[i] = new Visit();
        }
    }
}
class Visit{
    int hour;
    int day;
    String status;
}
class Lesson {
    int hour;
    String dayWeek;

    public Lesson(int hour, String dayWeek) {
        this.hour = hour;
        this.dayWeek = dayWeek;
    }

    public int compare(Lesson other) {
        String[] week = {"MO", "TU", "WE", "TH", "FR", "SA", "SU"};
        int thisWeekDay = 0;
        int otherWeekDay = 0;
        for (; thisWeekDay < 7; thisWeekDay++) {
            if (this.dayWeek.equals(week[thisWeekDay]))
                break;
        }
        for (; otherWeekDay < 7; otherWeekDay++) {
            if (this.dayWeek.equals(week[otherWeekDay]))
                break;
        }
        if  (otherWeekDay == thisWeekDay)
            return other.hour - this.hour;
        return otherWeekDay - thisWeekDay;
    }
}
