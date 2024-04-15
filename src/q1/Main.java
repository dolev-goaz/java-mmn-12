package q1;

import java.util.Random;

public class Main {
    // random birthdays generation parameters
    private static final int INITIAL_MONTH = 3; // march
    private static final int MONTH_SPREAD = 5; // 5 months ahead
    private static final int LOWEST_YEAR = 1990;
    private static final int YEAR_RANGE = 20;
    private static final int MAX_DAY = 28; // limited to 28 to prevent invalid dates in february

    // birth month gift
    private static final int BIRTH_MONTH_GIFT = 200;

    public static void main(String[] args) {
        Employee[] employees = initializeEmployees();

        for (Employee employee : employees) {
            System.out.println(employee);
            double salary = employee.earnings();
            // could put this section inside Employee::earnings, wasn't required
            // I think it's better to have the BIRTH_MONTH_GIFT constant outside of the Employee class,
            // since it isn't necessarily related to an employee
            // was also clarified with the course's mentor Moran via mail and she agreed
            if (employee.getBirthDate().isBirthdayThisMonth()) {
                System.out.println("This employee is celebrating their birthday this month! Happy birthday!!");
                salary += BIRTH_MONTH_GIFT;
            }
            // in the assignment it was asked that the units would be NIS, though in the provided
            // code it was in dollars. for consistency's sake, i kept it as USD here.
            System.out.println(String.format("Salary- %,.2f$", salary));
            System.out.println(); // separator
        }
    }

    // generates random birthdays, applying the parameters provided in the static attributes
    private static BirthDate[] generateBirthDates() {
        Random rnd = new Random();
        // generate random birthdays, one in each month
        BirthDate[] birthdays = new BirthDate[MONTH_SPREAD];
        for (int i = 0; i < MONTH_SPREAD; i++) {
            birthdays[i] = new BirthDate(
                    1 + rnd.nextInt(MAX_DAY), // 1 to 28, day index starts from 0
                    i + INITIAL_MONTH, // month index starts from 0
                    LOWEST_YEAR + rnd.nextInt(YEAR_RANGE)
            );
        }
        return birthdays;
    }


    // returns an array of different employees
    private static Employee[] initializeEmployees() {
        BirthDate[] birthdays = generateBirthDates();
        // assignment required an array
        Employee[] employees = {
                new SalariedEmployee("Dani", "Kushmaro",
                        "111-21-3333", birthdays[0], 500),
                new CommissionEmployee(
                        "Micky", "Buganim",
                        "123-45-6789", birthdays[1], 50000, 0.2),
                new BasePlusCommissionEmployee(
                        "Dolev", "Goaz",
                        "987-65-4321", birthdays[2], 50000, 0.2, 20000),
                new HourlyEmployee(
                        "Nicki", "Minaj",
                        "999-99-9999", birthdays[3], 20, 50),
                new PieceWorker(
                        "Yonit", "Levi",
                        "000-00-0000", birthdays[4], 500, 7)
        };

        return employees;
    }
}
