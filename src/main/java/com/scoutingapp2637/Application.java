package com.scoutingapp2637;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Theme(value = "scoutingapp2637")
@PWA(name = "PhantomCatz Scouting App", shortName = "PhantomCatz Scouting App", offlineResources = {})
public class Application implements AppShellConfigurator {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }

}