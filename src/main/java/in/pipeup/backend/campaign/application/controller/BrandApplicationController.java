package in.pipeup.backend.campaign.application.controller;

import in.pipeup.backend.campaign.application.dto.ApplicantResponse;
import in.pipeup.backend.campaign.application.dto.ApplicationDetailsResponse;
import in.pipeup.backend.campaign.application.service.IApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BrandApplicationController {

    private final IApplicationService applicationService;

    //View a single application
    @GetMapping("/brand/campaigns/{campaignId}/applications")
    public ResponseEntity<List<ApplicantResponse>> getApplicants(@PathVariable Long campaignId) {

        return ResponseEntity.ok(applicationService.getApplicants(campaignId));
    }



    //View a single application
    @GetMapping("/brand/applications/{applicationId}")
    public ResponseEntity<ApplicationDetailsResponse> getApplicationDetails(@PathVariable Long applicationId) {

        return ResponseEntity.ok(applicationService.getApplicationDetails(applicationId));
    }


    @PatchMapping("/brand/applications/{applicationId}/accept")
    public ResponseEntity<String> acceptApplication(@PathVariable Long applicationId) {

        applicationService.acceptApplication(applicationId);
        return ResponseEntity.ok("Application accepted successfully.");
    }


    @PatchMapping("/brand/applications/{applicationId}/reject")
    public ResponseEntity<String> rejectApplication(@PathVariable Long applicationId) {

        applicationService.rejectApplication(applicationId);
        return ResponseEntity.ok("Application rejected successfully.");
    }

}
