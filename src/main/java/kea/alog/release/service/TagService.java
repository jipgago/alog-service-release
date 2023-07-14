package kea.alog.release.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kea.alog.release.domain.tag.Tag;
import kea.alog.release.domain.tag.TagRepository;
import kea.alog.release.web.DTO.TagDTO.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService {
    final private TagRepository tagRepository;

    @Transactional
    public Long createTag(TagContentDTO tagContentDTO){
        Tag tag = Tag.builder().tagContent(tagContentDTO.getTagContent()).build();
        tagRepository.save(tag);
        return tag.getTagPk();
    }
    @Transactional
    public TagContentDTO getTag(Long tagId){
        Optional<Tag> optTag = tagRepository.findById(tagId);
        if(optTag.isPresent()){
            TagContentDTO sendTag = TagContentDTO.builder().tagContent(optTag.get().getTagContent()).build();
            return sendTag;
        } return TagContentDTO.builder().build();
    }

    @Transactional
    public boolean updateTag(TagContentDTO tagContentDTO, Long tagId){
        Optional<Tag> optTag = tagRepository.findById(tagId);
        if(optTag.isPresent()){
            Tag tag = optTag.get().toBuilder().tagContent(tagContentDTO.getTagContent()).build();
            tagRepository.save(tag);
            return true;
        } else return false;
    }

    @Transactional
    public void deleteTag(Long tagId){
        Optional<Tag> optTag = tagRepository.findById(tagId);
        if(optTag.isPresent()){
            tagRepository.delete(optTag.get());
        }
    }
    @Transactional
    public TagPageingDTO getAllList(Long currentPage){
        int pageSize = 10;
        Pageable pageable = PageRequest.of(currentPage.intValue() - 1, pageSize, Sort.by("tagPk").descending());
        Page<Tag> getPage = tagRepository.findAll(pageable);

        List<Tag> tagList = getPage.getContent();
        List<TagPrameterDTO> rspTagList = new ArrayList<>();
        for(Tag idx : tagList){
            TagPrameterDTO tagPrameterDTO = TagPrameterDTO.builder().tagPk(idx.getTagPk()).tagContent(idx.getTagContent()).build();
            rspTagList.add(tagPrameterDTO);
        }

        TagPageingDTO rspPageingDTO = TagPageingDTO.builder().tagList(rspTagList).totalPage(getPage.getTotalPages()).build();

        return rspPageingDTO;
    }
}
