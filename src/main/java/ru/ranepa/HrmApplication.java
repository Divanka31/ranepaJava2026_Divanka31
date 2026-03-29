package ru.ranepa;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepositoryImpl;
import ru.ranepa.service.EmployeeService;

import java.time.LocalDate;
import java.util.Scanner;

public class HrmApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeService service = new EmployeeService(new EmployeeRepositoryImpl());

        while (true) {
            System.out.println("\n=== HRM System Menu ===");
            System.out.println("1. Show all employees");
            System.out.println("2. Add employee");
            System.out.println("3. Delete employee");
            System.out.println("4. Find employee by ID");
            System.out.println("5. Show statistics");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> service.getAllEmployees().forEach(System.out::println);

                case 2 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Position: ");
                    String position = scanner.nextLine();

                    System.out.print("Salary: ");
                    double salary = readDouble(scanner);

                    Employee e = new Employee(name, position, salary, LocalDate.now());
                    Employee saved = service.addEmployee(e);

                    System.out.println("Added with ID: " + saved.getId());
                }

                case 3 -> {
                    System.out.print("Enter ID: ");
                    Long id = readLong(scanner);
                    service.delete(id);
                }

                case 4 -> {
                    System.out.print("Enter ID: ");
                    Long id = readLong(scanner);

                    service.getById(id)
                            .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println("Not found")
                            );
                }

                case 5 -> {
                    System.out.println("Average salary: " + service.calculateAverageSalary());
                    service.findTopPaidEmployee()
                            .ifPresent(e -> System.out.println("Top: " + e));
                }

                case 6 -> {
                    System.out.println("Exit");
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter number: ");
            }
        }
    }

    private static double readDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter number: ");
            }
        }
    }

    private static Long readLong(Scanner scanner) {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter number: ");
            }
        }
    }
}