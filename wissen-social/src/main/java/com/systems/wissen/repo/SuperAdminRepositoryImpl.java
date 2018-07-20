package com.systems.wissen.repo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.systems.wissen.model.Admin;
import com.systems.wissen.web.AdminDTO;

@Repository
@Transactional
public class SuperAdminRepositoryImpl implements SuperAdminRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Admin addAdmin(Admin admin) {
		return entityManager.merge(admin);
	}

	@Override
	public void removeAdmin(int admin_id) {
		Admin admin=new Admin();
		admin=entityManager.find(Admin.class, admin_id);
		System.out.println(admin);
       entityManager.remove(entityManager.find(Admin.class, admin_id));
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminDTO> getAdminDTO() {
		
		List<Admin> list = entityManager.createQuery("from Admin a").getResultList();
		Stream<Admin> stream = list.stream();
		return getAdminDTOResponse(stream);
	}

	private List<AdminDTO> getAdminDTOResponse(Stream<Admin> stream) {
		return stream.map((a) -> {
			AdminDTO adminDTO = new AdminDTO();
			adminDTO.setId(a.getId());
			adminDTO.setRole(a.getRole().getRoleId());
			
			System.out.println(adminDTO);
			return adminDTO;

		}).collect(Collectors.toList());
	}


}
