package com.furnifinders.backend.Entity;

import com.furnifinders.backend.Entity.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long user_id;

    private String user_first_name;

    private String user_last_name;

    private String user_email;

    private String user_password;

    private String user_phone;

    private Role user_role;

    private String user_address;
    private LocalDate user_dob;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProductUserLink> productUserLinkList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> cartList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user_role.name()));
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public String getUsername() {
        return user_email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
