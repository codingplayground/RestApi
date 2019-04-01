package rest_api_automation;


import org.junit.runner.RunWith;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartException;

import io.restassured.http.Method;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import utilities.RequestUtil;

@RunWith(SpringRunner.class)
@ContextConfiguration
@AutoConfigureMockMvc
public class DataFileTest {

private static Object response;
	    private URL base;

	    @Before
	    public void setUp() throws Exception {
	        this.base = new URL("http://localhost:" + "8080" );
	    }


	@Test
	public void postRequest()throws Exception  {
	TestRestTemplate restemplate =new TestRestTemplate();
	MultiValueMap<String, Object> body= new LinkedMultiValueMap<>();
	body.add("file", new File("classpath:test-data.json"));
	HttpEntity<MultiValueMap<String, Object>> httpEntity=new HttpEntity<>(RequestUtil.createMultiPartRequestHeader());
	ResponseEntity<?> postDataFile= restemplate.postForEntity(base.toString()+"api/hi/uploadFile", httpEntity, String.class);
	response=postDataFile.getBody();
	
	}

	@Test
	public void  getRequest() throws Exception {
		TestRestTemplate restemplate =new TestRestTemplate();
		HttpEntity< ?> httpEntity=new HttpEntity<>(RequestUtil.createJsonRequestHeader());
		ResponseEntity<String> getRequest = restemplate.exchange(base.toString()+"api/hi/uploadFile", HttpMethod.GET, httpEntity, String.class);
		getRequest.getBody();
		
	}

}

	  
