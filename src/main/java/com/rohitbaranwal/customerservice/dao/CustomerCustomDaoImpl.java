package com.rohitbaranwal.customerservice.dao;

import com.rohitbaranwal.customerservice.dto.request.CustomerSearchRequest;
import com.rohitbaranwal.customerservice.dto.response.CustomerResponseDto;
import com.rohitbaranwal.customerservice.entity.Addresses;
import com.rohitbaranwal.customerservice.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class CustomerCustomDaoImpl implements CustomerCustomDao {

    @Autowired
    ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    public List<CustomerResponseDto> getCustomersList(CustomerSearchRequest customerSearchRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> customerRoot = cq.from(Customer.class);
        Join<Customer, Addresses> addressJoin = customerRoot.join("addressesList", JoinType.INNER);

        List<Predicate> mainPredicates = new ArrayList<>();
        List<Predicate> namePredicates = new ArrayList<>();
        if (customerSearchRequest.getName() != null) {
            String lowerCasePattern = "%" + customerSearchRequest.getName().toLowerCase() + "%";
            namePredicates.add(cb.like(cb.lower(customerRoot.get("firstName")), lowerCasePattern));
            namePredicates.add(cb.like(cb.lower(customerRoot.get("lastName")), lowerCasePattern));
        }

        if (!namePredicates.isEmpty()) {
            mainPredicates.add(cb.or(namePredicates.toArray(new Predicate[0])));
        }

        if (customerSearchRequest.getCity() != null) {
            String lowerCasePattern = "%" + customerSearchRequest.getCity().toLowerCase() + "%";
            mainPredicates.add(cb.like(cb.lower(addressJoin.get("city")), lowerCasePattern));
        }

        if (customerSearchRequest.getState() != null) {
            String lowerCasePattern = "%" + customerSearchRequest.getState().toLowerCase() + "%";
            mainPredicates.add(cb.like(cb.lower(addressJoin.get("state")), lowerCasePattern));
        }


        if (!mainPredicates.isEmpty()) {
            cq.where(cb.and(mainPredicates.toArray(new Predicate[0])));
        }

        List<Customer> customers = entityManager.createQuery(cq).getResultList();

        return customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).collect(Collectors.toList());
    }
}
