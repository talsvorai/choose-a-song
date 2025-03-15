package com.example.songselector;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SongController {

    @GetMapping("/")
    public String home() {
        return "index";  // Returns the template (index.html)
    }
}