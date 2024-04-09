package q1;
import java.util.Calendar;

public class BirthDate {
    private final int day;
    private final int month;
    private final int year;

    public BirthDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
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
