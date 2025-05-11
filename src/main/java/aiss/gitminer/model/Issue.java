
package aiss.gitminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "Issue")
public class Issue {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    @Column(columnDefinition="TEXT")
    private String description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("closed_at")
    private String closedAt;

    @JsonProperty("labels")
    @ElementCollection
    private List<String> labels;

    @JsonProperty("author")
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private User author;

    @JsonProperty("assignee")
    @JoinColumn(name = "assignee_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private User assignee;

    @JsonProperty("votes")
    private Integer votes;

    @JsonProperty("comments")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "issueId")
    private List<Comment> comments;

    @Transient
    @JsonProperty("ref_id")
    private String refId = "1150"; // forzado

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(String closedAt) {
        this.closedAt = closedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @JsonProperty("ref_id")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("ref_id")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Issue.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id=").append(id).append(',');
        sb.append("title=").append(title).append(',');
        sb.append("description=").append(description).append(',');
        sb.append("state=").append(state).append(',');
        sb.append("createdAt=").append(createdAt).append(',');
        sb.append("updatedAt=").append(updatedAt).append(',');
        sb.append("closedAt=").append(closedAt).append(',');
        sb.append("labels=").append(labels).append(',');
        sb.append("author=").append(author).append(',');
        sb.append("assignee=").append(assignee).append(',');
        sb.append("votes=").append(votes).append(',');
        sb.append("comments=").append(comments).append(',');
        sb.append("refId=").append(refId).append(',');

        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append(']');
        }

        return sb.toString();
    }
}
