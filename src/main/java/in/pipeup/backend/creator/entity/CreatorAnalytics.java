package in.pipeup.backend.creator.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "creator_analytics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * One Creator Profile -> One Analytics Record
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_profile_id", nullable = false, unique = true)
    private CreatorProfile creatorProfile;

    /**
     * YouTube Channel Information
     */
    @Column(nullable = false, unique = true)
    private String channelId;

    @Column(nullable = false)
    private String channelName;

    @Column(nullable = false)
    private String channelUrl;

    private String channelLogo;

    /**
     * Public Channel Statistics
     * (Available using YouTube Data API)
     */
    @Column(nullable = false)
    @Builder.Default
    private Long subscriberCount = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long totalViews = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long totalVideos = 0L;

    /**
     * PipeUp Calculated Analytics
     */
    @Column(nullable = false)
    @Builder.Default
    private Double averageViews = 0.0;

    @Column(nullable = false)
    @Builder.Default
    private Double averageLikes = 0.0;

    @Column(nullable = false)
    @Builder.Default
    private Double averageComments = 0.0;

    @Column(nullable = false)
    @Builder.Default
    private Double engagementRate = 0.0;

    /**
     * Future Analytics (OAuth Required)
     * Keep null until OAuth integration is implemented.
     */
    private Long totalWatchTime;
    private Double averageViewDuration;
    private Long impressions;
    private Double clickThroughRate;
    private Long uniqueViewers;
    private Long returningViewers;

    /**
     * Metadata
     */
    private LocalDateTime lastSyncedAt;
}