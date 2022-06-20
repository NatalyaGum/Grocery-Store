package by.edu.webstore.util;

import by.edu.webstore.exception.DaoException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

/**
 * {@code ImageEncoder} util class to help encode image
 */
public class ImageEncoder {
    private static final String IMAGE_TYPE = "data:image/jpeg;base64,";

    private ImageEncoder() {
    }

    /**
     * {@code encode} method to encode image as byte array to string
     *
     * @param image - image as Blob
     * @return encoded image
     */
    public static String encodeBlob(Blob image) throws DaoException {
        try {
            byte[] imageBytes = image.getBinaryStream().readAllBytes();
            byte[] encodeBase64 = Base64.getEncoder().encode(imageBytes);
            String base64DataString = new String(encodeBase64, StandardCharsets.UTF_8);
            String src = IMAGE_TYPE + base64DataString;
            return src;
        } catch (SQLException e) {
            throw new DaoException("Image InputStream cannot be received. Error accessing BLOB value:", e);
        } catch (IOException e) {
            throw new DaoException("Image bytes cannot be read from InputStream:", e);
        }
    }
}
