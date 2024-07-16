package com.alura.foroHub.controller;

import com.alura.foroHub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@ResponseBody
public class TopicoController {

    @Autowired
    private TopicoRepository repositorio;

    @Autowired
    private TopicoService servicio;


    @GetMapping
    public ResponseEntity< Page<DatosListaTopico> > listarTopicos(@PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion){

        var page = this.repositorio.findByActivoTrue(paginacion).map(DatosListaTopico::new);

        return ResponseEntity.ok(page);
    }

    // Detalles
    @GetMapping("/{id}")
    public ResponseEntity detallesDatosTopico(@PathVariable Long id) {
        if ( this.repositorio.existsById(id) && this.repositorio.findActivoById(id) ){
            var topico = this.repositorio.getReferenceById(id);
            return ResponseEntity.ok( new DatosDetallesTopico(topico) );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El topico solicitado no existe.");
    }

    // Registrar
    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosTopico datos){
        var topico = servicio.registrar(datos);
        return ResponseEntity.ok(new DatosDetallesTopico(topico));
    }

    // Actualizacion
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarDatosTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = this.servicio.actualizar(id, datos);
        return ResponseEntity.ok(new DatosListaTopico(topico));
    }

    // Eliminacion
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        if (this.repositorio.existsById(id) && this.repositorio.findActivoById(id)){
            //Delete logico
            Topico topico = this.repositorio.getReferenceById(id);
            topico.desactivarTopico();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El topico a eliminar no existe.");
    }
}
