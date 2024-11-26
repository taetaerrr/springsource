package com.example.movie.controller;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequestMapping("/upload")
@Controller
public class UploadController {

    @Value("${com.example.movie.upload.path}")
    private String uploadPath;

    @GetMapping("/upload")
    public void getUload() {
        log.info("업로드 폼 요청");
    }

    @PostMapping("/upload")
    public void postUpload(MultipartFile[] uploadFiles) {

        for (MultipartFile multipartFile : uploadFiles) {
            log.info("OriginalFilename {}", multipartFile.getOriginalFilename());
            log.info("Size {}", multipartFile.getSize());
            log.info("ContentType {}", multipartFile.getContentType());

            // 이미지 파일 여부 확인
            if (!multipartFile.getContentType().startsWith("image")) {
                return;

            }

            String originName = multipartFile.getOriginalFilename();

            String saveFolderPath = makeFolder();

            // 파일저장 - uuid(중복파일 해결)
            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + saveFolderPath + File.separator + uuid + "_" + originName;

            Path savePath = Paths.get(saveName);

        }
        String dirs = makeFolder();

    }

    private String makeFolder() {
        // 오늘 날짜 구하기
        LocalDate today = LocalDate.now();
        log.info("today {}", today);
        today.format(DateTimeFormatter.ofPattern("YYYY/mm/dd"));

        File dirs = new File(uploadPath, dateStr);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }

        // 폴더구조 / or \\
        // c:/upload/1.jpg or c:\\upload\\1.jpg

        // 날짜나 시간, 숫자 특정 포맷을 지정해 보고 싶다? Formatter
        // SimpleDateFormat sdf = new SimpleDateFormat("YYYY/mm/dd");
        // sdf.format(new Date());

        // 오늘 날짜로 폴더 생성
        return dateStr;

    }

}
