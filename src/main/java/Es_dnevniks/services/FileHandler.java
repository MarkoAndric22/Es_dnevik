package Es_dnevniks.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface FileHandler {
	
	public  String SingleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes ) throws IOException;
	
	public void downloadFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}
