package Es_dnevniks.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class FileHandlerImpl implements FileHandler {

	private static String UPLOADED_FOLDER = "C:\\temp\\";

	@Override
	public String SingleFileUpload(MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:uploadStatus";
		}
		try {

			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
		} catch (IOException e) {
			throw e;
		}
		return "redirect:/uploadStatus";
	}

	public void downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain");

		response.setHeader("Content-disposition", "attachment; filename=admin.txt"); // Used to name the download file and its format
		
		File my_file = new File("C://temp/errors.txt"); // We are downloading .txt file, in the format of doc with name admin.txt
		
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(my_file);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
	}

}
