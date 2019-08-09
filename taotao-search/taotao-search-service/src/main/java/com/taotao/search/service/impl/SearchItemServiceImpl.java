package com.taotao.search.service.impl;

import com.taotao.mapper.SearchItemMapper;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.SearchItem;
import pojo.SearchResult;
import pojo.TaotaoResult;

import java.io.IOException;
import java.util.List;

@Service
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SolrServer solrServer;
    @Autowired
    private SearchDao searchDao;
    @Override
    public TaotaoResult importAllItems() {
        List<SearchItem> searchItems = searchItemMapper.getItemList();
        for (SearchItem searchItem : searchItems) {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",searchItem.getId());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSellpoint());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCategoryname());
            document.addField("item_desc",searchItem.getItemdesc());
            try {
                solrServer.add(document);
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String queryString, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        if(queryString != null && "".equals(queryString)){
            query.setQuery(queryString);
        }else{
            query.setQuery("*:*");
        }
        query.setStart((page-1)*rows);
        query.setRows(rows);
        query.set("df","item_keywords");

        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<span style='color:red'>");
        query.setHighlightSimplePost("</span>");
        SearchResult result = searchDao.search(query);
        long totlpage = result.getRecordCount()%rows==0?result.getRecordCount()/rows:result.getRecordCount()/rows+1;
        return result;
    }

    @Override
    public void addDocument(SearchItem searchItem) {
        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",searchItem.getId());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSellpoint());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCategoryname());
            document.addField("item_desc",searchItem.getItemdesc());
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
