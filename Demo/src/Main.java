import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@ RestController
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping("/yikes")
    public String yikes() {
        return "yikes";
    }

    @PostMapping("/submit")
    public String submit(@RequestBody String data) {
        // Handle the data and return a response
        return "submitted";
    }

    // Other routes go here
}