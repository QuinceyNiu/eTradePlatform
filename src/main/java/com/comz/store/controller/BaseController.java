package com.comz.store.controller;


import com.comz.store.controller.ex.*;
import com.comz.store.service.ex.*;
import com.comz.store.util.JsonResult;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/** 控制层的基类 */
public class BaseController {
    // 操作成功状态码
    public static final int OK = 200;

    /**
     *  一旦产生了异常，则会自动跳转到被@ExceptionHandler所修饰的方法，
     * @param e 异常对象
     * @return 返回的JSON数据格式
     */
    //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<>(e);

        if (e instanceof UsernameDuplicateException) {
            result.setState(4000);
            result.setMessage("Username is occupied");
        } else if (e instanceof AddressCountLimitException) {
            result.setState(4003);
            result.setMessage("The user's shipping address exceeds the upper limit exception");
        } else if (e instanceof InsertException) {
            result.setState(5000);
            result.setMessage("Unknown errors were generated during user registration");
        } else if (e instanceof UsernameNotFoundException) {
            result.setState(5001);
            result.setMessage("User data does not exist");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(5002);
            result.setMessage("Password error");
        } else if (e instanceof UpdateException) {
            result.setState(5003);
            result.setMessage("Unknown exception when updating data");
        } else if (e instanceof DeleteException) {
            result.setState(5004);
            result.setMessage("Unknown exception when deleting data");
        } else if (e instanceof FileEmptyException) {
            result.setState(6000);
        } else if (e instanceof FileSizeException) {
            result.setState(6001);
        } else if (e instanceof FileTypeException) {
            result.setState(6002);
        } else if (e instanceof FileStateException) {
            result.setState(6003);
        } else if (e instanceof FileUploadIOException) {
            result.setState(6004);
        }

        return result;
    }

    /**
     * 获取当前登录用户session对象中的uid
     * @param session session对象
     * @return 当前登录的用户的uid值
     */
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取当前登录用户session对象的username
     * @param session session对象
     * @return 返回当前登录用户的username
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
}
