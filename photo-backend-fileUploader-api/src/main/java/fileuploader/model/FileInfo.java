package fileuploader.model;

import lombok.Data;

@Data
public class FileInfo {
    private String name;
    private String url;

    public FileInfo(String filename, String url) {
        this.name = filename;
        this.url = url;
    }
}
