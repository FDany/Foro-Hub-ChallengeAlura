package com.alura.foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosListaTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion

    ) {

    public DatosListaTopico(Topico datos) {
        this( datos.getId(), datos.getTitulo(), datos.getMensaje(), datos.getFechaCreacion() );
    }

}
