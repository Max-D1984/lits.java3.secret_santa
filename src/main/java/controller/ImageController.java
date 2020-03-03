package controller;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/image")

public class ImageController {
    // public ImageController() throws IOException {
    // }
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity getImage(@RequestParam String imageURL) {
        Image image = null;
        try {
            URL url = new URL(imageURL);
            image = ImageIO.read(url);
            BufferedImage bufferedImage = ImageIO.read(new URL(imageURL));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", bos);
            byte[] data = bos.toByteArray();
            writeImage(data);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return ResponseEntity.of(Optional.of(
                image.getGraphics()));
    }

    public void writeImage(byte[] byteArray) throws IOException {
        FileWriter fw = new FileWriter("image.jpg");
        try {
            FileOutputStream fos = new FileOutputStream("E:\\LITS_CV_JAVA_3\\image.jpg");
            fos.write(byteArray, 0, byteArray.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");
    }

    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public void showImage(HttpServletResponse response) throws Exception {
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //* CALL_OR_CREATE_YOUR_IMAGE_OBJECT; */
            File file = new File("E:\\LITS_CV_JAVA_3\\image.jpg");
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image, "jpeg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        byte[] imgByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(imgByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}





