package com.sso.business.controller;

import com.sso.business.vo.DemoVO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * DemoController
 *
 * @author yitao
 * @version 1.0.0
 * @date 2017/7/5 21:15
 */

@Controller
@RequestMapping("/demo")
public class DemoController {

    @ResponseBody
    @RequestMapping("/{id}")
    public int queryById(@PathVariable int id) {
        return id;
    }

    @ResponseBody
    @RequestMapping("/query")
    public DemoVO queryByName(String name) {
        DemoVO demoVO=new DemoVO();
        demoVO.setName(name);
        return demoVO;
    }

    @ResponseBody
    @RequestMapping(value ="/update",method = RequestMethod.PUT)
    public boolean update(@RequestBody DemoVO demoVO) {
        return true;
    }

    @ResponseBody
    @RequestMapping(value ="/save",method = RequestMethod.POST)
    public boolean save(@Validated(DemoVO.Insert.class) @RequestBody DemoVO demoVO) {
        return true;
    }

    @ResponseBody
    @RequestMapping(value ="/del",method = RequestMethod.DELETE)
    public boolean del(int id) {
        return true;
    }


    @ResponseBody
    @GetMapping(value = "/query/{id}")
    public DemoVO queryByName(@PathVariable Integer id) {
        DemoVO demoVO=new DemoVO();
        demoVO.setId(id);
        demoVO.setName("name");
        return demoVO;
    }
    @ResponseBody
    @PutMapping
    public String updatePut(@Validated(DemoVO.Update.class) @RequestBody DemoVO demoVO) {
        return "updatePut";
    }

    @ResponseBody
    @PostMapping
    public String savePost(@Validated(DemoVO.Insert.class) @RequestBody DemoVO demoVO) {
        return "savePost";
    }

    @ResponseBody
    @DeleteMapping
    public String delete(int id) {
        return "Delete"+id;
    }
}
