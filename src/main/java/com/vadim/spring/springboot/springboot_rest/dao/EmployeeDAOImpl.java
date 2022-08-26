package com.vadim.spring.springboot.springboot_rest.dao;

import com.vadim.spring.springboot.springboot_rest.entity.Employee;
//import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private EntityManager manager;

    @Override
    public List<Employee> getAllEmployees() {
//        Session session = manager.unwrap(Session.class);
//
//        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();

        Query query = manager.createQuery("from Employee", Employee.class);
        List<Employee> allEmployees = query.getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(Employee employee) {
//        Session session = manager.unwrap(Session.class);
//        session.saveOrUpdate(employee); //для Hibernate
        Employee emp = manager.merge(employee); //for JPA
        employee.setId(emp.getId());
    }

    @Override
    public Employee getEmployee(int id) {

//        Session session = manager.unwrap(Session.class);
        Employee employee = manager.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session = manager.unwrap(Session.class);
//        session.delete(session.get(Employee.class, id));

        Query query = manager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
