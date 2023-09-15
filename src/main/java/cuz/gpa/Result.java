package cuz.gpa;

import lombok.Data;

// 后端返回数据的封装
@Data
public class Result {

    private int code;//编码 200or400
    private String msg;//成功or失败
    private Long total;//记录总数
    private Object data;//数据

    public static Result fail(){
        return result(400,"Fail", 0L, (Object) null);
    }
    public static Result success(){
        return result(200,"Suc", 0L, (Object) null);
    }
    public static Result success(Object data){
        return result(200,"Suc", 0L, data);
    }
    public static Result success(Object data,Long total){
        return result(200,"Suc", total, data);
    }
    private static Result result(int code, String msg, Long total, Object data){

        Result res = new Result();
        res.setData(data);
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);

        return res;
    }
}
