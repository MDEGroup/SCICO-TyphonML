package it.univaq.typhon.cassandra.test;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import com.datastax.oss.driver.api.core.CqlSession;

import it.univaq.typhon.cassandra.test.model.Clerance;

public class CassandraApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(CassandraApplication.class);

  protected static Clerance newPerson(String name, int age) {
    return new Clerance(UUID.randomUUID().toString(), name, age);
  }

  public static void main(String[] args) {

    CqlSession cqlSession = CqlSession.builder().withKeyspace("bezkoder").build();

    CassandraOperations template = new CassandraTemplate(cqlSession);

    Clerance jonDoe = template.insert(newPerson("Jon Doe", 40));
    LOGGER.info("__________JURI___________");
    LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Clerance.class).getId());

//    template.truncate(Person.class);
    cqlSession.close();
  }

}
