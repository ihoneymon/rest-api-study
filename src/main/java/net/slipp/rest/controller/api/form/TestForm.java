package net.slipp.rest.controller.api.form;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ihoneymon
 * Date: 13. 9. 11.
 * Time: 오후 6:51
 * To change this template use File | Settings | File Templates.
 */
@Data
@ToString
public class TestForm {
    private List<Test> tests = Lists.newArrayList();

    @Data
    @ToString
    @NoArgsConstructor
    public class Test {
        private String id;
        private String metaTag;
    }
}
