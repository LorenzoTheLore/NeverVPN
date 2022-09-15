package me.lorenzo.nevervpn.persistence.ipinfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.lorenzo.nevervpn.NeverVPN;
import me.lorenzo.nevervpn.exception.PersistenceException;
import me.lorenzo.nevervpn.persistence.PersistenceHandler;
import me.lorenzo.nevervpn.utils.FileUtils;
import me.lorenzo.nevervpn.vpn.IPInfo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link PersistenceHandler PersistenceHandler} for handling {@link IPInfo IPInfo} persistence
 */
public class IPInfoPersistenceHandler implements PersistenceHandler<IPInfo> {
    /**
     * Gson object, to handle json in persistence logic
     */
    private final Gson gson;

    /**
     * Base path for saving serialized games
     */
    private final File basePath;

    /**
     * Initialize instance fields
     */
    public IPInfoPersistenceHandler() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        this.basePath = new File(NeverVPN.getInstance().getDataFolder(), "cache");
    }

    /**
     * Save IPInfo in json format
     *
     * @param ipInfo Object to save in specified path
     */
    @Override
    public void serialize(IPInfo ipInfo) {
        Path ipPath = Paths.get(basePath.getPath(), ipInfo.getIp().replace(".", "-") + ".json");
        try {
            FileWriter fileWriter = FileUtils.createFileWriter(ipPath.toFile());

            gson.toJson(ipInfo, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Returns {@link IPInfo IPInfo} object from specified Json path
     *
     * @param path Path where object is located
     * @return IPInfo from specified path
     */
    @Override
    public IPInfo deserialize(Path path) {
        try {
            return gson.fromJson(new FileReader(path.toFile()), IPInfo.class);
        } catch (FileNotFoundException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Get all available IPInfos in root cache folder
     *
     * @return List of all available games as {@link IPInfo IPInfo} objects
     */
    public List<IPInfo> fetchIPInfos() {
        if (basePath.listFiles() == null) return new ArrayList<>();
        return Arrays.stream(basePath.listFiles())
                .map(file -> deserialize(file.toPath()))
                .collect(Collectors.toList());
    }
}
