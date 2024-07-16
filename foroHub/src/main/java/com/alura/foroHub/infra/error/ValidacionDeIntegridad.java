package com.alura.foroHub.infra.error;

public class ValidacionDeIntegridad extends RuntimeException {

    public ValidacionDeIntegridad(String s) {
        super(s);
    }

}
