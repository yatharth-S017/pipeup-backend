package in.pipeup.backend.campaign.application.controller;


import in.pipeup.backend.campaign.application.dto.ApplicationResponse;
import in.pipeup.backend.campaign.application.dto.ApplyCampaignRequest;
import in.pipeup.backend.campaign.application.dto.MyApplicationResponse;
import in.pipeup.backend.campaign.application.service.IApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreatorApplicationController {

    private final IApplicationService applicationService;


    @PostMapping("/api/campaigns/{campaignId}/apply")
    public ResponseEntity<ApplicationResponse> applyToCampaign(@PathVariable Long campaignId, @Valid @RequestBody ApplyCampaignRequest request) {

        ApplicationResponse response = applicationService.applyToCampaign(campaignId, request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/api/creator/applications")
    public ResponseEntity<List<MyApplicationResponse>> getMyApplications() {

        List<MyApplicationResponse> applications = applicationService.getMyApplications();
        return ResponseEntity.ok(applications);
    }

    

}
