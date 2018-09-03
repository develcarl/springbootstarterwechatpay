import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.develcarl.request.body.UnifiedOrderRequestBody;
import org.junit.Before;
import org.junit.Test;

/**
 * @author yichen
 * @description
 * @date 2018/7/30
 **/
public class TestFastXml {

//    private UnifiedOrderRequestBody body = new UnifiedOrderRequestBody();
//
//    @Before
//    public void init(){
//        body.setAppid("123");
//        body.setDe
//    }

    @Test
    public void testBean2XML() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
//        String xml = xmlMapper.writeValueAsString(body);
//        System.out.println("xml: " + xml);
    }

}
