package org.jersey.jaxb.ws.example.client;

import javax.ws.rs.core.MediaType;

import org.jersey.jaxb.ws.example.model.EmpRequest;
import org.jersey.jaxb.ws.example.model.EmpResponse;
import org.jersey.jaxb.ws.example.model.ErrorResponse;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
 
public class EmpClient {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        String uri = "http://localhost:8080/rest/emp/getEmp";
        EmpRequest request = new EmpRequest();
        request.setId(2);
        request.setName("PK");
        try{
        Client client = Client.create();
        WebResource r=client.resource(uri);
        ClientResponse response = r.type(MediaType.APPLICATION_XML).post(ClientResponse.class,request );
        System.out.println(response.getStatus());
        if(response.getStatus() == 200){
            EmpResponse empResponse = response.getEntity(EmpResponse.class);
            System.out.println(empResponse.getId() + "::"+empResponse.getName());
        }else{
            ErrorResponse exc = response.getEntity(ErrorResponse.class);
            System.out.println(exc.getErrorCode());
            System.out.println(exc.getErrorId());
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
 
}
