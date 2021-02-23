package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
* Message_boardデータベースのDAOクラスです
* @author yusuke hoguro
* @version 1.0.0
*/

public class DBUtil {

    //永続化ユニット名(データベース名)を指定
    private static final String PESISTENC_UNIT_NAME = "tasklist";

    //「エンティティマネージャ」を作成するためのファクトリクラスの変数宣言
    private static EntityManagerFactory emf;

    //EntityManagerのインスタンス作成
    public static EntityManager createEntityManager(){

        //EntityManagerFactoryクラスのcreateEntityManagerメソッドでEntityManagerのインスタンス作成
        return getEntityManagerFactory().createEntityManager();

    }


    private static EntityManagerFactory getEntityManagerFactory(){

        if(emf == null){

            //EntityManagerFactoryのインスタンスを作成する
            emf = Persistence.createEntityManagerFactory(PESISTENC_UNIT_NAME);
        }

        return emf;
    }

}