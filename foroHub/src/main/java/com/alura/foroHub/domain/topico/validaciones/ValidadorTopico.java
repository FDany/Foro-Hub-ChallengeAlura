package com.alura.foroHub.domain.topico.validaciones;

import com.alura.foroHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopico implements ValidacionTopicos{

    @Autowired
    private TopicoRepository repo;


    @Override
    public void validar(String titulo, String mensaje) {

        if( this.repo.existsByTituloAndMensaje( titulo, mensaje ) )
            throw new ValidationException("El topico proporcionado, ya esta registrado.");
    }
}
