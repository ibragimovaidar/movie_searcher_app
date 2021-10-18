package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model;

import java.util.Objects;
import java.util.StringJoiner;

public class ImageMetadata {

	private Integer id;
	private String folder;
	private String filename;

	public ImageMetadata() {
	}

	public ImageMetadata(String filename, String folder) {
		this.folder = folder;
		this.filename = filename;
	}

	public ImageMetadata(Integer id, String filename, String folder) {
		this.id = id;
		this.filename = filename;
		this.folder = folder;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ImageMetadata that = (ImageMetadata) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", ImageMetadata.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("folder=" + folder)
				.add("filename='" + filename + "'")
				.toString();
	}
}
