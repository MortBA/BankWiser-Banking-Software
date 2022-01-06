package com.logic.bankwiser.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Alternative tests for Epic Feature 4.
 *
 * @author Daniel Dovhun
 * @author Mathias Hallander
 */
public class Epic4AlternativeTests {
    private Facade facade;

    @BeforeEach
    public void setup() {
        facade = new Facade(true);


    }

    @Test
    public void requestMapAccessDenied() {
        //TODO fail to get request map
    }
}
