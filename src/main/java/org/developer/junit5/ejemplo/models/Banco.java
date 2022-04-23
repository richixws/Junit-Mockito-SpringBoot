package org.developer.junit5.ejemplo.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nombre;
    private List<Cuenta>  cuentas;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }
    public void setCuentas(List<Cuenta> cuentas) {   this.cuentas = cuentas; }
    public String getNombre() {return nombre;}



    public void addCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto){

        origen.debito(monto); //resta la cantidad
        destino.credito(monto); //deposita la cantidad cuenta destino
    }


}
