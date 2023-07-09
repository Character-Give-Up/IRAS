package org.character.iras.Service;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.character.iras.DataAccess.Interfaces.ResumeDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLResumeDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class UploadFileService {
    private String filename;
    private File file;
    private String username;

    private ResumeDataAccess resumeDataAccess;

    @Value("${minio.url}")
    private String minio;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucketName;

    public UploadFileService() {
    }

    @Autowired
    public void setResumeDataAccess(MySQLResumeDataAccess access){
        this.resumeDataAccess = access;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public File getFile() {
        return file;
    }

    public void putFile(MultipartFile multipartFile) throws IOException {
        File dest = File.createTempFile("file-" + getFilename().substring(0, getFilename().lastIndexOf('.')),
                getFilename().substring(getFilename().lastIndexOf('.')));
        multipartFile.transferTo(dest);
        this.file = dest;
    }

    public String upload() throws
            IOException,
            InsufficientDataException,
            ErrorResponseException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidResponseException,
            XmlParserException,
            InternalException, io.minio.errors.ServerException {
        MinioClient client = MinioClient.builder().endpoint(this.minio).credentials(accessKey, secretKey).build();
        client.uploadObject(UploadObjectArgs.builder()
                        .filename(file.getAbsolutePath())
                        .bucket(this.bucketName)
                        .object(filename)
                .build());
        String URL = client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(bucketName)
                .object(filename)
                .build());
        resumeDataAccess.putNewResumeData(resumeDataAccess.getMaximumId() + 1, URL);
        return URL;


    }
}
