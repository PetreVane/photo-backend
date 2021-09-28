package fileuploader.service;

import fileuploader.exception.FileNotFoundException;
import fileuploader.exception.FileSizeExceededException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            if (file != null) {
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            }
        } catch (Exception exception) {
            throw new FileSizeExceededException("Could not store the file. Error: " + exception.getLocalizedMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Resources could not be located or is not readable");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("Error: " + e.getLocalizedMessage());
        }
    }

    @Override
    public Resource delete(String filename) {
        var existingFile = load(filename);
        if (existingFile != null) {
            try {
                FileSystemUtils.deleteRecursively(Paths.get(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return existingFile;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException exception) {
            throw new FileNotFoundException("Error while loading files: " + exception.getLocalizedMessage());
        }

    }
}
