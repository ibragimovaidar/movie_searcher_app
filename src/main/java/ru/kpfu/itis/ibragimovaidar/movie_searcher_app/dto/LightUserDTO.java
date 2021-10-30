package ru.kpfu.itis.ibragimovaidar.movie_searcher_app.dto;

import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.common.util.PropertiesUtil;
import ru.kpfu.itis.ibragimovaidar.movie_searcher_app.model.ImageMetadata;

public class LightUserDTO {

	private final String username;
	private final String imageUrl;

	public LightUserDTO(String username, String imageUrl) {
		this.username = username;
		this.imageUrl = imageUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public static LightUserDTO from(UserDTO userDTO){
		return new LightUserDTO(
				userDTO.getUsername(),
				formatLightProfileImageUrl(userDTO.getImageMetadata())
		);
	}

	private static String formatLightProfileImageUrl(ImageMetadata imageMetadata){
		int height = Integer.parseInt(PropertiesUtil.getProperty("images.navbarProfilePhotoHeight"));
		int width = Integer.parseInt(PropertiesUtil.getProperty("images.navbarProfilePhotoWidth"));
		return "/images/" + imageMetadata.getFolder() + "?w=" + width +"&h=" + height;
	}
}
