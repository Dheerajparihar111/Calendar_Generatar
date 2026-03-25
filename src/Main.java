import java.time.Month;
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
        String []week ={"Mon","Tue","Wed","Thr","Fri","Sat","Sun"};
        for (String a: week){
            System.out.print(a+" ");
        }
        System.out.println();
        for (int i =1;i<=7;i++){
            System.out.print(" 0"+i+" ");
        }
        System.out.println();
        for (int j = 8;j<=14;j++){
            if (j < 10){
                System.out.print(" 0"+j+" ");
            }else {
                System.out.print(" "+j + " ");
            }
        }
        System.out.println();
        for (int j = 15;j<=21;j++){
            System.out.print(" "+j+" ");
        }
        System.out.println();
        for (int j = 22;j<=28;j++){
            System.out.print(" "+j+" ");
        }
        System.out.println();
        System.out.print(" 29"+" "+" 30");

    }
}