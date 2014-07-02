package net.aimeizi.spring.rest.example.customer;

import java.util.UUID;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import net.aimeizi.spring.rest.example.customer.internal.CustomerNumberTypeAdapter;


/**
 * Value object for {@link Customer} numbers.
 * 
 * @author Oliver Gierke
 */
@Data
@XmlType
@XmlJavaTypeAdapter(CustomerNumberTypeAdapter.class)
public class CustomerNumber {

    public String getNumber() {
		return number;
	}


	private final String number;


    @Override
    public String toString() {
        
        return number;
    }


    /**
     * Creates a new random {@link CustomerNumber}.
     * 
     * @return
     */
    public static CustomerNumber next() {

        return new CustomerNumber(UUID.randomUUID().toString());
    }


	public CustomerNumber(String number) {
		super();
		this.number = number;
	}
}
