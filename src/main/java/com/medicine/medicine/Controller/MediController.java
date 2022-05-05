package com.medicine.medicine.Controller;

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

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class MediController {
    private MediService mediService;
    private MemberService memberService;
    private FileService fileService;

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
    public String write(MediDto mediDto, FileDto fileDto) throws Exception {
        mediService.savePost(mediDto);
        fileService.saveFile(fileDto);

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
