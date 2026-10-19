package com.MyImageUpload.controller;

import com.MyImageUpload.dto.SeasonDTO;
import com.MyImageUpload.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*") // Allow Vue app to access API, it does not work since we have globally TRUE
//@CrossOrigin(origins="http://localhost:8080")
@RequestMapping("/api/seasons")
@RequiredArgsConstructor
public class SeasonController {

    private final SeasonService seasonService;

    @PostMapping
    public ResponseEntity<SeasonDTO> createSeason(
            @RequestBody SeasonDTO dto) {

        return ResponseEntity.ok(seasonService.saveSeason(dto));
    }

    @GetMapping
    public ResponseEntity<List<SeasonDTO>> getAllSeasons() {

        return ResponseEntity.ok(seasonService.getAllSeasons());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeasonDTO> updateSeason(
            @PathVariable Long id,
            @RequestBody SeasonDTO dto) {

        return ResponseEntity.ok(seasonService.updateSeason(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {

        seasonService.deleteSeason(id);
        return ResponseEntity.noContent().build();
    }

    /**
     PUT /api/seasons/1
     {
     "name": "Updated Season 1",
     "episodes": [
     {
     "title": "New Episode 1",
     "youtubeId": "NEW111"
     },
     {
     "title": "New Episode 2",
     "youtubeId": "NEW222"
     }
     ]
     }

     **/
    /**
        DELETE /api/seasons/1
     Season plus all episodes removed
     **/
}

