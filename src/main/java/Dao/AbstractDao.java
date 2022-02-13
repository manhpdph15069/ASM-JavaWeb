package Dao;

import Utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class AbstractDao<T> {
    public static final EntityManager entityManager = JpaUtil.getEntityManager();

    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable{
        entityManager.close();
        super.finalize();
    }

    public T findById(Class<T> clazz,Integer id){
        return entityManager.find(clazz,id);
    }

    public List<T> findAll(Class<T> clazz, boolean exitsIsActive){
        String entityName = clazz.getSimpleName();// getSimpleName() lấy tên của class
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsIsActive==true){
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
        return query.getResultList();
    }

    //pageSize là số lượng phần tử trong 1 trang
    public List<T> findAll(Class<T> clazz, boolean exitsIsActive,int pageNumber,int pageSize){
        String entityName = clazz.getSimpleName();// getSimpleName() lấy tên của class
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (exitsIsActive==true){
            sql.append(" WHERE isActive = 1");
        }
        TypedQuery<T> query = entityManager.createQuery(sql.toString(),clazz);
        query.setFirstResult((pageNumber-1)*pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public T findOne(Class<T> clazz,String sql,Object... params){
        TypedQuery<T> query = entityManager.createQuery(sql,clazz);
        for (int i = 0; i<params.length;i++){
            query.setParameter(i,params[i]);
        }
        List<T> result = query.getResultList();
        if (result.isEmpty()){
            return null;
        }
        return result.get(0);
    }

    public List<T> findMany(Class<T> clazz,String sql,Object... params){
        TypedQuery<T> query = entityManager.createQuery(sql,clazz);
        for (int i = 0; i<params.length;i++){
            query.setParameter(i,params[i]);
        }
        return query.getResultList();
    }
//Lấy list từ các bảng khác  nhau
    @SuppressWarnings("unchecked")
    public List<Object[]> findManyByNativeQuery(String sql,Object... params){
        Query query = entityManager.createNativeQuery(sql);
        for (int i = 0; i<params.length;i++){
            query.setParameter(i,params[i]);
        }
        return query.getResultList();
    }

    //dùng để dùng Stored Procedure (thủ tục)
    public List<T> callStored(String nameStored, Map<String, Object> params){
        StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(nameStored);
        params.forEach((key,value)->query.setParameter(key,value)); //key là tên tham số chuyền vào,value là giá trị muốn chuyền vào
        return (List<T>) query.getResultList();
    }
    public T create(T entity){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            System.out.println("Create thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Cannot insert entity "+ entity.getClass().getSimpleName() + " to Database");
            throw new RuntimeException(e);
        }
    }

    public T update(T entity){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            System.out.println("Update thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Cannot update entity "+ entity.getClass().getSimpleName() + " to Database");
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity){
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
            System.out.println("Delete thành công");
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            System.out.println("Cannot delete entity "+ entity.getClass().getSimpleName() + " to Database");
            throw new RuntimeException(e);
        }
    }
}
