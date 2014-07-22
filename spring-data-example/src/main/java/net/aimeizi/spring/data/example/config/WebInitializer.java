package net.aimeizi.spring.data.example.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		
		applicationContext.register(WebConfig.class);
		
		servletContext.addListener(new ContextLoaderListener(applicationContext));
		
		applicationContext.setServletContext(servletContext);
		
		FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncodingFilter", org.springframework.web.filter.CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "utf-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
		
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", new DispatcherServlet(applicationContext));
		
		dispatcher.setLoadOnStartup(1);
		
		dispatcher.addMapping("/");
	}
	
}
