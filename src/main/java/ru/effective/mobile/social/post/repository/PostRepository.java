package ru.effective.mobile.social.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.effective.mobile.social.post.model.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostByIdAndAuthor(Long id, String author);

    Page<Post> findPostByAuthor(String author, Pageable pageable);

}
