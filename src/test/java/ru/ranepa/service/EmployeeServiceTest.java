package ru.ranepa.service;

import org.junit.jupiter.api.Test;
import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepositoryImpl;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Test
    void shouldCalculateAverageSalary() {
        EmployeeService service = new EmployeeService(new EmployeeRepositoryImpl());

        service.addEmployee(new Employee("Anna", "Dev", 100, LocalDate.now()));
        service.addEmployee(new Employee("Bill", "Dev", 200, LocalDate.now()));
        service.addEmployee(new Employee("Cob", "Dev", 300, LocalDate.now()));

        assertEquals(200.0, service.calculateAverageSalary());
    }

    @Test
    void shouldFindTopPaidEmployee() {
        EmployeeService service = new EmployeeService(new EmployeeRepositoryImpl());

        service.addEmployee(new Employee("Anna", "Dev", 100, LocalDate.now()));
        Employee top = service.addEmployee(new Employee("Bob", "Dev", 500, LocalDate.now()));

        var result = service.findTopPaidEmployee();

        assertTrue(result.isPresent());
        assertEquals(top.getId(), result.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenNoEmployees() {
        EmployeeService service = new EmployeeService(new EmployeeRepositoryImpl());

        assertTrue(service.findTopPaidEmployee().isEmpty());
        assertEquals(0.0, service.calculateAverageSalary());
    }
}

