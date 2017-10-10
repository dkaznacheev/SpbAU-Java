import java.io.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * ZipArchiver is a class that can unzip files in directory that match the given regex.
 */
public class ZipArchiver {


    /**
     * Path to file
     */
    private String path;

    /**
     * Regex to match with
     */
    private Pattern regex;

    /**
     * Custom exception to throw if folder was not found
     */
    class FolderNotFoundException extends Exception {

    }

    /**
     * Custom exception to throw in case of zip file problems
     */
    class ZipException extends Exception {

    }

    public ZipArchiver(String path, String regex) {
        this.path = path;
        this.regex = Pattern.compile(regex);
    }

    /**
     * scans all archives in directory, unpacks only files that match the regex
     * @throws FolderNotFoundException
     * @throws FileNotFoundException
     * @throws ZipException
     */
    public void unpackRegex() throws FolderNotFoundException, FileNotFoundException, ZipException {
        File folder = new File(path);
        try {
            for (File fileEntry : folder.listFiles()) {

                ZipFile zipFile = new ZipFile(fileEntry);

                BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(fileEntry));

                ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);

                ZipEntry zipEntry = zipInputStream.getNextEntry();

                if (zipEntry == null)
                    continue;

                while (zipEntry != null) {
                    if (checkMatch(zipEntry.getName())) {
                        writeEntry(zipFile, zipEntry);
                    }
                    zipEntry = zipInputStream.getNextEntry();
                }
            }
        } catch (NullPointerException e) {
            throw new FolderNotFoundException();
        } catch (IOException e) {
            throw new ZipException();
        }

    }

    /**
     * extracts the file from a zip file
     * @param zipFile zip file where the file to write is located
     * @param zipEntry file to unzip
     */
    private void writeEntry(ZipFile zipFile, ZipEntry zipEntry) {
        try {
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            FileOutputStream fileOutputStream = new FileOutputStream(zipEntry.getName());
            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes)) >= 0) {
                fileOutputStream.write(bytes, 0, length);
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println("Unable to unpack " + zipEntry.getName());
        }
    }

    /**
     * Checks if file name matches the regex
     * @param name file name
     * @return if the name matches the regex
     */
    private boolean checkMatch(String name) {
        return regex.matcher(name).matches();
    }

    public static void main(String[] args) {
        String path = args[0];
        String regex = args[1];

        ZipArchiver zipArchiver = new ZipArchiver(path, regex);
        try {
            zipArchiver.unpackRegex();
        } catch (ZipException e) {
            System.out.println("Unable to access zip file");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (FolderNotFoundException e) {
            System.out.println("Folder \"" + path + "\" not found");
        }
    }
}
