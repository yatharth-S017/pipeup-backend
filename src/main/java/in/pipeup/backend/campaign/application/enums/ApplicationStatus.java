package in.pipeup.backend.campaign.application.enums;

public enum ApplicationStatus {

    // Creator has applied, waiting for brand review
    PENDING,

    // Brand accepted the creator's application
    ACCEPTED,

    // Brand rejected the creator's application
    REJECTED,

    // Creator has submitted the content
    CONTENT_SUBMITTED,

    // Brand requested changes in the submitted content
    REVISION_REQUESTED,

    // Brand approved the submitted content
    APPROVED,

    // Entire collaboration is completed
    COMPLETED
}
