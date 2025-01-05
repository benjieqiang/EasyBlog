package com.ben.infrastructure.persistent.repository;

import com.ben.domain.admin.model.aggregate.WikiCatalogAggregate;
import com.ben.domain.admin.model.aggregate.WikiPageAggregate;
import com.ben.domain.admin.model.entity.WikiEntity;
import com.ben.domain.admin.model.entity.WikiPageEntity;
import com.ben.domain.admin.model.valobj.ArticleTypeVO;
import com.ben.domain.admin.model.valobj.WikiCatalogLevelVO;
import com.ben.domain.admin.model.valobj.WikiPublishStatusVO;
import com.ben.domain.admin.repository.IAdminWikiRepository;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.dao.IWikiCatalogDao;
import com.ben.infrastructure.persistent.dao.IWikiDao;
import com.ben.infrastructure.persistent.po.Article;
import com.ben.infrastructure.persistent.po.Wiki;
import com.ben.infrastructure.persistent.po.WikiCatalog;
import com.ben.types.enums.ResponseCode;
import com.ben.types.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-05  10:09
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Repository
public class AdminWikiRepository implements IAdminWikiRepository {
    @Autowired
    private IWikiDao wikiDao;

    @Autowired
    private IWikiCatalogDao wikiCatalogDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private IArticleDao articleDao;

    @Override
    public void addWiki(String title, String summary, String cover) {
        Wiki wiki = Wiki.builder()
                .title(title)
                .summary(summary)
                .cover(cover)
                .build();
        transactionTemplate.execute(status -> {
                    try {
                        // 新增知识库
                        wikiDao.insert(wiki);
                        // 获取新增记录的主键 ID
                        Long wikiId = wiki.getId();

                        // 初始化默认目录
                        // > 概述 > 基础
                        wikiCatalogDao.insert(WikiCatalog.builder().wikiId(wikiId).level(WikiCatalogLevelVO.ONE.getValue()).title("概述").sort(1).build());
                        wikiCatalogDao.insert(WikiCatalog.builder().wikiId(wikiId).level(WikiCatalogLevelVO.ONE.getValue()).title("基础").sort(2).build());
                        return 1;
                    } catch (Exception e) {
                        status.setRollbackOnly();
                        log.error("写入wiki记录，失败", e);
                        throw new BizException(ResponseCode.INSERT_FAILED);
                    }
                }
        );
    }

    @Override
    public Integer deleteWiki(Long id) {
        return wikiDao.deleteByWikiId(id);
    }

    @Override
    public void updateWikiIsTop(Long id, Boolean isTop) {
        Integer maxWeight = 0;
        if (isTop) {
            // 查询最大权重值；
            Wiki wiki = wikiDao.selectMaxWeight();
            maxWeight = wiki.getWeight() + 1;
        }
        // 更新该wiki置顶：最大权重+1，不置顶：weight置0；
        Wiki wiki = Wiki.builder().id(id).weight(maxWeight).build();
        wikiDao.update(wiki);
    }

    @Override
    public void updateWikiIsPublish(Long id, Boolean isPublish) {
        Wiki wiki = Wiki.builder()
                .id(id)
                .isPublish(isPublish ? WikiPublishStatusVO.PUBLISHED.getStatus() : WikiPublishStatusVO.NOT_PUBLISHED.getStatus())
                .build();
        wikiDao.update(wiki);
    }

    @Override
    public void updateWiki(Long id, String title, String cover, String summary) {
        wikiDao.update(Wiki.builder()
                .id(id)
                .title(title)
                .cover(cover)
                .summary(summary)
                .build());
    }

    @Override
    public WikiPageAggregate findWikiPageList(WikiPageEntity wikiPageEntity) {
        // 获取当前页、以及每页需要展示的数据数量
        Integer current = wikiPageEntity.getCurrent();
        Integer size = wikiPageEntity.getSize();
        // 查询条件
        String title = wikiPageEntity.getTitle();
        LocalDateTime startDate = wikiPageEntity.getStartDate();
        LocalDateTime endDate = wikiPageEntity.getEndDate();
        PageHelper.startPage(current, size);
        // 执行分页查询
        List<Wiki> wikis = wikiDao.selectPageList(title, startDate, endDate, null);
        PageInfo<Wiki> wikiPageInfo = new PageInfo<>(wikis);
        List<Wiki> list = wikiPageInfo.getList();

        List<WikiEntity> wikiEntities = list.stream().map(wiki -> WikiEntity.builder()
                .id(wiki.getId())
                .title(wiki.getTitle())
                .cover(wiki.getCover())
                .summary(wiki.getSummary())
                .weight(wiki.getWeight())
                .createTime(wiki.getCreateTime())
                .isPublish(wiki.getIsPublish() == 1)
                .build()).collect(Collectors.toList());

        return WikiPageAggregate.builder()
                .total(wikiPageInfo.getTotal())
                .current(wikiPageInfo.getPageNum())
                .size(wikiPageInfo.getSize())
                .wikiEntities(wikiEntities)
                .build();
    }

    @Override
    public List<WikiCatalogAggregate> findWikiCatalogList(Long id) {
        // 查询此知识库下所有目录
        List<WikiCatalog> catalogList = wikiCatalogDao.selectByWikiId(id);

        // 组装一、二级目录结构
        List<WikiCatalogAggregate> aggregateList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(catalogList)) {
            aggregateList = Lists.newArrayList();

            // 提取一级目录
            List<WikiCatalog> level1Catalogs = catalogList.stream()
                    .filter(catalog -> Objects.equals(catalog.getLevel(), WikiCatalogLevelVO.ONE.getValue())) // 一级目录
                    .sorted(Comparator.comparing(WikiCatalog::getSort)) // 升序排列
                    .collect(Collectors.toList());

            // 循环一级目录 集合，转成WikiCatalogAggregate对象
            for (WikiCatalog level1Catalog : level1Catalogs) {
                aggregateList.add(WikiCatalogAggregate.builder()
                        .id(level1Catalog.getId())
                        .articleId(level1Catalog.getArticleId())
                        .title(level1Catalog.getTitle())
                        .level(level1Catalog.getLevel())
                        .sort(level1Catalog.getSort())
                        .editing(Boolean.FALSE)
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
                List<WikiCatalogAggregate> level2CatalogAggregates = level2Catalogs.stream()
                        .map(catalogDO -> WikiCatalogAggregate.builder()
                                .id(catalogDO.getId())
                                .articleId(catalogDO.getArticleId())
                                .title(catalogDO.getTitle())
                                .level(catalogDO.getLevel())
                                .sort(catalogDO.getSort())
                                .editing(Boolean.FALSE)
                                .build())
                        .collect(Collectors.toList());
                level1Catalog.setChildren(level2CatalogAggregates);
            });
        }
        return aggregateList;
    }

    @Override
    public void updateWikiCatalogs(Long wikiId, List<WikiCatalogAggregate> catalogAggregates) {
        // 1. 查询该wikiId的所有目录
        List<WikiCatalog> wikiCatalogs = wikiCatalogDao.selectByWikiId(wikiId);
        // 过滤文章 ID集合
        List<Long> articleIds = wikiCatalogs.stream()
                .filter(wikiCatalogDO -> Objects.nonNull(wikiCatalogDO.getArticleId()))
                .map(WikiCatalog::getArticleId).collect(Collectors.toList());

        transactionTemplate.execute(status -> {
            try {
                // 更新为普通文章类型
                if (!CollectionUtils.isEmpty(articleIds)) {
                    articleDao.updateByIds(Article.builder()
                            .type(ArticleTypeVO.NORMAL.getValue()).build(), articleIds);
                }
                // 2. 先删除该wiki所有目录
                wikiCatalogDao.deleteByWikiId(wikiId);

                // 3. 插入新的目录数据：一级+二级
                if (!CollectionUtils.isEmpty(catalogAggregates)) {
                    // 重新设置 sort 排序字段的值
                    for (int i = 0; i < catalogAggregates.size(); i++) {
                        WikiCatalogAggregate wikiCatalogAggregate = catalogAggregates.get(i);
                        List<WikiCatalogAggregate> children = wikiCatalogAggregate.getChildren();
                        wikiCatalogAggregate.setSort(i + 1);
                        if (!CollectionUtils.isEmpty(children)) {
                            for (int j = 0; j < children.size(); j++) {
                                children.get(j).setSort(j + 1);
                            }
                        }
                    }

                    catalogAggregates.forEach(catalog -> {
                        // 一级目录
                        WikiCatalog wikiCatalogDO = WikiCatalog.builder()
                                .wikiId(wikiId)
                                .title(catalog.getTitle())
                                .level(WikiCatalogLevelVO.ONE.getValue())
                                .sort(catalog.getSort())
                                .build();
                        // 添加一级目录
                        wikiCatalogDao.insert(wikiCatalogDO);

                        // 一级目录 ID
                        Long catalogId = wikiCatalogDO.getId();

                        // 获取下面的二级目录
                        List<WikiCatalogAggregate> children = catalog.getChildren();
                        // 更新 type 字段的所有文章 ID
                        List<Long> updateArticleIds = Lists.newArrayList();
                        // WikiCatalogAggregate集合转WikiCatalog集合，记录文章id集合；
                        if (!CollectionUtils.isEmpty(children)) {
                            List<WikiCatalog> level2Catalogs = Lists.newArrayList();
                            children.forEach(child -> {
                                level2Catalogs.add(WikiCatalog.builder()
                                        .wikiId(wikiId)
                                        .title(child.getTitle())
                                        .level(WikiCatalogLevelVO.TWO.getValue())
                                        .sort(child.getSort())
                                        .articleId(child.getArticleId())
                                        .parentId(catalogId)
                                        .isDeleted(0)
                                        .build());

                                updateArticleIds.add(child.getArticleId());
                            });

                            // 批量插入二级目录数据
                            wikiCatalogDao.insertBatch(level2Catalogs);
                            // 批量更新文章的 type 字段：知识库类型
                            articleDao.updateByIds(Article.builder()
                                    .type(ArticleTypeVO.WIKI.getValue()).build(), updateArticleIds);
                        }
                    });
                }
                return 1;
            } catch (Exception e) {
                status.setRollbackOnly();
                log.error("更新wiki记录，失败", e);
                throw new BizException(ResponseCode.WIKI_UPDATED_FAILED);
            }
        });

    }
}
