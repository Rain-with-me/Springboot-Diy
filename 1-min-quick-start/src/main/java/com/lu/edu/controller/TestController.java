package com.lu.edu.controller;




import com.lu.edu.utils.result.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zkteco.id100com.jni.id100sdk;

/**
 * @Author: 雨同我
 * @Description:
 * @DateTime: 2023/7/17 14:46
 **/
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "ok";
    }


    @GetMapping("/getCardT")
    public CommonResult<IdentificationCardVo> getCard() {

        String path = id100sdk.getPath();
        if (id100sdk.InitCommExt() <= 0) {
            System.out.println("InitCommExt fail");
            return CommonResult.failed("连接身份证阅读器失败");
        }
        System.out.println("InitCommExt succ");


        if (id100sdk.Authenticate() != 1) {
            System.out.println("Authenticate fail");
            return CommonResult.failed("验证身份证失败");
        }
        System.out.println("Authenticate succ");
        //循环读取
        long tickStart = System.currentTimeMillis();
        int ret = 0;
        while ((ret = id100sdk.ReadContent(1)) != 1 && System.currentTimeMillis() < 2000) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (ret != 1) {
            System.out.println("ReadContent fail");
            return CommonResult.failed("读取身份证信息失败");
        }

        System.out.println("ReadContent succ");
        System.out.println("输出文件路径：" + path);
        System.out.println("GetBmpPhotoExt ret=" + id100sdk.GetBmpPhotoExt());
        System.out.println("\n");
        System.out.println("姓名=" + id100sdk.getName());
        System.out.println("民族=" + id100sdk.getNation());
        System.out.println("性别=" + id100sdk.getSex());
        System.out.println("身份证号 =" + id100sdk.getIDNum());
        System.out.println("出生日期=" + id100sdk.getBirthdate());
        System.out.println("常住地址=" + id100sdk.getAddress());
        System.out.println("签发机关=" + id100sdk.getIssue());
        System.out.println("有效期=" + id100sdk.getEffectedDate() + "-" + id100sdk.getExpireDate());
        //System.out.printf("bmp base64头像=%s\n", id100sdk.getBMPPhotoBase64());
        System.out.printf("jpg base64头像=%s\n", id100sdk.getJPGPhotoBase64());

        IdentificationCardVo build = IdentificationCardVo.builder().path(path)
                .name(id100sdk.getName())
                .nation(id100sdk.getNation())
                .sex(id100sdk.getSex())
                .idNumber(id100sdk.getIDNum())
                .birthdate(id100sdk.getBirthdate())
                .address(id100sdk.getAddress())
                .issueAuthority(id100sdk.getIssue())
                .effectiveDate(id100sdk.getEffectedDate())
                .expiryDate(id100sdk.getExpireDate())
                .jpgPhotoBase64(id100sdk.getJPGPhotoBase64()).build();

        return CommonResult.success(build);
    }


    @GetMapping("/getCard")
    public CommonResult<IdentificationCardVo> getCardTest() throws InterruptedException {
        String path = id100sdk.getPath();
        if (id100sdk.InitCommExt() <= 0) {
            System.out.println("InitCommExt fail");
            return CommonResult.failed("连接身份证阅读器失败");
        }
        System.out.println("InitCommExt succ");

        //循环读取
        long tickStart = System.currentTimeMillis();
        long timeout = 10000; // 设置超时时间为 10 秒
        int ret = 0;
        while (true) {
            ret = id100sdk.ReadContent(1);
            if (ret>=1){
                break;
            }
            System.out.println(ret);
            Thread.sleep(1000);
            if (id100sdk.Authenticate()==1){
                ret=1;
                break;
            }
            if((System.currentTimeMillis() - tickStart) > timeout){
                break;
            }
        }
        if (ret != 1) {
            System.out.println("ReadContent fail");
            return CommonResult.failed("读取身份证信息失败");
        }

        ret=0;
        while ((ret = id100sdk.ReadContent(1)) != 1 && System.currentTimeMillis() < 2000) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("ReadContent succ");
        System.out.println("输出文件路径：" + path);
        System.out.println("GetBmpPhotoExt ret=" + id100sdk.GetBmpPhotoExt());
        System.out.println("\n");
        System.out.println("姓名=" + id100sdk.getName());
        System.out.println("民族=" + id100sdk.getNation());
        System.out.println("性别=" + id100sdk.getSex());
        System.out.println("身份证号 =" + id100sdk.getIDNum());
        System.out.println("出生日期=" + id100sdk.getBirthdate());
        System.out.println("常住地址=" + id100sdk.getAddress());
        System.out.println("签发机关=" + id100sdk.getIssue());
        System.out.println("有效期=" + id100sdk.getEffectedDate() + "-" + id100sdk.getExpireDate());
        //System.out.printf("bmp base64头像=%s\n", id100sdk.getBMPPhotoBase64());
        System.out.printf("jpg base64头像=%s\n", id100sdk.getJPGPhotoBase64());

        IdentificationCardVo build = IdentificationCardVo.builder().path(path)
                .name(id100sdk.getName())
                .nation(id100sdk.getNation())
                .sex(id100sdk.getSex())
                .idNumber(id100sdk.getIDNum())
                .birthdate(id100sdk.getBirthdate())
                .address(id100sdk.getAddress())
                .issueAuthority(id100sdk.getIssue())
                .effectiveDate(id100sdk.getEffectedDate())
                .expiryDate(id100sdk.getExpireDate())
                .jpgPhotoBase64(id100sdk.getJPGPhotoBase64()).build();

        return CommonResult.success(build);

    }
}

