import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    // multiple events per day
    static ArrayList<String>[] events = new ArrayList[32];

    // holidays storage
    static HashMap<String, String> holidays = new HashMap<>();

    static int currentMonth = -1;
    static int currentYear = -1;

    public static void main(String[] args) {

        // initialize event lists
        for (int i = 0; i < events.length; i++) {
            events[i] = new ArrayList<>();
        }

        // initialize holidays
        initHolidays();

        int choice;

        do {

            System.out.println("\n===== CALENDAR GENERATOR =====");
            System.out.println("1 Generate Calendar");
            System.out.println("2 View Calendar");
            System.out.println("3 Add Event");
            System.out.println("4 Edit Event");
            System.out.println("5 Delete Event");
            System.out.println("6 Exit");

            choice = input.nextInt();

            switch (choice) {

                case 1: generateCalendar(); break;
                case 2: viewCalendar(); break;
                case 3: addEvent(); break;
                case 4: editEvent(); break;
                case 5: deleteEvent(); break;
            }

        } while (choice != 6);
    }

    // ================= HOLIDAYS =================

    static void initHolidays() {

        holidays.put("26-1", "Republic Day");
        holidays.put("15-8", "Independence Day");
        holidays.put("2-10", "Gandhi Jayanti");
        holidays.put("1-5", "Maharashtra Day");
        holidays.put("25-12", "Christmas Day");
        // add more if needed
    }

    // ================= CORE FUNCTIONS =================

    static void generateCalendar() {

        System.out.print("Enter month: ");
        currentMonth = input.nextInt();

        System.out.print("Enter year: ");
        currentYear = input.nextInt();

        printCalendar(currentMonth, currentYear);
    }

    static void viewCalendar() {

        if (currentMonth == -1) {
            System.out.println("Generate calendar first");
            return;
        }

        printCalendar(currentMonth, currentYear);
    }

    static void addEvent() {

        System.out.print("Enter day: ");
        int day = input.nextInt();
        input.nextLine();

        System.out.print("Enter event: ");
        String text = input.nextLine();

        events[day].add(text);

        System.out.println("Event added");
    }

    static void editEvent() {

        System.out.print("Enter day: ");
        int day = input.nextInt();
        input.nextLine();

        if (events[day].isEmpty()) {
            System.out.println("No events");
            return;
        }

        for (int i = 0; i < events[day].size(); i++) {
            System.out.println((i + 1) + " : " + events[day].get(i));
        }

        System.out.print("Select event: ");
        int n = input.nextInt();
        input.nextLine();

        int index = n - 1;

        if (index >= 0 && index < events[day].size()) {

            System.out.print("Enter new text: ");
            String newText = input.nextLine();

            events[day].set(index, newText);

            System.out.println("Updated");
        }
    }

    static void deleteEvent() {

        System.out.print("Enter day: ");
        int day = input.nextInt();

        if (events[day].isEmpty()) {
            System.out.println("No events");
            return;
        }

        for (int i = 0; i < events[day].size(); i++) {
            System.out.println((i + 1) + " : " + events[day].get(i));
        }

        System.out.print("Select event: ");
        int n = input.nextInt();

        int index = n - 1;

        if (index >= 0 && index < events[day].size()) {

            events[day].remove(index);
            System.out.println("Deleted");
        }
    }

    // ================= CALENDAR PRINT =================

    static void printCalendar(int m, int y) {

        String[] months = {"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        int days = getDays(m, y);
        int start = getStartDay(m, y);

        System.out.println("\n   " + months[m] + " " + y);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        for (int i = 0; i < start; i++) System.out.print("    ");

        for (int d = 1; d <= days; d++) {

            String key = d + "-" + m;

            if (!events[d].isEmpty() || holidays.containsKey(key))
                System.out.printf("%2d* ", d);
            else
                System.out.printf("%3d ", d);

            if ((d + start) % 7 == 0) System.out.println();
        }

        System.out.println("\n");

        // show events
        for (int i = 1; i <= days; i++) {

            if (!events[i].isEmpty()) {

                System.out.println(i + " :");

                for (String e : events[i]) {
                    System.out.println("  - " + e);
                }
            }
        }

        // show holidays
        for (int i = 1; i <= days; i++) {

            String key = i + "-" + m;

            if (holidays.containsKey(key)) {
                System.out.println(i + " : " + holidays.get(key) + " (Holiday)");
            }
        }
    }

    // ================= HELPERS =================

    static int getDays(int m, int y) {
        if (m == 2) return isLeap(y) ? 29 : 28;
        if (m == 4 || m == 6 || m == 9 || m == 11) return 30;
        return 31;
    }

    static boolean isLeap(int y) {
        return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
    }

    static int getStartDay(int m, int y) {

        if (m < 3) { m += 12; y--; }

        int k = y % 100;
        int j = y / 100;

        int h = (1 + (13 * (m + 1)) / 5 + k + k/4 + j/4 + 5*j) % 7;

        return (h + 6) % 7;
    }
}