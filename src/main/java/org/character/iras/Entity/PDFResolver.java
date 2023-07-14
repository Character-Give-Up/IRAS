package org.character.iras.Entity;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * PDF解析器
 */
@Component
public class PDFResolver {
    private File file;

    public PDFResolver() {
    }

    public void setFile(File file) {
        this.file = file;
    }

    /**
     * 创建关于某一个文件的PDF解析器
     * @param path 要解析的PDF文件的路径
     */
    public PDFResolver(String path) {
        this.file = new File(path);
    }


    /**
     * 解析PDF文件
     * @return PDF文件的文本内容
     * @throws IOException 遇到I/O问题
     */
    public String resolve() throws IOException {
        PDDocument document = PDDocument.load(this.file);
        PDFTextStripper stripper = new PDFTextStripper();
        return stripper.getText(document);
    }

    public String resolve(String url) throws IOException {
        URL url1 = new URL(url);
        InputStream inputStream = url1.openConnection().getInputStream();
        PDDocument document = PDDocument.load(inputStream);
        PDFTextStripper stripper = new PDFTextStripper();
        return stripper.getText(document);
    }
}
