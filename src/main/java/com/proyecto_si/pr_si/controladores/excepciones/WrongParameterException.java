package com.proyecto_si.pr_si.controladores.excepciones;

public class WrongParameterException extends RuntimeException {
    public WrongParameterException(String message) {
        super(message);
    }
}