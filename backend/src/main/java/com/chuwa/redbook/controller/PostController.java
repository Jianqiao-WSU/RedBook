package com.chuwa.redbook.controller;

import com.chuwa.redbook.dao.PostRepository;
import com.chuwa.redbook.entity.Post;
import com.chuwa.redbook.payload.PostDto;
import com.chuwa.redbook.payload.PostResponse;
import com.chuwa.redbook.service.PostService;
import com.chuwa.redbook.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        PostDto postResponse = postService.createPost(postDto);
        return new ResponseEntity<>(postResponse, HttpStatus.CREATED);
    }

    // get all post rest api
    // {{localhost}}/api/v1/posts?pageNo=0&pageSize=10&sortBy=id&sortDir=desc
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @CrossOrigin
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/searchByTitle")
    public ResponseEntity<Post> getPostByTitle(@RequestParam("title") String title) {
        try {
            Post post = postRepository.findByTitle(title);
            if (post == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(post);
        } catch (NoResultException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,
                                                  @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }


    @GetMapping("/jpql")
    public List<PostDto> getAllPostJPQL() {
        return postService.getAllPostWithJPQL();
    }

    @GetMapping("/jpql-index/{id}")
    public ResponseEntity<PostDto> getPostByIdOrTitleJPQLIndex(@PathVariable(name = "id") long id,
                                                               @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdJPQLIndexParameter(id, title));
    }

    @GetMapping("/jpql-named/{id}")
    public ResponseEntity<PostDto> getPostByIdOrTitleJPQLNamed(@PathVariable(name = "id") long id,
                                                               @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdJPQLNamedParameter(id, title));
    }

    @GetMapping("/sql-index/{id}")
    public ResponseEntity<PostDto> getPostByIdOrTitleSQLIndex(@PathVariable(name = "id") long id,
                                                              @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdSQLIndexParameter(id, title));
    }

    @GetMapping("/sql-named/{id}")
    public ResponseEntity<PostDto> getPostByIdOrTitleSQLParameter(@PathVariable(name = "id") long id,
                                                                  @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.ok(postService.getPostByIdSQLNamedParameter(id, title));
    }
}
