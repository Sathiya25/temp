package digi;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.security.GraphicMode;
import com.spire.pdf.security.PdfCertificate;
import com.spire.pdf.security.PdfCertificationFlags;
import com.spire.pdf.security.PdfSignature;
import java.awt.geom.Rectangle2D;
import java.util.Date;


public class AddDigitalSignature {
    public static void main(String[] args) {

        //Load the PDF file
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile("Input.pdf");
        //Get the first Page
        PdfPageBase page = doc.getPages().get(0);

        Rectangle2D.Float rec = new Rectangle2D.Float(100, 450, 310, 100);

        //Load the .pfx certificate file
        PdfCertificate certificate = new PdfCertificate("fred.pfx", "e-iceblue");

        //Add digital signature to the first page at specified location
        PdfSignature signature = new PdfSignature(doc, page, certificate, "signature1", rec);

        //Set signature details
        signature.setNameLabel("Name:  ");
        signature.setName("Sathiyabalan");
        signature.setContactInfoLabel("ContactInfo:  ");
        signature.setContactInfo("9876543210");
        signature.setDateLabel("Date:  ");
        signature.setDate(new Date());
        signature.setLocationInfoLabel("Location:  ");
        signature.setLocationInfo("India");
        signature.setReasonLabel("Reason:  ");
        signature.setReason("The certificate of this document.");
        signature.setDistinguishedNameLabel("DL:  ");
        signature.setDistinguishedName(signature.getCertificate().get_IssuerName().getName());
        signature.setGraphicMode(GraphicMode.Sign_Name_And_Sign_Detail);
        signature.setDocumentPermissions(PdfCertificationFlags.Allow_Form_Fill);
        signature.setCertificated(true);

        //Save the file
        doc.saveToFile("AddSignature.pdf");
    }
}
