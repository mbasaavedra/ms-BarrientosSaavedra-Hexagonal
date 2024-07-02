package org.codigo.msregistro.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.codigo.*")          // Se indica donde va a buscar beans.
@EntityScan("org.codigo.*")             // Se indica donde va a buscar entidades.
@EnableJpaRepositories("org.codigo")    // Se indica donde van a estar los repositorios, o sea, nuestros JPA Repositorios.
class ApplicationLauncher {
    public static void main(String[] args){
        SpringApplication.run(ApplicationLauncher.class,args);
    }
}
