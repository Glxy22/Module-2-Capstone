package com.techelevator.tenmo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    public TransferService(String url) {
        this.baseUrl = url;
    }

    public Transfer getTransfer(AuthenticatedUser authenticatedUser, Transfer transfer){
        HttpEntity<Transfer> entity = createTransferEntity(authenticatedUser, transfer);

        try{
            String serializedTransferObject = new ObjectMapper().writeValueAsString(transfer);
            restTemplate.put(baseUrl + "/pending_transfer_status_change", entity);

        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return transfer;
    }

    public boolean pendingTransferStatusChange(AuthenticatedUser authenticatedUser, Transfer transfer){
        HttpEntity<Transfer> entity = createTransferEntity(authenticatedUser, transfer);
        boolean success = false;
        try{
            String serializedTransferObject = new ObjectMapper().writeValueAsString(transfer);
            restTemplate.put(baseUrl + "/pending_transfer_status_change", entity);
            success = true;

        }catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return success;
    }
    private HttpEntity<Transfer> createTransferEntity(AuthenticatedUser authenticatedUser, Transfer transfer) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authenticatedUser.getToken());
        return new HttpEntity<>(transfer, headers);
    }

}
