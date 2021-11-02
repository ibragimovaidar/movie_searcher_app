package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.PersonDTO;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository.PersonRepository;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service.PersonService;

import java.util.Optional;

public class PersonServiceImpl implements PersonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	private final PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}


	@Override
	public Optional<PersonDTO> findById(Integer id) {
		return personRepository.findById(id).map(PersonDTO::from);
	}
}
