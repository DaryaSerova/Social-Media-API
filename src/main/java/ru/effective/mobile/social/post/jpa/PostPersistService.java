package ru.effective.mobile.social.post.jpa;

import org.springframework.data.domain.Page;
import ru.effective.mobile.social.post.model.Post;

import java.util.Optional;

public interface PostPersistService {

    Post createPost(Post post);

    Optional<Post> findPostById(Long id, String author);

    void deletePost(Long id);

    Post updatePost(Post post);

    Page<Post> getPostsByFriend(int from, int size, String author);

}
