package com.pavan.devflow.common.util;

import java.text.Normalizer;
import java.util.Locale;

public final class SlugUtil {
	private SlugUtil() {
	}

	public static String toSlug(String input) {

		if (input == null || input.isBlank()) {
			return "";
		}

		return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase(Locale.ENGLISH)
				.replaceAll("[^a-z0-9]+", "-").replaceAll("(^-|-$)", "");
	}
}
