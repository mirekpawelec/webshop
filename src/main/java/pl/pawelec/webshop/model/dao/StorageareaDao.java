/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import pl.pawelec.webshop.model.Storagearea;

/**
 *
 * @author mirek
 */
public interface StorageareaDao extends Dao<Storagearea>{
    List<Storagearea> getByDescription(String wholeDescriptionOrPart);
}
