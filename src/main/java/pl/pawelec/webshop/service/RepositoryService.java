/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.service;

import java.util.List;
import pl.pawelec.webshop.model.Repository;

/**
 *
 * @author mirek
 */
public interface RepositoryService {
    void create(Repository repository);
    void update(Repository repository);
    void delete(Repository repository);
    void deleteById(Long Id);
    void deleteAll();
    Repository getById(Long Id);
    List<Repository> getAll();
    Long count();
    boolean exists(Long Id);
    Repository getByLoadunitNo(String loadunitNo);
    List<Repository> getByStatus(String status);
}
