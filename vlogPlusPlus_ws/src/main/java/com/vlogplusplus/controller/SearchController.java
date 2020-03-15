package com.vlogplusplus.controller;

import com.vlogplusplus.entity.Search;
import com.vlogplusplus.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController {
    @Autowired
    private ISearchService iSearchService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    private List<Search> list(@RequestParam int n){
        return iSearchService.list(n);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private void add(@RequestBody Search search){
        iSearchService.add(search.getVar());
    }
}
