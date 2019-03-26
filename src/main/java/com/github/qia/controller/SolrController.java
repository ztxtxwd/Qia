package com.github.qia.controller;



import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.qia.pojo.Question;
import com.github.qia.service.QuestionService;

import entity.PageResult;



import java.util.Map;


@RestController
@RequestMapping("solr")
public class SolrController {

    @Autowired
    private SolrClient client;

    @Autowired
    private QuestionService questionService;
    /**
     * 新增/修改 索引
     * 当 id 存在的时候, 此方法是修改(当然, 我这里用的 uuid, 不会存在的), 如果 id 不存在, 则是新增
     * @return
     */
    @RequestMapping("add")
    public String add() {
    	
        PageResult questions=questionService.findAll();
        List<Question> questions2=questions.getRows();
        try {
            SolrInputDocument doc = new SolrInputDocument();
            for(Question question:questions2){
            	
            	doc.setField("id", question.getId());
                doc.setField("title", question.getTitle());
                doc.setField("video", question.getVideo());
                doc.setField("detail", question.getDetail());
                /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 core2 这个参数
                 * 下面都是一样的
                 */

	            client.add("", doc);
	            //client.commit();
	            client.commit("");
            }
            

            
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

    /**
     * 根据id删除索引
     * @param id
     * @return
     */
    //@RequestMapping("delete")
    public String delete(String id)  {
        try {
            client.deleteById("core2",id);
            client.commit("core2");

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "error";
    }

    /**
     * 删除所有的索引
     * @return
     */
    @RequestMapping("deleteAll")
    public String deleteAll(){
        try {

            client.deleteByQuery("","*:*");
            client.commit("");

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 根据id查询索引
     * @return
     * @throws Exception
     */
    @RequestMapping("getById")
    public String getById() throws Exception {
        SolrDocument document = client.getById("core2", "536563");
        System.out.println(document);
        return document.toString();
    }

    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("search")
    public SolrDocumentList search(String keyword){

        try {
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            params.set("indent", "true");
            params.set("q","question_keywords:"+keyword);
            params.set("wt", "json");

            /*//过滤条件
            params.set("fq", "product_price:[100 TO 100000]");

            //排序
            params.addSort("product_price", SolrQuery.ORDER.asc);

            //分页
            params.setStart(0);
            params.setRows(20);

            //默认域
            params.set("df", "product_title");

            //只查询指定域
            params.set("fl", "id,product_title,product_price");*/

            /*//高亮
            //打开开关
            params.setHighlight(false);
            //指定高亮域
            params.addHighlightField("detail");
            
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");*/

            QueryResponse queryResponse = client.query(params);

            SolrDocumentList results = queryResponse.getResults();

            
            
//获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            //Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}