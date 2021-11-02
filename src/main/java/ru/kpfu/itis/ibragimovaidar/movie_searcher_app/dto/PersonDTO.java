package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.Person;

public class PersonDTO {

	private final String firstName;
	private final String lastName;
	private final String middleName;
	private final String description;
	private final String personType;
	private final ImageMetadata imageMetadata;

	public PersonDTO(String firstName, String lastName, String middleName, String description, String personType, ImageMetadata imageMetadata) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.description = description;
		this.personType = personType;
		this.imageMetadata = imageMetadata;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getDescription() {
		return description;
	}

	public String getPersonType() {
		return personType;
	}

	public ImageMetadata getImageMetadata() {
		return imageMetadata;
	}

	public static PersonDTO from(Person person){
		return new PersonDTO(
				person.getFirstName(),
				person.getLastName(),
				person.getMiddleName(),
				person.getDescription(),
				person.getPersonType().toString(),
				person.getImage()
		);
	}
}
