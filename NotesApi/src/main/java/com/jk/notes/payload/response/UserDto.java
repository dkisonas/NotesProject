package com.jk.notes.payload.response;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
public record UserDto(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String name,
                      @NotBlank @Size(max = 120) String lastName) implements Serializable {
}