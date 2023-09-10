package com.igr.media.service.impl;

import com.igr.media.dto.Role;
import com.igr.media.entity.Post;
import com.igr.media.entity.UserEntity;
import com.igr.media.exception.ElemNotFound;
import com.igr.media.repository.PostRepository;
import com.igr.media.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    PostRepository postRepository;

    public SecurityService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    /** Проверка пользователя на авторство */
    public boolean checkAuthor(int id, UserEntity user) {
        return id == user.getId();
    }
    /** Проверка автора сообщения на электронную почту */
    public boolean checkAuthor(int id, String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(ElemNotFound::new);
        return checkAuthor(id, user);
    }
    /** Проверка роли администратора по Authentication */
    public boolean checkAuthorRoleFromAuthentication(Authentication authentication) {
        UserEntity user = userRepository.findByEmail(authentication.getName()).orElseThrow(ElemNotFound::new);
        return isAdmin(user);
    }
    /** Проверка пользователя на электронную почту */
    public boolean isAuthorAuthenticated(String email, Authentication authentication) {
        return authentication.getName().equals(email) && authentication.isAuthenticated();
    }

    public boolean isAuthorAuthenticated(int id, Authentication authentication) {
        UserEntity user = userRepository.findById(id).orElseThrow(ElemNotFound::new);
        return isAuthorAuthenticated(user.getEmail(), authentication);
    }

    /** Проверка пользователя на роль администратора */
    public boolean isAdmin(UserEntity user) {
        return user.getRole().equals(Role.ADMIN);
    }

    public boolean isAdmin(Authentication authentication) {
        return authentication.isAuthenticated() &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    /** Проверка законности доступа к методам комментариям */
    public boolean isCommentUpdateAvailable(Authentication authentication, int commentEntityAuthorId,
                                            int commentDTOAuthorId) {
        if (isUpdateAvailable(authentication)) {
            return true;
        }
        if (checkAuthor(commentEntityAuthorId, authentication.getName()) &&
                commentEntityAuthorId == commentDTOAuthorId) {
            return true;
        }
        return false;
    }

    /** Проверка законности доступа к методам сообщений */
    public boolean isAdsUpdateAvailable(Authentication authentication, int AuthorId) {
        if (isUpdateAvailable(authentication)) {
            return true;
        }
        if (checkAuthor(AuthorId, authentication.getName())) {
            return true;
        }
        return false;
    }

    /** Проверка возможности обновления */
    private boolean isUpdateAvailable(Authentication authentication) {
        if (!authentication.isAuthenticated()) {
            return false;
        }
        if (isAdmin(authentication)) {
            return true;
        }
        return false;
    }
}
