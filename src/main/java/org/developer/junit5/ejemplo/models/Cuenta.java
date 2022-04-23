package org.developer.junit5.ejemplo.models;

import org.developer.junit5.ejemplo.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;
import java.util.Objects;

public class Cuenta {

      private String persona;
      private BigDecimal saldo;
      private Banco banco;

      public Cuenta(String persona, BigDecimal saldo) {
            this.persona = persona;
            this.saldo = saldo;
      }

      @Override
      public boolean equals(Object obj) {
             if (!(obj instanceof Cuenta)){
                   return false;
             }
             Cuenta c = (Cuenta) obj;
            if (this.persona == null || this.saldo == null){
                  return false;
            }

            return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
      }

      @Override
      public int hashCode() {
            return Objects.hash(persona, saldo);
      }

      public String getPersona() {
            return persona;
      }

      public void setPersona(String persona) {
            this.persona = persona;
      }

      public BigDecimal getSaldo() {
            return saldo;
      }

      public void setSaldo(BigDecimal saldo) {
            this.saldo = saldo;
      }

      //metodo para debito
      public void debito(BigDecimal monto){
          BigDecimal nuevoSaldo = this.saldo.subtract(monto);
          if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
             throw  new DineroInsuficienteException("Dinero Insuficiente");
          }
          this.saldo=nuevoSaldo;
      }

      //metodo para credito
      public void credito(BigDecimal monto){
           this.saldo = this.saldo.add(monto);
      }


}
