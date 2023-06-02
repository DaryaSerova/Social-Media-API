package ru.effective.mobile.social.post.service;

import org.springframework.data.domain.Page;
import ru.effective.mobile.social.post.dto.NewPostDto;
import ru.effective.mobile.social.post.dto.PostDto;
import ru.effective.mobile.social.post.dto.UpdatePostDto;

public interface PostService {

    PostDto createPost(NewPostDto newPostDto, String userLogin);

    PostDto getPost(Long id, String userLogin);

    void deletePost(Long id, String userLogin);

    PostDto updatePost(Long id, UpdatePostDto updatePostDto, String userLogin);

    Page<PostDto> getPostsByFriend(int from, int size, String userLogin, String friendId);
}
