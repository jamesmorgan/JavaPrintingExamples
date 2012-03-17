public class IcePdfPrinting {

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
		
		final File fileToPrint = new File("src/test/resources/tests/Sample_Printing.pdf");
		final Document pdf = new Document();
		try {
			pdf.setFile(fileToPrint.getCanonicalPath());
			final SwingController sc = new SwingController();
			final DocumentViewController vc = new DocumentViewControllerImpl(sc);
			vc.setDocument(pdf);

			final HashPrintRequestAttributeSet printRequestAttributes = new HashPrintRequestAttributeSet();
			printRequestAttributes.add(OrientationRequested.PORTRAIT);
			printRequestAttributes.add(PrintQuality.HIGH);
			printRequestAttributes.add(MediaSizeName.ISO_A4);
			printRequestAttributes.add(new JobName(fileToPrint.getName(), Locale.UK));

			final PrintHelper printHelper = new PrintHelper(vc, pdf.getPageTree(), null, printRequestAttributes);
			printHelper.setupPrintService(printService, 0, pdf.getNumberOfPages(), 1, false);
			printHelper.print();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
		catch (final PDFSecurityException e) {
			e.printStackTrace();
		}
		catch (final PDFException e) {
			e.printStackTrace();
		}
		catch (final PrintException e) {
			e.printStackTrace();
		}
		finally {
			pdf.dispose();
		}
	}
}
