# Thermal-Printer-servelet
Printer to thermal printer via this servlet

# How TO:
Simply send a post with the request body as in the sample.
<br> headerText,subHeading and footerText, pageWidth(papersize),and copies are optional
<pre>
curl -X POST http://localhost:8011/api/printReceipt \
-H "Content-Type: application/json" \
-d '{"headerText":"MUKISA BETTING LTD","subHeading":"The best betting provider in Uganda.\n","receiptBody": "Store Name\nDate: 2024-06-25\n-------------------------\nItem 1     Qty: 2  $10.00\nItem 2     Qty: 1  $5.00\n-------------------------\nTotal: $25.00\n-------------------------\nThank you for your purchase!\n-------------------------\n","footerText":"Terms and conditions apply","pageWidth":58,"copies":1}'
</pre>
