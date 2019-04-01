package utilities;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RequestUtil {

	public static HttpHeaders createJsonRequestHeader() {
		HttpHeaders headers=new HttpHeaders();
	   headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	   headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

public static HttpHeaders createMultiPartRequestHeader() {
	HttpHeaders headers=new HttpHeaders();
	   headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	   headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	 return headers;
	
}
}
