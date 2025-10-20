package org.example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class UrlShortenerController {
    // Speicher für Kurz-URLs (in Memory)
    private  final Map<String, String> urls = new HashMap<>();

    // Erstellt eine Kurz-URL
    @PostMapping("/shorten")
    public Map<String,String> shorten(@RequestBody Map<String,String>body){
        String longUrl=body.get("url");
        String id=UUID.randomUUID().toString().substring(0,6);
        urls.put(id,longUrl);
        return  Map.of("id",id);
    }

    // Leitet weiter oder gibt 404 zurück
    @GetMapping("/{id}")
    public Object redirect(@PathVariable String id){
        String url=urls.get(id);
        if(url==null){
            return ResponseEntity.notFound().build();
        }
        return new RedirectView(url);
    }
}
