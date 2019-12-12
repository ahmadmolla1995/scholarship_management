package DatabaseProject.models;

public class Date {
    private static int day = 21;
    private static int month = 9;
    private static int year = 1398;

    public static void nextDay() {
        day ++;
        if (day == 31) {
            day = 1;
            month++;
        }

        if (month == 13) {
            month = 1;
            year ++;
        }
    }

    public static String getDate() {
        return year + "/" + month + "/" + day;
    }
}

