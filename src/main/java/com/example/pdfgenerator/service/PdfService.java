package com.example.pdfgenerator.service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

@Service
public class PdfService {

    public void export(HttpServletResponse response) throws IOException, DocumentException, URISyntaxException {
        Document doc = new Document(PageSize.A4);
        PdfWriter.getInstance(doc,response.getOutputStream());

        doc.open();
        Font fontTitle= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph= new Paragraph("Invoice",fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingAfter(3);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(14);

        Paragraph paragraph1= new Paragraph("nsbdkchbjbvjksbvj KVbjkVjkNVKJVN kb JKVBNKJNjvk  n ",fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph1.setSpacingAfter(5);

        PdfPTable table = new PdfPTable(2);
//        addTableHeader(table);
        addRows(table);
        //addCustomRows(table);

        doc.add(paragraph);
        doc.add(paragraph1);
        doc.add(table);
        doc.close();

    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Address", "Phone Number")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    private void addRows(PdfPTable table) {
        table.addCell("Raj Reja");
        table.addCell("Gwalior");
        table.addCell("9874365686");
        table.addCell("Lavi Sharma");
        table.addCell("Agra");
        table.addCell("9874365686");
    }

    private void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {


        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }
}
