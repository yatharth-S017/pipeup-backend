package in.pipeup.backend.campaign.campaign.controller;


import in.pipeup.backend.campaign.campaign.dto.CampaignDetailsResponse;
import in.pipeup.backend.campaign.campaign.dto.CreatorCampaignCardResponse;
import in.pipeup.backend.campaign.campaign.service.ICreatorCampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
@RequiredArgsConstructor
public class CreatorCampaignController {

    private final ICreatorCampaignService creatorCampaignService;


    //get all published campaign
    @GetMapping
    public ResponseEntity<List<CreatorCampaignCardResponse>> getAllPublishedCampaigns() {

        List<CreatorCampaignCardResponse> campaigns = creatorCampaignService.getAllPublishedCampaigns();
        return ResponseEntity.ok(campaigns);
    }

    //get campaign detail by id
    @GetMapping("/{campaignId}")
    public ResponseEntity<CampaignDetailsResponse> getCampaignDetails(@PathVariable Long campaignId) {

        CampaignDetailsResponse campaign = creatorCampaignService.getCampaignDetails(campaignId);
        return ResponseEntity.ok(campaign);
    }
    

}
