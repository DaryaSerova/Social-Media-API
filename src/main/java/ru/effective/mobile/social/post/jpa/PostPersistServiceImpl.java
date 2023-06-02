package ru.effective.mobile.social.post.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.post.model.Post;
import ru.effective.mobile.social.post.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostPersistServiceImpl implements PostPersistService {

    private final PostRepository  postRepository;

    @Override
    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> findPostById(Long id, String author) {
        return postRepository.findPostByIdAndAuthor(id, author);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Page<Post> getPostsByFriend(int from, int size, String author) {

        return postRepository.findPostByAuthor(author, PageRequest.of(from, size, Sort.by("create", "DESC")));
    }
}
