package com.basicauth.service;

import com.basicauth.data.MaceReqInpatient;
import com.basicauth.domain.Room;
import com.basicauth.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roomService")
public class RoomService {

    @Autowired
    private RoomMapper myMapper;

    public List<Room> getRoomType() {
        return myMapper.getRoomType();
    }
}
