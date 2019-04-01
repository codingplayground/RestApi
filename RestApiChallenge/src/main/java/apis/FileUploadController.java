package apis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;




@RestController
@RequestMapping(path="/api/hi")
@Api(value="Hi API",produces=MediaType.APPLICATION_JSON_VALUE)
public class FileUploadController {


    //method for uploading single file
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
      //  File uploadedFile = new File(file.getOriginalFilename());
        File uploadedFile = new File( file.getOriginalFilename());

        try {
            uploadedFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(uploadedFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Object>("File Uploaded Succesfully!", HttpStatus.OK);
    }
     
 
     
    //method for downloading file
    @RequestMapping(value = "/getFile/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFile(@PathVariable String fileName, HttpServletResponse response) {
 
        String filePath = fileName;
        Path path = Paths.get(filePath);
        Resource resource = null;
 
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
 
        if (resource.exists()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } else {
            return new ResponseEntity<Object>("File Not Found ", HttpStatus.BAD_REQUEST);
        }
    }
 
}