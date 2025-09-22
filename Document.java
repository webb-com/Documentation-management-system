package com.example.docsystem;

import jakarta.persistence.*;

@Entity // Marks this class as a database table
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique ID for each document

    private String fileName;
    private String contentType;
    private Long size;

    @Lob // Specifies that this should be stored as a Large Object
    @Column(length = 10000000) // Adjust size limit if needed
    private byte[] data; // The actual file content

    // --- Getters and Setters ---
    // Your IDE can generate these for you.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
