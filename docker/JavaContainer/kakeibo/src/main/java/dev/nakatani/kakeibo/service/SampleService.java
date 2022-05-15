package dev.nakatani.kakeibo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.nakatani.kakeibo.model.SampleModel;
import dev.nakatani.kakeibo.repository.SampleRepository;

@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    public SampleModel getSampleModel(String zip){
        // 検索
        Map<String, Object> sampleMap = sampleRepository.findByZip(zip);

        // Mapから値を取得
        String prefecture = (String) sampleMap.get("prefecture");
        String city = (String) sampleMap.get("city");
        String address = (String) sampleMap.get("address");
        
        // RawDataクラスに値をセット
        SampleModel sampleModel = new SampleModel();
        sampleModel.setZip(zip);
        sampleModel.setPrefecture(prefecture);
        sampleModel.setCity(city);
        sampleModel.setAddress(address);

        return sampleModel;
    }
}
