package me.lorenzo.nevervpn.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Persistence handling base abstraction
 */
public interface PersistenceHandler<T> {

    /**
     * Save object into specified path
     *
     * @param object Object to save in specified path
     */
    void serialize(T object) throws IOException;

    /**
     * Retrieve and deserialize object from specified path
     *
     * @param path Path where object is located
     * @return Object deserialized
     */
    T deserialize(Path path) throws FileNotFoundException;

}
