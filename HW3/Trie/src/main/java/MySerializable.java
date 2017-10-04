import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Custom Serializable interface to work with InputStream and OutputStream
 */
public interface MySerializable {
    void serialize(OutputStream out) throws IOException;
    void deserialize(InputStream in) throws IOException;
}
