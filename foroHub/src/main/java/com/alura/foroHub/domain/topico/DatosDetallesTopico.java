package com.alura.foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetallesTopico(

        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        String status,
        String usuario,
        String curso

    ) {

    public DatosDetallesTopico(Topico datos){
        this( datos.getId(), datos.getTitulo(), datos.getMensaje(), datos.getFechaCreacion(),
                datos.getStatus(), datos.getUsuario().getNombre(), datos.getCurso().getNombre() );
    }

}
