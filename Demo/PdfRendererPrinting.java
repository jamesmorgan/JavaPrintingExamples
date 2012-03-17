public class PdfRendererPrinting {

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
			final File f = new File("src/test/resources/tests/Sample_Printing.pdf");
			final FileInputStream fis = new FileInputStream(f);
			final FileChannel fc = fis.getChannel();
			final ByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

			// Create PDF Print Page
			final PDFFile pdfFile = new PDFFile(bb);

			final PDFPrintPage pages = new PDFPrintPage(pdfFile);

			// Create Print Job
			final PrinterJob pjob = PrinterJob.getPrinterJob();
			final PageFormat pf = pjob.defaultPage();

			pf.setOrientation(PageFormat.PORTRAIT);

			pjob.setJobName(f.getName());
			final Book book = new Book();
			book.append(pages, pf, pdfFile.getNumPages());
			pjob.setPageable(book);
			pjob.setPrintService(printService);

			pjob.print();
		}
		catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
		catch (final PrinterException e) {
			e.printStackTrace();
		}
	}
}
