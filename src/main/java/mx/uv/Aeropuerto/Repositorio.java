package mx.uv.Aeropuerto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface Repositorio extends CrudRepository<Aeropuerto,Integer> {
    Aeropuerto findByNombre(String nombre);
    
}
