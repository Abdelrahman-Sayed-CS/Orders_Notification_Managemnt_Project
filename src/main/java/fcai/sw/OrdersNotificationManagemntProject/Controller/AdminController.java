package fcai.sw.OrdersNotificationManagemntProject.Controller;

import fcai.sw.OrdersNotificationManagemntProject.Models.Customer;
import fcai.sw.OrdersNotificationManagemntProject.Services.AdminService;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AdminAPI")
public class AdminController {
    AdminService adminService;

    public AdminController(){
        adminService = new AdminService();
    }
//  get most notifiedMail option
    @GetMapping("/mostNotifiedCustomer")
    public String mostNotifiedMail(){
        Customer customer = new Customer();
        customer = adminService.mostNotifiedCustomer();
        if (customer.getNumNotifiedInEmail() != 0)
            return "Not any email notified";
        return "The most notified email " + customer.getEmail() + " for customer " + customer.getUsername();
    }
//  The most sent notification template option
    @GetMapping("/mostNotifiedTemplate")
    public String mostNotifiedTempalte(){
        int typeCheck = adminService.mostSentNotificationTemplate();
//        shipping most --> set typeCheck with 0
//        placement most ---> set typeCheck with 1
//        else -1
        if (typeCheck == 0)
            return "Not have any notify message";
        String type = (typeCheck == 1)?"Placement Order":"Shipping Order";
        return "The most sent notification template is " + type;
    }
}