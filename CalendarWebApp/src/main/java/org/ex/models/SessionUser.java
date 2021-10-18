package org.ex.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Table(name = "task_users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class SessionUser {

    private String first_name;

    private String last_name;

    private String user_name;

    @Enumerated(EnumType.STRING)
    private UserType user_type;

    private String email;

}
