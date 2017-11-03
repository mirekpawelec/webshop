/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.model.dao;

import java.util.List;
import pl.pawelec.webshop.model.Repository;

/**
 *
 * @author mirek
 */
public interface RepositoryDao extends Dao<Repository>{
    Repository getByLoadunitNo(String loadunitNo);
    List<Repository> getByStatus(String status);
    List<Repository> getByProductNo(String productNo);
}
