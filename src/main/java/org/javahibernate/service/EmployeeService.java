package org.javahibernate.service;

import org.javahibernate.dao.EmployeeDAO;
import org.javahibernate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    // Basic CRUD
    public void save(Employee e) { employeeDAO.save(e); }
    public void saveAll(List<Employee> employees) { employeeDAO.saveAll(employees); }
    public Employee getById(int id) { return employeeDAO.getById(id); }
    public List<Employee> getAll() { return employeeDAO.getAll(); }
    public void updateEmployee(int id, String newDept, Double newSalary, Integer newAge) {
        employeeDAO.updateEmployee(id, newDept, newSalary, newAge);
    }
    public void deleteEmployee(int id) { employeeDAO.deleteEmployee(id); }

    // Advanced
    public List<Employee> getBySalaryRange(double min, double max) { return employeeDAO.getBySalaryRange(min, max); }
    public List<Employee> getSorted(String sortField) { return employeeDAO.getSorted(sortField); }
    public Employee getMaxSalaryEmployee() { return employeeDAO.getMaxSalaryEmployee(); }
    public List<Employee> getByDepartment(String department) { return employeeDAO.getByDepartment(department); }
    public long countEmployees() { return employeeDAO.countEmployees(); }

}
