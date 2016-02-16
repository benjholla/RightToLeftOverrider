package rtlo;

public class RTLO {

	private static final String RIGHT_TO_LEFT_OVERRIDE = "\u202E";
	
	/**
	 * Returns a human readable string of what the Right-To-Left Override would
	 * look like for a system supporting the Right-To-Left Override Unicode
	 * character
	 * 
	 * @param filename
	 * @param displayExtension
	 * @return
	 */
	public static String rtloPreview(String filenamePrefix, String filenameExtension, String displayExtension) {
		if(!displayExtension.startsWith(".")){
			displayExtension = "." + displayExtension;
		}
		return filenamePrefix + new StringBuilder(filenameExtension).reverse().toString() + displayExtension;
	}
	
	/**
	 * Returns a string representing the requested Right-To-Left Override
	 * filename.
	 * 
	 * rtlo(filename, display_extension) = filename_prefix + RTLO +
	 * reverse(display_extension) + . + filename_extension
	 * 
	 * @param filename
	 * @param displayExtension
	 * @return
	 */
	public static String rtlo(String filenamePrefix, String filenameExtension, String displayExtension) {
		if(!displayExtension.startsWith(".")){
			displayExtension = "." + displayExtension;
		}
		displayExtension = new StringBuilder(displayExtension).reverse().toString();
		return filenamePrefix + RIGHT_TO_LEFT_OVERRIDE + displayExtension + filenameExtension;
	}
	
	/**
	 * Returns the file prefix (name excluding the extension) or an empty string
	 * if the file is a hidden file.
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFilePrefix(String filename){
		String prefix = "";
		int i = filename.lastIndexOf('.');
		if (i > 0) {
		    prefix = filename.substring(0,i);
		}
		return prefix;
	}
	
	/**
	 * Returns the file extension or an empty string
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileExtension(String filename){
		String extension = "";
		int i = filename.lastIndexOf('.');
		if (i > 0) {
		    extension = filename.substring(i+1);
		}
		return extension;
	}

}
