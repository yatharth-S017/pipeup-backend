package in.pipeup.backend.brand.dto.request;

import in.pipeup.backend.brand.entity.CompanySize;
import in.pipeup.backend.brand.entity.IndustryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompleteBrandProfileRequest {

    @NotBlank(message = "Company name is required")
    @Size(min = 2, max = 100)
    private String companyName;

    @Size(max = 100)
    private String brandName;

    @Size(max = 255)
    private String website;

    @NotNull(message = "Industry is required")
    private IndustryType industry;

    @NotNull(message = "Company size is required")
    private CompanySize companySize;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "City is required")
    private String city;

    @Size(max = 500)
    private String description;

    private String logoUrl;
}
