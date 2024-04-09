package q1;
import java.util.Calendar;

public class BirthDate {
    private final int day;
    private final int month;
    private final int year;

    public BirthDate(int day, int month, int year) {
        if (!isDateValid(day, month, year)) {
            throw new IllegalArgumentException(
                    String.format("Invalid date provided! Date- %d/%d/%d", day, month, year)
            );
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    private static boolean isDateValid(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false); // strict date checking
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // month starts from 0
        calendar.set(Calendar.DAY_OF_MONTH, day);

        try {
            calendar.getTime(); // throws an exception if date was invalid
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%d/%d/%d", day, month, year);
    }

    public boolean isBirthdayThisMonth() {
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.MONTH) + 1 == month; // +1 because months start with 0
    }
}
