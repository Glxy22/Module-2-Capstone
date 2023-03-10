package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceDto implements AccountService{
    private final String baseUrl;
    private RestTemplate restTemplate;

    public AccountServiceDto(String baseurl){
        this.baseUrl= baseurl;
        this.restTemplate =new RestTemplate();
    }

    @Override
    public Account getBalance(AuthenticatedUser authenticatedUser) {
        HttpEntity entity = createHttpEntity(authenticatedUser);
        Account balance = null;

        try {
            balance = restTemplate.exchange(baseUrl + "/balance",
                    HttpMethod.GET,
                    entity,
                    Account.class
            ).getBody();
        } catch(RestClientResponseException e) {
            System.out.println("Could not complete request. Code: " + e.getRawStatusCode());
        } catch(ResourceAccessException e) {
            System.out.println("Could not complete request due to server network issue. Please try again.");
        }

        return balance;
    }
 // getting list of transaction for current user

    @Override
    public Transfer[] list_transaction(AuthenticatedUser authenticatedUser) {
        HttpEntity entity = createHttpEntity(authenticatedUser);
        Transfer[] transfer = new Transfer[0];
        try {
            ResponseEntity<Transfer[]> responseEntity = restTemplate.exchange(
                    baseUrl + "/list_transaction", HttpMethod.GET,entity, Transfer[].class);

            transfer = responseEntity.getBody();
            System.out.println("Response status code: " + responseEntity.getStatusCodeValue()); // Debug statement
//            Transfer[] transfer = responseEntity.getBody();
            System.out.println("Number of transfers: " + transfer.length); // Debug statement
        } catch(RestClientResponseException e) {
            System.out.println("Could not complete request. Code: " + e.getRawStatusCode());
        } catch(ResourceAccessException e) {
            System.out.println("Could not complete request due to server network issue. Please try again.");
        }
        System.out.println("transfer length "+ transfer.length);
        return transfer;
    }



//    @Override
//    public Transfer[] list_transaction(AuthenticatedUser authenticatedUser) {
//        HttpEntity entity = createHttpEntity(authenticatedUser);
//        Transfer[] transfer = null;
//
//        try {
//            ResponseEntity<Transfer[]> response = restTemplate.exchange(baseUrl + "/list_transaction", HttpMethod.GET,
//                    entity, Transfer[].class).getBody();
//        } catch(RestClientResponseException e) {
//            System.out.println("Could not complete request. Code: " + e.getRawStatusCode());
//        } catch(ResourceAccessException e) {
//            System.out.println("Could not complete request due to server network issue. Please try again.");
//        }
//
//        return transfer;
//    }
//
//
    private HttpEntity createHttpEntity(AuthenticatedUser authenticatedUser) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(authenticatedUser.getToken());
        HttpEntity entity = new HttpEntity(httpHeaders);
        return entity;
    }
}
