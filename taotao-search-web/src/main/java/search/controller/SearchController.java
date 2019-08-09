package search.controller;


import com.taotao.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.SearchResult;

@Controller
public class SearchController {
    @Value("${ITEM_ROWS}")
    private Integer ITEM_ROWS;

    @Autowired
    private SearchItemService service;
    @RequestMapping("/search")
    public String search(@RequestParam String q,@RequestParam(defaultValue = "1")int page, Model model) throws Exception {
        byte[] bytes = q.getBytes("ISO-8859-1");
        String queryString = new String(bytes,"UTF-8");
        SearchResult result = service.search(queryString,page,ITEM_ROWS);
        model.addAttribute("query",queryString);
        model.addAttribute("totalPages",result.getPageCount());
        model.addAttribute("page",page);
        return "search";
    }
}
