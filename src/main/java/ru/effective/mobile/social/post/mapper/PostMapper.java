package ru.effective.mobile.social.post.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.effective.mobile.social.post.dto.NewPostDto;
import ru.effective.mobile.social.post.dto.PostDto;
import ru.effective.mobile.social.post.dto.UpdatePostDto;
import ru.effective.mobile.social.post.model.Post;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PostMapper {

    @Mapping(target = "id", source = "post.id")
    @Mapping(target = "author", source = "userLogin")
    PostDto toPostDto(Post post, String userLogin);

    @Mapping(target = "id", source = "post.id")
    PostDto toPostDto(Post post);

    @Mapping(target = "author", source = "userLogin")
    Post toPost(NewPostDto newPostDto, String userLogin);

    Post toPost(PostDto postDto);

    void mergeToPost(UpdatePostDto updatePostDto, @MappingTarget Post post);
}
