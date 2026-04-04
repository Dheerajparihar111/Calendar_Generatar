import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);

    static ArrayList<String>[] events = new ArrayList[32];
    static HashMap<String, String> holidays = new HashMap<>();

    static int currentMonth = -1;
    static int currentYear = -1;

    // 🎨 COLORS
    static final String RESET = "\u001B[0m";
    static final String RED = "\u001B[31m";
    static final String GREEN = "\u001B[32m";
    static final String YELLOW = "\u001B[33m";
    static final String CYAN = "\u001B[36m";
    static final String BLUE = "\u001B[34m";

    public static void main(String[] args) {

        // initialize event lists
        for (int i = 0; i < events.length; i++) {
            events[i] = new ArrayList<>();
        }

        initHolidays();

        int choice;

        do {

            System.out.println(CYAN + "\n===== CALENDAR GENERATOR =====" + RESET);
            System.out.println(YELLOW + "1 Generate Calendar");
            System.out.println("2 View Calendar");
            System.out.println("3 Add Event");
            System.out.println("4 Edit Event");
            System.out.println("5 Delete Event");
            System.out.println("6 Exit" + RESET);

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
    }

    // ================= CORE =================

    static void generateCalendar() {

        System.out.print("Enter month(1-12): ");
        currentMonth = input.nextInt();

        System.out.print("Enter year: ");
        currentYear = input.nextInt();

        printCalendar(currentMonth, currentYear);
    }

    static void viewCalendar() {

        if (currentMonth == -1) {
            System.out.println(RED + "Generate calendar first" + RESET);
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

        System.out.println(GREEN + "Event added" + RESET);
    }

    static void editEvent() {

        System.out.print("Enter day: ");
        int day = input.nextInt();
        input.nextLine();

        if (events[day].isEmpty()) {
            System.out.println(RED + "No events" + RESET);
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

            System.out.println(GREEN + "Updated" + RESET);
        }
    }

    static void deleteEvent() {

        System.out.print("Enter day: ");
        int day = input.nextInt();

        if (events[day].isEmpty()) {
            System.out.println(RED + "No events" + RESET);
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
            System.out.println(RED + "Deleted" + RESET);
        }
    }

    // ================= CALENDAR =================

    static void printCalendar(int m, int y) {

        String[] months = {"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        int days = getDays(m, y);
        int start = getStartDay(m, y);

        System.out.println(BLUE + "\n   " + months[m] + " " + y + RESET);
        System.out.println(RED + "Sun " + CYAN + "Mon Tue Wed Thu Fri Sat" + RESET);

        for (int i = 0; i < start; i++) System.out.print("    ");

        for (int d = 1; d <= days; d++) {

            String key = d + "-" + m;
            boolean isSunday = ((d + start - 1) % 7 == 0);

            if (holidays.containsKey(key)) {
                System.out.printf(RED + "%2d* " + RESET, d);
            }
            else if (!events[d].isEmpty()) {
                System.out.printf(GREEN + "%2d* " + RESET, d);
            }
            else if (isSunday) {
                System.out.printf(RED + "%3d " + RESET, d);
            }
            else {
                System.out.printf("%3d ", d);
            }

            if ((d + start) % 7 == 0) System.out.println();
        }

        System.out.println("\n");

        // EVENTS
        for (int i = 1; i <= days; i++) {

            if (!events[i].isEmpty()) {

                System.out.println(GREEN + i + " :" + RESET);

                for (String e : events[i]) {
                    System.out.println("  - " + e);
                }
            }
        }

        // HOLIDAYS
        for (int i = 1; i <= days; i++) {

            String key = i + "-" + m;

            if (holidays.containsKey(key)) {
                System.out.println(RED + i + " : " + holidays.get(key) + " (Holiday)" + RESET);
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