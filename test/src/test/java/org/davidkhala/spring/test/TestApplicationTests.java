package org.davidkhala.spring.test;

import org.junit.jupiter.api.Test;
import org.neo4j.driver.Value;
import org.neo4j.driver.internal.security.InternalAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jConnectionDetails;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class TestApplicationTests {

    static int port = 7687;
    @Container
    @ServiceConnection
    static Neo4jContainer<?> neo4j = new Neo4jContainer<>("neo4j:latest").withExposedPorts(port);

    @Autowired
    Neo4jConnectionDetails neo4jConnectionDetails;

    @Test
    void loadNeo4j() {

        assertTrue(neo4j.isRunning());
        assertEquals(port, neo4j.getExposedPorts().get(0));
        assertEquals("password", neo4j.getAdminPassword());
        System.out.println(neo4jConnectionDetails.getUri());
        InternalAuthToken token = (InternalAuthToken) neo4jConnectionDetails.getAuthToken();
        Map<String, Value> tokenValues = token.toMap();
        assertEquals("neo4j", tokenValues.get("principal").asString());
        assertEquals("basic", tokenValues.get("scheme").asString());
        assertEquals("password", tokenValues.get("credentials").asString());




    }


}
