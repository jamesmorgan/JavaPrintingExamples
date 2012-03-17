public class ApachePDFBoxPrinting {

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
        
		try {
			final File fileToPrint = new File("src/test/resources/tests/Sample_Printing.pdf");

			final PDDocument load = PDDocument.load(fileToPrint);

			final PrinterJob printJob = PrinterJob.getPrinterJob();
			printJob.setPrintService(printService);
			printJob.setJobName(fileToPrint.getName());

			final HashPrintRequestAttributeSet printRequestAttributes = new HashPrintRequestAttributeSet();
			printRequestAttributes.add(OrientationRequested.PORTRAIT);
			printRequestAttributes.add(PrintQuality.HIGH);
			printRequestAttributes.add(MediaSizeName.ISO_A4);
			printRequestAttributes.add(new JobName(fileToPrint.getName(), Locale.UK));
			printRequestAttributes.add(Chromaticity.COLOR);
			printRequestAttributes.add(new RequestingUserName("System.User", Locale.UK));

			printJob.print(printRequestAttributes);
			load.silentPrint(printJob);
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
		catch (final PrinterException e) {
			e.printStackTrace();
		}
	}
}
