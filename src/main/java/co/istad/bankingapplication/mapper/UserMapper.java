package co.istad.bankingapplication.mapper;

import co.istad.bankingapplication.domain.User;
import co.istad.bankingapplication.feature.auth.dto.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromRegisterRequest(RegisterRequest registerRequest);
}
