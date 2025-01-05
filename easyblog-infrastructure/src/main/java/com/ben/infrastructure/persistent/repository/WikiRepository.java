package com.ben.infrastructure.persistent.repository;

import com.ben.domain.web.model.valobj.WikiCatalogLevelVO;
import com.ben.domain.web.model.aggregate.WebWikiCatalogAggregate;
import com.ben.domain.web.model.aggregate.WikiPreNextArticleAggregate;
import com.ben.domain.web.model.entity.ArticleEntity;
import com.ben.domain.web.model.entity.WikiInfoEntity;
import com.ben.domain.web.repository.IWikiRepository;
import com.ben.infrastructure.persistent.dao.IWikiCatalogDao;
import com.ben.infrastructure.persistent.dao.IWikiDao;
import com.ben.infrastructure.persistent.po.Wiki;
import com.ben.infrastructure.persistent.po.WikiCatalog;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  19:28
 * @Description: 前端wiki仓储
 * @Version: 1.0
 */
@Slf4j
@Component
public class WikiRepository implements IWikiRepository {
    @Autowired
    private IWikiDao wikiDao;

    @Autowired
    private IWikiCatalogDao wikiCatalogDao;

    @Override
    public List<WikiInfoEntity> findWikiList() {
        List<Wiki> wikis = wikiDao.selectPublished();

        List<WikiInfoEntity> wikiInfoEntities = null;

        if (CollectionUtils.isEmpty(wikis)) return wikiInfoEntities;

        wikiInfoEntities = wikis.stream().map(wiki -> WikiInfoEntity.builder()
                .id(wiki.getId())
                .title(wiki.getTitle())
                .cover(wiki.getCover())
                .summary(wiki.getSummary())
                .isTop(wiki.getWeight() > 0)
                .build()).collect(Collectors.toList());

        wikiInfoEntities.forEach(wikiInfo -> {
            Long wikiId = wikiInfo.getId();
            WikiCatalog wikiCatalog = wikiCatalogDao.selectFirstArticleId(wikiId);
            wikiInfo.setFirstArticleId(Objects.nonNull(wikiCatalog) ? wikiCatalog.getArticleId() : null);
        });

        return wikiInfoEntities;
    }

    @Override
    public WikiPreNextArticleAggregate findArticlePreNext(Long id, Long articleId) {

        WikiPreNextArticleAggregate articleAggregate = new WikiPreNextArticleAggregate();
        // 获取当前文章所属知识库的目录
        WikiCatalog wikiCatalog = wikiCatalogDao.selectByWikiIdAndArticleId(id, articleId);

        // 构建上一篇文章
        WikiCatalog selectPreArticle = wikiCatalogDao.selectPreArticle(id, wikiCatalog.getId());
        if (Objects.nonNull(selectPreArticle)) {
            ArticleEntity preArticle = ArticleEntity.builder()
                    .articleId(wikiCatalog.getArticleId())
                    .title(wikiCatalog.getTitle())
                    .build();
            articleAggregate.setPreArticle(preArticle);
        }

        // 构建下一篇文章 VO
        WikiCatalog selectNextArticle = wikiCatalogDao.selectNextArticle(id, wikiCatalog.getId());
        if (Objects.nonNull(selectNextArticle)) {
            ArticleEntity nextArticleVO = ArticleEntity.builder()
                    .articleId(selectNextArticle.getArticleId())
                    .title(selectNextArticle.getTitle())
                    .build();
            articleAggregate.setNextArticle(nextArticleVO);
        }
        return articleAggregate;
    }

    @Override
    public List<WebWikiCatalogAggregate> findWikiCatalogList(Long id) {
        // 查询此知识库下所有目录
        List<WikiCatalog> catalogList = wikiCatalogDao.selectByWikiId(id);

        // 组装一、二级目录结构
        List<WebWikiCatalogAggregate> aggregateList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(catalogList)) {
            aggregateList = Lists.newArrayList();

            // 提取一级目录
            List<WikiCatalog> level1Catalogs = catalogList.stream()
                    .filter(catalog -> Objects.equals(catalog.getLevel(), WikiCatalogLevelVO.ONE.getValue())) // 一级目录
                    .sorted(Comparator.comparing(WikiCatalog::getSort)) // 升序排列
                    .collect(Collectors.toList());

            // 循环一级目录 集合，转成WikiCatalogAggregate对象
            for (WikiCatalog level1Catalog : level1Catalogs) {
                aggregateList.add(WebWikiCatalogAggregate.builder()
                        .id(level1Catalog.getId())
                        .articleId(level1Catalog.getArticleId())
                        .title(level1Catalog.getTitle())
                        .level(level1Catalog.getLevel())
                        .sort(level1Catalog.getSort())
                        .build());
            }

            // 遍历聚合对象，设置一级目录的二级目录
            aggregateList.forEach(level1Catalog -> {
                Long parentId = level1Catalog.getId();
                // 提取二级目录：parentId是一级目录的id，level是2
                List<WikiCatalog> level2Catalogs = catalogList.stream()
                        .filter(catalog -> Objects.equals(catalog.getParentId(), parentId)
                                && Objects.equals(catalog.getLevel(), WikiCatalogLevelVO.TWO.getValue()))
                        .sorted(Comparator.comparing(WikiCatalog::getSort))
                        .collect(Collectors.toList());

                // 二级目录转聚合List
                List<WebWikiCatalogAggregate> level2CatalogAggregates = level2Catalogs.stream()
                        .map(catalogDO -> WebWikiCatalogAggregate.builder()
                                .id(catalogDO.getId())
                                .articleId(catalogDO.getArticleId())
                                .title(catalogDO.getTitle())
                                .level(catalogDO.getLevel())
                                .sort(catalogDO.getSort())
                                .build())
                        .collect(Collectors.toList());
                level1Catalog.setChildren(level2CatalogAggregates);
            });
        }
        return aggregateList;
    }
}
