package com.example.pdfgenerator.controller;

import com.example.pdfgenerator.service.PdfService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PdfController {

    @Autowired
    PdfService pdfService;

    @GetMapping("/generate/pdf")
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException, URISyntaxException {
        response.setContentType("application/pdf");
        DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd:hh:mm:sss");
        String current = dateFormat.format(new Date());

        String headerkey ="Content-Disposition";
        String headerValue="attachment; filename-pdf_"+ current + ".pdf";
        response.setHeader(headerkey,headerValue);

        pdfService.export(response);
    }
}
