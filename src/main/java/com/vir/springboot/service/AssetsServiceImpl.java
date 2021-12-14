package com.vir.springboot.service;

import com.vir.springboot.model.Assets;
import com.vir.springboot.model.Employee;
import com.vir.springboot.repository.AssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetsServiceImpl implements AssetsService{

    @Autowired
    AssetsRepository assetsRepository;

    @Override
    public List<Assets> getAllAssets() {
        return assetsRepository.findAll();
    }

    @Override
    public void saveAsset(Assets assets) {
        this.assetsRepository.save(assets);

    }

    @Override
    public Assets getAssetById(long id) {
        Optional<Assets> assetsOptional=this.assetsRepository.findById(id);
        Assets assets=null;
        if(assetsOptional.isPresent())
        {
            assets=assetsOptional.get();
        }else {
            throw new RuntimeException(" Assets not found for id :: " + id);
        }
        return assets;
    }

    @Override
    public void deleteAssetById(long id) {

        this.assetsRepository.deleteById(id);
    }

    @Override
    public List<Assets> getAssetsByEmployee(Employee employee) {

        return this.assetsRepository.findByEmployee(employee);
    }

    @Override
    public List<Assets> searchAssets(String keyword) {
        return this.assetsRepository.search(keyword);
    }
}
