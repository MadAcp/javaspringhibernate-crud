package org.javahibernate.dao;

import org.hibernate.SessionFactory;
import org.javahibernate.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(Employee e) {
        sessionFactory.getCurrentSession().save(e);
    }

    @Transactional
    public void saveAll(List<Employee> employees) {
        for (Employee e : employees) sessionFactory.getCurrentSession().save(e);
    }

    @Transactional
    public Employee getById(int id) {
        return sessionFactory.getCurrentSession().get(Employee.class, id);
    }

    @Transactional
    public List<Employee> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Employee", Employee.class).list();
    }

    @Transactional
    public void updateEmployee(int id, String dept, Double salary, Integer age) {
        Employee e = getById(id);
        if (e != null) {
            if (dept != null) e.setDepartment(dept);
            if (salary != null) e.setSalary(salary);
            if (age != null) e.setAge(age);
            sessionFactory.getCurrentSession().update(e);
        }
    }

    @Transactional
    public void deleteEmployee(int id) {
        Employee e = getById(id);
        if (e != null) sessionFactory.getCurrentSession().delete(e);
    }

    @Transactional
    public List<Employee> getBySalaryRange(double min, double max) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee where salary between :min and :max", Employee.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();
    }

    @Transactional
    public List<Employee> getSorted(String field) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee order by " + field, Employee.class).list();
    }

    @Transactional
    public Employee getMaxSalaryEmployee() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee order by salary desc", Employee.class)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Transactional
    public List<Employee> getByDepartment(String dept) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee where department=:dept", Employee.class)
                .setParameter("dept", dept)
                .list();
    }

    @Transactional
    public long countEmployees() {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(e) from Employee e", Long.class)
                .uniqueResult();
    }

}
