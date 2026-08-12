package in.pipeup.backend.brand.controller;

import in.pipeup.backend.brand.dto.request.CompleteBrandProfileRequest;
import in.pipeup.backend.brand.dto.request.UpdateBrandProfileRequest;
import in.pipeup.backend.brand.dto.response.BrandProfileResponse;
import in.pipeup.backend.brand.dto.response.BrandPublicProfileResponse;
import in.pipeup.backend.brand.service.IBrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
public class BrandController {

    private final IBrandService brandService;


    //creating brand profile
    @PostMapping("/profile")
    public BrandProfileResponse completeProfile(@Valid @RequestBody CompleteBrandProfileRequest request) {
        return brandService.completeProfile(request);
    }



    //get logged-in profile of brands
    @GetMapping("/profile")
    public BrandProfileResponse getProfile() {
        return brandService.getProfile();
    }

    //update brand profile
    @PutMapping("/profile")
    public BrandProfileResponse updateProfile(@Valid @RequestBody UpdateBrandProfileRequest request) {
        return brandService.updateProfile(request);
    }


    @GetMapping("/{brandId}")
    public BrandPublicProfileResponse getPublicProfile(@PathVariable Long brandId) {
        return brandService.getPublicProfile(brandId);
    }

}
