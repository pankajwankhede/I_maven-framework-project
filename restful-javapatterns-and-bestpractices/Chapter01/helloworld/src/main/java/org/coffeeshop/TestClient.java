package org.coffeeshop;

import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Simple client code for JAX-RS
 * @author Bhakti Mehta
 */
@WebServlet(name = "TestClient", urlPatterns = {"/TestClient"} ,asyncSupported=true)
public class TestClient extends HttpServlet {
    private final static String TARGET_URI = "http://localhost:8080/helloworld/v1/coffees/orders";


    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Client client = ClientBuilder.newBuilder().register(MessageBodyReaderWriter.class)
                       .build();
        final javax.ws.rs.client.WebTarget webTarget;
        try {
            final PrintWriter out = response.getWriter();
            webTarget = client.target(new URI(TARGET_URI));
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Helloworld TestClient</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>");

            javax.ws.rs.core.Response resourceResponse =
                    webTarget.path("1").request(MediaType.TEXT_PLAIN_TYPE).get();
            out.println("Coffee order " + resourceResponse.readEntity(String.class));
            out.println("</h1>");


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
