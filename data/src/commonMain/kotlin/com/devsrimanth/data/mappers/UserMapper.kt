package com.devsrimanth.data.mappers

import com.devsrimanth.data.model.UserDto
import com.devsrimanth.domain.model.UserModel

object UserMapper {
        fun toDomain(dto: UserDto): UserModel {
            return UserModel(
                id = dto.id,
                firstName = dto.firstName,
                lastName = dto.lastName,
                email = dto.email,
            )
        }

        fun toDomain(dtos: List<UserDto>): List<UserModel> {
            return dtos.map { toDomain(it ) }
        }
    }
