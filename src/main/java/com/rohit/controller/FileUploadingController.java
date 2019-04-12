package com.rohit.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rohit.constant.Constants;
import com.rohit.exception.SORuntimeException;
import com.rohit.service.FileProcessorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class FileUploadingController {

	@Autowired
	private FileProcessorService fileProcessorService;

	@Value("${csvfilepath}")
	public String PATH;

	/**
	 * 
	 * mapped to show homepage
	 */
	@GetMapping("/")
	public String index() {
		return "upload";
	}

	/**
	 * 
	 * This method is used to read and process CSV file
	 * 
	 * @throws IOException
	 * 
	 */
	@RequestMapping(value = Constants.UPLOAD, method = RequestMethod.POST)
	@ApiOperation(code = 201, value = "upload csv file", responseContainer = "String", notes = "upload csv file", httpMethod = "POST")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "The request has succeeded. Server is able to respond successfully."),
			@ApiResponse(code = 400, message = "Bad Request: The request could not be understood by the server due to malformed syntax"),
			@ApiResponse(code = 401, message = "Unauthorized: The request requires user authentication"),
			@ApiResponse(code = 403, message = "Forbidden: The server understood the request, but is refusing to fulfill it"),
			@ApiResponse(code = 404, message = "Not Found: The server has not found anything matching the Request-URI"),
			@ApiResponse(code = 412, message = "Precondition Failed: The precondition given in one or more of the request-header fields evaluated to false"),
			@ApiResponse(code = 500, message = "Internal Server Error: The server encountered an unexpected condition which prevented it from fulfilling the request") })

	public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {
		try {
			if (file.isEmpty()) {
				redirectAttributes.addFlashAttribute(Constants.MESSAGE,
						Constants.NOT_FOUND);
			}
			System.out.println("path: " + PATH);
			fileProcessorService.process(PATH, file);
			redirectAttributes.addFlashAttribute(Constants.MESSAGE,
					file.getOriginalFilename() + "\t" + Constants.SUCCESS);
		} catch (SQLException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(Constants.MESSAGE,
					Constants.ERROR_MESSAGE);
		} catch (SORuntimeException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(Constants.MESSAGE, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(Constants.MESSAGE,
					Constants.ERROR_MESSAGE);
		}
		return Constants.REDR_UPLOAD_STATUS;
	}

	/**
	 * 
	 * This method is show status page.
	 * 
	 */
	@GetMapping(Constants.UPLOAD_STATUS)
	public String uploadStatus() {
		return Constants.PAGE_UPLOAD_STATUS;
	}
}
