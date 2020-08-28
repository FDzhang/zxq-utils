package hutool;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.fd.demo.timeconsume.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author ：zxq
 * @date ：Created in 2020/8/27 16:56
 */
@Slf4j
public class PoiTest {

    @Test
    public void test() {
        ExcelReader reader = ExcelUtil.getReader("d:/aaa.xlsx");
        List<List<Object>> readAll = reader.read();

        List<Map<String,Object>> readAll1 = reader.readAll();

        List<Person> all = reader.readAll(Person.class);

        log.info("file: {}", readAll);
        log.info("file: {}", readAll1);
        log.info("file: {}", JSON.toJSONString(all));
        log.info("file: {}", all);
    }
}
