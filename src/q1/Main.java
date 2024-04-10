package q1;

import java.util.Random;

public class Main {
    private static final int MONTHS_IN_YEAR = 12;
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

    // returns an array of different employees
    private static Employee[] initializeEmployees() {
        Random rnd = new Random();
        // generate random birthdays, one in each month
        BirthDate[] birthdays = new BirthDate[MONTHS_IN_YEAR];
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            birthdays[i] = new BirthDate(
                    1 + rnd.nextInt(28), // 1 to 28
                    i + 1,
                    1990 + rnd.nextInt(20)
            );
        }
        // assignment required an array
        Employee[] employees = {
                new SalariedEmployee("Dani", "Kushmaro",
                        "111-21-3333", birthdays[2], 500),
                new CommissionEmployee(
                        "Micky", "Buganim",
                        "123-45-6789", birthdays[3], 50000, 0.2),
                new BasePlusCommissionEmployee(
                        "Dolev", "Goaz",
                        "987-65-4321", birthdays[4], 50000, 0.2, 20000),
                new HourlyEmployee(
                        "Nicki", "Minaj",
                        "999-99-9999", birthdays[5], 20, 50),
                new PieceWorker(
                        "Yonit", "Levi",
                        "000-00-0000", birthdays[6], 500, 7)
        };

        return employees;
    }
}
