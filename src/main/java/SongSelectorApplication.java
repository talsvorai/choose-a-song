//Insuring the class is properly namespaced
package com.mytask.songselector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Combines @Configuration, @EnableAutoConfiguration and 
@ComponentScan that Scans for components @Controller, @Service, @Repository, etc.
*/
@SpringBootApplication
public class SongSelectorApplication {
    public static void main(String[] args) {
        //By default runs on port 8080
        SpringApplication.run(SongSelectorApplication.class, args);
    }
}