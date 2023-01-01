package com.auth.detailes.service.mpl;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.repositories.auth.RoleRepository;
import com.auth.detailes.business.repositories.auth.UserRepository;
import com.auth.detailes.service.IUtilisateurService;
import com.auth.detailes.service.common.FileStorageService;
import com.auth.detailes.web.common.DateTimeConverter;
import com.auth.detailes.web.dto.UtilisateurDTO;
import com.auth.detailes.web.requests.ResetPasswordRequest;
import com.auth.detailes.web.requests.UserSearchRequest;
import com.auth.detailes.web.requests.ChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import javax.persistence.criteria.Predicate;

@Service
public class CustomUtilisateurService implements IUtilisateurService {

    @Value("${default.dir}")
    private String dir;
    @Autowired
    UserRepository repository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    FileStorageService storage;

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Object searchObject, Pageable pageable) {
        if(searchObject instanceof UserSearchRequest) {
        	UserSearchRequest searchRequest = (UserSearchRequest) searchObject;
            Specification<User> specification = (root, criteriaQuery, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();

                if(searchRequest.getFirstname() != null && !searchRequest.getFirstname().isEmpty()){
                    predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + searchRequest.getFirstname() + "%"));
                }
                if(searchRequest.getLastname() != null && !searchRequest.getLastname().isEmpty()){
                    predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + searchRequest.getLastname() + "%"));
                }
                if(searchRequest.getUserName() != null && !searchRequest.getUserName().isEmpty()){
                    predicates.add(criteriaBuilder.like(root.get("userName"),  searchRequest.getUserName()));
                }
                if(searchRequest.getEmail() != null && !searchRequest.getEmail().isEmpty()){
                    predicates.add(criteriaBuilder.like(root.get("email"), "%" + searchRequest.getEmail() + "%"));
                }
                if(searchRequest.getMembre() != null && !searchRequest.getMembre().isEmpty()){
                    predicates.add(criteriaBuilder.like(root.get("email"), "%" + searchRequest.getEmail() + "%"));
                }
                if(searchRequest.getEnabled() != null ){
                    predicates.add(criteriaBuilder.equal(root.get("membre").get("libelle"), searchRequest.getMembre()));
                }

                if(searchRequest.getRefRoleCode() != null && !searchRequest.getRefRoleCode().isEmpty()){
                    predicates.add(root.join("roles").get("code").in(searchRequest.getRefRoleCode()));
                }

                if(searchRequest.getSort().equals("asc")){
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(searchRequest.getSortColumn())));
                }else{
                    criteriaQuery.orderBy(criteriaBuilder.desc(root.get(searchRequest.getSortColumn())));
                }
                if(searchRequest.getDateCreationfrom() != null && !searchRequest.getDateCreationfrom().isEmpty()){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), DateTimeConverter.getInstantStartOfDayFromString(searchRequest.getDateCreationfrom(), DateTimeConverter.FORMAT_YYYY_MM_DD)));
                }
                if(searchRequest.getDateCreationTo() != null && !searchRequest.getDateCreationTo().isEmpty()){
                    predicates.add(criteriaBuilder.lessThan(root.get("createdAt"), DateTimeConverter.getInstantEndOfDayFromString(searchRequest.getDateCreationTo(), DateTimeConverter.FORMAT_YYYY_MM_DD)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            };
            return this.repository.findAll(specification, pageable);
        }
        return Page.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> find(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findAccountByLogin(String login) {
        return repository.findByUserName(login);
    }

    @Override
    @Transactional
    public User init(UtilisateurDTO formulaire) {
        User entity = User.builder()
                .email(formulaire.getEmail())
                .userName(formulaire.getUserName())
                .firstName(formulaire.getFirstName())
                .lastName(formulaire.getLastName())
                .email(formulaire.getEmail())
                .role(this.roleRepository.findByCode("ROLE_USER").orElse(null))
                .password(passwordEncoder.encode(formulaire.getPassword()))
                .enable(true)
                .build();
        return this.save(entity);
    }

    @Override
    public Boolean changePassword(ChangePassword pwd, String userName) {

      Optional<User>  current = this.repository.findByUserName(userName);
    if(current.isPresent())
    {
        current.get().setPassword(passwordEncoder.encode(pwd.getNewPassword()));
        this.repository.save(current.get());
        return true;
    }
        return false;
    }

    @Override
    public Boolean profile(User entity, String username) {
        Optional<User> current = repository.findByUserName(username);
        if(current.isPresent())
        {
            current.get().setFirstName(entity.getFirstName());
            current.get().setLastName(entity.getLastName());
            current.get().setPersonalPhone(entity.getPersonalPhone());
            current.get().setPostalCode(entity.getPostalCode());
            current.get().setProfesstionalPhone(entity.getProfesstionalPhone());
            current.get().setEmail(entity.getEmail());
            current.get().setAddressLine1(entity.getAddressLine1());
            current.get().setAddressLine2(entity.getAddressLine2());
            current.get().setFonction(entity.getFonction());
            current.get().setDescription(entity.getDescription());
            this.repository.save(current.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String login) {
        return repository.existsByUserName(login);
    }

    @Override
    @Transactional
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public User update(Long id, User entity) {

        User form = this.find(id).get();
        if(entity.getPassword() != null && !entity.getPassword().isEmpty()){
            form.setPassword(passwordEncoder.encode(entity.getPassword()));
        }

        form.setEmail(entity.getEmail());
        form.setAddressLine1(entity.getAddressLine1());
        form.setAddressLine2(entity.getAddressLine2());
        form.setPersonalPhone(entity.getPersonalPhone());
        form.setProfesstionalPhone(entity.getProfesstionalPhone());
        form.setPostalCode(entity.getPostalCode());
        form.setLastName(entity.getLastName());
        form.setFirstName(entity.getFirstName());
        form.setFonction(entity.getFonction());
        form.setDescription(entity.getDescription());
        form.setRole(entity.getRole());
        form.setEnable(entity.isEnable());
        return this.save(form);
    }

    @Override
    @Transactional
    public User resetPassword(ResetPasswordRequest request) {

        Optional<User> current = this.repository.findOneByUserNameAndEmail(request.getLogin(),request.getEmail());
        if(current.isPresent()){
            current.get().setPassword(passwordEncoder.encode(request.getResetPassword()));
            current.get().setFirstLogin(true);
            return this.repository.save(current.get());
        }
        return null;
    }

    @Override
    public User uploadAvatar(String username, MultipartFile file) {
        Optional<User> current = this.repository.findByUserName(username);
        if(current.isPresent()) {
            current.get().setAvatar(this.getPath(file));
            return this.repository.save(current.get());
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(User entity) {
        try {
            repository.delete(entity);
        } catch (Exception ex){
            return false;
        }
        return true;
    }

    private String getPath(MultipartFile file){
        return this.storage.uplode(file,dir);
    }

}
