package fileuploader.controler;

import fileuploader.message.ResponseMessage;
import fileuploader.model.FileInfo;
import fileuploader.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/uploader")
@CrossOrigin("http:localhost")
public class FilesController {

    @Autowired
    private FileStorageService storageService;
    private Environment environment;

    @Autowired
    public FilesController( Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/status")
    public String getStatus() {
        var portNumber = environment.getProperty("local.server.port");
        return String.format("File-Uploader microservice is running in port %s", portNumber);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.stream(files).forEach(file -> {
                storageService.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            message = "Uploaded the files successfully: " + fileNames;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Fail to upload files!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(file -> {
            String filename = file.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", file.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllFiles() {
        storageService.deleteAll();
        return ResponseEntity.ok("Files deleted successfully");
    }

    public ResponseEntity<Resource> deleteFile(String fileName) {
        var deletedFile = storageService.delete(fileName);
        return ResponseEntity.status(HttpStatus.OK).body(deletedFile);
    }
}
