package com.encrypto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.encrypto.model.EncryptoModel;
import com.encrypto.model.User;

@Repository
public interface EncryptRepository extends JpaRepository<EncryptoModel, Long> {

	List<EncryptoModel> findByReciever_id(User reciever_id);
	List<EncryptoModel> findById(long id);

	@Query("select e FROM EncryptoModel e WHERE e.reciever = ?1 AND e.isRead = false")
	public List<EncryptoModel> findNotRead(User reciever_id);
	
	@Query("select e FROM EncryptoModel e WHERE e.sender = ?1 AND e.isrequested = true AND e.isAccepted = false")
	public List<EncryptoModel> findRequested(User sender_id);
	
}
