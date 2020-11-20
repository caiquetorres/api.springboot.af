package com.app.reserve.repositories;

import com.app.common.BaseRepository;
import com.app.reserve.models.Reserve;

import org.springframework.stereotype.Component;

import com.app.reserve.dto.CreateReserveDTO;
import com.app.reserve.dto.UpdateReserveDTO;

@Component
public class ReserveRepository extends BaseRepository<Reserve, CreateReserveDTO, UpdateReserveDTO> {
}
