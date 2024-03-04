package ru.stormsar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        System.out.println("Введите дату в формате dd.MM.yyyy:");
        Scanner scanner = new Scanner(System.in);
        String inputDate = scanner.nextLine();

        Date date = sdf.parse(inputDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DAY_OF_YEAR, 45);
        System.out.println("Дата после увеличения на 45 дней: " + sdf.format(calendar.getTime()));

        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("Дата после сдвига на начало года: " + sdf.format(calendar.getTime()));

        int workingDaysToAdd = 10;
        while (workingDaysToAdd > 0) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            if (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workingDaysToAdd--;
            }
        }
        System.out.println("Дата после увеличения на 10 рабочих дней: " + sdf.format(calendar.getTime()));

        System.out.println("Введите вторую дату в формате dd.MM.yyyy:");
        String inputSecondDate = scanner.nextLine();

        Date secondDate = sdf.parse(inputSecondDate);

        long startMillis = Math.min(date.getTime(), secondDate.getTime());
        long endMillis = Math.max(date.getTime(), secondDate.getTime());

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTimeInMillis(startMillis);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTimeInMillis(endMillis);

        int workingDays = 0;
        while (startCalendar.before(endCalendar)) {
            if (startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workingDays++;
            }
            startCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        System.out.println("Количество рабочих дней между введенными датами: " + workingDays);
    }
}