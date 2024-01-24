package cinema;

import java.util.Scanner;

public class Cinema {
    static int rows;
    static int seats;
    static int currentRow = -1;
    static int currentSeat = -1;
    static char[][] cinemaLook;
    static int countTickets;
    static int currentIncome;
    static int totalIncome;

    public static void setTotalIncome (int totalIncome) {
        Cinema.totalIncome = totalIncome;
    }

    public static int getTotalIncome () {
        return totalIncome;
    }

    public static void setCountTickets (int countTickets) {
        Cinema.countTickets = countTickets;
    }

    public static int getCountTickets () {
        return countTickets;
    }

    public static void setCurrentIncome (int currentIncome) {
        Cinema.currentIncome = currentIncome;
    }

    public static int getCurrentIncome () {
        return currentIncome;
    }

    public static int getCurrentRow () {
        return currentRow;
    }

    public static void setCurrentRow (int currentRow) {
        Cinema.currentRow = currentRow;
    }

    public static void setCurrentSeat (int currentSeat) {
        Cinema.currentSeat = currentSeat;
    }

    public static int getCurrentSeat () {
        return currentSeat;
    }

    public static void setRows (int rows) {
        Cinema.rows = rows;
    }

    public static int getRows () {
        return rows;
    }

    public static void setSeats (int seats) {
        Cinema.seats = seats;
    }

    public static int getSeats () {
        return seats;
    }

    public static void setCinemaLook (char[][] cinemaLook) {
        Cinema.cinemaLook = cinemaLook;
    }

    public static void setCinemaLook(char type, int curSeat, int curRow, char[][] cinemaLook)
    {
        cinemaLook[curRow][curSeat] = type;
    }

    public static char[][] getCinemaLook () {
        return cinemaLook;
    }

    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        long totalSeats = 0, ticketPrice;
        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        setRows(rows);
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        setSeats(seats);
        char[][] array = new char[rows][seats];
        setCinemaLook(array);
        initCinema();
        int choose;
   do{
       choose = printMenu();
        if (choose == 1)
        {
            printCinema();
        }
    else if(choose == 2)
    {
        buyTicket(rows, seats);
    }
    else if(choose == 3)
        {
            showStatistics();
        }
   }while(choose != 0);
    }
    public static void showStatistics(){
        float per;
        System.out.println();
        System.out.println("Number of purchased tickets: " + getCountTickets());
        per = getCountTickets()*100/(float) (getRows() * getSeats()) ;
        System.out.print("Percentage: ");
        System.out.printf("%.2f", per);
        System.out.println("%");
        System.out.println("Current income: $"+ getCurrentIncome());
        if (getRows()*getSeats()>60) {
            setTotalIncome(((rows/2)*getSeats()*10)+(rows-(rows/2))*seats*8);
        }else {
          setTotalIncome(rows*seats*10);
        }
        System.out.println("Total income: $"+ getTotalIncome());
    }
    public static void buyTicket(int rows, int seats)
    {
        Scanner scanner = new Scanner(System.in);
        int ticketPrice, totalSeats, curIncome, purchasedTickets;
        totalIncome = getTotalIncome();
        curIncome = getCurrentIncome();
        purchasedTickets = getCountTickets();
    do {
        System.out.println();
        System.out.println("Enter a row number:");
        currentRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        currentSeat = scanner.nextInt();
        if ((currentRow < 0 || currentRow > rows)||(currentSeat<0 ||currentSeat>seats)){
            System.out.println();
            System.out.println("Wrong input!");
        }
    }while((currentRow < 0 || currentRow > rows)||(currentSeat<0 ||currentSeat>seats));
        totalSeats = rows * seats;
        if (totalSeats < 60){
            ticketPrice = 10;
        }else if((rows / 2) >= currentRow){

            ticketPrice = 10;
        }else{
            ticketPrice = 8;
        }

        setCurrentSeat(currentSeat);
        setCurrentRow(currentRow);
        if (cinemaLook[currentRow-1][currentSeat-1] == 'S')
        {
            cinemaLook[currentRow-1][currentSeat-1] = 'B';
            curIncome += ticketPrice;
            purchasedTickets ++;

            setCountTickets(purchasedTickets);
            setCurrentIncome(curIncome);

            setCinemaLook(cinemaLook);
            System.out.println();
            System.out.print("Ticket price: ");
            System.out.println("$" + ticketPrice);
            printCinema();
        }else{
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            buyTicket(rows, seats);
        }


    }
    public static int printMenu(){
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();
        return choose;
    }
    public static void printCinema(){
        rows = getRows();
        seats = getSeats();
        currentSeat = getCurrentSeat();
        currentRow = getCurrentRow();
        cinemaLook = getCinemaLook();

        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++)
        {
            System.out.print(i+" ");
        }
        System.out.println();
        for (int i = 1; i <= rows; i++)
        {
            System.out.print(i+" ");
            for(int m = 0; m < seats; m++)
            {
                System.out.print(cinemaLook[i-1][m]+" ");
            }
            System.out.println();
        }
        setCinemaLook(cinemaLook);

    }
    public static void initCinema(){
        rows = getRows();
        seats = getSeats();
        cinemaLook = getCinemaLook();
        for (int i = 0; i < rows; i++)
        {
            for(int m = 0; m < seats; m++)
            {
                setCinemaLook('S', m, i, cinemaLook);
            }
        }

        }
}