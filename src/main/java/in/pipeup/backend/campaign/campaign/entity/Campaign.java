package in.pipeup.backend.campaign.campaign.entity;

import in.pipeup.backend.brand.entity.BrandProfile;
import in.pipeup.backend.campaign.campaign.enums.CampaignStatus;
import in.pipeup.backend.campaign.campaign.enums.Platform;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campaign Owner
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private BrandProfile brand;

    // Basic Details
    @NotBlank
    @Column(nullable = false, length = 150)
    private String title;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String requirements;

    // Platforms
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "campaign_platforms", joinColumns = @JoinColumn(name = "campaign_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    @Builder.Default
    private Set<Platform> platforms = new HashSet<>();

    // Budget
    @NotNull
    @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalBudget;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal payoutPerCreator;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer requiredCreators;

    // Deadlines
    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate applicationDeadline;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate submissionDeadline;

    // Attachments
    @ElementCollection
    @CollectionTable(name = "campaign_attachments", joinColumns = @JoinColumn(name = "campaign_id"))
    @Column(name = "file_url")
    private List<String> attachmentUrls;

    // Status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private CampaignStatus status = CampaignStatus.PUBLISHED;

    // Audit Fields
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
