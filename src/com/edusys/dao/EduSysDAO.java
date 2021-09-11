
package com.edusys.dao;

import java.util.List;
import java.util.ArrayList;

abstract public class EduSysDAO<EntityType, KeyType> {
   abstract public void insert(EntityType enity);
   abstract public void update(EntityType enity);
   abstract public void delete(KeyType id);
   abstract public EntityType selectById(KeyType id);
   abstract public List<EntityType> selectAll();
   abstract protected List<EntityType> selectBySql(String sql, Object...args);
}
