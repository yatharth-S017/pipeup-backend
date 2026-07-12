package in.pipeup.backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "creator_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Public name shown to brands
    @Column(nullable = false)
    private String displayName;

    @Column(length = 1000)
    private String bio;

    private String city;
    private String state;
    private String country;

    /**
     * Languages in which creator creates content.
     * Example: English, Hindi
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "creator_languages", joinColumns = @JoinColumn(name = "creator_profile_id"))
    @Column(name = "language")
    @Builder.Default
    private Set<String> contentLanguages = new HashSet<>();


    /**
     * Multiple creator niches.
     * Example:
     * TECH
     * AI
     * EDUCATION
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "creator_niches", joinColumns = @JoinColumn(name = "creator_profile_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "niche")
    @Builder.Default
    private Set<Niche> niches = new HashSet<>();

    /**
     * Used only when niche contains OTHER.
     */
    private String customNiche;

    private String youtubeChannelUrl;
    private String instagramProfileUrl;


     // URL returned from Cloudinary (or any storage provider).
    private String profileImageUrl;

    @Builder.Default
    private Boolean profileCompleted = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
