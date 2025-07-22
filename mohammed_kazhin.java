import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mohammed_kazhin {
    // This valid size is for when we use #2 many times, so that it does not get jumbled up
    static int valid_size_e = 0;
    static int valid_size_b = 0;
    static int valid_size_f = 0;
    // this is the key to open up choice #2
    static int one;
    static Scanner sc = new Scanner(System.in);
    static int total = 0;
    static int ticket_amount = 0;
    // this is the database for the tickets which were ordered before officially booking them, the normal ones have the fare type and the numbers have the seat in row+column form
    static ArrayList<String> economy = new ArrayList<>();
    static ArrayList<Integer> economy_numbers = new ArrayList<>();
    static ArrayList<String> business = new ArrayList<>();
    static ArrayList<Integer> business_numbers = new ArrayList<>();
    static ArrayList<String> first_class = new ArrayList<>();
    static ArrayList<Integer> first_class_numbers = new ArrayList<>();
    //this is the db for the booked tickets, has an initial denoting the flight type and the seat number
    static ArrayList<String> booked_seats = new ArrayList<>();


    static String refunded_object;

    public static void main(String[] args){
        while (true){
        int choice = menu();
        if (choice == 1){
            flight_classes_and_fare_sub_menu();
            //this valid size is necessary to solve the problem of using choices 1 and 2 multiple times, otherwise it will get jumbled up
            valid_size_e = economy_numbers.size();
            valid_size_b = business_numbers.size();
            valid_size_f = first_class_numbers.size();

        }
        else if (choice == 2){
            // if the user did not alr go to choice 1, he can not choose choice 2
            if (one != 1){
                System.out.println("It is imperative to select choice [1] first sir! ");
                continue;
            }
            String booking_grid; //gonna use this to store the seat number I want to book
            System.out.println("You bought " + ticket_amount + " tickets until now");
            System.out.println("Economy you have 6 rows with 10 seats (columns) each");
            System.out.println("Busniess you have 4 rows with 6 seats (columns) each");
            System.out.println("First Class you have 2 rows with 4 seats (columns) each");
            System.out.println("you have to select the seat with a grid like structure of: (row, column)");
            try {
            if (economy.size() != 0){
            System.out.println("For Economy, you have " + (economy.size() - valid_size_e) + " Tickets to officially book. Where do you want it? (rows are from 1-6, columns 1-10)");
            for (int i = valid_size_e; i<economy.size(); i++){
            System.out.println("Ticket " + (i+1));
            System.out.println("Row: ");
            int E_row = sc.nextInt();
            System.out.println("Column: ");
            int E_col = sc.nextInt();
            if ((E_row < 1 || E_row > 6) || (E_col <1 || E_col > 10)){
                System.out.println("This seat choice is not on the grid, sorry.");
                i--;
                continue;
            }
            booking_grid = String.format("E%d", (E_row*10) + E_col);
            
            if (booked_seats.contains(booking_grid)){
                i--;
                System.out.println("Already booked! ");
                continue;
            }else{
                booked_seats.add(booking_grid);
                economy_numbers.add((E_row*10) + E_col);
            }
            }
            }
            if (business.size() != 0){
            System.out.println("For Business, you have " + (business.size() - valid_size_b) + " Tickets to officially book. Where do you want it? (rows are from 1-4, columns 1-6)");
            for (int i = valid_size_b; i<business.size(); i++){
            System.out.println("Ticket " + (i + 1));
            System.out.println("Row: ");
            int B_row = sc.nextInt();
            System.out.println("Column: ");
            int B_col = sc.nextInt();
            if ((B_row < 1 || B_row > 4) || (B_col <1 || B_col > 6)){
                System.out.println("This seat choice is not on the grid, sorry.");
                i--;
                continue;
            }
            booking_grid = String.format("B%d", (B_row*10) + B_col);
            if (booked_seats.contains(booking_grid)){
                i--;
                System.out.println("Already booked! ");
                continue;
            }else{
                booked_seats.add(booking_grid);
                business_numbers.add((B_row*10) + B_col);
            }
            }
            }
            if (first_class.size() != 0){
            System.out.println("For First class, you have " + (first_class.size() - valid_size_f) + " Tickets to officially book. Where do you want it? (rows are from 1-2, columns 1-4)");
            for (int i = valid_size_f; i<first_class.size(); i++){
            System.out.println("Ticket " + (i+1));
            System.out.println("Row: ");
            int F_row = sc.nextInt();
            System.out.println("Column: ");
            int F_col = sc.nextInt();
            if ((F_row < 1 || F_row > 2) || (F_col <1 || F_col > 4)){
                System.out.println("This seat choice is not on the grid, sorry.");
                i--;
                continue;
            }
            booking_grid = String.format("F%d", (F_row*10) + F_col);
            if (booked_seats.contains(booking_grid)){
                i--;
                System.out.println("Already booked! ");
                continue;
            }else{
                booked_seats.add(booking_grid);
                first_class_numbers.add((F_row*10) + F_col);
            }
            }
            }
            System.out.println("Thank you for booking with us!");
            one = 0;

            } catch (InputMismatchException e){
            System.out.println("Sorry, enter an integer next time");
            }
        }
        else if (choice == 3){
            System.out.println("===============[Your Economy Seats]===============");
            if (economy.isEmpty()){
                System.out.println("You have no seats in Economy!");
            }
            else{
                for(int i = 0; i<economy.size();i++){
                    System.out.println("<----------------------------------------->");
                    System.out.println("Ticket seat: " + economy_numbers.get(i));
                    System.out.println("Fare type: " + economy.get(i));
                    System.out.print("Price of ticket: ");
                    if (economy.get(i).equals("One way")){
                        System.out.println("100k IQD");
                    }else{
                        System.out.println("200k IQD");
                    }
                    System.out.println("<----------------------------------------->");
                }
            }
            System.out.println("===============[Your Business Seats]===============");
            if (business.isEmpty()){
                System.out.println("You have no seats in Business!");
            }
            else{
                for(int i = 0; i<business.size();i++){
                    System.out.println("<----------------------------------------->");
                    System.out.println("Ticket seat: " + business_numbers.get(i));
                    System.out.println("Fare type: " + business.get(i));
                    System.out.print("Price of ticket: ");
                    if (business.get(i).equals("One way")){
                        System.out.println("200k IQD");
                    }else{
                        System.out.println("300k IQD");
                    }
                    System.out.println("<----------------------------------------->");
                }
            }
            System.out.println("===============[Your First Class Seats]===============");
            if (first_class.isEmpty()){
                System.out.println("You have no seats in First class!");
            }
            else{
                for(int i = 0; i<first_class.size();i++){
                    System.out.println("<----------------------------------------->");
                    System.out.println("Ticket seat: " + first_class_numbers.get(i));
                    System.out.println("Fare type: " + first_class.get(i));
                    System.out.print("Price of ticket: ");
                    if (first_class.get(i).equals("One way")){
                        System.out.println("300k IQD");
                    }else{
                        System.out.println("400k IQD");
                    }
                    System.out.println("<----------------------------------------->");
                }
            }
            System.out.println("===============[Your Total]===============");
            System.out.println("Total tickets: " + ticket_amount);
            System.out.println("Total price of tickets: " + total);
            System.out.println("===============[==========]===============");
        }
        else if (choice == 4){
            System.out.println("These are the lists of the booked flights in ascending order: ");
            System.out.println("============================================================================");
            Asc();
            System.out.println("And these are the lists of the booked flights in descending order: ");
            System.out.println("============================================================================");
            Desc();


        }
        else if (choice == 5){
        String temp;
        temp = cancel();
        refund_getter(temp, refunded_object);

        }
        else if (choice == 6){
            System.out.println("Economy:");
            String target;
            for (int i = 1; i < 7; i++){
                for (int j = 1; j < 11; j++){
                    target = String.format("E%d", (i*10) + j);
                    if (booked_seats.contains(target)){
                        System.out.print("@ ");
                    }
                    else {
                        System.out.print("X ");
                    }
                }
                System.out.println("");
            }
            System.out.println("----------------------------------------------");
            System.out.println("Busniess");
            for (int i = 1; i < 5; i++){
                for (int j = 1; j < 7; j++){
                    target = String.format("B%d", (i*10) + j);
                    if (booked_seats.contains(target)){
                        System.out.print("@ ");
                    }
                    else {
                        System.out.print("X ");
                    }
                }
                System.out.println("");
            }
            System.out.println("----------------------------------------------");
            System.out.println("First Class");
            for (int i = 1; i < 3; i++){
                for (int j = 1; j < 5; j++){
                    target = String.format("F%d", (i*10) + j);
                    if (booked_seats.contains(target)){
                        System.out.print("@ ");
                    }
                    else {
                        System.out.print("X ");
                    }
                }
                System.out.println("");
            }
            System.out.println("----------------------------------------------");
        }
        else if (choice == 7){
            // to access the database
            String x;
            String x_class;
            String x_num;
            System.out.println("This is a list of all the seats you reserved recently in this session!");
            for (int i = booked_seats.size() - 1; i>=0;i--){
                x = booked_seats.get(i);
                x_class = x.substring(0,1);
                x_num = x.substring(1);
                System.out.println("Number " + (i+1) + ": ");
                if (x_class.equals("E")){
                    System.out.println("Economy, #" + x_num);
                    System.out.println("|<-------------------------->|");
                }
                else if (x_class.equals("B")){
                    System.out.println("Business, #" + x_num);
                    System.out.println("|<-------------------------->|");
                }
                else if (x_class.equals("F")){
                    System.out.println("First Class, #" + x_num);
                    System.out.println("|<-------------------------->|");
            
                }else{
                    System.out.println("Not a choice");
                }
            }

        }
        else if (choice == 8){
            char class_status;
            System.out.println("In which class do you wish to start searching? Economy (E), Business (B) or First class (F)?");
            class_status = sc.next().charAt(0);
            seat_getter(class_status);
        }
        else if (choice == 9){
            //these are to extract the data
            String report;
            String report_class;
            String report_num;
            System.out.println("This is a list of each booked seat in the order of it being booked! ");
            for (int i = 0; i<booked_seats.size();i++){
                report = booked_seats.get(i);
                report_class = report.substring(0, 1);
                report_num = report.substring(1);
                System.out.println("Number " + (i+1));
                seat_report(report_class, report_num);

            }
        }
        else{
            System.out.println("We are exiting the program... ");
            break;
        }

        }
    
}
//funciton for the menu
public static int menu(){
    int condition = 0;
    int choice;
    //I wanted to make the validation of the choice within the menu function
    while (condition == 0){
    System.out.println("[1] Select class and fare ");
    System.out.println("[2] Book a seat ");
    System.out.println("[3] View e-ticket ");
    System.out.println("[4] View booked seats ");
    System.out.println("[5] Cancel flight ");
    System.out.println("[6] View status of seating arrangement ");
    System.out.println("[7] View recently reserved seats ");
    System.out.println("[8] Search for the status of a seat ");
    System.out.println("[9] View report of booked seats ");
    System.out.println("[0] Exit: ");

    System.out.println("Choose an option! ");
    // we used a try catch block to filter out any mishaps
    try{
    choice = sc.nextInt();
    } catch (InputMismatchException e){
    System.out.println("Please enter an integer! ");
    sc.nextLine();
    continue;
    } finally {
    System.out.println("");
    }
    if (choice > 9 || choice < 0){
        System.out.println("Pick a correct choice");
        continue;
    } 
    else {
    // if the choice was correct, the code breaks the loop
    condition = -1;
    return choice;
    }
    }
    return -1;
}
//function for choice 1
public static void flight_classes_and_fare_sub_menu(){
    //F capital denotes flight, and f small denotes fare
    int choice_flight;   
    int choice_fare;
    char ans;
    //used to track the choice of trip the person used
    char choice;
    one = 1;
    while (true){
    ticket_amount = ticket_amount + 1;
    System.out.println("Which flight class do you fancy? ");
    System.out.println("1) Economy class: 100k Iraqi Dinar");
    System.out.println("2) Business class: 200k Iraqi Dinar");
    System.out.println("3) First class: 300k Iraqi Dinar");
    choice_flight = sc.nextInt();
    if (choice_flight == 1){
        System.out.println("You have chosen Economy. ");
        System.out.println("-----------------------------------------------------------------");
        total += 100000;
        choice = 'a';
    }
    else if (choice_flight == 2){
        System.out.println("Have a good Busniess class experience, hope you succeed! ");
        System.out.println("-----------------------------------------------------------------");
        total += 200000;
        choice = 'b';
    }
    else if (choice_flight == 3) {
        System.out.println("Have a good First class experience sir! ");
        System.out.println("-----------------------------------------------------------------");
        total += 300000;
        choice = 'c';
    } else {
        System.out.println("Sorry, this is not a choice.");
        continue;
    }
    System.out.println("What is your choice of fare? ");
    System.out.println("1) One way trip ");
    System.out.println("2) Roundtrip (+ 100k IQD bonus)");
    choice_fare = sc.nextInt();
    if (choice_fare == 1){
        System.out.println("One way trip chosen, don't get lost! ");
        System.out.println("-----------------------------------------------------------------");
        if (choice == 'a'){
            economy.add("One way");
        }else if (choice == 'b'){
            business.add("One way");

        }else if (choice == 'c'){
            first_class.add("One way");

        }
    }
    else if (choice_fare == 2){
        System.out.println("Roundtrip chosen, 100k was added to total ");
        System.out.println("-----------------------------------------------------------------");
        total += 100000;
        if (choice == 'a'){
            economy.add("Roundtrip");
        }else if (choice == 'b'){
            business.add("Roundtrip");

        }else if (choice == 'c'){
            first_class.add("Roundtrip");

        }
    }
    else {
        System.out.println("Not a choice, you have to start over.");
        continue;
    }
    System.out.println("Do you wish to make another ticket? (y/n)");
    ans = sc.next().charAt(0);
    if (ans == 'y'){
        continue;
    }
    else if (ans == 'n'){
        break;
    }
    else {
        System.out.println("I will count that as a yes! ");
    }
    }
    System.out.println("--------------------------------------------------------------------------------------------");
    System.out.println("Your total expenses are: " + total + ", and your total amount of tickets is: " + ticket_amount);
    System.out.println("You should now go to option 2 and book the seats! ");
    System.out.println("--------------------------------------------------------------------------------------------");


}
// function of choice 4
public static void Asc(){
    boolean swapped;
    //I made these copies array lists so that I do not change the original array lists
    ArrayList<Integer> economy_numbers_n = new ArrayList<>(economy_numbers);
    ArrayList<Integer> business_numbers_n = new ArrayList<>(business_numbers);
    ArrayList<Integer> first_class_numbers_n = new ArrayList<>(first_class_numbers);


    int arr = economy_numbers.size()-1;
    int arr_busi = business_numbers.size() - 1;
    int arr_first = first_class_numbers.size() - 1;
    for (int i = 0; i<arr;i++){
        swapped = false;
        for (int j = 0; j<arr-i;j++){
            if (economy_numbers_n.get(j) > economy_numbers_n.get(j+1)){
                swap(economy_numbers_n, j, j+1);
                swapped = true;
            
            }
        }
        if (swapped == false){
            break;
        }            
    }
    System.out.println("Ascending order of the booked seats in Economy: " + economy_numbers_n);
    System.out.println("-----------------------------------------------");
    for (int i = 0; i<arr_busi;i++){
        swapped = false;
        for (int j = 0; j<arr_busi-i;j++){
            if (business_numbers_n.get(j) > business_numbers_n.get(j+1)){
                swap(business_numbers_n, j, j+1);
                swapped = true;
            
            }
        }
        if (swapped == false){
            break;
        }            
    }
    System.out.println("Ascending order of the booked seats in Business: " + business_numbers_n);
    System.out.println("-----------------------------------------------");
    for (int i = 0; i<arr_first;i++){
        swapped = false;
        for (int j = 0; j<arr_first-i;j++){
            if (first_class_numbers_n.get(j) > first_class_numbers_n.get(j+1)){
                swap(first_class_numbers_n, j, j+1);
                swapped = true;
            
            }
        }
        if (swapped == false){
            break;
        }            
    }
    System.out.println("Ascending order of the booked seats in First Class: " + first_class_numbers_n);
    System.out.println("-----------------------------------------------");
}
// function of choice 4
public static void Desc(){
    boolean swapped;
    ArrayList<Integer> economy_numbers_n = new ArrayList<>(economy_numbers);
    ArrayList<Integer> business_numbers_n = new ArrayList<>(business_numbers);
    ArrayList<Integer> first_class_numbers_n = new ArrayList<>(first_class_numbers);
    int arr = economy_numbers.size()-1;
    int arr_busi = business_numbers.size() - 1;
    int arr_first = first_class_numbers.size() - 1;
    for (int i = 0; i<arr;i++){
        swapped = false;
        for (int j = 0; j<arr-i;j++){
            if (economy_numbers_n.get(j) < economy_numbers_n.get(j+1)){
                swap(economy_numbers_n, j, j+1);
                swapped = true;
            
            }
        }
        if (swapped == false){
            break;
        }            
    }
    System.out.println("Descending order of the booked seats in Economy: " + economy_numbers_n);
    System.out.println("-----------------------------------------------");
    for (int i = 0; i<arr_busi;i++){
        swapped = false;
        for (int j = 0; j<arr_busi-i;j++){
            if (business_numbers_n.get(j) < business_numbers_n.get(j+1)){
                swap(business_numbers_n, j, j+1);
                swapped = true;
            
            }
        }
        if (swapped == false){
            break;
        }            
    }
    System.out.println("Descending order of the booked seats in Business: " + business_numbers_n);
    System.out.println("-----------------------------------------------");
    for (int i = 0; i<arr_first;i++){
        swapped = false;
        for (int j = 0; j<arr_first-i;j++){
            if (first_class_numbers_n.get(j) < first_class_numbers_n.get(j+1)){
                swap(first_class_numbers_n, j, j+1);
                swapped = true;
            
            }
        }
        if (swapped == false){
            break;
        }            
    }
    System.out.println("Descending order of the booked seats in First Class: " + first_class_numbers_n);
    System.out.println("-----------------------------------------------");
}
//swap method used for the asc and desc function
public static void swap(ArrayList<Integer> arr, int swap_1, int swap_2){
    int temp = arr.get(swap_2);
    arr.set(swap_2, arr.get(swap_1));
    arr.set(swap_1, temp);
}
// function for choice 5
public static String cancel(){
     //uncreative variable names
     int r_o_w;
     int c_o_l;
     //will use this temp variable to store the refunded flight 
     String temp;
     String cancelled_flight;
     System.out.println("Hello, if you wish to cancel a flight, enter the flight class and the seat number: ");
     System.out.println("Choose flight class: ");
     System.out.println("1) Economy");
     System.out.println("2) Business");
     System.out.println("3) First class");
     int cancelled_class = sc.nextInt();
    try{
    if (cancelled_class == 1){
         System.out.println("Enter the seat number (rows: 1-6, columns 1-10)");
         System.out.println("Row: ");
         r_o_w = sc.nextInt();
         System.out.println("Column: ");
         c_o_l = sc.nextInt();
         //we need to cancel from the 3 array lists, and plug in the fare type of the refunded flight into the static variable "refunded_object":
        temp = "E";
        refunded_object = economy.get(economy_numbers.indexOf((r_o_w*10) + c_o_l));
        economy.remove(economy_numbers.indexOf((r_o_w*10) + c_o_l)); 
        economy_numbers.remove(Integer.valueOf(((r_o_w*10)+c_o_l)));
        cancelled_flight = String.format("E%d", (r_o_w * 10) + c_o_l);
        booked_seats.remove(cancelled_flight);
        System.out.println("Alright, you just cancelled ticket number " + ((r_o_w * 10) + c_o_l) + " in Economy");
        ticket_amount--;
        return temp;

     }
    else if (cancelled_class == 2){
        System.out.println("Enter the seat number (rows: 1-4, columns 1-6)");
        System.out.println("Row: ");
        r_o_w = sc.nextInt();
        System.out.println("Column: ");
        c_o_l = sc.nextInt();
        temp = "B";        
        refunded_object = business.get(business_numbers.indexOf((r_o_w*10) + c_o_l));
        business.remove(business_numbers.indexOf((r_o_w*10) + c_o_l));
        business_numbers.remove(Integer.valueOf(((r_o_w*10)+c_o_l)));
        cancelled_flight = String.format("B%d", (r_o_w * 10) + c_o_l);
        booked_seats.remove(cancelled_flight);
        System.out.println("Alright, you just cancelled ticket number " + ((r_o_w * 10) + c_o_l) + " in Business");
        ticket_amount--;
        return temp;
     }
    else if (cancelled_class == 3){
        System.out.println("Enter the seat number (rows: 1-2, columns 1-4)");
        System.out.println("Row: ");
        r_o_w = sc.nextInt();
        System.out.println("Column: ");
        c_o_l = sc.nextInt();
        temp = "F";
        refunded_object = first_class.get(first_class_numbers.indexOf((r_o_w*10) + c_o_l));
        first_class.remove(first_class_numbers.indexOf((r_o_w*10) + c_o_l));
        first_class_numbers.remove(Integer.valueOf(((r_o_w*10)+c_o_l)));
        cancelled_flight = String.format("F%d", (r_o_w * 10) + c_o_l);
        booked_seats.remove(cancelled_flight);
        System.out.println("Alright, you just cancelled ticket number " + ((r_o_w * 10) + c_o_l) + " in First Class");
        ticket_amount--;
        return temp;
    }
    else{
         System.out.println("That is not an option! ");
         return "";
    }
    } catch(InputMismatchException e){
        System.out.println("Please input only integers");
        return "";
    } catch (Exception e){
        System.out.println("Error... ");
        return "";
    }
} public static void refund_getter(String refunded_flight_class, String fare_type){
    String clas;    
    clas = refunded_flight_class;
    if (clas.equals("E")){
        if (fare_type.equals("One way")){
            System.out.println("You will be refunded your 100k IQD for the ticket with a 2% deduction of 2k IQD");
            total = total - 98000;
            System.out.println("Your new total price is " + total);
        }
        else{
            System.out.println("You will be refunded your 200k IQD for the ticket with a 2% deduction of 4k IQD");
            total = total - 196000;
            System.out.println("Your new total price is " + total);
        }
    }
    else if (clas.equals("B")){
        if (fare_type.equals("One way")){
            System.out.println("You will be refunded your 200k IQD for the ticket with a 2% deduction of 4k IQD");
            total = total - 196000;
            System.out.println("Your new total price is " + total);
        }
        else{
            System.out.println("You will be refunded your 300k IQD for the ticket with a 2% deduction of 6k IQD");
            total = total - 294000;
            System.out.println("Your new total price is " + total);
        }
    }
    else if (clas.equals("F")) {
        if (fare_type.equals("One way")){
            System.out.println("You will be refunded your 300k IQD for the ticket with a 2% deduction of 6k IQD");
            total = total - 294000;
            System.out.println("Your new total price is " + total);
        }
        else{
            System.out.println("You will be refunded your 400k IQD for the ticket with a 2% deduction of 8k IQD");
            total = total - 392000;
            System.out.println("Your new total price is " + total);
        }
    }
    else {
        System.out.println("");
    }

}
public static void seat_getter(char flight_class){
    int row;
    int col;
    String target;
    if (flight_class == 'E' || flight_class == 'e'){
        System.out.println("Enter the row (1-6) and column (1-10): ");
        System.out.println("Row: ");
        row = sc.nextInt();
        System.out.println("Column: ");
        col = sc.nextInt();
        if ((row > 6 || row<1) || (col >10 || col <1)){
            System.out.println("This seat is outside of the grid! ");
            return;
        }
        target = String.format("E%d", ((row*10) + col));
        if (booked_seats.contains(target)){
            System.out.println("This seat seems to be booked! ");
        }else{
            System.out.println("Good news! This seat is not booked! ");
        }
    }else if (flight_class == 'B' || flight_class == 'b'){
        System.out.println("Enter the row (1-4) and column (1-6): ");
        System.out.println("Row: ");
        row = sc.nextInt();
        System.out.println("Column: ");
        col = sc.nextInt();
        if ((row > 4 || row<1) || (col >6 || col <1)){
            System.out.println("This seat is outside of the grid! ");
            return;
        }
        target = String.format("B%d", ((row*10) + col));
        if (booked_seats.contains(target)){
            System.out.println("This seat seems to be booked! ");
        }else{
            System.out.println("Good news! This seat is not booked! ");
        }
    }else if (flight_class == 'F' || flight_class=='f'){
        System.out.println("Enter the row (1-2) and column (1-4): ");
        System.out.println("Row: ");
        row = sc.nextInt();
        System.out.println("Column: ");
        col = sc.nextInt();
        if ((row > 2 || row<1) || (col >4 || col <1)){
            System.out.println("This seat is outside of the grid! ");
            return;
        }
        target = String.format("F%d", ((row*10) + col));
        if (booked_seats.contains(target)){
            System.out.println("This seat seems to be booked! ");
        }else{
            System.out.println("Good news! This seat is not booked! ");
        }
    }else{
        System.out.println("That is not a choice! ");
    }
}
public static void seat_report(String x, String seat){
    String fare;
    int y;
    y = Integer.parseInt(seat);
    if (x.equals("E")){
        System.out.println("Economy, seat number " + seat);
        fare = economy.get(economy_numbers.indexOf(y));
        System.out.println(fare + " fare");
        System.out.println("|<-------------------------->|");
    }
    else if (x.equals("B")){
        System.out.println("Business, seat number " + seat);
        fare = business.get(business_numbers.indexOf(y));
        System.out.println(fare + " fare");
        System.out.println("|<-------------------------->|");
    }
    else if (x.equals("F")){
        System.out.println("First Class, seat number " + seat);
        fare = first_class.get(first_class_numbers.indexOf(y));
        System.out.println(fare + " fare");
        System.out.println("|<-------------------------->|");

    }else{
        System.out.println("Not a choice");
    }
}
}