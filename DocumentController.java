package com.example.docsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    // Display the main page with a list of documents
    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Document> docs = documentRepository.findAll();
        model.addAttribute("docs", docs);
        return "index"; // This will look for a file named "index.html"
    }

    // Handle the file upload
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Document doc = new Document();
            doc.setFileName(file.getOriginalFilename());
            doc.setContentType(file.getContentType());
            doc.setSize(file.getSize());
            doc.setData(file.getBytes());
            documentRepository.save(doc);
        }
        return "redirect:/"; // Redirect back to the home page
    }

    // Handle file download
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Document doc = documentRepository.findById(id).orElse(null);
        if (doc == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + doc.getFileName() + "\"")
                .body(doc.getData());
    }

    // Handle file deletion
    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id) {
        documentRepository.deleteById(id);
        return "redirect:/";
    }
}
