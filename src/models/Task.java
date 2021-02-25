package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
* tasklistデータベースのtasksデーブルのDTOクラス
* @author yusuke hoguro
* @version 1.0.0
*/


@Entity

@NamedQueries({
    @NamedQuery(
            name = "getAllTasks",
            /*
             * 注：テーブル名のところ（Message）はJPQLではテーブル名ではなく、エンティティクラス名
             * from句にはエンティティ名とそのエンティティのエイリアス(別名)を記述 FROM Task t
             * select句にエンティティを指定すると、エンティティの全プロパティが取得される（select *に近い）
             */
            query = "SELECT t FROM Task t ORDER BY t.id DESC"
            ),
    @NamedQuery(
            name = "getTasksCount",
            /*
             * 注：テーブル名のところ（Message）はJPQLではテーブル名ではなく、エンティティクラス名
             * from句にはエンティティ名とそのエンティティのエイリアス(別名)を記述 FROM Task t
             * select句にエンティティを指定すると、エンティティの全プロパティが取得される（select *に近い）
             */
            query = "SELECT COUNT(t) FROM Task t"
            )
})
@Table(name = "tasks")
public class Task {

    @Id
    @Column(name = "id")
    //主キーの自動採番
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "created_at",  nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at",  nullable = false)
    private Timestamp updated_at;


    //■フィールドのgetter/setter

     /**
     * idのgetter
     * @return idを返す
     */
    public Integer getId() {
        return id;
    }

     /**
     * idのsetter
     */
    public void setId(Integer id) {
        this.id = id;
    }

     /**
     * contentのgetter
     * @return contentを返す
     */
    public String getContent() {
        return content;
    }

    /**
    * contentのsetter
    */
    public void setContent(String content) {
        this.content = content;
    }

    /**
    * created_atのgetter
    * @return created_atを返す
    */
    public Timestamp getCreated_at() {
        return created_at;
    }

    /**
    * created_atのsetter
    */
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    /**
    * updated_atのgetter
    * @return updated_atを返す
    */
    public Timestamp getUpdated_at() {
        return updated_at;
    }

    /**
    * updated_atのsetter
    */
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }




}
