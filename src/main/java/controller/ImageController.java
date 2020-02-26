package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
}




