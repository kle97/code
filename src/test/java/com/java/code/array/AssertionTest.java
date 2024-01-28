package com.java.code.array;

import com.java.code.common.BaseTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class AssertionTest extends BaseTest {
    
    @Test
    public void test() {
        String[][] array1 = new String[][] {new String[] {"eat", "tea", "ate", "eta"}, new String[] {"nat", "tan"}, new String[] {"mov"}};
        Set<Set<String>> set1 = Set.of(Set.of("eat", "tea", "ate", "eta"), Set.of("nat", "tan"), Set.of("mov"));
        List<List<String>> list1 = List.of(List.of("eat", "tea", "ate", "eta"), List.of("nat", "tan"), List.of("mov"));
        List<List<String>> list2 = List.of(List.of("mov"), List.of("eta", "ate", "tea", "eat"),  List.of("tan", "nat"));
        List<String> list3 = List.of("mov", "eta", "ate", "tea", "eat",  "tan", "nat");
        softAssert.as(Arrays.toString(list1.toArray())).assertThat(list2).isEqualNoOrder(list1);
    }
}
