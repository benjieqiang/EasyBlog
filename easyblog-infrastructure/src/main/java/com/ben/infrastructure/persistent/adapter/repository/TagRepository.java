package com.ben.infrastructure.persistent.adapter.repository;

import com.ben.domain.web.model.entity.TagArticlePageEntity;
import com.ben.domain.web.model.entity.TagEntity;
import com.ben.domain.web.adapter.repository.ITagRepository;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.dao.IArticleTagRelDao;
import com.ben.infrastructure.persistent.dao.ITagDao;
import com.ben.infrastructure.persistent.dao.po.Article;
import com.ben.infrastructure.persistent.dao.po.ArticleTagRel;
import com.ben.infrastructure.persistent.dao.po.Tag;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2024-12-13  09:03
 * @Description: Tag
 * @Version: 1.0
 */
@Slf4j
@Component
public class TagRepository implements ITagRepository {
    @Autowired
    private ITagDao tagDao;

    @Autowired
    private IArticleTagRelDao articleTagRelDao;

    @Autowired
    private IArticleDao articleDao;

    @Override
    public List<TagEntity> findTagSelectList(Long size) {
        List<Tag> tagList = null;
        // 如果接口入参中未指定 size
        if (Objects.isNull(size) || size == 0) {
            // 查询所有分类
            tagList = tagDao.selectAll();
        } else {
            // 否则查询指定的数量
            tagList = tagDao.selectByLimit(size);
        }
        if (tagList == null) return null;
        return tagList.stream().map(
                tag -> TagEntity.builder()
                        .id(tag.getId())
                        .name(tag.getName())
                        .createTime(tag.getCreateTime())
                        .updateTime(tag.getUpdateTime())
                        .articlesTotal(tag.getArticlesTotal())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public PageInfo<TagArticlePageEntity> findTagArticlePageList(Long tagId, Integer current, Integer size) {
        // 0. 判断tag是否存在
        Tag tag = tagDao.selectById(tagId);
        if (Objects.isNull(tag)) {
            log.warn("==> 该标签不存在, categoryId: {}", tagId);
            throw new BizException(ResponseCode.TAG_NOT_EXISTED);
        }
        // 1. 分页查询tagId关联的所有文章id；
        PageHelper.startPage(current, size);
        List<ArticleTagRel> articleTagRels = articleTagRelDao.selectByTagId(tagId);
        // 若该分类下未发布任何文章
        if (CollectionUtils.isEmpty(articleTagRels)) {
            log.info("==> 该标签下还未发布任何文章, tagId: {}", tagId);
            return new PageInfo<>(null);
        }
        PageInfo<ArticleTagRel> pageInfo = new PageInfo<>(articleTagRels);
        List<ArticleTagRel> tagRels = pageInfo.getList();// 分页查询的数据；
        List<Long> articleIds = tagRels.stream().map(ArticleTagRel::getArticleId).collect(Collectors.toList());
        // 2. 查询文章表；
        List<Article> articles = articleDao.selectPageListByArticleIds(articleIds);
        // 3. 填充响应
        List<TagArticlePageEntity> resList = articles.stream().map(article ->
                        TagArticlePageEntity.builder()
                                .id(article.getId())
                                .title(article.getTitle())
                                .cover(article.getCover())
                                .createDate(article.getCreateTime().toLocalDate())
                                .build()
                )
                .collect(Collectors.toList());

        return new PageInfo<>(resList);
    }
}
