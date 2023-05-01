package com.axing;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

@Controller
public class WordToPdfController {

	@GetMapping("/convert/{fileName}")
	@ResponseBody
	public byte[] convertToPdf(@PathVariable String fileName) throws IOException {

		String filePath = "path/to/files/" + fileName + ".docx";

		File wordFile = new File(filePath);
		InputStream inputStream = new FileInputStream(wordFile);
		XWPFDocument document = new XWPFDocument(inputStream);

		PdfOptions options = PdfOptions.create();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfConverter.getInstance().convert(document, out, options);

		inputStream.close();
		document.close();

		return out.toByteArray();
	}
}
