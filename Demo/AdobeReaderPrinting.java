public class AdobeReaderPrinting {

    public static void main(final String[] args) {
		final File file = new File("src/test/resources/tests/Sample_Printing.pdf");
		final Runtime r = Runtime.getRuntime();
		Process p = null;
		try {
            // Information about command line printing flags can be foud @ http://livedocs.adobe.com/acrobat_sdk/10/Acrobat10_HTMLHelp/wwhelp/wwhimpl/common/html/wwhelp.htm?context=Acrobat10_SDK_HTMLHelp&file=DevFAQ_UnderstandingSDK.22.31.html
			final String printing =
					"C:\\Program Files (x86)\\Adobe\\Reader 10.0\\Reader\\AcroRd32.exe /s /h /t " + file.getCanonicalPath()
						+ " \"\\\\My_Remote_Or_Locally_Installed_Printer\"";
			p = r.exec(printing);
		}
		catch (final Exception e) {
			System.out.println("Exception thrown: " + e.getMessage());
			e.printStackTrace();
		}
	}
}