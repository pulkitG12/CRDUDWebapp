package com.vir.springboot.service;

import com.vir.springboot.model.Assets;
import com.vir.springboot.model.Employee;

import java.util.List;

public interface AssetsService {

    List<Assets> getAllAssets();
    void saveAsset(Assets assets);
    Assets getAssetById(long id);
    void deleteAssetById(long id);
    List<Assets> getAssetsByEmployee(Employee employee);
    List<Assets> searchAssets(String keyword);
}
