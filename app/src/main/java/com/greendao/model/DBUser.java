package com.greendao.model;

import android.content.Context;
import com.greendao.model.DaoMaster;
import com.greendao.model.DaoSession;
 import com.greendao.model.UserDao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Created by itsdon on 16/7/30.
 */
public class DBUser {
    private Context context;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserDao userDao;
    private static DBUser dbUser;

    private DBUser(Context context){
        this.context = context;
        daoMaster = new DaoMaster(DBManager.getInstance(context).getReadableDatabase());
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    public static DBUser getInstance(Context  context){
        if(dbUser == null){
            synchronized (DBUser.class){
                if(dbUser == null){
                    dbUser = new DBUser(context);
                }
            }
        }
        return  dbUser;
    }


    public void insertUser(User user){
        // 插入前判断该user是否已在数据库中
        if(queryById(user.getName()) != null){
            return;
        }
        if(user != null){
            userDao.insert(user);
        }
    }

    public void insetUserList(List<User> list){
        if(list == null || list.isEmpty()){
            return;
        }
        userDao.insertInTx(list);
    }

    public User queryById(String userName){
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Name.eq(userName));
        if(qb.list() == null || qb.list().size() == 0){
            return null;
        }
        return qb.list().get(0);
    }

    public List<User> queryAllUser(){
        QueryBuilder<User> qb = userDao.queryBuilder();
        return qb.list();
    }

    public void deleteByName(String userName){
        QueryBuilder<User> qb = userDao.queryBuilder();
        DeleteQuery<User> dq = qb.where(UserDao.Properties.Name.eq(userName)).buildDelete();
        dq.executeDeleteWithoutDetachingEntities();
    }

    public void clear(){
        userDao.deleteAll();
    }

    public void update(User user){
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Name.eq(user.getName()));
        List<User> list = qb.list();
        if(list == null ||list.size()== 0){
           return;
        }
        User item  = list.get(0);
        item.setAge(user.getAge());
        item.setName(user.getName());
        userDao.insertOrReplace(item);

    }

}
