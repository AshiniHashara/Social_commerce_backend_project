package com.socialCommerce.backend_social.repo;

import com.socialCommerce.backend_social.model.UniqueLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniqueLinkRepo extends JpaRepository<UniqueLink, Integer> {
}
