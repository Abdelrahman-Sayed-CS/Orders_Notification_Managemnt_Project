package fcai.sw.OrdersNotificationManagemntProject.Database;
import fcai.sw.OrdersNotificationManagemntProject.Models.Customer;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
@Service
public class CustomerDB {
    private static ArrayList<Customer> customerDatabase = new ArrayList<>();
    public CustomerDB() {
        if (customerDatabase.isEmpty()) {
            setData();
        }
    }
    public int getNumUsers() {
        return customerDatabase.size();
    }
    public void setData() {
        String[] USERNAMES = {"user1", "user2", "user3", "user4", "user5", "user6", "user7", "user8", "user9", "user10"};
        String[] PASSWORDS = {"pass1@#", "pass2@#", "pass3@#", "pass4@#", "pass5@#", "pass6@#", "pass7@#", "pass8@#", "pass9@#", "pass10@#"};
        String[] EMAILS = {"user1@example.com", "user2@example.com", "user3@example.com", "user4@example.com", "user5@example.com",
                "user6@example.com", "user7@example.com", "user8@example.com", "user9@example.com", "user10@example.com"};
        String[] LOCATIONS = {"Location1", "Location1", "Location2", "Location2", "Location1", "Location3", "Location3", "Location3", "Location5", "Location5"};
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer();
            customer.setUsername(USERNAMES[i]);
            customer.setPassword(PASSWORDS[i]);
            customer.setEmail(EMAILS[i]);
            customer.setLocation(LOCATIONS[i]);
            customer.setBalance(10000000); // Random float balance between 0 and 1000
            customer.setNumNotifiedInEmail(0); // Random integer between 0 and 9
            customerDatabase.add(customer);
        }
    }
    public Customer getCustomerByUsername(String usrName) {
        for (Customer customer : customerDatabase) {
            if (customer.getUsername().equals(usrName))
                return customer;
        }
        return null;
    }
    //    using during login
    public boolean isExist(Customer customer) {
        for (int i = 0; i < getNumUsers(); i++) {
            if (customer.getUsername().equals(customerDatabase.get(i).getUsername()) && customer.getPassword().equals(customerDatabase.get(i).getPassword()))
                return true;
        }
        return false;
    }
    //   check if this username is Unique
    public boolean isUnique(String customerUsername) {
        if (customerDatabase.isEmpty()) {
            return true;
        }
        for (int i = 0; i < getNumUsers(); i++) {
            // Username is not unique
            if (customerUsername.equals(customerDatabase.get(i).getUsername()))
                return false;
        }
        // OtherWise return true (unique)
        return true;
    }
    //    update balance of customer
    public void updateBalance(String username, float money) {
        for (int i = 0; i < getNumUsers(); i++) {
            if (username.equals(customerDatabase.get(i).getUsername())) {
                customerDatabase.get(i).setBalance(customerDatabase.get(i).getBalance() - money);
                break;
            }
        }
    }
    //   add new customer
    public String addCustomer(Customer customer) {
        customerDatabase.add(customer);
        return "This Customer is added successfully";
    }

    public Customer searchMostNotifiedEmail() {
        int mx = 0;
        Customer customer = new Customer();
        for (int i = 0; i < getNumUsers(); i++) {
            if (customerDatabase.get(i).getNumNotifiedInEmail() > mx) {
                customer = customerDatabase.get(i);
                mx = customerDatabase.get(i).getNumNotifiedInEmail();
            }
        }
        return customer;
    }
    //    update state of shipment
    public void updateNumNotifiedInEmail(String username) {
        for (Customer customer : customerDatabase) {
            if (customer.getUsername().equals(username))
                customer.setNumNotifiedInEmail(customer.getNumNotifiedInEmail() + 1);
        }
    }

    //   for testing
    public static ArrayList<Customer> getCustomers() {
        return customerDatabase;
    }
}