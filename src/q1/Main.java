package q1;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final int MONTHS_IN_YEAR = 12;
    private static final int BIRTH_MONTH_GIFT = 200;

    public static void main(String[] args) {
        ArrayList<Employee> employees = initializeEmployees();

        for (Employee employee : employees) {
            System.out.println(employee);
            double salary = employee.earnings();
            if (employee.getBirthDate().isBirthdayThisMonth()) {
                System.out.println("This employee is celebrating their birthday this month! Happy birthday!!");
                salary += BIRTH_MONTH_GIFT;
            }
            System.out.println(String.format("Salary- %,.2f$", salary));
            System.out.println(); // separator
        }
    }

    private static ArrayList<Employee> initializeEmployees() {
        Random rnd = new Random();
        BirthDate[] birthdays = new BirthDate[MONTHS_IN_YEAR];
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            birthdays[i] = new BirthDate(
                    rnd.nextInt(30),
                    i + 1,
                    1990 + rnd.nextInt(20)
            );
        }
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(new SalariedEmployee(
                "Dani", "Kushmaro",
                "111-21-3333", birthdays[2], 500));

        employees.add(new CommissionEmployee(
                "Micky", "Buganim",
                "123-45-6789", birthdays[3], 50000, 0.2));

        employees.add(new BasePlusCommissionEmployee(
                "Dolev", "Goaz",
                "987-65-4321", birthdays[4], 50000, 0.2, 20000));

        employees.add(new HourlyEmployee(
                "Nicki", "Minaj",
                "999-99-9999", birthdays[5], 20, 50));

        return employees;
    }
}
