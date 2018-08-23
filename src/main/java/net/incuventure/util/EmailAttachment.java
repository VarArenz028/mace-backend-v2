package net.incuventure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by angulo on 11/21/2016.
 */
public class EmailAttachment {
    public static final String FILE_TYPE_EXCEL = "application/vnd.ms-excel";
    public static final String FILE_TYPE_TEXT = "text/plain";

    private String fileName;
    private String fileType;
    private byte[] fileContent;

    private static Logger logger = LoggerFactory.getLogger(EmailAttachment.class);

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
}
