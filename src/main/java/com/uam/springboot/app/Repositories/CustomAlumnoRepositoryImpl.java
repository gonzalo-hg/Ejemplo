package com.uam.springboot.app.Repositories;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.uam.springboot.app.Entidad.Alumno;

public class CustomAlumnoRepositoryImpl implements CustomAlumnoRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void UpdateAlumno(String alumnoId, String fieldname, Object fieldValue) {
		mongoTemplate.findAndModify(BasicQuery.query(Criteria.where("id").is(alumnoId)),
				BasicUpdate.update(fieldname, fieldValue),
				FindAndModifyOptions.none(),Alumno.class);
		
	}
	
	
	@Override
	public List<Alumno> findByCarrera2(String carrera) {
		Query query = new Query();
		query.addCriteria(Criteria.where("carrera").is(carrera));
		query.fields().include("nombre","carrera","matricula","apellidoP","apellidoM");
		List<Alumno> alumonosCoincidentes = mongoTemplate.find(query,Alumno.class);
		return alumonosCoincidentes;
	}
}
