import java .util.*;
public class Main {
    static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter The Year");
        int Year = input.nextInt();
        input.nextLine();
        System.out.println("Enter The Month");
        String Month = input.nextLine();
        System.out.println(Month+" "+Year);

    }
}