/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pawelec.webshop.model.ClientMessage;
import pl.pawelec.webshop.model.dao.ClientMessageDao;
import pl.pawelec.webshop.service.ClientMessageService;

/**
 *
 * @author mirek
 */
@Service
@Transactional
public class ClientMessageServiceImpl implements ClientMessageService{
    @Autowired
    private ClientMessageDao clientMessageDao;
    
    @Override
    public void create(ClientMessage clientMessage) {
        clientMessageDao.create(clientMessage);
    }

    @Override
    public void update(ClientMessage clientMessage) {
        clientMessageDao.update(clientMessage);
    }

    @Override
    public void delete(ClientMessage clientMessage) {
        clientMessageDao.delete(clientMessage);
    }

    @Override
    public void deleteById(Long id) {
        clientMessageDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        clientMessageDao.deleteAll();
    }

    @Override
    public ClientMessage getOneById(Long id) {
        return clientMessageDao.getOneById(id);
    }

    @Override
    public List<ClientMessage> getAll() {
        return clientMessageDao.getAll();
    }

    @Override
    public Long count() {
        return clientMessageDao.count();
    }

    @Override
    public boolean exists(Long id) {
        return clientMessageDao.exists(id);
    }
    
}
