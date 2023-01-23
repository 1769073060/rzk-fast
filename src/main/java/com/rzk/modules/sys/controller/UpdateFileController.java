package com.rzk.modules.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.rzk.common.utils.R;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.swagger.annotations.ApiImplicitParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName : com.rzk.modules.sys.controller
 * @FileName : UpdateFileController
 * @Description : 上传文件
 * @Author : rzk
 * @CreateTime : 2022年 12月 15日 下午10:02
 * @Version : 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/update")
public class UpdateFileController {

    @Value(value = "${minio.endpoint}")
    private String endpoint;

    @Value(value = "${minio.accessKey}")
    private String accessKey;

    @Value(value = "${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucketName}")
    private String bucketName;

    @ApiImplicitParam(name = "userId", value = "用户的id", required = true, dataType = "String", paramType = "query")
    @PostMapping("/upLoadImage")
    public R upLoadImage(String userId, @RequestParam("file") MultipartFile image) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return R.error("用户id不能为空");
        }
        if (image.isEmpty()) {
            return R.error("不能上传空文件哦");

        }
        //图片上传路径
        //String fileDownloadPath = "C:\\lnsf_mod_dev";
        //String fileDownloadPath = "/opt/lnsf_mod_dev";
        //图片保存路径
        //String fileUploadPath ="/"+userId+"/image";
        String uploadFile=null;
        if (image != null && !image.isEmpty()) {

            String imageName = image.getOriginalFilename();
            if (StringUtils.isNotBlank(imageName)) {

                String date = DateUtil.formatDate(new Date());
                String filename = System.currentTimeMillis() + image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
                String imgName = userId + "/" + "img" + "/" + date + "/" + filename;
                minIoClientUpload(image.getInputStream(), imgName);
                uploadFile =  "/" + bucketName + "/" + imgName;
                /**
                 String videoUrl = endpoint + "/" + bucketName + "/" + imgName;
                 //获取视频的第一帧图片输出流
                 InputStream first = MinioUtils.randomGrabberFFmpegImage(videoUrl);
                 //获取文件名
                 String fileName = videoUrl.substring(videoUrl.lastIndexOf("/"), videoUrl.lastIndexOf(".")).concat(".jpg");
                 //将流转化为multipartFile
                 MultipartFile multipartFile = new MockMultipartFile("file", fileName, "image/jpg", first);

                 String pictureName = minioUtils.upload(multipartFile);
                 logger.info("pictureName{}=============>"+pictureName);
                 String pictureUrl = endpoint + "/" + bucketName + "/" + pictureName;
                 **/

                //图片上传最终路径
                //图片最终	保存路径
                log.info("上传地址："+uploadFile);
                return R.ok("http://www.rzkai.com:9999"+uploadFile);
            }
        } else {
            return R.error("上传功能出错");
        }
        log.info("上传地址："+uploadFile);
        return R.ok("http://www.rzkai.com:9999"+uploadFile);

    }

    /**
     * 上传
     * @param imgInputStream
     * @param objectName
     * @throws Exception
     */
    public void minIoClientUpload(InputStream imgInputStream, String objectName) throws Exception {
        //创建头部信息
        Map<String, String> headers = new HashMap<>(10);
        //添加自定义内容类型
        headers.put("Content-Type", "application/octet-stream");
        //添加存储类
        headers.put("X-Amz-Storage-Class", "REDUCED_REDUNDANCY");
        //添加自定义/用户元数据
        Map<String, String> userMetadata = new HashMap<>(10);
        userMetadata.put("My-Project", "Project One");


        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(endpoint)
                        .credentials(accessKey, secretKey)
                        .build();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(imgInputStream, imgInputStream.available(), -1)
                        .userMetadata(userMetadata)
                        .build());
        imgInputStream.close();

    }
}
