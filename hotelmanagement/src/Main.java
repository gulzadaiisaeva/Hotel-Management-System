import java.util.Scanner;
import java.security.*;
import java.lang.*;

/**
 * Main class to test Hotel Management class
 *
 * Ask user to login , sign in, exit
 * There book, cancel, exit section if guests login
 * There book, cancel ,check-in, checkout ,exit section if guests login
 *
 */
public class Main {

    public static void main(String [] args) {

        try {

            HotelManagement hotel= new HotelManagement();

            boolean flag1=true,flag2=true;

            while(flag1) {

                System.out.println("Welcome to GTUHotel \n");
                System.out.println("1)Login as guest\n2)Login as receptionist\n3)Sign in\n4)Exit");

                Scanner in = new Scanner(System.in);
                int loginChoise = in.nextInt();in.nextLine();

                if (loginChoise == 1)
                {
                    hotel.userLogin(in);

                    while(flag2) {
                        System.out.println("1)BOOK \n2)Cancel\n3)EXIT");
                        int userChoice=in.nextInt();
                        if (userChoice == 1)
                            hotel.userReservation(in);
                        if (userChoice == 2)
                            hotel.userCancel(in);
                        if (userChoice == 3) {
                            flag2=false;
                        }
                    }
                }
                flag2=true;

                if (loginChoise == 2) {
                    hotel.receptionistLogin(in);
                    while(flag2) {
                        System.out.println("1)BOOK \n2)Cancel\n3)Check-in\n4)Checkout\n5)EXIT");
                        int userChoice=in.nextInt();in.nextLine();
                        if (userChoice == 1)
                            hotel.receptReservation(in);
                        if (userChoice == 2)
                            hotel.receptCancel(in);
                        if (userChoice == 3)
                            hotel.checkin(in);
                        if (userChoice == 4)
                            hotel.checkout(in);
                        if (userChoice == 5) {
                            flag2=false;
                        }
                    }

                }
                flag2=true;
                if (loginChoise == 4) {
                    flag1=false;
                }
                if(loginChoise == 3)
                    hotel.signIn(in);
                continue;


            }


        } catch (Throwable throwable) {
            throw new InvalidParameterException(throwable.getMessage());
        }
    }
}
