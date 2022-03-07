package org.developer.junit5.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {


    @Test
    void testNombreCuenta() {

        Cuenta cuenta = new Cuenta("richard",  new BigDecimal( "1000.12345")) ;
        cuenta.setPersona("richard");
        String esperado = "richard";

        Assertions.assertEquals(esperado,cuenta.getPersona());
        assertEquals(esperado,cuenta.getPersona());
        assertTrue(esperado.equals(cuenta.getPersona()));
    }

    @Test
    void testSaldoCuenta(){

        Cuenta cuenta = new Cuenta("richard", new BigDecimal("100.12345"));
        assertEquals(100.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO)> 0);
    }

    @Test
    void testReferenciaCuenta(){

        Cuenta cuenta = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));

        assertEquals(cuenta2,cuenta);

    }


}