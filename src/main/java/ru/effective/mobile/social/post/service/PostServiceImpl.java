package ru.effective.mobile.social.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.effective.mobile.social.exception.BadRequestException;
import ru.effective.mobile.social.exception.NotFoundException;
import ru.effective.mobile.social.friendship.service.FriendshipService;
import ru.effective.mobile.social.post.dto.NewPostDto;
import ru.effective.mobile.social.post.dto.PostDto;
import ru.effective.mobile.social.post.dto.UpdatePostDto;
import ru.effective.mobile.social.post.jpa.PostPersistService;
import ru.effective.mobile.social.post.mapper.PostMapper;
import ru.effective.mobile.social.post.model.Post;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostPersistService postPersistService;

    private final PostMapper postMapper;

    private final FriendshipService friendshipService;

    @Override
    public PostDto createPost(NewPostDto newPostDto, String userLogin) {

        if (newPostDto.getTitle() == null) {
            throw new BadRequestException("Плохой запрос", "Заголовок поста не может быть пуст");
        }

        if (newPostDto.getDescription() == null) {
            throw new BadRequestException("Плохой запрос", "Пост не может быть пустым");
        }

        Post post = postPersistService.createPost(postMapper.toPost(newPostDto, userLogin));

        return postMapper.toPostDto(post);

    }

    @Override
    public PostDto getPost(Long id, String userLogin) {

        Post post = postPersistService.findPostById(id, userLogin).orElseThrow(() ->
                new NotFoundException("Требуемый объект не был найден.",
                        String.format("Пост с id = %s пользователя = %s не найден.", id, userLogin)));

        return postMapper.toPostDto(post, userLogin);
    }

    @Override
    public void deletePost(Long id, String author) {

        findPost(id, author);

        postPersistService.deletePost(id);

    }

    @Override
    public PostDto updatePost(Long id, UpdatePostDto updatePostDto, String userLogin) {

        PostDto postDto = findPost(id, userLogin);

        if (updatePostDto.getTitle() == null) {
            throw new BadRequestException("Плохой запрос", "Заголовок поста не может быть пуст");
        }

        if (updatePostDto.getDescription() == null) {
            throw new BadRequestException("Плохой запрос", "Пост не может быть пустым");
        }

        Post post = postMapper.toPost(postDto);

        postMapper.mergeToPost(updatePostDto, post);

        return postMapper.toPostDto(postPersistService.updatePost(post));
    }

    @Override
    public Page<PostDto> getPostsByFriend(int from, int size, String firstUser, String secondUser) {

        if (!friendshipService.isFollowing(firstUser, secondUser)) {

            throw new BadRequestException("Плохой запрос", "У вас нет подписок.");

        }

        Page<Post> posts = postPersistService.getPostsByFriend(from, size, secondUser);

        return posts.map(post -> postMapper.toPostDto(post));
    }

    private PostDto findPost(Long id, String author) {

        Post post = postPersistService.findPostById(id, author).orElseThrow(() ->
                new NotFoundException("Требуемый объект не был найден.",
                        String.format("Пост с id = %s не найден.", id)));

        return postMapper.toPostDto(post);
    }
}
