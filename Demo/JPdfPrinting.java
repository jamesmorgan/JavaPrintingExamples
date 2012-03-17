public class JPdfPrinting {

	public static void main(final String[] args) {
    	PrintingHelper.lookupAllPrinters();
		final PrintService[] printers = PrintingHelper.findPrinterOutputLocation("\\\\My_Remote_Or_Locally_Installed_Printer");
		if (0 == printers.length || 1 < printers.length) {
			// Handle
		}
		final PrintService printService = printers[0];
		PrintingHelper.listsPrinterAttributes(printService);
		if (PrintingHelper.printerNotAcceptingJobs(printService)) {
			// Handle
		}
		
        PDFPrint pdfPrint;
		try {
			final File fileToPrint = new File("src/test/resources/tests/Sample_Printing.pdf");

			final PrintRequestAttributeSet printRequestAttributes = new HashPrintRequestAttributeSet();
			printRequestAttributes.add(OrientationRequested.PORTRAIT);
			printRequestAttributes.add(PrintQuality.HIGH);
			printRequestAttributes.add(MediaSizeName.ISO_A4);
			printRequestAttributes.add(new JobName(fileToPrint.getName(), Locale.UK));

			pdfPrint = new PDFPrint(new FileInputStream(fileToPrint), null);

			final PrinterJob pJob = PrinterJob.getPrinterJob();
			pJob.setPageable(pdfPrint.getPageable(pJob));
			pJob.setJobName(fileToPrint.getName());
			pJob.setPrintService(printer_05);

			pJob.print(printRequestAttributes);
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final PDFException e) {
			e.printStackTrace();
		}
		catch (final PrinterException e) {
			e.printStackTrace();
		}

	}

	private static PrintService[] findPrinterOutputLocation(final String outputLocation) {
		final AttributeSet attributeSet = new HashAttributeSet();
		attributeSet.add(new PrinterName(outputLocation, null));
		return PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, attributeSet);
	}
}
