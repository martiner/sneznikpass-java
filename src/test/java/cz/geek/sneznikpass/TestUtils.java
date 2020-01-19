package cz.geek.sneznikpass;

import static cz.geek.sneznikpass.SneznikPassClient.OBJECT_MAPPER;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

	public static <T> T readJson(String path, Class<T> type) throws IOException {
		return readJson(OBJECT_MAPPER, path, type);
	}

	public static <T> T readJson(ObjectMapper objectMapper, String path, Class<T> type) throws IOException {
		String json = readString(path);
		return objectMapper.readValue(json, type);
	}

	public static String readString(String path) throws IOException {
		try (InputStream stream = TestUtils.class.getResourceAsStream(path)) {
			requireNonNull(stream, "Resource doesn't exist " + path);
			return StreamUtils.copyToString(stream, Charset.defaultCharset());
		}
	}

	public static String writeString(Object value) throws IOException {
		return OBJECT_MAPPER.writeValueAsString(value);
	}

}