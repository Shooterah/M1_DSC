package com.heeere.grouped.orders.controller;

import java.util.List;

import javax.inject.Inject;

import com.heeere.grouped.orders.model.Product;
import com.heeere.grouped.orders.model.elastic.ProductInfo;
import com.heeere.grouped.orders.model.elastic.ProductInfoElasticRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ElasticController
 */
/* ___ELASTICSEARCH___ */ // Remove a slash below as in /**/ to enable elastic search
//**/@Controller
public class ElasticController {
    /* ___ELASTICSEARCH___ */
    @Inject
    ProductInfoElasticRepository piElasticRepo;

    /* ___ELASTICSEARCH___ */
    @RequestMapping("/manage/product/{id}-elastic")
    public String productDetail(Model model, @ModelAttribute("id") Product p) {
        var elasticSize = 0;
        elasticSize = (int) piElasticRepo.count();
        model.addAttribute("elastic", true);
        model.addAttribute("elasticSize", elasticSize);
        model.addAttribute("p", p);
        return "product-details";
    }

    /* ___ELASTICSEARCH___ */
    @RequestMapping("/manage/product/ELASTICALL")
    @ResponseBody
    public List<ProductInfo> allPI(@RequestParam String q) {
        var suggestions = piElasticRepo.findByShortNameContaining(q);
        return suggestions;
    }

}