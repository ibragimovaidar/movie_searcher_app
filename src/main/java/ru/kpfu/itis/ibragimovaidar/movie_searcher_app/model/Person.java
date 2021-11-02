package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model;

import java.util.Objects;
import java.util.StringJoiner;

public class Person {

	private Integer id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String description;
	private PersonType personType;
	private ImageMetadata image;

	public Person() {
	}

	public Person(String firstName, String lastName, String middleName, String description, PersonType personType, ImageMetadata image) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.description = description;
		this.personType = personType;
		this.image = image;
	}

	public Person(Integer id, String firstName, String lastName, String middleName, String description, PersonType personType, ImageMetadata image) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.description = description;
		this.personType = personType;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PersonType getPersonType() {
		return personType;
	}

	public void setPersonType(PersonType personType) {
		this.personType = personType;
	}

	public ImageMetadata getImage() {
		return image;
	}

	public void setImage(ImageMetadata image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return id.equals(person.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("firstName='" + firstName + "'")
				.add("lastName='" + lastName + "'")
				.add("middleName='" + middleName + "'")
				.toString();
	}
}
