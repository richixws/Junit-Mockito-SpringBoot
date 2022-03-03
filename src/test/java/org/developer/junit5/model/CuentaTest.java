package org.developer.junit5.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {


    @Test
    void testNombreCuenta() {

        Cuenta cuenta = new Cuenta();
        cuenta.setPersona("richard");

        String esperado = "richard";

        Assertions.assertEquals(esperado,cuenta.getPersona());
        assertEquals(esperado,cuenta.getPersona());
    }

}