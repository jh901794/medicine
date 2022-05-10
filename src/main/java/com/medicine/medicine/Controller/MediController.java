package com.medicine.medicine.Controller;


import com.medicine.medicine.config.MD5Generator;
import com.medicine.medicine.dto.FileDto;
import com.medicine.medicine.dto.MediDto;
import com.medicine.medicine.dto.MemberDto;
import com.medicine.medicine.service.FileService;
import com.medicine.medicine.service.MediService;
import com.medicine.medicine.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class MediController {
    private FileService fileService;
    private MediService mediService;
    private MemberService memberService;


    @GetMapping("/")
    public String list(Model model) {
        List<MediDto> mediList = mediService.getmedilist();

        model.addAttribute("boardList", mediList);
        return "board/main";
    }

    //글쓰기
    @GetMapping("/post")
    public String write() {
        return "board/write";
    }

    @PostMapping("/post")
    public String write(@RequestParam(value = "tradstat", required = false) List<MultipartFile> tradstat,
                        @RequestParam(value = "taxbill", required = false) List<MultipartFile> taxbill,
                        @RequestParam(value = "shopcert", required = false) List<MultipartFile> shopcert,
                        MediDto mediDto) throws Exception {
        Long Mediid = mediService.savePost(mediDto);
        try {
            for (int i = 0; i < tradstat.size(); i++) {
                String origFilename1 = tradstat.get(i).getOriginalFilename();
                String filename1 = new MD5Generator(origFilename1).toString();
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath1 = System.getProperty("user.dir") + "\\tradstat";
                if (!new File(savePath1).exists()) {
                    try {
                        new File(savePath1).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath1 = savePath1 + "\\" + filename1;
                tradstat.get(i).transferTo(new File(filePath1));

                FileDto tradstatDto = new FileDto();
                tradstatDto.setOrigFilename(origFilename1);
                tradstatDto.setFilename(filename1);
                tradstatDto.setFilePath(filePath1);


                tradstatDto.setMediid(Mediid);
                fileService.saveFile1(tradstatDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//-----------------------------------------------------------------------------------------
        try {
            for (int i = 0; i < taxbill.size(); i++) {
                String origFilename2 = taxbill.get(i).getOriginalFilename();
                String filename2 = new MD5Generator(origFilename2).toString();
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath2 = System.getProperty("user.dir") + "\\taxbill";
                if (!new File(savePath2).exists()) {
                    try {
                        new File(savePath2).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath2 = savePath2 + "\\" + filename2;
                taxbill.get(i).transferTo(new File(filePath2));

                FileDto taxbillDto = new FileDto();
                taxbillDto.setOrigFilename(origFilename2);
                taxbillDto.setFilename(filename2);
                taxbillDto.setFilePath(filePath2);


                taxbillDto.setMediid(Mediid);
                fileService.saveFile2(taxbillDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//-----------------------------------------------------------------------------------------
        try {
            for (int i = 0; i < taxbill.size(); i++) {
                String origFilename3 = shopcert.get(i).getOriginalFilename();
                String filename3 = new MD5Generator(origFilename3).toString();
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath3 = System.getProperty("user.dir") + "\\shopcert";
                if (!new File(savePath3).exists()) {
                    try {
                        new File(savePath3).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath3 = savePath3 + "\\" + filename3;

                shopcert.get(i).transferTo(new File(filePath3));
                FileDto shopcertDto = new FileDto();
                shopcertDto.setOrigFilename(origFilename3);
                shopcertDto.setFilename(filename3);
                shopcertDto.setFilePath(filePath3);

                shopcertDto.setMediid(Mediid);
                fileService.saveFile3(shopcertDto);
            }


            //mediService.savePost(mediDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }


    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<MediDto> mediDtoList = mediService.searchPosts(keyword);

        model.addAttribute("boardList", mediDtoList);

        return "board/main";
    }


    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup() {
        return "user/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(MemberDto memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "user/login";
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult(Model model) {
        List<MediDto> mediList = mediService.getmedilist();

        model.addAttribute("boardList", mediList);
        return "board/main";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "redirect:/";
    }


    // 진행사항 소팅
    @GetMapping("/board/doing")
    public String doing(Model model) {
        List<MediDto> mediList = mediService.doingPost("진행");

        model.addAttribute("boardList", mediList);
        return "board/main";
    }

    @GetMapping("/board/done")
    public String done(Model model) {
        List<MediDto> mediList = mediService.doingPost("완료");

        model.addAttribute("boardList", mediList);
        return "board/main";
    }

    //기간 소팅
    @GetMapping("/date/range")
    public String rangedate(@RequestParam("strdate")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate strdate,
                            @RequestParam("enddate")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate,
                            Model model) {
        if (strdate.equals("") && enddate.equals("")) {
            List<MediDto> mediList = mediService.getmedilist();

            model.addAttribute("boardList", mediList);
            return "redirect:/";
        } else {
            List<MediDto> mediList = mediService.Daterange(strdate, enddate);

            model.addAttribute("boardList", mediList);
            return "board/main";
        }
    }

    //상세 페이지 이동
    @GetMapping("/detail")
    public String detail(@RequestParam(value = "_id") String id, Model model) {
        if (id == null) {
            return "redirect:/";
        } else {
            List<MediDto> mediDtoList = mediService.searchId(id);

            model.addAttribute("boardList", mediDtoList);

            return "board/detail";
        }
    }

}
