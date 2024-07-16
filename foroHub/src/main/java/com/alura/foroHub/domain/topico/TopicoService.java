package com.alura.foroHub.domain.topico;

import com.alura.foroHub.domain.curso.*;
import com.alura.foroHub.domain.curso.CursoRepository;
import com.alura.foroHub.domain.topico.validaciones.ValidacionTopicos;
import com.alura.foroHub.domain.usuario.Usuario;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
import com.alura.foroHub.infra.error.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepo;

    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    List<ValidacionTopicos> validadores;


    public Topico registrar(DatosTopico datos){
        if( !this.usuarioRepo.findById(datos.usuarioID()).isPresent() )
            throw new ValidacionDeIntegridad("El id del usuario es invalido.");
        if( !this.cursoRepo.existsByNombre(datos.curso()) )
            throw new ValidacionDeIntegridad("El curso ingresado/seleccionado no existe.");

        //Validaciones
        this.validadores.forEach(v -> v.validar( datos.titulo(), datos.mensaje() ));

        Usuario usuario = this.usuarioRepo.getReferenceById(datos.usuarioID());

        Curso curso = this.cursoRepo.findByNombre( datos.curso() );

        Topico topico = new Topico( datos.titulo(), datos.mensaje(), usuario, curso );

        this.topicoRepo.save(topico);

        return topico;
    }

    public Topico actualizar(Long id, DatosActualizarTopico datos){
        Topico topico = this.topicoRepo.getReferenceById( id );

        if( !this.cursoRepo.existsByNombre( datos.curso() ) )
            throw new ValidacionDeIntegridad("El curso elegido no existe.");

        this.validadores.forEach(v -> v.validar( datos.titulo(), datos.mensaje() ));

        Curso curso = this.cursoRepo.findByNombre( datos.curso() );

        topico.actualizarDatos( datos.titulo(), datos.mensaje(), datos.status(), curso );

        return topico;
    }


}
