package net.tecgurus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.tecgurus.model.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Integer>{
	
}
