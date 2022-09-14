package com.heeere.grouped.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import com.heeere.grouped.orders.model.Order;
import com.heeere.grouped.orders.model.OrderSheet;
import com.heeere.grouped.orders.model.Product;
import com.heeere.grouped.orders.model.ProductGroup;
import com.heeere.grouped.orders.model.UserGroup;
import com.heeere.grouped.orders.model.repos.OrderRepository;
import com.heeere.grouped.orders.model.repos.OrderSheetRepository;
import com.heeere.grouped.orders.model.repos.ProductGroupRepository;
import com.heeere.grouped.orders.model.repos.ProductRepository;
import com.heeere.grouped.orders.model.repos.UserGroupRepository;
import com.heeere.grouped.orders.model.user.User;
import com.heeere.grouped.orders.model.user.UserRepository;
import com.heeere.grouped.orders.model.user.UserService;

import org.springframework.stereotype.Service;

/**
 * AddTestData
 */
/* ___GENERATETESTDATA___ */
@Service
public class AddTestData {

    @Inject
    UserService userService; // This will be set automatically (injection) by spring data, with an implementation of UserService
    @Inject
    UserRepository userRepository; // etc.
    @Inject
    ProductRepository productRepository;
    @Inject
    ProductGroupRepository productGroupRepository;
    @Inject
    OrderSheetRepository orderSheetRepository;
    @Inject
    UserGroupRepository userGroupRepository;
    @Inject
    OrderRepository orderRepository;
    
    interface RandomElement {
         <T> T gen(List<T> l);
    }

    // See class AutoPopulate that calls this method on app initialization
    public void generateTestData() {
        System.err.println("###################### " + userRepository.count() );
        /* ___GENERATETESTDATA___ */
        if (userRepository.count() != 0) return; // do not do anything if there are already som data

        Random r = new Random(202109);
        RandomElement re = new RandomElement(){
                @Override
                public <T> T gen(List<T> l) {
                    return l.get(r.nextInt(l.size()));
                }
        };
        
        // Users
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            /* ___GENERATETESTDATA___ */
            users.add(userService.saveUserComputingDerivedPassword(new User("user"+i), "pwd"+i));
        }
        /* ___GENERATETESTDATA___ */
        User admin = new User("abby");
        users.add(userService.saveUserComputingDerivedPassword(admin, "cd"));
        userService.makeUserAdmin("abby");

        List<UserGroup> userGroups = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            UserGroup ug = new UserGroup("ugroup"+i);
            for (int j = 0; j < 4; j++) {
                ug.getUsers().add(re.gen(users));
            }
            if (i == 0) {
                ug.getUsers().add(admin);
            }
            userGroups.add(userGroupRepository.save(ug));
        }


        // Products
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            products.add(productRepository.save(new Product("prod"+i, "kg", 5+i/10.f)));
        }

        List<ProductGroup> productGroups = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ProductGroup pg = new ProductGroup("pgroup"+i);
            for (int j = 0; j < 4; j++) {
                pg.getProducts().add(re.gen(products));
            }
            productGroups.add(productGroupRepository.save(pg));
        }

        // Order Sheets
        List<OrderSheet> orderSheets = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            OrderSheet os = new OrderSheet("OrderSheet"+i);
            os.setDate(new Date());
            for (int j = 0; j < 2; j++) {
                os.getProductGroups().add(re.gen(productGroups));
            }
            for (int j = 0; j < 2; j++) {
                os.getUserGroups().add(re.gen(userGroups));
            }
            List<Order> orders = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                User u = re.gen(re.gen(os.getUserGroups()).getUsers());
                Product p = re.gen(re.gen(os.getProductGroups()).getProducts());
                Order o = new Order(u, r.nextInt(30)/10f, p);
                o.setOrderSheet(os);
                // orderRepository.save(o); // no need as OrderSheet will cascade it
                os.getOrders().add(o);
                orders.add(o);
            }
            orderSheets.add(orderSheetRepository.save(os));
        }


    }

}