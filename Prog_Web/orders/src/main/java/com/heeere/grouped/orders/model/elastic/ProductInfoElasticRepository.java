package com.heeere.grouped.orders.model.elastic;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ProductInfoElasticRepository
 */
/* ___ELASTICSEARCH___ */ // Add a slash as in //*/ just below to enable
/*/
public interface ProductInfoElasticRepository extends ElasticsearchRepository<ProductInfo, Long> {
/*/
public interface ProductInfoElasticRepository { //*/

    long count(); // already defined, but make it explicit, especially for the code to compile without "extend"

    List<ProductInfo> findByShortNameContaining(String sub);

}