package com.wah.email.bean;

import javax.mail.internet.ContentType;

/**
 *
 * @author vikram
 *
 */
public class Attachment {

    private String fileName;
    private String fileLocation;
    private ContentType contentType;

    /**
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the fileLocation
     */
    public String getFileLocation() {
        return this.fileLocation;
    }

    /**
     * @param fileLocation the fileLocation to set
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    /**
     * @return the contentType
     */
    public ContentType getContentType() {
        return this.contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Attachment [fileName=" + this.fileName + ", fileLocation=" + this.fileLocation + ", contentType="
                + this.contentType + "]";
    }


}
