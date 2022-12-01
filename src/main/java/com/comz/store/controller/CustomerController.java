package com.comz.store.controller;


import com.comz.store.controller.ex.*;
import com.comz.store.entity.Customer;
import com.comz.store.service.ICustomerService;
import com.comz.store.service.ex.UsernameDuplicateException;
import com.comz.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController //等价于 @Controller + @ResponseBody
@RequestMapping("customers")
public class CustomerController extends BaseController{

    @Autowired
    private ICustomerService customerService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(Customer customer) {
        JsonResult<Void> result = new JsonResult<>();

        customerService.reg(customer);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<Customer> login(String username, String password, HttpSession session) {
        Customer data = customerService.login(username, password);

        //向session对象中完成数据的绑定
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());

        System.out.println(getUidFromSession(session));
        System.out.println(getUsernameFromSession(session));

        return new JsonResult<Customer>(OK, data);
    }

    @RequestMapping("change_pwd")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        /*System.out.println("controller层的oldPassword = " + oldPassword);
        System.out.println("controller层的newPassword = " + newPassword);*/
        customerService.changePassword(uid, username, oldPassword, newPassword);

        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<Customer> getByUid(HttpSession session) {
        Customer data = customerService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("change_info")
    public JsonResult<Customer> changeInfo(Customer customer, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        /*//
        System.out.println(uid);
        System.out.println(username);
        System.out.println(customer.getPhone());
        System.out.println(customer.getEmail());
        //*/
        customerService.changeInfo(uid, username, customer);
        return new JsonResult<>(OK);
    }

    /* 设置上传的文件的最大值为10MB */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;

    /* 限制上传文件的类型 */
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("File is empty");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("File is out of limited size");
        }

        String contentType = file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)) {
            throw new FileTypeException("File type not supported");
        }

        //上传的文件 .../upload/file.png
        String parent = session.getServletContext().getRealPath("upload");
        //File对象指向这个路径，检查File是否存在
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdir(); //当前路径不存在，则创建一个
        }
        System.out.println("相对路径为 = " + dir);

        //获取当前文件的名称，用UUID生成一个新的字符串作为文件名
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;
        File dest = new File(dir, filename);// 是一个空文件
        System.out.println("dest = " + dest);
        //参数file中的数据写入到空文件(pre:这两个文件的前后缀一致)
        try {
            file.transferTo(dest);
            System.out.println("after dest = " + dest);
        } catch (FileStateException e) {
            throw new FileStateException("File state exceptions");
        } catch (IOException e) {
            throw new FileUploadIOException("File read and write exceptions");
        }

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        //返回头像的路径(相对路径)，/upload/test.png
        String avatar = "/upload/" + filename;
        customerService.changeAvatar(uid, avatar, username);

        return new JsonResult<>(OK, avatar);
    }

    @RequestMapping("logout")
    public JsonResult<Void> logout(HttpSession session) {
        String username = getUsernameFromSession(session);
        session.removeAttribute(username);
        session.invalidate(); // 设置session失效
        return new JsonResult<>(OK);
    }

}
