import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    static String[] events = new String[32]; // store events (1–31)

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== CALENDAR GENERATOR =====");
            System.out.println("1 Generate Calendar");
            System.out.println("2 Add Event");
            System.out.println("3 Exit");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    generateCalendar();
                    break;

                case 2:
                    addEvent();
                    break;
            }

        } while (choice != 3);
    }


    static void generateCalendar() {

        System.out.print("Enter month (1-12): ");
        int month = input.nextInt();

        System.out.print("Enter year: ");
        int year = input.nextInt();

        printCalendar(month, year);
    }


    static void addEvent() {

        System.out.print("Enter day (1-31): ");
        int day = input.nextInt();
        input.nextLine();

        System.out.print("Enter event: ");
        String text = input.nextLine();

        events[day] = text;

        System.out.println("Event added");
    }


    static void printCalendar(int month, int year) {

        String[] months = {
                "", "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"
        };

        int days = getDays(month, year);
        int startDay = getStartDay(month, year);

        System.out.println("\n   " + months[month] + " " + year);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        for (int i = 0; i < startDay; i++) {
            System.out.print("    ");
        }

        for (int d = 1; d <= days; d++) {

            if (events[d] != null)
                System.out.printf("%2d* ", d);  // mark event
            else
                System.out.printf("%3d ", d);

            if ((d + startDay) % 7 == 0) {
                System.out.println();
            }
        }

        System.out.println("\n");

        // show event list
        for (int i = 1; i <= days; i++) {

            if (events[i] != null) {
                System.out.println(i + " : " + events[i]);
            }
        }
    }


    static int getDays(int month, int year) {

        if (month == 2) {
            return isLeap(year) ? 29 : 28;
        }

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;

        return 31;
    }


    static boolean isLeap(int year) {

        return (year % 400 == 0) ||
                (year % 4 == 0 && year % 100 != 0);
    }


    static int getStartDay(int m, int y) {

        if (m < 3) {
            m += 12;
            y--;
        }

        int k = y % 100;
        int j = y / 100;

        int h = (1 + (13 * (m + 1)) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;

        return (h + 6) % 7;
    }
}