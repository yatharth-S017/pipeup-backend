package in.pipeup.backend.campaign.application.entity;

import in.pipeup.backend.campaign.application.enums.ApplicationStatus;
import in.pipeup.backend.campaign.campaign.entity.Campaign;
import in.pipeup.backend.creator.entity.CreatorProfile;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "campaign_applications",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"campaign_id", "creator_id"})}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campaign to which creator applied
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    // Creator who applied
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorProfile creator;

    // Current application status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ApplicationStatus status = ApplicationStatus.PENDING;

    // Optional message from creator
    @Column(length = 1000)
    private String message;

    // Time when creator applied
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime appliedAt = LocalDateTime.now();

    // Audit fields
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}