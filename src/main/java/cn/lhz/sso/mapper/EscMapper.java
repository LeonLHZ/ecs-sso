package cn.lhz.sso.mapper;

import cn.lhz.common.entity.Esc;

public interface EscMapper {

    int addEsc(Esc esc);

    int updateEsc(Esc esc);

    int deleteEsc(String escId);

}