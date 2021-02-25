package models;

import java.util.ArrayList;
import java.util.List;

/**
* タスク管理アプリケーションのバリデーション用クラス
* @author yusuke hoguro
* @version 1.0.0
*/

public class TaskValidator {

    /**
    * バリデーションを実施する
    * @return エラー内容が保存されたString型のリストを返す
    */
    public static List<String> validate(Task taskTable){

        List<String> errors = new ArrayList<String>();

        String taskError = validateTask(taskTable.getContent());

        if(!taskError.equals("")){

            errors.add(taskError);

        }

        return errors;

    }

    /**
    * タスクの入力項目チェック
    * @return エラー内容が保存されたString型のリストを返す
    */
    private static String validateTask(String task){
        if(task == null || task.equals("")){

            return "タスクを入力してください。";

        }

        return "";
    }


}
