/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1task4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author Rui
 */
public class Project1Task4Model {
    
    private String url = "http://nationalzoo.si.edu/scbi/migratorybirds/featured_photo/";
    
    /**
     * Using Jsoup to get all bird name from url.
     * @return
     * @throws IOException 
     */
    public List<String> extractName() throws IOException {
        List<String> arrayLable = new ArrayList();
        Document doc = Jsoup.connect(url).get();
        Elements options = doc.select("select[name=pix] > option");
        for(Element option: options) {
            String optionName = option.toString();
            arrayLable.add(optionName);
        }
        //System.out.println(arrayLable.size());
        return arrayLable;
    }
    
    /**
     * From the given url to extract information including images and authors.
     * Find the big image.
     * @param url
     * @return
     * @throws IOException 
     */
    public List<Element> extractContent(String url) throws IOException {
        List<Element> arrayContent = new ArrayList();
        Document doc = Jsoup.connect(url).get();
        Elements contents = doc.select("div.modal-content");
        for(Element content: contents) {
            arrayContent.add(content);
            //System.out.print(content);
        }
        //System.out.println(arrayContent.get(0));
        System.out.println(arrayContent.size());
        return arrayContent;
    }
    
    /**
     * randomly extract the author and image address based on a given bird name.
     * @param content
     * @return 
     */
    
    public List<String> pictureInfo(List<Element> content){
        List<String> list = new ArrayList();
        int length = content.size();
        int max = length;
        int min = 0;
        Random random= new Random();
        int randomNum = random.nextInt(max) % (max - min + 1) + min;
        Element c = content.get(randomNum);    // random choose a picture.
        // get the author of a image.
        String author = c.select("p").text();
        // get the src from HTML
        String imgUrl = c.select("div.modal-body").select("img").attr("src");
        // save the author and image url into a same list.
        list.add(author);
        list.add(imgUrl);
        return list;
    }
}
