//package br.com.vinniccius.masteringextjs.service;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.List;
//
//import br.com.vinniccius.masteringextjs.model.entity.Film;
//
//import com.lowagie.text.Document;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
//import com.lowagie.text.Phrase;
//import com.lowagie.text.pdf.PdfPCell;
//import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfWriter;
//
//public class Pdf {
//
//	public InputStream films(List<Film> films) {
//		
//		PdfPTable table = new PdfPTable(5);
//
//		PdfPCell titleCell = new PdfPCell(new Phrase("Title"));
//		titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//	    table.addCell(titleCell);
//	    
//	    PdfPCell languageCell = new PdfPCell(new Phrase("language"));
//	    languageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//	    table.addCell(languageCell);
//	    
//	    PdfPCell releaseYearCell = new PdfPCell(new Phrase("Release Year"));
//	    releaseYearCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//	    table.addCell(releaseYearCell);
//		
//	    table.setHeaderRows(1);
//	    
//	    for (Film film : films) {
//			table.addCell(film.toString());
//		}
//	    
//	    Document document = new Document();	    
//	    
//	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	    try {
//			PdfWriter.getInstance(document, outputStream);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}	    
//	    document.open();
//	    try {
//			document.add(table);
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
//	    document.close();
//	    
//		return new ByteArrayInputStream(outputStream.toByteArray());
//	}
//	
//}
