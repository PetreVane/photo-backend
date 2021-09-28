package fileuploader.exception;

import fileuploader.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({FileSizeExceededException.class})
    public ResponseEntity<ResponseMessage> handleMaxSizeException(FileSizeExceededException exc) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("One or more files are too large!"));
    }

    @ExceptionHandler({FileNotFoundException.class})
    public ResponseEntity<ResponseMessage> handleFileMissingException(FileNotFoundException exc) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseMessage("File(s) could not be found!"));
    }
}
