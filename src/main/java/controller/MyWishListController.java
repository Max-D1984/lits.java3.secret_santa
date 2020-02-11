package controller;
import model.MyWishListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PresentService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.List;
import java.util.Optional;

@EnableSwagger2
@RestController
@RequestMapping(value = "/wishlist")
public class MyWishListController {

    @RequestMapping(
            value = "/my-wishlist/list",
            method = RequestMethod.GET)
    public ResponseEntity getMyWishListList() {
        return ResponseEntity.of(Optional.of(List.of(
                new MyWishListResponse("Pen","www.pen.com","remove"),
                new MyWishListResponse ("Car","www.car.com","remove"),
                new MyWishListResponse("Ball","www.ball.com","No"))));
    }

}