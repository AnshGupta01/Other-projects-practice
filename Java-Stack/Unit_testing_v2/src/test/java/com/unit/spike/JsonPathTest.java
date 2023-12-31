package com.unit.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {
    //USed to get specific elements and do more than jsonassert provides us
    @Test
    public void test(){
        String reponse = "[" +
                "{\"id\":10000, \"name\":\"Pencil\", \"quantity\":5}," +
                "{\"id\":10001, \"name\":\"Pen\", \"quantity\":15}," +
                "{\"id\":10002, \"name\":\"Eraser\", \"quantity\":10}" +
                "]";

        DocumentContext context = JsonPath.parse(reponse);
        int length = context.read("$.length()");
        assertThat(length).isEqualTo(3);

        List<Integer> ids = context.read("$..id");

        assertThat(ids).containsExactly(10000,10001,10002);

        System.out.println(context.read("$.[1]").toString());
        System.out.println(context.read("$.[0:2]").toString());
        System.out.println(context.read("$.[?(@.name=='Eraser')]").toString());
        System.out.println(context.read("$.[?(@.quantity==5)]").toString());

    }
}
