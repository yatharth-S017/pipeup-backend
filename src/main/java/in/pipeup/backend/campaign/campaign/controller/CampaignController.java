package in.pipeup.backend.campaign.campaign.controller;


import in.pipeup.backend.campaign.campaign.dto.CampaignResponse;
import in.pipeup.backend.campaign.campaign.dto.CreateCampaignRequest;
import in.pipeup.backend.campaign.campaign.dto.UpdateCampaignRequest;
import in.pipeup.backend.campaign.campaign.service.CampaignServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand/campaigns")
@RequiredArgsConstructor
public class CampaignController {


    private final CampaignServiceImpl campaignService;

    @PostMapping
    public ResponseEntity<CampaignResponse> createCampaign(@Valid @RequestBody CreateCampaignRequest request) {

        CampaignResponse response = campaignService.createCampaign(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CampaignResponse>> getMyCampaigns() {
        return ResponseEntity.ok(campaignService.getMyCampaigns());
    }


    @GetMapping("/{campaignId}")
    public ResponseEntity<CampaignResponse> getCampaignById(@PathVariable Long campaignId) {

        return ResponseEntity.ok(campaignService.getCampaignById(campaignId));
    }


    @PatchMapping("/{campaignId}")
    public ResponseEntity<CampaignResponse> updateCampaign(@PathVariable Long campaignId, @Valid @RequestBody UpdateCampaignRequest request) {

        CampaignResponse response = campaignService.updateCampaign(campaignId, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{campaignId}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long campaignId) {

        campaignService.deleteCampaign(campaignId);
        return ResponseEntity.noContent().build();
    }

}
