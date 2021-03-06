package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojo.SearchItem;
import pojo.SearchResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;
    public SearchResult search(SolrQuery query) throws Exception {
        QueryResponse response = solrServer.query(query);

        SolrDocumentList solrDocumentList = response.getResults();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem item = new SearchItem();
            item.setId((String) solrDocument.get("id"));
            item.setCategoryname((String)solrDocument.get("item_category_name"));
            item.setPrice((long)solrDocument.get("iten_price"));
            item.setSellpoint((String) solrDocument.get("item_sell_point"));
            Map<String,Map<String,List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle="";
            if(list != null && list.size()>0){
                itemTitle = list.get(0);
            }else{
                itemTitle = (String) solrDocument.get("item_title");
            }
            item.setTitle(itemTitle);
            itemList.add(item);
        }
        SearchResult result = new SearchResult();
        result.setItemList(itemList);
        result.setRecordCount(solrDocumentList.getNumFound());
        return result;
    }
}
