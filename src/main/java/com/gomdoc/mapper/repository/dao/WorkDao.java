package com.gomdoc.mapper.repository.dao;

import com.gomdoc.mapper.repository.AirtableFieldInfoRepository;
import com.gomdoc.mapper.repository.AirtableKeyInfoRepository;
import com.gomdoc.mapper.repository.SendInfoRepository;
import com.gomdoc.model.vo.AirtableFieldInfo;
import com.gomdoc.model.vo.AirtableKeyInfo;
import com.gomdoc.model.vo.SendInfo;
import com.gomdoc.utils.GUtil;
import org.springframework.stereotype.Component;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * description    :
 * packageName    : com.gomdoc.mapper.repository.dao
 * fileName       : WorkDao
 * author         : cho
 * date           : 24. 9. 4.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 4.        cho       최초 생성
 */
@Component
public class WorkDao {
    @Inject
    AirtableFieldInfoRepository airtableFieldInfoRepository;

    @Inject
    AirtableKeyInfoRepository airtableKeyInfoRepository;

    @Inject
    SendInfoRepository sendInfoRepository;

    public int setSendInfo(SendInfo vo) {
        try {
            Object res = sendInfoRepository.save(vo);
            if (GUtil.isNullOrEmpty(res)) {
                return -1;
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    public int setAirtableFieldInfo(AirtableFieldInfo vo) {
        try {
            Object res = airtableFieldInfoRepository.save(vo);
            if (GUtil.isNullOrEmpty(res)) {
                return -1;
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    public int setAirtableKeyInfo(AirtableKeyInfo vo) {
        try {
            Object res = airtableKeyInfoRepository.save(vo);
            if (GUtil.isNullOrEmpty(res)) {
                return -1;
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    public List<SendInfo> getAllSendInfo() {
        try {
            Iterable<SendInfo> info = sendInfoRepository.findAll();
            if (GUtil.isNullOrEmpty(info)) {
                return null;
            }
            List<SendInfo> list = new ArrayList<>();
            info.forEach(list::add);
            return list;
        }catch (Exception e){
            return null;
        }
    }
    public List<AirtableFieldInfo> getAllAirtableFieldInfo() {
        try {
            Iterable<AirtableFieldInfo> info = airtableFieldInfoRepository.findAll();
            if (GUtil.isNullOrEmpty(info)) {
                return null;
            }
            List<AirtableFieldInfo> list = new ArrayList<>();
            info.forEach(list::add);
            return list;
        }catch (Exception e){
            return null;
        }
    }
    public List<AirtableKeyInfo> getAllAirtableKeyInfo() {
        try {
            Iterable<AirtableKeyInfo> info = airtableKeyInfoRepository.findAll();
            if (GUtil.isNullOrEmpty(info)) {
                return null;
            }
            List<AirtableKeyInfo> list = new ArrayList<>();
            info.forEach(list::add);
            return list;
        } catch (Exception e) {
            return null;
        }
    }
    public SendInfo getSendInfoById(String id) {
        try{
            SendInfo sendInfo = sendInfoRepository.findById(id).orElse(null);
            if (GUtil.isNullOrEmpty(sendInfo)) {
                return null;
            }
            return sendInfo;
        }catch (Exception e){
            return null;
        }
    }
    public AirtableFieldInfo getAirtableFieldInfoById(String id) {
        try{
            AirtableFieldInfo airtableFieldInfo = airtableFieldInfoRepository.findById(id).orElse(null);
            if (GUtil.isNullOrEmpty(airtableFieldInfo)) {
                return null;
            }
            return airtableFieldInfo;
        }catch (Exception e){
            return null;
        }
    }
    public AirtableKeyInfo getAirtableKeyInfoById(String id) {
        try {
            AirtableKeyInfo airtableKeyInfo = airtableKeyInfoRepository.findById(id).orElse(null);
            if (GUtil.isNullOrEmpty(airtableKeyInfo)) {
                return null;
            }
            return airtableKeyInfo;
        } catch (Exception e) {
            return null;
        }
    }
}
