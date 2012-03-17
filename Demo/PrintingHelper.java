

public static class PrintingHelper {
    
    public static PrintService[] findPrinterOutputLocation(final String outputLocation) {
		final AttributeSet attributeSet = new HashAttributeSet();
		attributeSet.add(new PrinterName(outputLocation, null));
		return PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, attributeSet);
	}

	public static void lookupAllPrinters() {
		final PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
		System.out.println("Number of print services: " + printServices.length);
		for (final PrintService printer : printServices) {
			System.out.println("Printer: " + printer.getName());
		}
	}

	public static boolean printerNotAcceptingJobs(final PrintService printService) {
		final PrinterIsAcceptingJobs printerIsAcceptingJob = printService.getAttribute(PrinterIsAcceptingJobs.class);
		System.out.println("PrinterIsAcceptingJob -> " + printerIsAcceptingJob);
		return printerIsAcceptingJob.equals(PrinterIsAcceptingJobs.NOT_ACCEPTING_JOBS);
	}

	public static void listsPrinterAttributes(final PrintService printService) {
		System.out.println("Supported Job Properties for printer: " + printService.getName());
		final Class[] supportedAttributes = printService.getSupportedAttributeCategories();
		for (int i = 0, max = supportedAttributes.length; i < max; i++) {
			System.out.println("   " + supportedAttributes[i].getName() + ":= "
				+ printService.getDefaultAttributeValue(supportedAttributes[i]));
		}
	}
    
}