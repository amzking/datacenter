package com.ccue.datacenter.core.db.mongo;

import java.util.List;

public interface MongoBaseService<T> {


    void insert(T t);

    void upsert(T t);

    void delete(T t);

    T query();

    void deleteByQuery(T t);

    void insertAll(List<T> list);

    void upsertAll(List<T> list);


}
