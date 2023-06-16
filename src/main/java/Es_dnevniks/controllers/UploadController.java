package Es_dnevniks.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Es_dnevniks.services.FileHandler;
@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping(path = "/")
public class UploadController {

	@Autowired
	private FileHandler fileHandler;

	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	@Secured("ROLE_ADMIN")
	public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		String result = null;
		try {
			result = fileHandler.SingleFileUpload(file, redirectAttributes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET)
	@Secured("ROLE_ADMIN")
	public String index() {
		return "upload";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/uploadStatus")
	@Secured("ROLE_ADMIN")
	public String uploadStatus() {
		return "uploadStatus";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/download")
	@Secured("ROLE_ADMIN")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		fileHandler.downloadFile(request, response);

	}

}
