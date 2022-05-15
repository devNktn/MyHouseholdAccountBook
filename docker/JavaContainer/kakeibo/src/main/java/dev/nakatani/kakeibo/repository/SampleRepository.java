package dev.nakatani.kakeibo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SampleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, Object> findByZip(String zip){
        //SELECT文
        String query = "SELECT"
            + " id,"
            + " zip,"
            + " prefecture,"
            + " city,"
            + " address"
            + " FROM kakeibo.sample_city"
            + " WHERE zip=?";

        //検索実行
        Map<String, Object> sampleMap = jdbcTemplate.queryForMap(query, zip);

        return sampleMap;
    }
}