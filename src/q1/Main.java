package q1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    private static final int MONTHS_IN_YEAR = 12;
    public static void main(String[] args) {
        ArrayList<Employee> employees = initializeEmployees();

        for (Employee employee : employees) {
            // TODO: add 200₪
            System.out.println(employee + "\n");
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
                "111-21-3333", birthdays[3], 500));

        employees.add(new CommissionEmployee(
                "Micky", "Buganim",
                "123-45-6789", birthdays[4], 50000, 0.2));

        employees.add(new BasePlusCommissionEmployee(
                "Dolev", "Goaz",
                "987-65-4321", birthdays[5], 50000, 0.2, 20000));

        employees.add(new HourlyEmployee(
                "Nicki", "Minaj",
                "999-99-9999", birthdays[5], 20, 50));

        return employees;
    }
}
