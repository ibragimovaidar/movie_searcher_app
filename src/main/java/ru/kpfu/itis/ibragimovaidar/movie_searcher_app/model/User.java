package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class User {

	private Integer id;
	private String username;
	private String passwordHash;
	private String email;
	private String firstName;
	private String lastName;
	private String middleName;
	private LocalDate dateOfBirth;

	private String description;

	private List<Review> reviews;

	private UserRole role = UserRole.USER;

	private ImageMetadata imageMetadata;

	public User() {
	}

	public User(String username, String passwordHash, String email, String firstName, String lastName, String middleName, LocalDate dateOfBirth, String description, List<Review> reviews, UserRole role, ImageMetadata imageMetadata) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.dateOfBirth = dateOfBirth;
		this.description = description;
		this.reviews = reviews;
		this.role = role;
		this.imageMetadata = imageMetadata;
	}

	public User(Integer id, String username, String passwordHash, String email, String firstName, String lastName, String middleName, LocalDate dateOfBirth, String description, List<Review> reviews, UserRole role, ImageMetadata imageMetadata) {
		this.id = id;
		this.username = username;
		this.passwordHash = passwordHash;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.dateOfBirth = dateOfBirth;
		this.description = description;
		this.reviews = reviews;
		this.role = role;
		this.imageMetadata = imageMetadata;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public ImageMetadata getImageMetadata() {
		return imageMetadata;
	}

	public void setImageMetadata(ImageMetadata imageMetadata) {
		this.imageMetadata = imageMetadata;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id.equals(user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("username='" + username + "'")
				.toString();
	}
}
