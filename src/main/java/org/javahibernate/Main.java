package org.javahibernate;

import org.javahibernate.model.Employee;
import org.javahibernate.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Java Spring Hibernate with Java Config and CRUD and other Operations");

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(org.javahibernate.config.AppConfig.class);

        EmployeeService service = ctx.getBean(EmployeeService.class);

        // 1. Add employee
        Employee e1 = new Employee("Jane Doe", 25, 60000, "Design");
        service.save(e1);

        // 2. Add multiple employees (batch)
        List<Employee> more = Arrays.asList(
                new Employee("John Smith", 30, 52000, "IT"),
                new Employee("Lisa Ray", 28, 70000, "HR"),
                new Employee("Sam Lee", 35, 85000, "Finance"),
                new Employee("Ravi Kumar", 31, 78000, "IT")
        );
        service.saveAll(more);

        // 3. Update employee's department and salary
        service.updateEmployee(e1.getId(), "Creative", 62000.0, 26);

        // 4. Fetch all employees
        List<Employee> all = service.getAll();
        System.out.println("--- All Employees ---");
        all.forEach(System.out::println);

        // 5. Get employee by id
        Employee fetched = service.getById(e1.getId());
        System.out.println("Fetched by ID: " + fetched);

        // 6. Get employees by salary range
        List<Employee> salaryRange = service.getBySalaryRange(60000, 80000);
        System.out.println("--- Employees with Salary 60k-80k ---");
        salaryRange.forEach(System.out::println);

        // 7. Get employees sorted by salary
        System.out.println("--- Sorted by Salary ---");
        service.getSorted("salary").forEach(System.out::println);

        // 8. Get employee with max salary
        Employee highest = service.getMaxSalaryEmployee();
        System.out.println("Max salary employee: " + highest);

        // 9. List employees by department
        System.out.println("--- IT Department Staff ---");
        service.getByDepartment("IT").forEach(System.out::println);

        // 10. Count employees
        System.out.println("Total employees: " + service.countEmployees());

        // 11. Delete employee
        service.deleteEmployee(e1.getId());
        System.out.println("Deleted Jane Doe!");

        // 12. Show all after delete
        System.out.println("--- After Delete ---");
        service.getAll().forEach(System.out::println);

        ctx.close();

    }
}
