package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.ItemDescMapper;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import pojo.EasyUIResult;
import pojo.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IDUtils;

import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private Destination topicDestination;

    @Override
    public TbItem findItemById(Long itemId) {
        return itemMapper.findItemById(itemId);
    }

    @Override
    public EasyUIResult findItem(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TbItem> items = itemMapper.findItemAll();
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        EasyUIResult result = new EasyUIResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(items);
        return result;
    }

    @Override
    public TaotaoResult delItems(Long[] ids) {
        int i = itemMapper.delItems(ids);
        if (i != 0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult updateUpItem(Long[] ids) {
        int i = itemMapper.updateUpItem(ids);
        if (i != 0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult updateDownItem(Long[] ids) {
        int i = itemMapper.updateDownItem(ids);
        if (i != 0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult addItems(TbItem tbItem, String desc) {
        Long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        Date date = new Date();
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        //所有数据准备完毕才能添加商品信息
        int itemCount = itemMapper.addItems(tbItem);
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        //准备了描述信息的所有数据才能添加描述信息
        int itemDescCount = itemDescMapper.addItemDesc(itemDesc);

        if(itemCount!=0&&itemDescCount!=0){
            jmsTemplate.send(topicDestination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(itemId+"");
                    return message;
                }
            });
            return TaotaoResult.ok();
        }

        return TaotaoResult.build(500,"添加商品有误，请重新输入");
    }
}



