package com.alura.foroHub.domain.topico;
import com.alura.foroHub.domain.respuesta.Respuesta;
import com.alura.foroHub.domain.curso.Curso;
import com.alura.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    private Boolean activo;


    public Topico( String titulo, String mensaje, Usuario usuario, Curso curso){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = "activo";
        this.usuario = usuario;
        this.curso = curso;
        this.activo = true;
    }

    public void actualizarDatos(String titulo, String mensaje, String status, Curso curso){
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.status = status;
        this.curso = curso;
    }

    public void desactivarTopico(){
        this.activo = false;
    }
}
