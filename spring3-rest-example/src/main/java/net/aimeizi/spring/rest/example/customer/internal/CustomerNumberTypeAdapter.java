package net.aimeizi.spring.rest.example.customer.internal;

import net.aimeizi.spring.rest.example.customer.CustomerNumber;
import javax.xml.bind.annotation.adapters.XmlAdapter;




/**
 * @author Oliver Gierke
 */
public class CustomerNumberTypeAdapter extends
        XmlAdapter<String, CustomerNumber> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
     */
    @Override
    public String marshal(CustomerNumber v) throws Exception {

        return v.getNumber();
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
     */
    @Override
    public CustomerNumber unmarshal(String v) throws Exception {

        return new CustomerNumber(v);
    }
}
