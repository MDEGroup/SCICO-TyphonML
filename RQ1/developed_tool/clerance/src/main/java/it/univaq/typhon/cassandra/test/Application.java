package it.univaq.typhon.cassandra.test;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import it.univaq.typhon.cassandra.test.model.Clerance;
import it.univaq.typhon.cassandra.test.model.repository.CleranceRepository;

@SpringBootApplication
@EnableCassandraRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired CleranceRepository customers;

	public @PostConstruct void init() {
		customers.save(new Clerance(UUID.randomUUID().toString(),"Mio1",51.0f));
		customers.save(new Clerance(UUID.randomUUID().toString(),"Mio2",52.0f));
		customers.save(new Clerance(UUID.randomUUID().toString(),"Mio3",53.0f));
		customers.save(new Clerance(UUID.randomUUID().toString(),"Mio4",54.0f));
	}
}
