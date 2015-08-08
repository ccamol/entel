package com.epcs.recursoti.configuracion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.ujac.print.DocumentHandlerException;
import org.ujac.print.DocumentPrinter;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Clase auxiliar para el manejo de creacion de archivos pdf en el sistema.
 * 
 * @author rmesino (I2B) en nombre de Absalon Opazo (Atencion al Cliente,
 *         EntelPcs)
 * 
 */

public class PDFCreatorHelper {
    
    /**
     * Este metodo crea el archivo pdf en un flujo de salida especificado por parametro OutputStream out
     * @param properties
     *        Propiedades que se utilizaran en el documento
     * @Param fileXMLTemplate
     *        Ruta del archivo xml que contiene el template del archivo pdf
       @Param out
              stream de salida donde se escribira el document
     * @return OutputStream
     * */
    public static OutputStream createPDF(Map<String, Object> properties, String fileXMLTemplate, OutputStream out)throws FileNotFoundException, 
    IOException, DocumentHandlerException{
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        File file = new File(fileXMLTemplate);
        FileInputStream inputTemplate = new FileInputStream(file);
        DocumentPrinter documentPrinter = new DocumentPrinter(inputTemplate, properties);           
        documentPrinter.printDocument(bout);        
        bout.writeTo(out);
        bout.flush();           
        out.flush();
        
        return out;

    }
    
    /**
     * Este metodo crea el archivo pdf a partir de un xml y hace que el navegador lo vea como una descarga
     * */
    public static void createAndDownloadPDF(Map<String, Object> properties, String fileXMLTemplate, String fileName,
            HttpServletResponse response, OutputStream out)throws FileNotFoundException, 
    IOException, DocumentHandlerException{
        
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        File file = new File(fileXMLTemplate);
        FileInputStream inputTemplate = new FileInputStream(file);

        DocumentPrinter documentPrinter = new DocumentPrinter(inputTemplate, properties);
        documentPrinter.printDocument(bout);
        
        response.setContentType("application/pdf");
        response.setContentLength(bout.size());
        response.setHeader("content-disposition","attachment;name=\""+fileName+"\";filename=\""+fileName+"\"");
        
        bout.writeTo(out);
        bout.flush();           
        out.flush();

    }
    
    /**
     * Este metodo crea el archivo pdf a partir de un string y hace que el
     * navegador lo vea como una descarga
     * */
    public static void createAndDownloadPDF(String content, String fileName,
            OutputStream out, Font font, HttpServletResponse response)
            throws DocumentException, IOException {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        Paragraph parrafo = new Paragraph(content, font);

        document.open();
        document.add(parrafo);
        document.close();

        response.setContentType("application/pdf");
        response.setHeader("content-disposition","inline;filename=\""+fileName+"\"");
        response.setContentLength(baos.size());

        baos.writeTo(out);
        baos.flush();
        out.flush();    
    }
}
