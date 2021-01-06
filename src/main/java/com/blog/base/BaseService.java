package com.blog.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * author:xiujiang.liu
 * Date:2018/12/11
 * Time:23:46
 */
public abstract class BaseService<T> {

    private BaseDao<T> dao;

    public BaseService(BaseDao baseDao){
        this.dao= baseDao;
    }

    public T add(T data){
        if(data instanceof BaseDomain){
            ((BaseDomain) data).setCreateTime(LocalDateTime.now());
            ((BaseDomain) data).setLastUpdateTime(LocalDateTime.now());
        }

        data = this.dao.saveAndFlush(data);

        System.out.println("data:"+data);

        return data;
    }

    public T update(T data){
        if(data instanceof BaseDomain){
            ((BaseDomain) data).setLastUpdateTime(LocalDateTime.now());
        }
//        Optional<T> newData = this.dao.findById(((BaseDomain)data).getId());
//        if(newData.isPresent()){
//            return data;
//        }
        data = this.dao.save(data);
        return data;
    }

    public T get(int id){
        if (id == 0) {
            return null;
        }
        Optional<T> t = this.dao.findById(id);
        if(t.isPresent()){
            return t.get();
        }else{
            return null;
        }
    }

    public List<T> findAll(){
        return this.dao.findAll();
    }

    public Page<T> findAll(Example<T> example, Pageable pageable){
        return this.dao.findAll(example,pageable);
    }

//    public List<T> findListByField(List<ConditionParam> conditionParams){
//        Example<T> example;
//        //通过反射把字段都放进去
//
//
//    }

}
