package com.alura.foroHub.domain.topico;

import com.alura.foroHub.domain.curso.DatosCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        Long usuario_id,

        @NotBlank
        String curso

) {
}
