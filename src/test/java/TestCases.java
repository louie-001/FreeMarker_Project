import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import self.louie.service.QRCodeService;
import self.louie.service.WordDocumentService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.util.*;

/**
 * Created by louie on 2017-11-13.
 */
public class TestCases {

    @Test
    /**
     * 单元格合并
     */
    public void testCellMerge() throws Exception {
        WordDocumentService service = new WordDocumentService();
        Map<String,Object> datas = new HashMap<String, Object>();

        datas.put("reportNo","J2017-0001");
        datas.put("company","上海象融");
        datas.put("sampleName","羽绒服");
        datas.put("item","GB18401-2010C");
        datas.put("date", DateFormatUtils.format(new Date(),"yyyy年MM月dd日"));
//        datas.put("qrCode",new QRCodeService().createQRCode());
        datas.put("address","上海市顾戴路2568号4号楼");
        datas.put("sampleNo","J2017-0010-001");
        datas.put("size","M");
        datas.put("girard","--");
        datas.put("component","--");
        datas.put("brand","--");
        datas.put("color","卡其色");
        datas.put("quantity",5);
        datas.put("description","来样整洁完好，包装于密封袋中");
        datas.put("testType","委托检测");
        datas.put("standard","GB18401-2010C《国家纺织产品基本安全技术规范》");
        datas.put("accessDate","2017-10-18");
        datas.put("completeDate",DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
        datas.put("year",DateFormatUtils.format(new Date(),"yyyy"));
        datas.put("month",new Date().getMonth()+1);
        datas.put("day",new Date().getDay()+1);

        List<Map<String,Object>> testItems = new ArrayList<Map<String, Object>>();
        Map<String,Object> firstItem = new HashMap<String, Object>();
        Map<String,Object> secendItem = new HashMap<String, Object>();
        Map<String,Object> thirdItem = new HashMap<String, Object>();

        firstItem.put("name","firstName");
        thirdItem.put("name","thirdName");

        testItems.add(firstItem);
        testItems.add(secendItem);
        testItems.add(thirdItem);
        datas.put("testItems",testItems);

        service.datasMappingWord(datas,"module.ftl","自动生成的文档.doc");
    }

    /**
     * word文档中添加二维码
     * @throws Exception
     */
    @Test
    public void testQRCodeToDocument() throws Exception {
        WordDocumentService service = new WordDocumentService();
        Map<String,Object> datas = new HashMap<String, Object>();

        datas.put("reportNo","J2017-0001");
        datas.put("company","上海象融");
        datas.put("sampleName","羽绒服");
        datas.put("item","GB18401-2010C");
        datas.put("date", DateFormatUtils.format(new Date(),"yyyy年MM月dd日"));
        datas.put("qrCode",new QRCodeService().createQRCode());
        datas.put("address","上海市顾戴路2568号4号楼");
        datas.put("sampleNo","J2017-0010-001");
        datas.put("size","M");
        datas.put("girard","--");
        datas.put("component","--");
        datas.put("brand","--");
        datas.put("color","卡其色");
        datas.put("quantity",5);
        datas.put("description","来样整洁完好，包装于密封袋中");
        datas.put("testType","委托检测");
        datas.put("standard","GB18401-2010C《国家纺织产品基本安全技术规范》");
        datas.put("accessDate","2017-10-18");
        datas.put("completeDate",DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
        datas.put("year",DateFormatUtils.format(new Date(),"yyyy"));
        datas.put("month",new Date().getMonth()+1);
        datas.put("day",new Date().getDay()+1);

        BASE64Encoder encoder = new BASE64Encoder();
        datas.put("sampleImg", encoder.encode(FileUtils.readFileToByteArray(new File("timg.jpg"))));

        List<Map<String,Object>> testItems = new ArrayList<Map<String, Object>>();
        Map<String,Object> firstItem = new HashMap<String, Object>();
        Map<String,Object> secendItem = new HashMap<String, Object>();
        Map<String,Object> thirdItem = new HashMap<String, Object>();

        firstItem.put("name","firstName");
        thirdItem.put("name","thirdName");

        testItems.add(firstItem);
        testItems.add(secendItem);
        testItems.add(thirdItem);
        datas.put("testItems",testItems);

        service.datasMappingWord(datas,"module2.ftl","自动生成的文档.doc");
    }

}
