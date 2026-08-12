package in.pipeup.backend.brand.dto.response;

import in.pipeup.backend.brand.entity.CompanySize;
import in.pipeup.backend.brand.entity.IndustryType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandPublicProfileResponse {

    private Long id;

    private String companyName;

    private String brandName;

    private String website;

    private IndustryType industry;

    private CompanySize companySize;

    private String city;

    private String state;

    private String description;

    private String logoUrl;

    private Boolean verified;
}