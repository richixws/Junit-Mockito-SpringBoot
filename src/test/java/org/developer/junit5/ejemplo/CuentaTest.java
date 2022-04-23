package org.developer.junit5.ejemplo;

import org.developer.junit5.ejemplo.exceptions.DineroInsuficienteException;
import org.developer.junit5.ejemplo.models.Banco;
import org.developer.junit5.ejemplo.models.Cuenta;
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

    @Test
    void testDebitoCuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345",cuenta.getSaldo().toPlainString());
    }

    @Test
    void tetDineroInsuficienteException() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        Exception exception= assertThrows(DineroInsuficienteException.class, ()-> {
            cuenta.debito(new BigDecimal(1500));
        });

        String actual= exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado,actual);
    }

    @Test
    void testTransferirDineroCuentas(){

        Cuenta cuenta1= new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2= new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco= new Banco();
        banco.setNombre("banco de la Nacion");
        banco.transferir(cuenta2,cuenta1, new BigDecimal(500));

        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000",cuenta1.getSaldo().toPlainString());

    }
    @Test
    void testRelacionBancoCuentas(){

        Cuenta cuenta1= new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2= new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco= new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        banco.setNombre("bancos de la Nacion");
        banco.transferir(cuenta2,cuenta1, new BigDecimal(500));

        assertAll(()-> assertEquals("1000.8989",cuenta2.getSaldo().toPlainString()),
                  ()-> assertEquals("3000",cuenta1.getSaldo().toPlainString()),
                  ()-> assertEquals(2,banco.getCuentas().size()),
                  () -> assertEquals("Andres", banco.getCuentas().stream()
                          .filter(c -> c.getPersona().equals("Andres"))
                          .findFirst()
                          .get().getPersona()),
                  () -> assertTrue(banco.getCuentas().stream().anyMatch( c-> c.getPersona().equals("Jhon Doe"))));

        //);
        //assertEquals("100.8989", cuenta2.getSaldo().toPlainString());
        //assertEquals("300",cuenta1.getSaldo().toPlainString());
       // assertEquals(2,banco.getCuentas().size());
    }


}