package digi;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.security.PdfSignature;
import com.spire.pdf.widget.PdfFormFieldWidgetCollection;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfSignatureFieldWidget;

public class VerifyDigitalSignature {
    public static void main(String[] args) {

        //Load the PDF file
        PdfDocument doc = new PdfDocument();
        doc.loadFromFile("AddSignature.pdf");

        //Get the forms collection
        PdfFormWidget pdfFormWidget = (PdfFormWidget) doc.getForm();
        PdfFormFieldWidgetCollection pdfFormFieldWidgetCollection = pdfFormWidget.getFieldsWidget();

        //Get the signature field
        for (int i = 0; i < pdfFormFieldWidgetCollection.getCount(); i++) {
            if (pdfFormFieldWidgetCollection.get(i) instanceof PdfSignatureFieldWidget) {
                PdfSignatureFieldWidget signatureFieldWidget = (PdfSignatureFieldWidget) pdfFormFieldWidgetCollection.get(i);
                //Get the signature in the signature field
                PdfSignature signature = signatureFieldWidget.getSignature();
                //Verify the signature
                boolean result = signature.verifySignature();
                if(result) {
                    System.out.println("Signature is valid");
                }else {
                    System.out.println("Signature is invalid");
                }
            }
        }
    }
}

