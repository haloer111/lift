package com.gexiao.lift.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

/**
 * 上传工具类
 *
 * @author : lzx
 * created on 2019/3/21
 */
@Slf4j
public class WebUploadUtil {

    public static final String UPLOAD_DIRECTORY = "/upload/";

    /**
     * @param file 上传文件
     * @return 文件依据项目的相对路径+文件名
     */
    public static String upload(MultipartFile file) throws IOException {
        return upload(file, null, null);
    }

    /**
     * @param file      上传文件
     * @param directory 自定义上传目录（为空则使用时间格式yyyy/MM/dd生成目录）
     * @return 文件依据项目的相对路径+文件名
     */
    public static String upload(MultipartFile file, String directory) throws IOException {
        return upload(file, directory, null);
    }

    /**
     * @param file          上传文件
     * @param extensionList 需要过滤的文件后缀名
     * @return 文件依据项目的相对路径+文件名
     */
    public static String upload(MultipartFile file, Collection<String> extensionList) throws IOException {
        return upload(file, null, extensionList);
    }

    /**
     * @param file          上传文件
     * @param directory     自定义上传目录（为空则使用时间格式yyyy/MM/dd生成目录）
     * @param extensionList 需要过滤的文件后缀名
     * @return 文件依据项目的相对路径+文件名
     */
    public static String upload(MultipartFile file, String directory, Collection<String> extensionList) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }

        String originalFilename = Objects.requireNonNull(file.getOriginalFilename(), "文件名不存在");
        int position = originalFilename.lastIndexOf(".");
        if (position <= 0) {
            throw new RuntimeException("文件名中无文件类型扩展名");
        }

        //获取文件扩展名（转换小写）
        String extension = originalFilename.substring(position + 1).toLowerCase();
        if (!CollectionUtils.isEmpty(extensionList) && !extensionList.contains(extension)) {
            throw new RuntimeException("上传文件只接受" + String.join("/", extensionList) + "类型，当前文件类型为：" + extension);
        }

        //若directory为空，则按照时间生成文件目录（按照日期yyyy/MM/dd）
        if (StringUtils.isEmpty(directory)) {
            directory = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        }

        //拼接系统上传目录
        directory = UPLOAD_DIRECTORY + directory;

        //生成UUID文件名
        String fileName = UUIDUtil.generateUUID() + "." + extension;

        //获取项目根路径
        //通过ServletContext获取路径
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes(), "请确认当前处于web环境中");
        ServletContext servletContext = servletRequestAttributes.getRequest().getServletContext();
        String realDirectory = servletContext.getRealPath(directory);
        //创建目录
        Files.createDirectories(Paths.get(realDirectory));
        //保存文件
        file.transferTo(Paths.get(realDirectory, fileName));

        return directory + fileName;
    }


    private WebUploadUtil() {
    }
}
