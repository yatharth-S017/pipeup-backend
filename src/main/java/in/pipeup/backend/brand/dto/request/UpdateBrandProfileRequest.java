package in.pipeup.backend.brand.dto.request;

import in.pipeup.backend.brand.entity.CompanySize;
import in.pipeup.backend.brand.entity.IndustryType;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBrandProfileRequest {

    @Size(min = 2, max = 100)
    private String companyName;

    @Size(max = 100)
    private String brandName;

    @Size(max = 255)
    private String website;

    private IndustryType industry;

    private CompanySize companySize;

    private String state;

    private String city;

    @Size(max = 500)
    private String description;

    private String logoUrl;
}
