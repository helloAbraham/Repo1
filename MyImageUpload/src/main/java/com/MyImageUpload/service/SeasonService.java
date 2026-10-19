package com.MyImageUpload.service;

import com.MyImageUpload.dto.EpisodeDTO;
import com.MyImageUpload.dto.SeasonDTO;
import com.MyImageUpload.model.Episode;
import com.MyImageUpload.model.Season;
import com.MyImageUpload.repository.SeasonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {

    private final SeasonRepository seasonRepository;

    public SeasonDTO saveSeason(SeasonDTO dto) {

        Season season = new Season();
        season.setName(dto.getName());

        List<Episode> episodes = dto.getEpisodes()
                .stream()
                .map(e -> {
                    Episode episode = new Episode();
                    episode.setTitle(e.getTitle());
                    episode.setYoutubeId(e.getYoutubeId());
                    episode.setSeason(season);
                    return episode;
                })
                .toList();

        season.setEpisodes(episodes);

        Season savedSeason = seasonRepository.save(season);

        return mapToDTO(savedSeason);
    }

    public List<SeasonDTO> getAllSeasons() {
        return seasonRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private SeasonDTO mapToDTO(Season season) {

        SeasonDTO dto = new SeasonDTO();
        dto.setId(season.getId());
        dto.setName(season.getName());

        List<EpisodeDTO> episodeDTOS = season.getEpisodes()
                .stream()
                .map(e -> {
                    EpisodeDTO edto = new EpisodeDTO();
                    edto.setId(e.getId());
                    edto.setTitle(e.getTitle());
                    edto.setYoutubeId(e.getYoutubeId());
                    return edto;
                })
                .toList();

        dto.setEpisodes(episodeDTOS);

        return dto;
    }
    @Transactional
    public SeasonDTO updateSeason(Long id, SeasonDTO dto) {

        Season season = seasonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Season not found"));

        season.setName(dto.getName());

        // Clear existing episodes (orphanRemoval = true will delete them)
        season.getEpisodes().clear();

        // Add updated episodes
        List<Episode> updatedEpisodes = dto.getEpisodes()
                .stream()
                .map(e -> {
                    Episode episode = new Episode();
                    episode.setTitle(e.getTitle());
                    episode.setYoutubeId(e.getYoutubeId());
                    episode.setSeason(season);
                    return episode;
                })
                .toList();

        season.getEpisodes().addAll(updatedEpisodes);

        return mapToDTO(season);
    }
    public void deleteSeason(Long id) {
        if (!seasonRepository.existsById(id)) {
            throw new RuntimeException("Season not found");
        }
        seasonRepository.deleteById(id);
    }
}

