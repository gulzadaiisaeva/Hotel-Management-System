import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.util.Scanner;

/**
 * Created by Gulzada on 6.03.2018.
 */
public class MainTest {

    public static void main(String [] args) {

        try {

            HotelManagement hotel= new HotelManagement();
            FileReader file = new FileReader("test.txt");
            Scanner in = new Scanner(file);
            boolean flag1=true,flag2=true;

            while(flag1) {

                System.out.println("Welcome to GTUHotel \n");
                System.out.println("1)Login as guest\n2)Login as receptionist\n3)Sign in\n4)Exit");

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

            file.close();

        } catch (Throwable throwable) {
            throw new InvalidParameterException(throwable.getMessage());
        }
    }
}
