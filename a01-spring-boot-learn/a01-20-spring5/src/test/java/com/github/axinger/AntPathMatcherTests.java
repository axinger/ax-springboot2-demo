package com.github.axinger;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AntPathMatcherTests {

    @Test
    void tet1() {
        PathMatcher matcher = new AntPathMatcher();
        // ?
        System.out.println(matcher.match("/test/a?c", "/test/abc"));// true
        // *
        System.out.println(matcher.match("/test/*", "/test/"));// true
        System.out.println(matcher.match("/test/*", "/test/aa"));// true
        System.out.println(matcher.match("/test/*.html", "/test/aa"));// false
        // **
        System.out.println(matcher.match("/test/**", "/test/"));// true
        System.out.println(matcher.match("/test/**", "/test/aa"));// true
        System.out.println(matcher.match("/test/**", "/test/aa/bb"));// true
        // {id}
        System.out.println(matcher.match("/test/{id}", "/test/111"));// true
        System.out.println(matcher.match("/test/a{id}", "/test/a111"));// true
        System.out.println(matcher.match("/test/{id}/aa", "/test/111/aa"));// true
        System.out.println(matcher.match("/test/{id}-{name}/aa", "/test/111-haha/aa"));// true
        // {id:[a-z]+}
        System.out.println(matcher.match("/test/{id:[a-z]+}", "/test/111"));// false
        System.out.println(matcher.match("/test/{id:[a-z]+}", "/test/abc"));// true
        System.out.println(matcher.match("/test/{id:\\w+}", "/test/1a_"));// true
        System.out.println(matcher.match("/test/{id:\\w+}", "/test/--"));// false
    }

    //2. match
    @Test
    void tet2() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.match("/test/*", "/test/111"));// true
        System.out.println(matcher.match("/test/**", "/test/111/222"));// true
        System.out.println(matcher.match("/test/{id}", "/test/111"));// true
        System.out.println(matcher.match("/test/{id}/aa", "/test/111/aa"));// true
        System.out.println(matcher.match("/test/{id}-{name}/aa", "/test/111-haha/aa"));// true
        System.out.println(matcher.match("/test/{id}-{name}/aa", "/test/111-/aa"));// true
    }

    //3. matchStart
    //前缀匹配的意思：路径能与模式的前面部分匹配，但模式可能还有后面多余部分（可以理解为模式是否是以路径开头）
    @Test
    void tet3() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.matchStart("/test/*", "/test"));// true
        System.out.println(matcher.matchStart("/test/aa/*", "/test"));// true
        System.out.println(matcher.matchStart("/test/{id}", "/test"));// true
        System.out.println(matcher.matchStart("/test/{id}-{name}/aa", "/test"));// true
        System.out.println(matcher.matchStart("/test/{id}", "/test/111/222"));// false
    }

    //4. extractPathWithinPattern
    //得到模式匹配的映射部分。找出通过*或者?匹配上的那一段路径及其后续路径。
    @Test
    void tet4() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.extractPathWithinPattern("/test/*", "/test"));//
        System.out.println(matcher.extractPathWithinPattern("/test/*", "/test/aa"));// aa
        System.out.println(matcher.extractPathWithinPattern("/test/**", "/test/aa/bb"));// aa/bb
        System.out.println(matcher.extractPathWithinPattern("/test/a?c/aa", "/test/abc/aa"));// abc/aa
        System.out.println(matcher.extractPathWithinPattern("/test/aa?c/aa/cc", "/test/abc/aa"));// abc/aa
    }

    //5. extractUriTemplateVariables
    //路径必须完全匹配（否则抛出异常），并提取路径中的路径参数值。

    @Test
    void tet5() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.extractUriTemplateVariables("/test/{id}", "/test/111"));// {id=111}
        System.out.println(matcher.extractUriTemplateVariables("/test/a{id}", "/test/a111"));// {id=111}
        System.out.println(matcher.extractUriTemplateVariables("/test/{id}/aa", "/test/111/aa"));// {id=111}
        System.out.println(matcher.extractUriTemplateVariables("/test/{id}-{name}/aa", "/test/111-haha/aa"));// {id=111, name=haha}
        System.out.println(matcher.extractUriTemplateVariables("/test/{id:[a-z]+}", "/test/abc"));// {id=abc}
        System.out.println(matcher.extractUriTemplateVariables("/test/{id:\\w+}", "/test/1a_"));// {id=1a_}
    }

    //6. getPatternComparator
    //得到一个排序比较器。
    @Test
    void tet6() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.getPatternComparator("/test/*"));// AntPatternComparator[]
    }

    //7. combine
    //合并两个模式。
    @Test
    void tet7() {
        AntPathMatcher matcher = new AntPathMatcher();
        System.out.println(matcher.combine("/test/*", "/test/aa"));// /test/aa
        System.out.println(matcher.combine("/test/*", "/test/aa/bb"));// /test/test/aa/bb
        System.out.println(matcher.combine("/test/**", "/test/aa"));// /test/aa
        System.out.println(matcher.combine("/test/{id}", "/test/aa"));// /test/{id}/test/aa
    }
}
