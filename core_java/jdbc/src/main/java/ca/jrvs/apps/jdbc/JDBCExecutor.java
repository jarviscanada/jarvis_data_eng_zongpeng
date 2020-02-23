package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCExecutor {

  public static void main(String[] args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "hplussport", "postgres", "password");
    try{
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
	    
      /*Creating data example...*/
      Customer customer = new Customer();
      customer.setFirstName("Edward");
      customer.setLastName("Wang");
      customer.setEmail("EdwardWang@jrvs.ca");
      customer.setPhone("(647) 416-6666");
      customer.setAddress("1234 That Rd");
      customer.setCity("Toronto");
			customer.setState("ON");;
      customer.setZipCode("M1G1Q1");
      customerDAO.create(customer);

      /*Reading data example...*/
      customer = customerDAO.findById(10003);
      System.out.println(customer.getFirstName() + " " + customer.getLastName());
      
      /*Update data example...*/
      customer = customerDAO.findById(10002);
      System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " +
           customer.getEmail());
      customer.setEmail("AnotherEdwardEmail@jrvs.ca");
      System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " +
           customer.getEmail());
      
      /*Delete data example and trying new toString()...*/
      customer = new Customer();
      customer.setFirstName("Zongpeng");
      customer.setLastName("Yang");
      customer.setEmail("13zy20@queensu.ca");
      customer.setAddress("1234 This Rd");
      customer.setCity("Kingston");
      customer.setState("ON");
      customer.setPhone("(613) 613-1234");
      customer.setZipCode("K7L3V8");

      Customer dbCustomer = customerDAO.create(customer);
      System.out.println(dbCustomer);
      dbCustomer = customerDAO.findById(dbCustomer.getId());
			System.out.println(dbCustomer);
      dbCustomer.setEmail("yangzongpeng@hotmail.com");
      dbCustomer = customerDAO.update(dbCustomer);
      System.out.println(dbCustomer);
      customerDAO.delete(dbCustomer.getId());

      OrderDAO orderDAO = new OrderDAO(connection);

      /*Find order by id example...*/
      Order order = orderDAO.findById(1000);
      System.out.println(order);

      /*Find all order detail by id using stored procedure...*/
      List<Order> orders = orderDAO.getOrdersForCustomer(789);
      orders.forEach(System.out::println);

      /*Paging testing... */
      customerDAO.findAllSorted(20).forEach(System.out::println);
      System.out.println("Paged");
      for (int i=1; i<3; i++){
        System.out.println("Page number: " + i);
        customerDAO.findAllPaged(10, i).forEach(System.out::println);
      }

    }catch(SQLException e){
      throw new RuntimeException("Unable to execute the SQL query.", e);
    }
  }

}


