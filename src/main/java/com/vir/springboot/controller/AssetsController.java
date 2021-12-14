package com.vir.springboot.controller;

import com.vir.springboot.model.Assets;
import com.vir.springboot.service.AssetsServiceImpl;
import com.vir.springboot.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class AssetsController {

    @Autowired
    AssetsServiceImpl assetsService;
    @Autowired
    EmployeeServiceImpl employeeService;

    private static final  String ASSETS="assets";

    @GetMapping("/assets")
    public String getAllAssets(Model model)
    {
        model.addAttribute("assetsList",assetsService.getAllAssets());
        return ASSETS;
    }

    @GetMapping("/asset")
    public List<Assets> getAll()
    {
        return assetsService.getAllAssets();
    }
    @GetMapping("/asset/search")
    public String searchAssets(String keyword,Model model)
    {
        model.addAttribute("assetsList",assetsService.searchAssets(keyword));
        return ASSETS;
    }
    @GetMapping("/assets/add")
    public String addAssets(Model model)
    {
        Assets assets=new Assets();
        model.addAttribute(ASSETS,assets);
        model.addAttribute("employeeList",employeeService.getAllEmployees());
        return "assetsAdd";
    }
    @PostMapping("/assets/save")
    public String saveAssets(@ModelAttribute Assets assets)
    {
        assetsService.saveAsset(assets);
        return "redirect:/assets";
    }
}
