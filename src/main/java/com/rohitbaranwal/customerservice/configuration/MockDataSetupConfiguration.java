package com.rohitbaranwal.customerservice.configuration;


import com.rohitbaranwal.customerservice.dao.CustomerDao;
import com.rohitbaranwal.customerservice.entity.AddressType;
import com.rohitbaranwal.customerservice.entity.Addresses;
import com.rohitbaranwal.customerservice.entity.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MockDataSetupConfiguration implements CommandLineRunner {

    private final CustomerDao customerDao;

    private static Addresses createBangaloreOfficeAddress() {
        Addresses addresses3 = new Addresses();
        addresses3.setAddress1("8th Floor, DLF, White Field");
        addresses3.setCity("Bangalore");
        addresses3.setState("Karnataka");
        addresses3.setZipcode("432121");
        addresses3.setType(AddressType.OFFICE);
        return addresses3;
    }

    private static Addresses createPuneAddress() {
        Addresses addresses2 = new Addresses();
        addresses2.setAddress1("High Way");
        addresses2.setAddress2("Near StarBucks");
        addresses2.setCity("Pune");
        addresses2.setState("Maharastra");
        addresses2.setZipcode("123456");
        addresses2.setType(AddressType.OTHERS);
        return addresses2;
    }

    private static Addresses createNewJerseyAddress() {
        Addresses addresses4 = new Addresses();
        addresses4.setAddress1("New Road");
        addresses4.setAddress2("Post Office BO Block");
        addresses4.setCity("Santa Clara");
        addresses4.setState("New Jersey");
        addresses4.setZipcode("469772249");
        addresses4.setType(AddressType.OFFICE);
        return addresses4;
    }

    private static Addresses createKolkataAddress() {
        Addresses addresses5 = new Addresses();
        addresses5.setAddress1("Old Station Road");
        addresses5.setCity("Kolkata");
        addresses5.setState("West Bengal");
        addresses5.setZipcode("111111");
        addresses5.setType(AddressType.OTHERS);
        return addresses5;
    }

    private static Addresses createBangaloreHomeAddress() {
        Addresses addresses1 = new Addresses();
        addresses1.setAddress1("MH Street");
        addresses1.setAddress2("Near McDonalds");
        addresses1.setCity("Bangalore");
        addresses1.setState("Karnataka");
        addresses1.setZipcode("432121");
        addresses1.setType(AddressType.HOME);
        return addresses1;
    }

    private static Customer createCustomer(String firstName, String lastName, List<Addresses> addressesList) {
        Customer customer1 = new Customer();
        customer1.setCustomerId(UUID.randomUUID());
        customer1.setFirstName(firstName);
        customer1.setLastName(lastName);
        customer1.setMobileNumber("Test");
        customer1.setSpendingLimit(100000.0);
        customer1.setAddressesList(addressesList);
        addressesList.forEach(addresses -> addresses.setCustomer(customer1));
        return customer1;
    }

    @Override
    public void run(String... args) {
        log.info("Mock Data Setup Started...");
        generateCustomers(5);
        log.info("Mock Data Setup Completed...");

    }

    private void generateCustomers(int numberOfCustomers) {

        customerDao.save(createCustomer("PM", "Manager", List.of(createNewJerseyAddress())));
        customerDao.save(createCustomer("Test", "QA", List.of(createBangaloreHomeAddress(), createBangaloreOfficeAddress())));
        customerDao.save(createCustomer("Dev", "FE", List.of(createPuneAddress(), createKolkataAddress())));
        customerDao.save(createCustomer("Dev", "BE", List.of(createBangaloreHomeAddress(), createNewJerseyAddress())));
    }

}
