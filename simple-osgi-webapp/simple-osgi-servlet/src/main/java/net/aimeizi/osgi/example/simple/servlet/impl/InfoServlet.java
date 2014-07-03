package net.aimeizi.osgi.example.simple.servlet.impl;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.aimeizi.osgi.example.simple.bundle.api.InfoBroker;

import org.osgi.util.tracker.ServiceTracker;

public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1110490906466282279L;
	private ServiceTracker serviceTracker;

	public InfoServlet(ServiceTracker serviceTracker) {
		this.serviceTracker = serviceTracker;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.getOutputStream().println("<html><body>");
			InfoBroker broker = (InfoBroker) serviceTracker
					.getService();
			if (broker != null) {
				res.getOutputStream().println(broker.getInformation());
			}
			res.getOutputStream().println("</body></html>");
		} catch (IOException e) {
		}
	}
}
