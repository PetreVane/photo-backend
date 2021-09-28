package fileuploader;

import fileuploader.service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class FileUploaderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FileUploaderApplication.class, args);
	}

	@Resource
	FileStorageService fileStorageService;

	@Override
	public void run(String... args) throws Exception {
		fileStorageService.deleteAll();
		fileStorageService.init();
	}
}
