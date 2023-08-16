package com.chuwa.redbook.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

public class PostDto {
    /**
     * 1. title should not be null or empty
     * 2. title should have at least 2 characters
     * Question, our database have set it as nullable=false,
     * why do we need to set validation here? what is the benefits?
     */
    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post description should have at least 10 characters")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 2 characters")
    private String description;
    @NotEmpty
    private String content;
    private String image;
    private Set<CommentDto> comments;
    private LocalDateTime createDateTime;
    public PostDto() {
    }

    public PostDto(Long id, String title, String description, String content, String image, Set<CommentDto> comments, LocalDateTime createDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.image = image;
        this.comments = comments;
        this.createDateTime = createDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image + '\'' +
                ", create time=" + createDateTime + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }
}
