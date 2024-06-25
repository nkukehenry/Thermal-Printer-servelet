package com.solveretch.thermalprint;

public class ReceiptRequest {

    private String headerText;
    private String subHeading;
    private String receiptBody;
    private String footerText;
    private int copies= 1;
    private int pageWidth=48;

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getReceiptBody() {
        return receiptBody;
    }

    public void setReceiptBody(String receiptBody) {
        this.receiptBody = receiptBody;
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getPageWidth() {
        return pageWidth;
    }

    public void setPageWidth(int pageWidth) {
        this.pageWidth = pageWidth;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }
}
