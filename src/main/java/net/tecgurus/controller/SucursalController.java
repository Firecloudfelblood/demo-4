package net.tecgurus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import net.tecgurus.model.Sucursal;
import net.tecgurus.repository.SucursalRepository;

@RestController
public class SucursalController {

	@Autowired
	SucursalRepository sucursalRepo;
	
	@PostMapping("/sucursal")
	public ResponseEntity<Sucursal> crear(@RequestBody Sucursal sucursal){
		
		Sucursal sucursalCreada = sucursalRepo.save(sucursal);
		return new ResponseEntity<Sucursal>(sucursalCreada, HttpStatus.CREATED);
	}
	@GetMapping("/sucursal/all")
	public ResponseEntity<List<Sucursal>> listar(){
		List<Sucursal> sucursales = sucursalRepo.findAll();
		for (Sucursal s : sucursales) {
			Link selfLink = linkTo(methodOn(SucursalController.class).consultar(s.getIdSucursal())).withSelfRel();
			s.add(selfLink);
		}
		
		return new ResponseEntity<List<Sucursal>>(sucursales, HttpStatus.OK);
	}
	
	@GetMapping("/sucursal/{id}")
	public ResponseEntity<Sucursal> consultar(@PathVariable Integer id){
		Sucursal s = sucursalRepo.findById(id).get();
		return new ResponseEntity<Sucursal>(s, HttpStatus.OK);
	}
}
