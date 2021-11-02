package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.service;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto.PersonDTO;

import java.util.Optional;

public interface PersonService {

	Optional<PersonDTO> findById(Integer id);
}
