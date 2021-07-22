package com.mycompany.app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mycompany.app.models.Company;
import com.mycompany.app.models.Employee;
import com.mycompany.app.models.Seminar;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
    	Configuration configuration = new Configuration().configure();
    	StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    	
    	SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
    	Session session = sessionFactory.openSession();
    	Transaction transaction = session.beginTransaction();
    	
    	Employee employeeOne = new Employee();
    	employeeOne.setId(101);
    	employeeOne.setFirstName("James");
    	employeeOne.setLastName("Bond");

    	Employee employeeTwo = new Employee();
    	employeeTwo.setId(101);
    	employeeTwo.setFirstName("Kelly");
    	employeeTwo.setLastName("Mars");

    	Employee employeeThree= new Employee();
    	employeeThree.setId(101);
    	employeeThree.setFirstName("Kenny");
    	employeeThree.setLastName("Bui");

    	session.save(employeeOne);
    	session.save(employeeTwo);
    	session.save(employeeThree);
    	
    	ArrayList<Employee> list1 = new ArrayList<Employee>();
    	list1.add(employeeOne);
    	list1.add(employeeTwo);
    	ArrayList<Employee> list2 = new ArrayList<Employee>();
    	list2.add(employeeTwo);
    	list2.add(employeeThree);
    	ArrayList<Employee> list3 = new ArrayList<Employee>();
    	list3.add(employeeOne);
    	list3.add(employeeThree);

    	Company companyOne = new Company();
    	companyOne.setCompanyName("Google");
    	companyOne.setType("Technology");
    	companyOne.setEmployees(list1);

    	Company companyTwo = new Company();
    	companyTwo.setCompanyName("Whole Foods");
    	companyTwo.setType("Market");
    	companyTwo.setEmployees(list2);
    	
    	Company companyThree = new Company();
    	companyThree.setCompanyName("Best Buy");
    	companyThree.setType("Electronics");
    	companyThree.setEmployees(list3);
    	
    	Set<Employee> semSet = new HashSet<Employee>();
    	semSet.add(employeeThree);
    	semSet.add(employeeTwo);

    	Seminar seminarOne = new Seminar();
    	seminarOne.setTitle("Git");
    	seminarOne.setDescription("Version Control");
    	seminarOne.setEmployees(semSet);

    	Seminar seminarTwo = new Seminar();
    	seminarTwo.setTitle("AWS");
    	seminarTwo.setDescription("SAAS");
    	seminarTwo.setEmployees(semSet);
    	
    	session.persist(seminarOne);
    	session.persist(seminarTwo);

    	session.persist(companyOne);
    	session.persist(companyTwo);
    	session.persist(companyThree);
    	
    	transaction.commit();
    	System.out.println("Successfully saved.");

    	// Query - parameterized HQL and native SQL
    	System.out.println("Regular Query");
    	List<Company> list = session.createQuery("from Company where id > :companyId")
    			.setInteger("companyId", 1).list();
    	Iterator<Company> itr = list.iterator();
    	Iterator<Employee> itr2;
    	
    	while(itr.hasNext()) {
    		Company company = itr.next();
    		System.out.println("Company name: "+ company.getCompanyName());

    		List<Employee> employees = company.getEmployees();
    		itr2 = employees.iterator();
    		while(itr2.hasNext()) {
    			Employee employee = itr2.next();
    			System.out.println("	Employee: "+employee);
    		}

    	}
    	
    	// Criteria - SELECT statement
    	System.out.println("Criteria");
    	list = session.createCriteria(Company.class).add(Restrictions.between("id", 1, 4))
    			.addOrder(Order.desc("companyName")).list();
    	itr = list.iterator();
    	while(itr.hasNext()) {
    		Company company = itr.next();
    		System.out.println("Company name: "+ company.getCompanyName());
    	}

    	
    	// Persist Company from DB to Java Object
    	System.out.println("Saved to Java Object");
    	Company comp = (Company) session.get(Company.class, 3);
    	System.out.println(comp);
    	
    	// Seminar Many To Many relationship
    	List<Seminar> seminarList = session.createQuery("from Seminar").list();
    	Iterator<Seminar> itrSeminar = seminarList.iterator();
    	while(itrSeminar.hasNext()) {
    		System.out.println(itrSeminar.next());
    	}

    	session.close();
    	sessionFactory.close();
    }
}
