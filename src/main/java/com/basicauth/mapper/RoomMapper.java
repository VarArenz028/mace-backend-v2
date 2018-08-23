package com.basicauth.mapper;

import com.basicauth.domain.Room;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomMapper {

    List<Room> getRoomType();
}
