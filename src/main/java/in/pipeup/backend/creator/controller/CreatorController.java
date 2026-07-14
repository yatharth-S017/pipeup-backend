package in.pipeup.backend.creator.controller;

import in.pipeup.backend.creator.dto.request.CompleteCreatorOnboardingRequest;
import in.pipeup.backend.creator.dto.request.UpdateCreatorProfileRequest;
import in.pipeup.backend.creator.dto.response.CreatorProfileResponse;
import in.pipeup.backend.creator.service.ICreatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/creator")
@RequiredArgsConstructor
public class CreatorController {

    private final ICreatorService creatorService;


    //Complete Creator Onboarding (One Time Only)
    @PostMapping("/onboarding")
    public ResponseEntity<CreatorProfileResponse> completeOnboarding(@Valid @RequestBody CompleteCreatorOnboardingRequest request) {

        return ResponseEntity.ok(creatorService.completeOnboarding(request));
    }


    //Get Logged-in Creator Profile
    @GetMapping("/profile")
    public ResponseEntity<CreatorProfileResponse> getProfile() {

        return ResponseEntity.ok(
                creatorService.getProfile()
        );
    }


    //Update Editable Creator Profile Fields
    @PutMapping("/profile")
    public ResponseEntity<CreatorProfileResponse> updateProfile(@Valid @RequestBody UpdateCreatorProfileRequest request) {

        return ResponseEntity.ok(creatorService.updateProfile(request));
    }
}