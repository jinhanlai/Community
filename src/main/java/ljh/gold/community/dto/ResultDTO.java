package ljh.gold.community.dto;

import ljh.gold.community.exception.CustomizeErrorCode;
import ljh.gold.community.exception.CustomizeException;
import lombok.Data;

/**
 * @Author: jinhanlai
 * @Date: 2020/2/5 15:31
 */
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorof(Integer code, String message) {

        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorof(CustomizeErrorCode errorCode) {
        return errorof(errorCode.getCode(), errorCode.getMessage());
    }


    public static ResultDTO errorof(CustomizeException e) {
        return errorof(e.getCode(), e.getMessage());
    }

    public static ResultDTO okof() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！！");
        return resultDTO;
    }

    public static <T> ResultDTO okof(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！！");
        resultDTO.setData(t);
        return resultDTO;
    }
}
