package com.ben.infrastructure.persistent.adapter.repository;

import com.ben.domain.web.adapter.repository.IArchiveRepository;
import com.ben.domain.web.model.aggregate.ArchiveArticleAggregate;
import com.ben.domain.web.model.entity.ArchiveArticleEntity;
import com.ben.infrastructure.persistent.dao.IArticleDao;
import com.ben.infrastructure.persistent.dao.po.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Author: benjieqiang
 * @CreateTime: 2025-01-03  11:41
 * @Description: 归类仓储
 * @Version: 1.0
 */
@Repository
@Slf4j
public class ArchiveRepository implements IArchiveRepository {
    @Autowired
    private IArticleDao articleDao;

    @Override
    public PageInfo<ArchiveArticleAggregate> findArchivePageList(Integer current, Integer size) {
        // 开启分页
        PageHelper.startPage(current, size);
        // 1. 分页查询文章表
        List<Article> articles = articleDao.selectPageList(null, null, null, null);

        PageInfo<Article> articlePageInfo = new PageInfo<>(articles);
        List<Article> articlePageInfoList = articlePageInfo.getList(); // 分页查询的数据；

        List<ArchiveArticleAggregate> resList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(articlePageInfoList)) {
            List<ArchiveArticleEntity> archiveArticleEntities = articlePageInfoList.stream().map(article ->
                    ArchiveArticleEntity.builder()
                            .id(article.getId())
                            .title(article.getTitle())
                            .cover(article.getCover())
                            .createDate(article.getCreateTime().toLocalDate()) // 转成日期
                            .createMonth(YearMonth.from(article.getCreateTime())) // 转成month
                            .build()
            ).collect(Collectors.toList());

            // 按创建的月份进行分组
            Map<YearMonth, List<ArchiveArticleEntity>> map = archiveArticleEntities.stream().collect(Collectors.groupingBy(ArchiveArticleEntity::getCreateMonth));
            // 使用 TreeMap 按月份倒序排列
            Map<YearMonth, List<ArchiveArticleEntity>> sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(map);

            // 遍历排序后的 Map，将其转换为归档 VO
            sortedMap.forEach((k, v) -> resList.add(ArchiveArticleAggregate.builder().month(k).articles(v).build()));
        }
        return new PageInfo<>(resList);
    }
}
