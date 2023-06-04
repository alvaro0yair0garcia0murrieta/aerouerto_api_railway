package mx.uv.Aeropuerto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@SpringBootApplication
@CrossOrigin(origins = "*") // Configuración del encabezado "Access-Control-Allow-Origin"
public class AeropuertoApplication {
	@Autowired
	private Repositorio repositorio;
	public static void main(String[] args) {
		SpringApplication.run(AeropuertoApplication.class, args);
	}
	//curl -X GET localhost:8080/
	@GetMapping("/")
	public String returnRoot(){
		return "Esta es la raiz";
	}

	@GetMapping(value="/aeropuertos")
	public List<Aeropuerto> getMethodName() {
		Iterable<Aeropuerto> aeropuertos = repositorio.findAll();
   		List<Aeropuerto> aeropuertosList = new ArrayList<>();
   		aeropuertos.forEach(aeropuertosList::add); 
		return aeropuertosList;
	}
	@GetMapping(value = "/aeropuertos/{nombre}")
	public Aeropuerto getAeropuertoByNombre(@PathVariable String nombre){
		Aeropuerto aeropuerto = repositorio.findByNombre(nombre);
		return aeropuerto;
	}
	//no se pueden poner nombres iguales porque crachea la api
	@PostMapping(value = "/aeropuertos")
	public Aeropuerto postAeropuerto(@RequestBody Aeropuerto aeropuerto){
		repositorio.save(aeropuerto);
		return aeropuerto;
	}
	@DeleteMapping(value = "/aeropuertos/{nombre}")
	public Aeropuerto deleteAeropuertoByNombre(@PathVariable String nombre){
		Aeropuerto aeropuerto = repositorio.findByNombre(nombre);
		repositorio.delete(aeropuerto);
		return aeropuerto;
	}
	@PatchMapping(value = "/aeropuertos/{nombre}")
	public Aeropuerto PatchAeropuerto(@PathVariable String nombre,@RequestBody Aeropuerto aeropuerto){
		Aeropuerto aeroTemp = repositorio.findByNombre(nombre);
		aeroTemp.setDescripcion(aeropuerto.getDescripcion());
		aeroTemp.setNombre(aeropuerto.getNombre());
		aeroTemp.setUbicacion(aeropuerto.getUbicacion());
		aeroTemp = repositorio.save(aeroTemp);
		return aeroTemp;
	}
}
