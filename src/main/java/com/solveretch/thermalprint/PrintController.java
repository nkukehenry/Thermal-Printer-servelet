package com.solveretch.thermalprint;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
public class PrintController {

    @PostMapping("/printReceipt")
    @CrossOrigin
    public String printReceipt(@RequestBody ReceiptRequest receiptRequest) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            int copies   = receiptRequest.getCopies();
            int pageSize = receiptRequest.getPageWidth();

            // Add ESC/POS commands
            outputStream.write(new byte[]{0x1B, 0x40}); // Initialize printer
            outputStream.write(new byte[]{0x1B, 0x21, 0x01}); // Font A, half size

            // Print header text centered
            if (receiptRequest.getHeaderText() != null && !receiptRequest.getHeaderText().isEmpty()) {
                // Calculate center alignment position for 48-character line
                int headerLength = receiptRequest.getHeaderText().length();
                int spacesToCenter = (pageSize - headerLength) / 2;
                for (int i = 0; i < spacesToCenter; i++) {
                    outputStream.write(new byte[]{0x20}); // Space
                }
                outputStream.write(new byte[]{0x1B, 0x21, 0x10}); // Font A, normal height
                outputStream.write(receiptRequest.getHeaderText().getBytes("UTF-8"));
                outputStream.write(new byte[]{0x0A}); // Line feed
            }

            // Print header text centered
            if (receiptRequest.getSubHeading() != null && !receiptRequest.getSubHeading().isEmpty()) {
                // Calculate center alignment position for 48-character line
                int headerLength = receiptRequest.getSubHeading().length();
                int spacesToCenter = (pageSize - headerLength) / 2;
                for (int i = 0; i < spacesToCenter; i++) {
                    outputStream.write(new byte[]{0x20}); // Space
                }
                outputStream.write(new byte[]{0x1B, 0x21, 0x01}); // Font A, normal height
                outputStream.write(receiptRequest.getSubHeading().getBytes("UTF-8"));
                outputStream.write(new byte[]{0x0A}); // Line feed
            }

            // Print receipt body with normal text size
            outputStream.write(new byte[]{0x1B, 0x21, 0x00}); // Font A, normal height
            outputStream.write(receiptRequest.getReceiptBody().getBytes("UTF-8"));
            outputStream.write(new byte[]{0x0A}); // Line feed

            // Print footer text centered
            if (receiptRequest.getFooterText() != null && !receiptRequest.getFooterText().isEmpty()) {
                // Calculate center alignment position for 48-character line
                int footerLength = receiptRequest.getFooterText().length();
                int spacesToCenter = (pageSize - footerLength) / 2;
                for (int i = 0; i < spacesToCenter; i++) {
                    outputStream.write(new byte[]{0x20}); // Space
                }
                outputStream.write(new byte[]{0x1B, 0x21, 0x01}); // Font A, normal height
                outputStream.write(receiptRequest.getFooterText().getBytes("UTF-8"));
                outputStream.write(new byte[]{0x0A}); // Line feed
            }

            // Add ESC/POS commands for cutting, etc.
            outputStream.write(new byte[]{0x1B, 0x64, 0x05}); // Feed paper (5 lines)
            outputStream.write(new byte[]{0x1D, 0x56, 0x00}); // Cut paper

            byte[] bytes = outputStream.toByteArray();

            // Set up the print job
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(bytes, flavor, null);
            PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
            attributes.add(new Copies(copies));

            // Locate the default print service
            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();

            if (printService != null) {
                try {
                    // Create a print job
                    DocPrintJob job = printService.createPrintJob();
                    job.print(doc, attributes);
                    return "success";
                } catch (PrintException e) {
                    e.printStackTrace();
                    return "Failed to print the receipt.";
                }
            } else {
                return "No default print service found.";
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Failed to encode the receipt text.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to write ESC/POS commands.";
        }
    }
}