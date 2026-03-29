package ru.ranepa.service;

import ru.ranepa.model.Employee;
import ru.ranepa.repository.EmployeeRepositoryImpl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {

    private final EmployeeRepositoryImpl repository;

    public EmployeeService(EmployeeRepositoryImpl repository) {
        this.repository = repository;
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<Employee> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public double calculateAverageSalary() {
        List<Employee> employees = repository.findAll();
        if (employees.isEmpty()) return 0;

        return employees.stream()
                .map(Employee::getSalary)
                .mapToDouble(BigDecimal::doubleValue)
                .average()
                .orElse(0);
    }

    public Optional<Employee> findTopPaidEmployee() {
        return repository.findAll().stream()
                .max(Comparator.comparing(e -> e.getSalary().doubleValue()));
    }

    public List<Employee> findByPosition(String position) {
        return repository.findAll().stream()
                .filter(e -> e.getPosition().equalsIgnoreCase(position))
                .collect(Collectors.toList());
    }
}
