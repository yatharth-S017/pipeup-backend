package in.pipeup.backend.creator.controller;

import in.pipeup.backend.creator.dto.request.CreateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;
import in.pipeup.backend.creator.service.ICreatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/creator")
public class CreatorController {


    private final ICreatorService creatorService;

    public CreatorController(ICreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @PostMapping("/profile")
    public ResponseEntity<CreatorProfileResponse> createProfile(@Valid @RequestBody CreateCreatorProfileRequest request) {

        return ResponseEntity.ok(creatorService.createProfile(request));
    }


    @GetMapping("/profile")
    public ResponseEntity<CreatorProfileResponse> getProfile() {

        return ResponseEntity.ok(creatorService.getProfile());
    }

    @PutMapping("/profile")
    public ResponseEntity<CreatorProfileResponse> updateProfile(@Valid @RequestBody UpdateCreatorProfileRequest request) {

        return ResponseEntity.ok(creatorService.updateProfile(request));
    }
}
