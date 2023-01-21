package com.demo.multisport.services.page;

import com.demo.multisport.dto.center.CenterDto;
import com.demo.multisport.dto.page.CommentDto;
import com.demo.multisport.entities.center.RelaxCenter;
import com.demo.multisport.entities.center.SportCenter;
import com.demo.multisport.mapper.CommentMapper;
import com.demo.multisport.services.center.CenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPageServiceImpl implements PageService {
    private final CenterService centerService;
    private final CommentMapper commentMapper;

    @Override
    public CenterDto getCenterDtoFromSportCenter(SportCenter sportCenter) {
        return centerService.getCenterDtoFromSportCenterAdmin(sportCenter);
    }

    @Override
    public CenterDto getCenterDtoFromRelaxCenter(RelaxCenter relaxCenter) {
        return centerService.getCenterDtoFromRelaxCenterAdmin(relaxCenter);
    }

    public void addSportCenter(CenterDto centerDto) {
        centerService.addSportCenterAdmin(centerDto);
    }

    public void addRelaxCenter(CenterDto centerDto) {
        centerService.addRelaxCenterAdmin(centerDto);
    }


    public void deleteCenter(String address) {
        centerService.deleteCenter(address);
    }

    public void addComment(CommentDto commentDto) {

    }
}
