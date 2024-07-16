package com.alura.foroHub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    //Page<Curso> findByActivoTrue(Pageable paginacion);
    Curso findByNombre(String nombre);

   //boolean existsByNombre(String nombre);
   Boolean existsByNombre(String nombre);

}
