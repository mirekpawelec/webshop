/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.ClientMessage;

/**
 *
 * @author mirek
 */
public interface ClientMessageService {
    void create(ClientMessage clientMessage);
    void update(ClientMessage clientMessage);
    void delete(ClientMessage clientMessage);
    void deleteById(Long id);
    void deleteAll();
    ClientMessage getOneById(Long id);
    List<ClientMessage> getAll();
    Long count();
    boolean exists(Long id);
}
