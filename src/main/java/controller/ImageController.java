package controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/image")

public class ImageController {

    @ApiOperation("Return image by URL")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
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


    @ApiOperation("Return image from file")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
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

    @ApiOperation("Upload image from file to another file")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    )
    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("D:\\LITS_CV_JAVA\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File is uploaded successfully",HttpStatus.OK);
    }

//    @ApiOperation("Upload image from file to DataBase for company")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
//    )
//    @RequestMapping(value = "/upload",method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<Object> uploadImageForCompany(@RequestParam("file") MultipartFile file) throws IOException {
//        File convertFile = new File("D:\\LITS_CV_JAVA\\" + file.getOriginalFilename());
//        convertFile.createNewFile();
//        FileOutputStream fout = new FileOutputStream(convertFile);
//        fout.write(file.getBytes());
//        fout.close();
//        return new ResponseEntity<>("File is uploaded successfully",HttpStatus.OK);
//    }
}





