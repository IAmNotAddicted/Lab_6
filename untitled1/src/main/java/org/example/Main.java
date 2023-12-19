package org.example;

public class Main
{
    private static final int NUM_HALLS = 5;
    private static final int NUM_ROWS = 10;
    private static final int NUM_SEATS_PER_ROW = 20;

    // 3D масив для представлення розміщення місць в кінотеатрі
    private int[][][] seatingArrangement;

    public void CinemaApp()
    {
        // Ініціалізація масиву нулями
        seatingArrangement = new int[NUM_HALLS][NUM_ROWS][NUM_SEATS_PER_ROW];
    }

    // Метод для бронювання місць
    public void bookSeats(int hallNumber, int row, int[] seats)
    {
        for (int seat : seats)
        {
            if (seatingArrangement[hallNumber - 1][row - 1][seat - 1] == 0)
            {
                seatingArrangement[hallNumber - 1][row - 1][seat - 1] = 1;

                System.out.println("Місце " + seat + " в ряду " + row + " залу " + hallNumber + " броньоване.");
            }
            else
            {
                System.out.println("Місце " + seat + " в ряду " + row + " залу " + hallNumber + " вже заброньоване.");
            }
        }
    }

    // Метод для скасування бронювання місць
    public void cancelBooking(int hallNumber, int row, int[] seats)
    {
        for (int seat : seats)
        {
            if (seatingArrangement[hallNumber - 1][row - 1][seat - 1] == 1)
            {
                seatingArrangement[hallNumber - 1][row - 1][seat - 1] = 0;

                System.out.println("Бронювання для місця " + seat + " в ряду " + row + " залу " + hallNumber + " скасовано.");
            }
            else
            {
                System.out.println("Місце " + seat + " в ряду " + row + " залу " + hallNumber + " не було заброньоване.");
            }
        }
    }

    // Метод для перевірки наявності послідовних місць
    public boolean checkAvailability(int hallNumber, int numSeats)
    {
        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int seat = 0; seat <= NUM_SEATS_PER_ROW - numSeats; seat++)
            {
                boolean available = true;

                for (int i = 0; i < numSeats; i++)
                {
                    if (seatingArrangement[hallNumber - 1][row][seat + i] == 1)
                    {
                        available = false;

                        break;
                    }
                }
                if (available)
                {
                    return true;
                }
            }
        }
        return false;
    }

    // Метод для друку схеми розміщення місць
    public void printSeatingArrangement(int hallNumber)
    {
        System.out.println("Схема розміщення місць для залу " + hallNumber + ":");

        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++)
            {
                if (seatingArrangement[hallNumber - 1][row][seat] == 0)
                {
                    System.out.print("O "); // Вільне місце
                }
                else
                {
                    System.out.print("X "); // Заброньоване місце
                }
            }
            System.out.println(); // Перехід на новий ряд
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        CinemaApp cinema = new CinemaApp();

        cinema.bookSeats(1, 3, new int[]{5, 6, 7});
        cinema.printSeatingArrangement(1);

        cinema.cancelBooking(1, 3, new int[]{6});
        cinema.printSeatingArrangement(1);

        boolean isAvailable = cinema.checkAvailability(1, 3);

        System.out.println("Доступно 3 послідовних місця: " + isAvailable);
    }
}