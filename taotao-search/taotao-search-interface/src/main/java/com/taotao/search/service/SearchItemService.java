package com.taotao.search.service;

import pojo.SearchItem;
import pojo.SearchResult;
import pojo.TaotaoResult;

public interface SearchItemService {
    TaotaoResult importAllItems();
    SearchResult search(String queryString,int page,int rows) throws Exception;

    void addDocument(SearchItem searchItem);
}
