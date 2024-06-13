package org.davidkhala.spring.data.testcontainers;

import org.testcontainers.containers.GenericContainer;

import java.util.List;

public class Generic {

    public static int getExposedPort(GenericContainer container) {
        List<Integer> ports = container.getExposedPorts();
        return ports.getFirst();
    }
}
