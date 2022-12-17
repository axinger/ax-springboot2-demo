package demo;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ESDemoApplicationTest {

    @Autowired
    private ElasticsearchClient client;

    @SneakyThrows
    @Test
    void test1() {

        CreateIndexResponse createIndexResponse = client.indices().create(
                c ->
                        c.index("javaboy_books")
                                .settings(s ->
                                        s.numberOfShards("3")
                                                .numberOfReplicas("1"))
                                .mappings(m ->
                                        m.properties("name", p -> p.text(f -> f.analyzer("ik_max_word")))
                                                .properties("birthday", p -> p.date(d -> d.format("yyyy-MM-dd"))))
                                .aliases("books_alias", f -> f.isWriteIndex(true)));
        System.out.println("createResponse.acknowledged() = " + createIndexResponse.acknowledged());
        System.out.println("createResponse.index() = " + createIndexResponse.index());
        System.out.println("createResponse.shardsAcknowledged() = " + createIndexResponse.shardsAcknowledged());
    }
}
