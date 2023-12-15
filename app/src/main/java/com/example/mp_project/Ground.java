package com.example.mp_project;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import android.util.Log;
public class Ground {

    String SUM_YY;
    String SIGUN_NM;
    String SIGUN_CD;
    String FACLT_NM;
    String POSESN_INST_NM;
    String OPERT_ORGNZT_NM;
    String CONTCT_NO;
    String HMPG_ADDR;
    String MANAGE_MAINBD_NM;
    String PLOT_AR;
    String BUILD_AR;
    String TOT_AR;
    String BOTM_MATRL_NM;
    String BT;
    String LENG;
    String AR;
    String PLANE_CN;
    String AUDTRM_SEAT_CNT;
    String ACEPTNC_PSN_CNT;
    String SEAT_FORM_NM;
    String BUILD_RESCUE_NM;
    String COMPLTN_YY;
    String CONSTR_BIZ_EXPN;
    String RM_MATR;
    String REFINE_LOTNO_ADDR;
    String REFINE_ROADNM_ADDR;
    String REFINE_ZIP_CD;
    String REFINE_WGS84_LOG;
    String REFINE_WGS84_LAT;
    public Ground(Element itemElement) {
        // XML에서 필요한 데이터를 추출하여 객체 생성
        this.FACLT_NM = getValueFromElement(itemElement, "FACLT_NM");
        this.AR = getValueFromElement(itemElement, "AR");
        this.BOTM_MATRL_NM = getValueFromElement(itemElement, "BOTM_MATRL_NM");

        // 로그 추가
        Log.d("Ground", "Ground created - FacilityName: " + FACLT_NM + ", PlotArea: " + AR);
    }


    private String getValueFromElement(Element itemElement, String tagName) {
        // XML 엘리먼트에서 태그명에 해당하는 값을 추출
        NodeList nodeList = itemElement.getElementsByTagName(tagName);

        // 태그가 존재하는지 확인
        if (nodeList != null && nodeList.getLength() > 0) {
            // 값을 추출하여 반환
            return nodeList.item(0).getTextContent();
        } else {
            // 태그가 존재하지 않을 경우 예외 처리 또는 기본값 반환
            return ""; // 또는 다른 적절한 처리
        }
    }


    public String getFacilityName() {
        return FACLT_NM;
    }
    public String getPlotArea() {
        return AR;
    }
    public String getBOTM_MATRL_NM() {
        return BOTM_MATRL_NM;
    }
}
