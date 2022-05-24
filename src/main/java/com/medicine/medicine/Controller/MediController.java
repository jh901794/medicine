package com.medicine.medicine.Controller;


import com.medicine.medicine.config.MD5Generator;
import com.medicine.medicine.domain.entity.MemberEntity;
import com.medicine.medicine.domain.repository.MemberRepository;
import com.medicine.medicine.dto.FileDto;
import com.medicine.medicine.dto.MediDto;
import com.medicine.medicine.dto.MemberDto;
import com.medicine.medicine.service.FileService;
import com.medicine.medicine.service.MediService;
import com.medicine.medicine.service.MemberService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
                String sourceFileNameExtension = FilenameUtils.getExtension(origFilename1).toLowerCase();
                String filename1 = new MD5Generator(origFilename1).toString() + "." + sourceFileNameExtension;
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath1 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\tradstat";
                if (!new File(savePath1).exists()) {
                    try {
                        new File(savePath1).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath1 = savePath1 + "\\" + filename1;
                String category1 = "tradstat";
                tradstat.get(i).transferTo(new File(filePath1));

                FileDto tradstatDto = new FileDto();
                tradstatDto.setOrigFilename(origFilename1);
                tradstatDto.setFilename(filename1);
                tradstatDto.setFilePath(filePath1);
                tradstatDto.setCategory(category1);


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
                String sourceFileNameExtension = FilenameUtils.getExtension(origFilename2).toLowerCase();
                String filename2 = new MD5Generator(origFilename2).toString() + "." + sourceFileNameExtension;
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath2 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\taxbill";
                if (!new File(savePath2).exists()) {
                    try {
                        new File(savePath2).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath2 = savePath2 + "\\" + filename2;
                String category2 = "taxbill";
                taxbill.get(i).transferTo(new File(filePath2));

                FileDto taxbillDto = new FileDto();
                taxbillDto.setOrigFilename(origFilename2);
                taxbillDto.setFilename(filename2);
                taxbillDto.setFilePath(filePath2);
                taxbillDto.setCategory(category2);


                taxbillDto.setMediid(Mediid);
                fileService.saveFile2(taxbillDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//-----------------------------------------------------------------------------------------
        try {
            for (int i = 0; i < shopcert.size(); i++) {
                String origFilename3 = shopcert.get(i).getOriginalFilename();
                String sourceFileNameExtension = FilenameUtils.getExtension(origFilename3).toLowerCase();
                String filename3 = new MD5Generator(origFilename3).toString() + "." + sourceFileNameExtension;
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath3 = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\shopcert";
                if (!new File(savePath3).exists()) {
                    try {
                        new File(savePath3).mkdir();
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                String filePath3 = savePath3 + "\\" + filename3;
                String category3 = "shopcert";
                shopcert.get(i).transferTo(new File(filePath3));

                FileDto shopcertDto = new FileDto();
                shopcertDto.setOrigFilename(origFilename3);
                shopcertDto.setFilename(filename3);
                shopcertDto.setFilePath(filePath3);
                shopcertDto.setCategory(category3);

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

    @PostMapping("/user/login")
    public String login(String userEmail, String password, HttpSession session){
        MemberDto member = memberService.findEmail(userEmail);
        if(member == null){
            System.out.println("Login fail");
            return "redirect:/users/login";
        }

        if(!member.getPassword().equals(password)){
            System.out.println("Login fail");
            return "redirect:/users/login";
        }
        System.out.println("Login Success");

        session.setAttribute("Member", member);

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
    public String detail(@RequestParam(value = "_id") String id, Model medimodel, Model filemodel) {
        String category = "tradstat";
        if (id == null) {
            return "redirect:/";
        } else {
            List<MediDto> mediDtoList = mediService.searchId(id);
            medimodel.addAttribute("boardList", mediDtoList);

            List<FileDto> fileDtoList = fileService.searchmediId(id, category);
            filemodel.addAttribute("fileList", fileDtoList);

            return "board/detail";
        }
    }
    @GetMapping("/detail/tradstat")
    public String detailtrat(@RequestParam(value = "_id") String id, Model medimodel, Model filemodel) {
        String category = "tradstat";
        if (id == null) {
            return "redirect:/";
        } else {
            List<MediDto> mediDtoList = mediService.searchId(id);
            medimodel.addAttribute("boardList", mediDtoList);

            List<FileDto> fileDtoList = fileService.searchmediId(id, category);
            filemodel.addAttribute("fileList", fileDtoList);

            return "board/detail";
        }
    }

    @GetMapping("/detail/taxbill")
    public String detailtax(@RequestParam(value = "_id") String id, Model medimodel, Model filemodel) {
        String category = "taxbill";
        if (id == null) {
            return "redirect:/";
        } else {
            List<MediDto> mediDtoList = mediService.searchId(id);
            medimodel.addAttribute("boardList", mediDtoList);

            List<FileDto> fileDtoList = fileService.searchmediId(id, category);
            filemodel.addAttribute("fileList1", fileDtoList);

            return "board/detail";
        }
    }

    @GetMapping("/detail/shopcert")
    public String detailshop(@RequestParam(value = "_id") String id, Model medimodel, Model filemodel) {
        String category = "shopcert";
        if (id == null) {
            return "redirect:/";
        } else {
            List<MediDto> mediDtoList = mediService.searchId(id);
            medimodel.addAttribute("boardList", mediDtoList);

            List<FileDto> fileDtoList = fileService.searchmediId(id, category);
            filemodel.addAttribute("fileList2", fileDtoList);

            return "board/detail";
        }
    }

}
