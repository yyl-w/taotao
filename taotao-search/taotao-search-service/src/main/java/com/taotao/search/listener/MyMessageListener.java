package com.taotao.search.listener;

import com.taotao.mapper.ItemMapper;
import com.taotao.mapper.SearchItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import pojo.SearchItem;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    @Autowired
    private SearchItemMapper searchItemMapper;
    @Autowired
    private SearchItemService service;
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
          String itemId = textMessage.getText();
        SearchItem searchItem = searchItemMapper.getItemById(Long.valueOf(itemId));
        service.addDocument(searchItem);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
