//Insuring the class is properly namespaced
package com.mytask.songselector;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Marking class as a Spring MVC controller that handles HTTPS requests
@Controller
public class SongController {

    //Mapping HTTP get requests - "/" is root url
    @GetMapping("/")
    public String home() {
        // Looks into templates and returns index.html Using
        return "index";  
    }
}