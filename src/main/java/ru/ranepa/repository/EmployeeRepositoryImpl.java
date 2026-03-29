package ru.ranepa.repository;

import ru.ranepa.model.Employee;

import java.util.*;

public class EmployeeRepositoryImpl {

    private final Map<Long, Employee> storage = new HashMap<>();
    private long idCounter = 1;

    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(idCounter++);
        }
        storage.put(employee.getId(), employee);
        return employee;
    }

    public List<Employee> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void delete(Long id) {
        storage.remove(id);
    }
}