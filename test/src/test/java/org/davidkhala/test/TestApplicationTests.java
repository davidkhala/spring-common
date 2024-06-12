package org.davidkhala.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class TestApplicationTests {

    static int port = 7687;
    @ServiceConnection
    @Container
    static Neo4jContainer<?> neo4j = new Neo4jContainer<>("neo4j:5").withExposedPorts(port);


    @Test
    void contextLoads() {

        assertTrue(neo4j.isRunning());
        assertEquals(port, neo4j.getExposedPorts().get(0));
        assertEquals("password", neo4j.getAdminPassword());
    }

}
