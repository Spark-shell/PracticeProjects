package com.gsau.portal.controller.app;


import com.gsau.order_sersvice.projo.po.UserInfo;
import com.gsau.portal.pojo.MessageObject;
import com.gsau.portal.pojo.po.Choicequestion;
import com.gsau.portal.pojo.po.LearnCurrent;
import com.gsau.portal.pojo.po.LearnQuestion;
import com.gsau.portal.portal.service.impl.UserInfoServiceImpl;
import com.gsau.portal.repository.ChoicequestionRepository;
import com.gsau.portal.repository.LearnCurrentRepository;
import com.gsau.portal.repository.LearnQuestionRepository;
import com.gsau.portal.service.learnservice.LearnQuestionManager;
import com.gsau.portal.util.GsonUtil;
import com.gsau.portal.util.StringUtil;
import com.gsau.portal.util.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author WangGuoQing
 * @date 2019/5/13 21:13
 * @Desc
 */
@RestController
@RequestMapping("/app/learnquestion")
public class LearnquestionApp {
    private MessageObject mo;
    @Autowired
    ChoicequestionRepository choicequestionRepository;
    @Autowired
    LearnQuestionRepository learnQuestionRepository;

    @Autowired
    LearnCurrentRepository learnCurrentRepository;

    @Autowired
    LearnQuestionManager learnQuestionManager;

    @Autowired
    UserInfoServiceImpl userInfoService;


    /**
     * 每次用户在界面上，选择答案的时候调用。
     * 创建一个学习的题目（可能学习的结果是错误或者正确）
     * 创建当前学习的题目对象
     *
     * @param userid
     * @param questionid
     * @param ismistake
     * @return
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public MessageObject createlearnquestion(@RequestParam(required = false) long userid,
                                             @RequestParam(required = false) String questionid,
                                             @RequestParam(required = false) String ismistake
    ) {
        mo = new MessageObject();

        Choicequestion cq = null;
        if (StringUtil.isEmpty(questionid)) {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("你学习的题目编号不存在，请从头再来！");
            return mo;
        }

        cq = choicequestionRepository.findChoiceQuestion(questionid);
        if (cq == null) {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("你学习的题目不存在，请重新选择一题！");
            return mo;
        }

        LearnQuestion learnQuestion = learnQuestionRepository.findLearnquestion(userid, questionid);
        if (null == learnQuestion) {
            learnQuestion = new LearnQuestion();
            learnQuestion.setCreatetime(System.currentTimeMillis());
            learnQuestion.setIsmistake(ismistake);
            learnQuestion.setMoniname(cq.getMoniname());
            learnQuestion.setQuestionid(questionid);
            learnQuestion.setSubjectid(cq.getSublevel1());
            learnQuestion.setUserid(userid);
        } else {
            learnQuestion.setIsmistake(ismistake);
        }

        /**
         * 创建当前模拟年份学习的对象，如果对象已经存在则修改mcode
         */
        LearnCurrent learnCurrent = learnCurrentRepository.findLearnCurrentObjByMoniname(userid, cq.getSublevel1(), cq.getMoniname());
        if (learnCurrent == null) {
            System.out.println("---查出来的对象为空!!!!---");
            learnCurrent = new LearnCurrent();
            learnCurrent.setUserid(userid);
            learnCurrent.setSubjectid(cq.getSublevel1());
            learnCurrent.setMoniname(cq.getMoniname());
            learnCurrent.setCreatetime(System.currentTimeMillis());
            learnCurrent.setMcode(cq.getMcode());
        } else {
            //确保每次学习顺序正确
            if (cq.getMcode() > learnCurrent.getMcode()) {
                learnCurrent.setMcode(cq.getMcode());
            }

        }

        mo = learnQuestionManager.createLearnObj(learnCurrent, learnQuestion);
//        learnQuestionRepository.save(learnQuestion);

        return mo;

    }

    /**
     * 查询当前用户的 学习题目的数量
     * 包括此科目的总题目数量、此用户已经学习的数量、错误的题数、正确的题目数量
     *
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value = "/learncount")
    @ResponseBody
    public MessageObject learncount(@RequestParam(required = false) long userid,
                                    @RequestParam(required = false) String subjectid) {
        mo = new MessageObject();
        UserInfo userInfo = userInfoService.findUserByUserId((int) userid);
        if (userInfo == null) {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        HashMap<String, String> map = new HashMap<String, String>();

        long allquestion = 0;//科目下面的总条数
        long learnall = 0;//已经学习总条数
        long mistakeno = 0;//已经学会
        long mistakeyes = 0;//我的错题

        try {
            allquestion = choicequestionRepository.findChoicequestionCount(subjectid);
        } catch (Exception ex) {
            System.out.println(this.getClass().getName() + "--没有题目数据--");
        }

        try {
            mistakeno = learnQuestionRepository.findcountismistakeLearnquestion(userid, subjectid, SystemConfig.mistake_no);
        } catch (Exception ex) {
            System.out.println(this.getClass().getName() + "--没有对的题目数据--");
        }

        try {
            mistakeyes = learnQuestionRepository.findcountismistakeLearnquestion(userid, subjectid, SystemConfig.mistake_yes);
        } catch (Exception ex) {
            System.out.println(this.getClass().getName() + "--没有错误题目数据--");
        }

        learnall = mistakeno + mistakeyes;

        map.put("allquestion", allquestion + "");
        map.put("mistakeno", mistakeno + "");
        map.put("mistakeyes", mistakeyes + "");
        map.put("learnall", learnall + "");

        String content = GsonUtil.objTOjson(map);
        mo.setMcontent(content);
        return mo;

    }


    /**
     * 这个科目下查找，当前用户的错误的题目，不是列表是一个一个来
     * 创建时间最大的那个
     *
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value = "/finduserMistkeQuestion")
    @ResponseBody
    public MessageObject finduserMistkeQuestion(@RequestParam(required = false) long userid,
                                                @RequestParam(required = false) String subjectid) {
        mo = new MessageObject();
        UserInfo userInfo = userInfoService.findUserByUserId((int) userid);
        if (userInfo == null) {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }

        LearnQuestion lq = learnQuestionRepository.findLearnquestionMaxIsMitake(userid, subjectid);
        Choicequestion cq;

        if (lq != null) {
            cq = choicequestionRepository.findChoiceQuestion(lq.questionid);
            if (cq != null) {

                long mistakeyes = 0;
                try {
                    mistakeyes = learnQuestionRepository.findcountismistakeLearnquestion(userid, subjectid, SystemConfig.mistake_yes);
                } catch (Exception ex) {
                    System.out.println(this.getClass().getName() + "--没有错误题目数据--");
                }

                HashMap<String, String> map = GsonUtil.tomap(cq);
                map.put("pkid", lq.getPkid() + "");
                map.put("mistakeyes", mistakeyes + "");
                String content = GsonUtil.objTOjson(map);
                System.out.println("=======AAAAA=======" + content);
                mo.setMcontent(content);
            } else {
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("查询题目失败！");
                return mo;
            }
        } else {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("您已经没有错误的题目了！");
            return mo;
        }

        return mo;

    }


    /**
     * 这个科目下查找，当前用户的正在查看错题目，的下一个题目，不是列表,是一个一个来
     * 创建时间最大的那个
     *
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value = "/findNextLearnquestionMaxIsMitake")
    @ResponseBody
    public MessageObject findNextLearnquestionMaxIsMitake(@RequestParam(required = false) String userid,
                                                          @RequestParam(required = false) String subjectid,
                                                          @RequestParam(required = false) String pkid) {

        System.out.println(userid + "--" + subjectid + "--" + pkid);

        mo = new MessageObject();
        UserInfo userInfo = userInfoService.findUserByUserId(Integer.parseInt(userid));
        if (userInfo == null) {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }


        LearnQuestion lq = learnQuestionRepository.findNextLearnquestionMaxIsMitake(Long.parseLong(userid), subjectid, Long.parseLong(pkid));

        Choicequestion cq;

        if (lq != null) {
            cq = choicequestionRepository.findChoiceQuestion(lq.questionid);
            if (cq != null) {

                long mistakeyes = 0;
                try {
                    mistakeyes = learnQuestionRepository.findcountismistakeLearnquestion(Long.parseLong(userid), subjectid, SystemConfig.mistake_yes);
                } catch (Exception ex) {
                    System.out.println(this.getClass().getName() + "--没有错误题目数据--");
                }

                HashMap<String, String> map = GsonUtil.tomap(cq);
                map.put("pkid", lq.getPkid() + "");
                map.put("mistakeyes", mistakeyes + "");
                String content = GsonUtil.objTOjson(map);
                mo.setMcontent(content);
            } else {
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("已经是最后一题了。");
                return mo;
            }
        } else {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("已经是最后一题了！");
            return mo;
        }

        return mo;

    }

    /**
     * 这个科目下查找，当前用户的正在查看错题目，的下一个题目，不是列表,是一个一个来
     * 创建时间最大的那个
     *
     * @param userid
     * @param subjectid
     * @return
     */
    @RequestMapping(value = "/findUpLearnquestionMaxIsMitake")
    @ResponseBody
    public MessageObject findUpLearnquestionMaxIsMitake(@RequestParam(required = false) String userid,
                                                        @RequestParam(required = false) String subjectid,
                                                        @RequestParam(required = false) String pkid) {

        System.out.println(userid + "--" + subjectid + "--" + pkid);

        mo = new MessageObject();
        UserInfo userInfo = userInfoService.findUserByUserId(Integer.parseInt(userid));
        if (userInfo == null) {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("用户失效，校验失败！");
            return mo;
        }


        LearnQuestion lq = learnQuestionRepository.findUpLearnquestionMaxIsMitake(Long.parseLong(userid), subjectid, Long.parseLong(pkid));

        Choicequestion cq;

        if (lq != null) {
            cq = choicequestionRepository.findChoiceQuestion(lq.questionid);
            if (cq != null) {

                long mistakeyes = 0;
                try {
                    mistakeyes = learnQuestionRepository.findcountismistakeLearnquestion(Long.parseLong(userid), subjectid, SystemConfig.mistake_yes);
                } catch (Exception ex) {
                    System.out.println(this.getClass().getName() + "--没有错误题目数据--");
                }

                HashMap<String, String> map = GsonUtil.tomap(cq);
                map.put("pkid", lq.getPkid() + "");
                map.put("mistakeyes", mistakeyes + "");
                String content = GsonUtil.objTOjson(map);
                mo.setMcontent(content);
            } else {
                mo.setCode(SystemConfig.mess_failed);
                mo.setMdesc("题目没有查到。");
                return mo;
            }
        } else {
            mo.setCode(SystemConfig.mess_failed);
            mo.setMdesc("已经是第一题目了！");
            return mo;
        }

        return mo;

    }


}
