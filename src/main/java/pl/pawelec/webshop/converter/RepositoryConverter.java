/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pawelec.webshop.converter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.pawelec.webshop.model.Repository;
import pl.pawelec.webshop.service.RepositoryService;

/**
 *
 * @author mirek
 */
public class RepositoryConverter implements Converter<Object, Repository>{
    
    private Logger logger = Logger.getLogger(RepositoryConverter.class);
    
    @Autowired
    private RepositoryService repositoryService;
    
    @Override
    public Repository convert(Object element) {
        Long id = Long.valueOf( (String) element );
        Repository repository = repositoryService.getById(id);
        logger.info("### RepositoryConverter: element="+id+" , repository=" + repository );
        return repository;
    }
    
}
