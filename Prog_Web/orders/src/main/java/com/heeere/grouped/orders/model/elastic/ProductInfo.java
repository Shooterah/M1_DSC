package com.heeere.grouped.orders.model.elastic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

/**
 * ProductInfo
 */
/* ___ELASTICSEARCH___ */
@Entity
@Data
@Document(indexName = "product")
public class ProductInfo {

    // https://docs.spring.io/spring-boot/docs/2.2.1.RELEASE/reference/html/howto.html#howto-use-spring-data-jpa--and-mongo-repositories
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String url;
    String shortName;
    String longName;


    /*
     cat openfoodfacts_search.csv | awk -F "\t" '{print NR"ề"$2"ề"$6"ề"$7}' | tr -d '"' | sed 's@\([^ề]*\)ề\([^ề]*\)ề\([^ề]*\)ề\([^ề]*\)@curl -XPOST "http://localhost:9200/product/productinfo/\1?pretty" -H "Content-Type: application/json" -d "{\\"url\\": \\"\2\\", \\"shortName\\":\\"\3\\", \\"longName\\":\\"\4\\"}"@g'
     
     e.g.,

     curl -XPOST "http://localhost:9200/product/productinfo/303?pretty" -H "Content-Type: application/json" -d "{\"url\": \"https://world.openfoodfacts.org/product/61370944\", \"shortName\":\"Salade de Falafel et de Boulgour de Blé\", \"longName\":\"Boulghour de blé cuit et sauce au citron et à l'huile de menthe avec falafel et fromage feta avec sauce au houmous épicée. - Salade de Falafel et de Boulgour de Blé\"}"
     curl -XPOST "http://localhost:9200/product/productinfo/262?pretty" -H "Content-Type: application/json" -d "{\"url\": \"https://world.openfoodfacts.org/product/3222476718136\", \"shortName\":\"Faisselle 0% de matières grasses\", \"longName\":\"Fromage frais en faisselle au lait écrémé pasteurisé\"}"
     curl -XPOST "http://localhost:9200/product/productinfo/237?pretty" -H "Content-Type: application/json" -d "{\"url\": \"https://world.openfoodfacts.org/product/3596710413744\", \"shortName\":\"Pause Snack Jambon Concombre\", \"longName\":\"Sandwich au pain suédois garni de jambon cuit choix, de sauce au fromage blanc et à la menthe et de concombre\"}"

    */
}