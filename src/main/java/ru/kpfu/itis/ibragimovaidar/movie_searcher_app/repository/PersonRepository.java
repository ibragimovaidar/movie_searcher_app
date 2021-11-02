package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.repository;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

	Optional<Person> findById(Integer id);
	List<Person> findByMovieId(Integer id);
	Person save(Person person);
	Person update(Person person);
}
