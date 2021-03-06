/*
 * Copyright 2015 EuregJUG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.euregjug.site.events;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Michael J. Simons, 2015-12-26
 */
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {

    /**
     * @param event
     * @param email
     * @return A registration for a given event by a user.
     */
    public Optional<RegistrationEntity> findByEventAndEmail(final EventEntity event, final String email);

    @Transactional(readOnly = true)
    public List<RegistrationEntity> findAllByEventId(Integer eventId);
}
