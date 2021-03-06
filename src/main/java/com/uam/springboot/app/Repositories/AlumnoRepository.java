package com.uam.springboot.app.Repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.uam.springboot.app.Entidad.Alumno;

public interface AlumnoRepository extends MongoRepository<Alumno, String>,CustomAlumnoRepository{
	

	//public List<Alumno> findByNombre(String nombre);
	
	/**
	 * La regex devuelve los calumnos con careras que comienzan con alguna letra
	 * */
	@Query("{ 'carrera' : { $regex: ?0} }")
	public List<Alumno> findByCarrera(String regex);
}
