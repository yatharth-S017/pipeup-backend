package in.pipeup.backend.creator.controller;

import in.pipeup.backend.creator.dto.response.CreatorAnalyticsResponse;
import in.pipeup.backend.creator.integration.YoutubeService;
import in.pipeup.backend.creator.integration.dto.YoutubeChannel;
import in.pipeup.backend.creator.service.ICreatorAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/creator")
@RequiredArgsConstructor
public class CreatorAnalyticsController {

    private final ICreatorAnalyticsService creatorAnalyticsService;


    @GetMapping("/analytics")
    public ResponseEntity<CreatorAnalyticsResponse> getAnalytics() {

        return ResponseEntity.ok(creatorAnalyticsService.getAnalytics());
    }

    @PostMapping("/analytics/refresh")
    public ResponseEntity<CreatorAnalyticsResponse> refreshAnalytics() {

        return ResponseEntity.ok(creatorAnalyticsService.refreshAnalytics());
    }

    // View any creator's analytics (Brand / Creator / Admin)
    @GetMapping("/{creatorId}/analytics")
    public ResponseEntity<CreatorAnalyticsResponse> getCreatorAnalytics(@PathVariable Long creatorId) {
        return ResponseEntity.ok(creatorAnalyticsService.getCreatorAnalytics(creatorId));
    }

}
