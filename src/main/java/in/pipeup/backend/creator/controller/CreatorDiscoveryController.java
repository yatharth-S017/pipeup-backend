package in.pipeup.backend.creator.controller;

import in.pipeup.backend.creator.dto.response.CreatorDiscoveryResponse;
import in.pipeup.backend.creator.service.ICreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creators")
@RequiredArgsConstructor
public class CreatorDiscoveryController {

    private final ICreatorService creatorService;

    @GetMapping
    public ResponseEntity<List<CreatorDiscoveryResponse>> getCreators() {

        return ResponseEntity.ok(
                creatorService.getCreators()
        );
    }
}