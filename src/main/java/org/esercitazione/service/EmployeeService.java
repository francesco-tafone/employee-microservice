package org.esercitazione.service;

import org.esercitazione.exception.EmployeeNotFoundException;
import org.esercitazione.model.Employee;
import org.esercitazione.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new
                EmployeeNotFoundException(id));
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        try{
            Employee existingEmployee = getEmployeeById(id);
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            return employeeRepository.save(existingEmployee);
        }catch(Exception e){
            throw new EmployeeNotFoundException(id);
        }
    }

    public void deleteEmployee(Long id) {
        try{
            getEmployeeById(id);
            employeeRepository.deleteById(id);
        }catch(Exception e){
            throw new EmployeeNotFoundException(id);
        }
    }
}
